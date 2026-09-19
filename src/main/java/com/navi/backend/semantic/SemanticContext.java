package com.navi.backend.semantic;

import lombok.Getter;

@Getter
public class SemanticContext {
    private final SymbolTable symbolTable;
    private final TypeRegistry typeRegistry;
    private final ModuleRegistry moduleRegistry;

    public SemanticContext(SymbolTable symbolTable, TypeRegistry typeRegistry, ModuleRegistry moduleRegistry) {
        this.symbolTable = symbolTable;
        this.typeRegistry = typeRegistry;
        this.moduleRegistry = moduleRegistry;
    }
}