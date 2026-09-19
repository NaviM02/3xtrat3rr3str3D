package com.navi.backend.semantic.model;

import lombok.Getter;

import java.util.List;

@Getter
public class FunctionSignature {
    private final List<Type> parameterTypes;
    private final Type returnType;

    public FunctionSignature(List<Type> parameterTypes, Type returnType) {
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
    }
}