package com.navi.backend.c3d.lat;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.expressions.MemberAccessExpression;
import com.navi.backend.ast.lat.expressions.VariableExpression;
import com.navi.backend.ast.lat.expressions.literals.NumberLiteral;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
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

// direcciones, layout y asignacion de Lat para C3D (lo compartido entre
// declaraciones, sentencias y expresiones)
class LatC3DResolver {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final AstLatVisitor<String> visitor;

    // offsets BP de las dimensiones ocultas de un parametro arreglo multi-D
    private final Map<String, List<Integer>> paramDims = new LinkedHashMap<>();

    LatC3DResolver(SemanticContext context, C3DEmitter emitter, AstLatVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.visitor = visitor;
    }

    void recordParamDims(String name, List<Integer> dimOffsets) {
        if (dimOffsets != null && !dimOffsets.isEmpty()) paramDims.put(name, dimOffsets);
    }

    // ---------------------------------------------------------------- inicializadores

    String initializerValue(Initializer init) {
        if (init instanceof ExpressionInitializer ei) return ei.getExpression().accept(visitor);
        return null; // struct initializer no produce un valor unico (se emite campo a campo)
    }

    // emite un literal de struct escribiendo cada campo en su bloque inline
    void emitStructInitializer(StructInitializer init, Expression target, Type structType) {
        if (target == null || structType == null || !structType.isStruct()) return;
        emitStructInitializerAt(init, structAddr(target), structType);
    }

    private void emitStructInitializerAt(StructInitializer init, String base, Type structType) {
        AggregateType agg = context.getTypeTable().resolve(structType.getName());
        if (agg == null) return;

        for (int i = 0; i < structSize(structType.getName()); i++) {
            emitter.stackStoreAt(emitter.binary("+", base, String.valueOf(i)), "0");
        }
        int index = 0;
        for (StructFieldInitializer field : init.getFields()) {
            Field declared = field.getName() != null
                    ? agg.findField(field.getName())
                    : (index < agg.getFields().size() ? agg.getFields().get(index) : null);
            index++;
            if (declared == null) continue;
            int off = fieldOffset(structType, declared.getName());
            String addr = emitter.binary("+", base, String.valueOf(off));
            Type type = declared.getType();
            // Los campos arreglo/struct se inicializan por copia, incluidos literales anidados.
            if (field.getValue() instanceof StructInitializer nested && type.isStruct()) {
                emitStructInitializerAt(nested, addr, type);
            } else if (field.getValue() instanceof ExpressionInitializer ei) {
                if (type.isArray() || type.isStruct()) {
                    StructLayoutC3D.copy(emitter, addr, addressOf(ei.getExpression()),
                            StructLayoutC3D.fieldSize(context.getTypeTable(), declared));
                } else {
                    emitter.stackStoreAt(addr, emitter.coerceTo(type, ei.getExpression().accept(visitor)));
                }
            }
        }
    }

    void assignTo(Expression target, String value) {
        if (target instanceof VariableExpression v) {
            emitter.storeVar(v.getName(), value);
        } else if (target instanceof ArrayAccessExpression a) {
            emitter.stackStoreAt(arrayElementAddr(a), emitter.coerceTo(context.typeOf(a), value));
        } else if (target instanceof MemberAccessExpression m) {
            Type owner = context.typeOf(m.getObject());
            Type fieldType = fieldType(owner, m.getMember());
            Type targetType = context.typeOf(m) != null ? context.typeOf(m) : fieldType;
            String coerced = emitter.coerceTo(targetType, value);
            if (owner != null && owner.isStruct()) {
                int off = fieldOffset(owner, m.getMember());
                emitter.stackStoreAt(emitter.binary("+", structAddr(m.getObject()), String.valueOf(off)), coerced);
            } else {
                int off = fieldIndex(owner, m.getMember());
                emitter.heapStore(m.getObject().accept(visitor), String.valueOf(off), coerced);
            }
        }
    }

    // ---------------------------------------------------------------- direcciones

