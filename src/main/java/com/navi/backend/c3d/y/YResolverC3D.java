package com.navi.backend.c3d.y;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.declarations.ArrayInitializer;
import com.navi.backend.ast.y.expressions.ArrayAccessExpression;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.expressions.MemberAccessExpression;
import com.navi.backend.ast.y.expressions.VariableExpression;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.c3d.StructLayoutC3D;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Field;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// direcciones, layout y asignacion de Y para C3D; los arreglos/structs
// por referencia guardan una direccion en su slot, el resto vive en el stack
class YResolverC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final AstYVisitor<String> visitor;

    // offsets BP de las dimensiones ocultas de un parametro arreglo multi-D
    // (dims[1..N-1]); el slot del arreglo guarda solo la direccion base
    private final Map<String, List<Integer>> paramDims = new LinkedHashMap<>();

    YResolverC3D(SemanticContext context, C3DEmitter emitter, AstYVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.visitor = visitor;
    }

    void recordParamDims(String name, List<Integer> dimOffsets) {
        if (dimOffsets != null && !dimOffsets.isEmpty()) paramDims.put(name, dimOffsets);
    }

    void assignTo(Expression target, String value) {
        if (target instanceof VariableExpression v) {
            emitter.storeVar(v.getName(), value);
        } else if (target instanceof ArrayAccessExpression a) {
            emitter.stackStoreAt(arrayElementAddr(a), emitter.coerceTo(context.typeOf(a), value));
        } else if (target instanceof MemberAccessExpression m) {
            Type owner = context.typeOf(m.getObject());
            int off = fieldOffset(owner, m.getMember());
            Type targetType = context.typeOf(m) != null ? context.typeOf(m) : fieldType(owner, m.getMember());
            emitter.stackStoreAt(emitter.binary("+", structAddr(m.getObject()), String.valueOf(off)),
                    emitter.coerceTo(targetType, value));
        }
    }

    // direccion del elemento de un arreglo, aplanando indices
    String arrayElementAddr(ArrayAccessExpression node) {
        List<String> indices = new ArrayList<>();
        Expression current = node;
        while (current instanceof ArrayAccessExpression access) {
            indices.add(0, access.getIndex().accept(visitor));
            current = access.getArray();
        }
        String base = arrayBase(current);
        Type arrayType = context.typeOf(current);
        int stride = arrayType != null && arrayType.isArray()
                ? StructLayoutC3D.size(context.getTypeTable(), arrayType.getElementType()) : 1;
        // arreglo multi-D por referencia: las dimensiones vienen en slots ocultos
        if (current instanceof VariableExpression v && emitter.isReference(v.getName()) && indices.size() > 1) {
            return paramArrayAddr(base, v.getName(), indices, stride);
        }
        return emitter.addressOffset(base, arrayDimsOf(current), indices, stride);
    }

    // fila-mayor usando dimensiones en tiempo de ejecucion (slots ocultos del param)
    private String paramArrayAddr(String base, String name, List<String> indices, int stride) {
        String flat = indices.get(0);
        List<Integer> dimOffsets = paramDims.get(name);
        for (int k = 1; k < indices.size(); k++) {
            String dim = dimOffsets != null && k - 1 < dimOffsets.size()
                    ? emitter.stackLoad("BP", dimOffsets.get(k - 1)) : "1";
            flat = emitter.binary("+", emitter.binary("*", flat, dim), indices.get(k));
        }
        if (stride > 1) flat = emitter.binary("*", flat, String.valueOf(stride));
        return emitter.binary("+", base, flat);
    }

    // dimensiones conocidas (constantes) de una expresion arreglo, para pasarlas
    // como argumentos extra en una llamada; null si no se pueden calcular
    List<Integer> dimsOf(Expression e) {
        if (e instanceof VariableExpression v) {
            Symbol s = context.getSymbolTable().resolve(v.getName());
            if (s != null && !s.getArraySizes().isEmpty()) return s.getArraySizes();
            return emitter.arrayDims(v.getName());
        }
        if (e instanceof MemberAccessExpression m) {
            Type owner = context.typeOf(m.getObject());
            if (owner != null) {
                AggregateType agg = context.getTypeTable().resolve(owner.getName());
                if (agg != null) {
                    Field field = agg.findField(m.getMember());
                    if (field != null && !field.getArrayDims().isEmpty()) return field.getArrayDims();
                }
            }
        }
        return null;
    }

    // base de un arreglo: local por valor, param por referencia guarda la direccion
    private String arrayBase(Expression array) {
        if (array instanceof VariableExpression v) {
            return emitter.isReference(v.getName()) ? emitter.loadVar(v.getName()) : emitter.varAddr(v.getName());
        }
        if (array instanceof MemberAccessExpression m) return structAddr(m);
        return array.accept(visitor);
    }

    private List<Integer> arrayDimsOf(Expression array) {
        if (array instanceof VariableExpression v) return emitter.arrayDims(v.getName());
        if (array instanceof MemberAccessExpression m) {
            Type owner = context.typeOf(m.getObject());
            if (owner != null) {
                AggregateType agg = context.getTypeTable().resolve(owner.getName());
                if (agg != null) {
                    Field field = agg.findField(m.getMember());
                    if (field != null) return field.getArrayDims();
                }
            }
        }
        return null;
    }

    // direccion de un struct en el stack (encadena offsets de miembros anidados)
    String structAddr(Expression obj) {
        if (obj instanceof VariableExpression v) {
            return emitter.isReference(v.getName()) ? emitter.loadVar(v.getName()) : emitter.varAddr(v.getName());
        }
        if (obj instanceof MemberAccessExpression m) {
            String parent = structAddr(m.getObject());
            int off = fieldOffset(context.typeOf(m.getObject()), m.getMember());
            return emitter.binary("+", parent, String.valueOf(off));
        }
        // Un elemento struct ya es un bloque inline: no cargar su primer campo como puntero.
        if (obj instanceof ArrayAccessExpression a) return arrayElementAddr(a);
        return obj.accept(visitor);
    }

    Type fieldType(Type owner, String member) {
        if (owner == null) return null;
        AggregateType agg = context.getTypeTable().resolve(owner.getName());
        Field field = agg == null ? null : agg.findField(member);
        return field == null ? null : field.getType();
    }

    int fieldOffset(Type owner, String member) {
        return StructLayoutC3D.fieldOffset(context.getTypeTable(), owner, member);
    }

    int structSize(String name) {
        return StructLayoutC3D.size(context.getTypeTable(), Type.struct(name));
    }

    void setPos(Object node, int offset) {
        Symbol s = context.symbolOf(node);
        if (s != null) s.setPosMemory(offset);
    }

    // celdas de un arreglo de tamano constante (1 si es dinamico/desconocido)
    int arrayCells(List<Expression> dims) {
        if (dims == null || dims.isEmpty()) return 1;
        int total = 1;
        for (Expression e : dims) {
            if (e instanceof LiteralExpression le && le.getValue() instanceof Integer n && n > 0) total *= n;
            else return 1;
        }
        return total;
    }

    // dimensiones constantes de un arreglo, o null si no se pueden calcular
    List<Integer> constantDims(List<Expression> dims) {
        if (dims == null || dims.isEmpty()) return null;
        List<Integer> out = new ArrayList<>();
        for (Expression e : dims) {
            if (e instanceof LiteralExpression le && le.getValue() instanceof Integer n && n > 0) out.add(n);
            else return null;
        }
        return out;
    }

    // aplana y almacena un inicializador de arreglo (soporta anidados)
    int storeArrayInitializer(String base, List<AstYNode> elements, int start) {
        if (elements == null) return start;
        int i = start;
        for (AstYNode element : elements) {
            if (element instanceof ArrayInitializer nested) {
                i = storeArrayInitializer(base, nested.getElements(), i);
            } else if (element instanceof Expression expression) {
                emitter.stackStoreAt(emitter.binary("+", base, String.valueOf(i)), expression.accept(visitor));
                i++;
            }
        }
        return i;
    }
}
