package com.navi.backend.c3d.z;

import com.navi.backend.ast.z.expressions.ArrayAccessExpression;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.expressions.MemberAccessExpression;
import com.navi.backend.ast.z.expressions.VariableExpression;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.List;

// direcciones, layout y asignacion de Z para C3D; locales/params en el stack
// y objetos/arreglos en el heap. Guarda la clase actual para resolver this
class ZResolverC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final AstZVisitor<String> visitor;

    private AggregateType currentClass;

    ZResolverC3D(SemanticContext context, C3DEmitter emitter, AstZVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.visitor = visitor;
    }

    AggregateType currentClass() {
        return currentClass;
    }

    void setCurrentClass(AggregateType currentClass) {
        this.currentClass = currentClass;
    }

    void assignTo(Expression target, String value) {
        if (target instanceof VariableExpression v) {
            if (emitter.localOffset(v.getName()) != null) {
                emitter.storeVar(v.getName(), value);
                return;
            }
            int idx = currentClass == null ? -1 : fieldIndex(currentClass, v.getName());
            if (idx >= 0) emitter.heapStore(thisPlace(), String.valueOf(idx), value);
            else emitter.storeVar(v.getName(), value);
        } else if (target instanceof ArrayAccessExpression a) {
            emitter.heapStoreAt(arrayElementAddr(a), value);
        } else if (target instanceof MemberAccessExpression m) {
            String base = m.getObject().accept(visitor);
            int off = fieldIndex(context.typeOf(m.getObject()), m.getMember());
            emitter.heapStore(base, String.valueOf(off), value);
        }
    }

    // lugar de una variable: local/param del stack, campo de this (heap) o nombre suelto
    String place(String name) {
        if (emitter.localOffset(name) != null) return emitter.loadVar(name);
        int idx = currentClass == null ? -1 : fieldIndex(currentClass, name);
        if (idx >= 0) return emitter.heapLoad(thisPlace(), String.valueOf(idx));
        return emitter.loadVar(name);
    }

    // direccion de un elemento de arreglo de Z en el heap
    // el bloque guarda la cabecera [rank][d0][d1]... y luego los datos (row-major)
    String arrayElementAddr(ArrayAccessExpression node) {
        List<String> indices = new ArrayList<>();
        Expression current = node;
        while (current instanceof ArrayAccessExpression access) {
            indices.add(0, access.getIndex().accept(visitor));
            current = access.getArray();
        }
        String base = current.accept(visitor);
        int rank = arrayRank(current, indices.size());
        String flat = indices.get(0);
        for (int k = 1; k < indices.size(); k++) {
            String dim = emitter.heapLoad(base, String.valueOf(k));
            flat = emitter.binary("+", emitter.binary("*", flat, dim), indices.get(k));
        }
        return emitter.binary("+", base, emitter.binary("+", String.valueOf(rank), flat));
    }

    // rank declarado del arreglo (fallback si no se conoce)
    private int arrayRank(Expression array, int fallback) {
        Type t = context.typeOf(array);
        if (t != null && t.isArray() && t.getDimensions() > 0) return t.getDimensions();
        return fallback;
    }

    // receptor del metodo actual: parametro 0 del marco (this)
    String thisPlace() {
        return emitter.localOffset("this") != null ? emitter.loadVar("this") : "this";
    }

    int fieldIndex(Type owner, String member) {
        if (owner == null || !owner.isClass()) return 0;
        return fieldIndex(context.getTypeTable().resolve(owner.getName()), member);
    }

    int fieldIndex(AggregateType agg, String member) {
        if (agg == null) return 0;
        for (int i = 0; i < agg.getFields().size(); i++) {
            if (agg.getFields().get(i).getName().equals(member)) return i;
        }
        return 0;
    }

    int objectSize(String className) {
        AggregateType agg = context.getTypeTable().resolve(className);
        return agg == null ? 1 : Math.max(1, agg.getFields().size());
    }

    void setPos(Object node, int offset) {
        Symbol s = context.symbolOf(node);
        if (s != null) s.setPosMemory(offset);
    }
}
