package com.navi.backend.semantic;

import lombok.Getter;

import java.util.List;

/**
 * Firma de una función/método/constructor. Dos callables con el mismo nombre y
 * los mismos tipos de parámetros se consideran duplicados; con distinto tipo de
 * parámetros son sobrecarga válida (requerido por Z).
 */
@Getter
public class FunctionSignature {
    private final List<Type> parameters;
    private final Type returnType;

    public FunctionSignature(List<Type> parameters, Type returnType) {
        this.parameters = parameters == null ? List.of() : List.copyOf(parameters);
        this.returnType = returnType;
    }

    /** Misma lista de parámetros (para detectar duplicados). */
    public boolean sameParams(FunctionSignature other) {
        return parameters.equals(other.parameters);
    }

    /** Coincidencia exacta de argumentos. La coerción se agrega luego vía {@link TypeCompat}. */
    public boolean matches(List<Type> argTypes) {
        if (argTypes == null) return parameters.isEmpty();
        return parameters.equals(argTypes);
    }
}
