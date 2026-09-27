package com.navi.backend.semantic.model;

import com.navi.backend.semantic.rules.TypeCompat;
import lombok.Getter;

import java.util.List;

// firma de funcion/metodo/constructor
// mismo nombre + mismos tipos = duplicado; distintos tipos = sobrecarga valida (Z)
@Getter
public class FunctionSignature {
    private final List<Type> parameters;
    private final Type returnType;

    public FunctionSignature(List<Type> parameters, Type returnType) {
        this.parameters = parameters == null ? List.of() : List.copyOf(parameters);
        this.returnType = returnType;
    }

    // misma lista de parametros (para detectar duplicados)
    public boolean sameParams(FunctionSignature other) {
        return parameters.equals(other.parameters);
    }

    // los argumentos son asignables a los parametros?
    // ademas de coincidencia exacta se admite ensanchamiento numerico (int -> double)
    public boolean matches(List<Type> argTypes) {
        if (argTypes == null) return parameters.isEmpty();
        if (parameters.size() != argTypes.size()) return false;
        for (int i = 0; i < parameters.size(); i++) {
            if (!TypeCompat.canAssign(parameters.get(i), argTypes.get(i))) return false;
        }
        return true;
    }
}
