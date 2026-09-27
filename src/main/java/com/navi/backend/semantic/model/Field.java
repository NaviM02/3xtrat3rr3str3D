package com.navi.backend.semantic.model;

import lombok.Getter;

import java.util.List;

// campo de una estructura (Y) o clase (Z); su orden define los offsets en C3D
// arrayDims guarda tamaños si el campo es arreglo (ej. [4] para entero datos[4]); vacio si escalar
@Getter
public class Field {
    private final String name;
    private final Type type;
    private final List<Integer> arrayDims;

    public Field(String name, Type type) {
        this(name, type, List.of());
    }

    public Field(String name, Type type, List<Integer> arrayDims) {
        this.name = name;
        this.type = type;
        this.arrayDims = arrayDims == null ? List.of() : List.copyOf(arrayDims);
    }
}
