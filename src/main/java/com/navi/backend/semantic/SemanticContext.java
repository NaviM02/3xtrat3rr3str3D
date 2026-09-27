package com.navi.backend.semantic;

import com.navi.backend.semantic.errors.SemanticErrors;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.SymbolTable;
import com.navi.backend.semantic.model.Type;
import com.navi.backend.semantic.model.TypeTable;
import lombok.Getter;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// contexto compartido por los 3 lenguajes durante una corrida
// guarda symbolTable, typeTable, modulos cargados, anotaciones y errores
@Getter
public class SemanticContext {
    private final SymbolTable symbolTable = new SymbolTable();
    private final TypeTable typeTable = new TypeTable();
    private final Set<String> loadedModules = new HashSet<>();
    private final Map<String, Object> loadedAsts = new LinkedHashMap<>();
    private final Map<Object, Type> annotations = new IdentityHashMap<>();
    private final Map<Object, Symbol> symbolBindings = new IdentityHashMap<>();
    private final SemanticErrors errors = new SemanticErrors();

    // guarda el AST de un import para que C3D lo emita
    public void recordAst(String importPath, Object ast) {
        loadedAsts.put(importPath, ast);
    }

    public boolean isLoaded(String importPath) {
        return loadedModules.contains(importPath);
    }

    public void markLoaded(String importPath) {
        loadedModules.add(importPath);
    }

    // anota el tipo de un nodo de expresion para que lo lea C3D
    public void annotate(Object node, Type type) {
        annotations.put(node, type);
    }

    public Type typeOf(Object node) {
        return annotations.get(node);
    }

    // liga una declaracion con su simbolo para que C3D le ponga Pos_memory
    public void bindSymbol(Object node, Symbol symbol) {
        symbolBindings.put(node, symbol);
    }

    public Symbol symbolOf(Object node) {
        return symbolBindings.get(node);
    }
}
