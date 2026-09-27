package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.ScopeKind;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// tipo agregado: estructura (Y) o clase (Z), base del layout para C3D
// fields: campos en orden (dan offsets); memberScope: metodos/constructores en clase, vacio en struct
@Getter
public class AggregateType {
    private final String name;
    private final boolean isClass;
    private final List<Field> fields = new ArrayList<>();
    private final Scope memberScope;

    public AggregateType(String name, boolean isClass, Scope parentScope) {
        this.name = name;
        this.isClass = isClass;
        this.memberScope = new Scope(isClass ? ScopeKind.CLASS : ScopeKind.STRUCT, parentScope);
    }

    public boolean isStruct() {
        return !isClass;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public Field findField(String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) return field;
        }
        return null;
    }

    // offset en celdas del campo: suma los tamaños de los anteriores
    // escalar = 1 celda; arreglo tipo datos[4] = 4
    public int fieldOffset(String name) {
        int offset = 0;
        for (Field field : fields) {
            if (field.getName().equals(name)) return offset;
            offset += cellSize(field);
        }
        return 0;
    }

    // tamaño total en celdas (minimo 1)
    public int size() {
        int total = 0;
        for (Field field : fields) total += cellSize(field);
        return Math.max(total, 1);
    }

    private static int cellSize(Field field) {
        if (field.getType() != null && field.getType().isArray()) {
            int count = 1;
            int dims = field.getArrayDims().isEmpty() ? 1 : field.getArrayDims().size();
            for (int i = 0; i < dims; i++) {
                int d = i < field.getArrayDims().size() ? field.getArrayDims().get(i) : 1;
                count *= Math.max(d, 1);
            }
            return count;
        }
        return 1;
    }

    public Symbol findMethod(String name, List<Type> argTypes) {
        return resolveCallable(memberScope, name, argTypes);
    }

    public Symbol findConstructor(List<Type> argTypes) {
        return resolveCallable(memberScope, name, argTypes);
    }

    private Symbol resolveCallable(Scope scope, String name, List<Type> argTypes) {
        List<Symbol> candidates = scope.resolveOverloads(name);
        if (candidates == null) return null;
        for (Symbol symbol : candidates) {
            if (symbol.getSignature() != null && symbol.getSignature().matches(argTypes)) {
                return symbol;
            }
        }
        return null;
    }
}
