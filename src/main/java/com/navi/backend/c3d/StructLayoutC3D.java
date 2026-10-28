package com.navi.backend.c3d;

import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Field;
import com.navi.backend.semantic.model.Type;
import com.navi.backend.semantic.model.TypeTable;

/** Layout por valor compartido por los structs de Y y sus consumidores Lat. */
public final class StructLayoutC3D {
    private StructLayoutC3D() { }

    public static int size(TypeTable types, Type type) {
        if (type == null || !type.isStruct()) return 1;
        AggregateType aggregate = types.resolve(type.getName());
        if (aggregate == null) return 1;
        int cells = 0;
        // Un struct anidado ocupa todos sus campos, no una celda de referencia.
        for (Field field : aggregate.getFields()) cells += fieldSize(types, field);
        return Math.max(cells, 1);
    }

    public static int fieldSize(TypeTable types, Field field) {
        Type type = field.getType();
        if (type == null || !type.isArray()) return size(types, type);
        int count = size(types, type.getElementType());
        for (int dim : field.getArrayDims()) count *= Math.max(dim, 1);
        return count;
    }

    public static int fieldOffset(TypeTable types, Type owner, String member) {
        AggregateType aggregate = owner == null ? null : types.resolve(owner.getName());
        if (aggregate == null) return 0;
        if (aggregate.isClass()) return aggregate.fieldOffset(member);
        int offset = 0;
        for (Field field : aggregate.getFields()) {
            if (field.getName().equals(member)) return offset;
            offset += fieldSize(types, field);
        }
        return offset;
    }

    public static void copy(C3DEmitter emitter, String target, String source, int cells) {
        // Copia el bloque inline; guardar solo la direccion destruiria el primer campo.
        for (int i = 0; i < cells; i++) {
            String value = emitter.stackLoadAt(emitter.binary("+", source, String.valueOf(i)));
            emitter.stackStoreAt(emitter.binary("+", target, String.valueOf(i)), value);
        }
    }
}