    // direccion del elemento de un arreglo, aplanando indices
    String arrayElementAddr(ArrayAccessExpression node) {
        List<String> indices = new ArrayList<>();
        Expression current = node;
        while (current instanceof ArrayAccessExpression access) {
            indices.add(0, access.getIndex().accept(visitor));
            current = access.getArray();
        }
        String base = arrayBaseAddr(current);
        Type arrayType = context.typeOf(current);
        int stride = arrayType != null && arrayType.isArray()
                ? StructLayoutC3D.size(context.getTypeTable(), arrayType.getElementType()) : 1;
        // arreglo multi-D por referencia: dimensiones vienen en slots ocultos
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

    private String arrayBaseAddr(Expression array) {
        if (array instanceof VariableExpression v) {
            return emitter.isReference(v.getName()) ? emitter.loadVar(v.getName()) : emitter.varAddr(v.getName());
        }
        if (array instanceof MemberAccessExpression m) return memberAddr(m);
        return array.accept(visitor);
    }

    private String memberAddr(MemberAccessExpression m) {
        Type owner = context.typeOf(m.getObject());
        if (owner != null && owner.isStruct()) {
            return emitter.binary("+", structAddr(m.getObject()), String.valueOf(fieldOffset(owner, m.getMember())));
        }
        return emitter.heapAddr(m.getObject().accept(visitor), String.valueOf(fieldIndex(owner, m.getMember())));
    }

    // dimensiones de un arreglo (variable o campo de struct); tambien las usa
    // la llamada para pasar dims[1..N-1] como argumentos extra
    List<Integer> arrayDimsOf(Expression array) {
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

    String structAddr(Expression obj) {
        if (obj instanceof VariableExpression v) return emitter.varAddr(v.getName());
        if (obj instanceof MemberAccessExpression m) {
            int off = fieldOffset(context.typeOf(m.getObject()), m.getMember());
            return emitter.binary("+", structAddr(m.getObject()), String.valueOf(off));
        }
        if (obj instanceof ArrayAccessExpression a) return arrayElementAddr(a);
        return obj.accept(visitor);
    }

    // direccion (no valor) de una expresion que ocupa celdas contiguas
    String addressOf(Expression e) {
        if (e instanceof VariableExpression v) {
            return emitter.isReference(v.getName()) ? emitter.loadVar(v.getName()) : emitter.varAddr(v.getName());
        }
        if (e instanceof ArrayAccessExpression a) return arrayElementAddr(a);
        if (e instanceof MemberAccessExpression m) return memberAddr(m);
        return e.accept(visitor);
    }

    // ---------------------------------------------------------------- layout

    Type fieldType(Type owner, String member) {
        if (owner == null) return null;
        AggregateType agg = context.getTypeTable().resolve(owner.getName());
        Field field = agg == null ? null : agg.findField(member);
        return field == null ? null : field.getType();
    }

    int fieldIndex(Type owner, String member) {
        if (owner == null) return 0;
        AggregateType agg = context.getTypeTable().resolve(owner.getName());
        if (agg == null) return 0;
        int offset = agg.fieldOffset(member);
        return agg.isClass() ? offset + 1 : offset;
    }

    // offset en celdas de un campo (los arreglo ocupan varias celdas)
    int fieldOffset(Type owner, String member) {
        return StructLayoutC3D.fieldOffset(context.getTypeTable(), owner, member);
    }

    // tamano en celdas de un struct (1 si no se conoce)
    int structSize(String name) {
        return StructLayoutC3D.size(context.getTypeTable(), Type.struct(name));
    }

    int objectSize(String className) {
        AggregateType agg = context.getTypeTable().resolve(className);
        return agg == null ? 1 : agg.objectSize();
    }

    // ---------------------------------------------------------------- arreglos

    // celdas de un arreglo de tamano constante (1 si es dinamico/desconocido)
    int arrayCells(List<Expression> sizes) {
        if (sizes == null || sizes.isEmpty()) return 1;
        int total = 1;
        for (Expression e : sizes) {
            if (e instanceof NumberLiteral n && n.getValue() > 0) total *= n.getValue();
            else return 1;
        }
        return total;
    }

    // dimensiones constantes de un arreglo, o null si no se pueden calcular
    List<Integer> constantDims(List<Expression> sizes) {
        if (sizes == null || sizes.isEmpty()) return null;
        List<Integer> dims = new ArrayList<>();
        for (Expression e : sizes) {
            if (e instanceof NumberLiteral n && n.getValue() > 0) dims.add(n.getValue());
            else return null;
        }
        return dims;
    }

    // aplana y almacena un inicializador de arreglo (soporta anidados)
    int storeArrayInitializer(String base, List<AstLatNode> elements, int start) {
        if (elements == null) return start;
        int i = start;
        for (AstLatNode element : elements) {
            if (element instanceof ArrayInitializer nested) {
                i = storeArrayInitializer(base, nested.getElements(), i);
            } else if (element instanceof Expression expression) {
                String value = expression.accept(visitor);
                emitter.stackStoreAt(emitter.binary("+", base, String.valueOf(i)), value);
                i++;
            }
        }
        return i;
    }

    void setPos(Object node, int offset) {
        Symbol s = context.symbolOf(node);
        if (s != null) s.setPosMemory(offset);
    }
}
