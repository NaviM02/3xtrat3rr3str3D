package com.navi.backend.semantic;

import lombok.Getter;

import java.util.List;

/**
 * Campo de una estructura (Y) o de una clase (Z). La lista ordenada de campos
 * en {@link AggregateType} define el layout (offsets) para la generación de C3D.
 *
 * <p>{@link #arrayDims} guarda los tamaños de un campo arreglo (p. ej. {@code [4]}
 * para {@code entero datos[4]}); es vacío en campos escalares. Se usa para calcular
 * el tamaño de la estructura y el offset de cada campo (un arreglo ocupa varias
 * celdas, no una).
 */
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
