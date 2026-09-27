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
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.Field;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Direcciones, layout y asignación de Y para C3D. Los arreglos/structs por
 * referencia guardan una dirección en su slot; el resto vive en el stack.
 */
class YResolverC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final AstYVisitor<String> visitor;

    YResolverC3D(SemanticContext context, C3DEmitter emitter, AstYVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.visitor = visitor;
    }

    void assignTo(Expression target, String value) {
        if (target instanceof VariableExpression v) {
            emitter.storeVar(v.getName(), value);
        } else if (target instanceof ArrayAccessExpression a) {
            emitter.stackStoreAt(arrayElementAddr(a), value);
        } else if (target instanceof MemberAccessExpression m) {
            int off = fieldOffset(context.typeOf(m.getObject()), m.getMember());
            emitter.stackStoreAt(emitter.binary("+", structAddr(m.getObject()), String.valueOf(off)), value);
        }
    }

    /** Dirección del elemento de un arreglo, aplanando índices multidimensionales. */
    String arrayElementAddr(ArrayAccessExpression node) {
        List<String> indices = new ArrayList<>();
        Expression current = node;
        while (current instanceof ArrayAccessExpression access) {
            indices.add(0, access.getIndex().accept(visitor));
            current = access.getArray();
        }
        return emitter.addressOffset(arrayBase(current), arrayDimsOf(current), indices);
    }

    /** Dirección base de un arreglo: los locales son por valor; los params por referencia guardan la dirección. */
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

    /** Dirección de un struct en el stack (encadenando offsets para miembros anidados). */
    String structAddr(Expression obj) {
        if (obj instanceof VariableExpression v) {
            return emitter.isReference(v.getName()) ? emitter.loadVar(v.getName()) : emitter.varAddr(v.getName());
        }
        if (obj instanceof MemberAccessExpression m) {
            String parent = structAddr(m.getObject());
            int off = fieldOffset(context.typeOf(m.getObject()), m.getMember());
            return emitter.binary("+", parent, String.valueOf(off));
        }
        return obj.accept(visitor);
    }

    int fieldOffset(Type owner, String member) {
        if (owner == null) return 0;
        AggregateType agg = context.getTypeTable().resolve(owner.getName());
        if (agg == null) return 0;
        return agg.fieldOffset(member);
    }

    int structSize(String name) {
        AggregateType agg = context.getTypeTable().resolve(name);
        return agg == null ? 1 : agg.size();
    }

    void setPos(Object node, int offset) {
        Symbol s = context.symbolOf(node);
        if (s != null) s.setPosMemory(offset);
    }

    /** Celdas de un arreglo de tamaño constante (1 si el tamaño es dinámico/desconocido). */
    int arrayCells(List<Expression> dims) {
        if (dims == null || dims.isEmpty()) return 1;
        int total = 1;
        for (Expression e : dims) {
            if (e instanceof LiteralExpression le && le.getValue() instanceof Integer n && n > 0) total *= n;
            else return 1;
        }
        return total;
    }

    /** Dimensiones constantes de un arreglo, o {@code null} si no se pueden calcular. */
    List<Integer> constantDims(List<Expression> dims) {
        if (dims == null || dims.isEmpty()) return null;
        List<Integer> out = new ArrayList<>();
        for (Expression e : dims) {
            if (e instanceof LiteralExpression le && le.getValue() instanceof Integer n && n > 0) out.add(n);
            else return null;
        }
        return out;
    }

    /** Aplana y almacena un inicializador de arreglo (soporta anidados). */
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
