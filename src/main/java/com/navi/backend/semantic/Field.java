package com.navi.backend.semantic;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Campo de una estructura (Y) o de una clase (Z). La lista ordenada de campos
 * en {@link AggregateType} define el layout (offsets) para la generación de C3D.
 */
@Getter
@AllArgsConstructor
public class Field {
    private final String name;
    private final Type type;
}
