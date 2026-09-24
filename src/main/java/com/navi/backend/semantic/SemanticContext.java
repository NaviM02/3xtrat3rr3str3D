package com.navi.backend.semantic;

import lombok.Getter;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Contexto semántico compartido por los tres lenguajes. Estado de una corrida:
 *
 * <ul>
 *   <li>{@link #symbolTable}: árbol de ámbitos con todos los símbolos (globales de
 *       Lat + símbolos importados de Y/Z).</li>
 *   <li>{@link #typeTable}: layouts de struct/class por nombre.</li>
 *   <li>{@link #loadedModules}: paths ya cargados (evita re-cargar / ciclos).</li>
 *   <li>{@link #annotations}: tipo resuelto de cada nodo de expresión (para C3D).</li>
 *   <li>{@link #errors}: recolector de errores de esta corrida.</li>
 * </ul>
 */
@Getter
public class SemanticContext {
    private final SymbolTable symbolTable = new SymbolTable();
    private final TypeTable typeTable = new TypeTable();
    private final Set<String> loadedModules = new HashSet<>();
    private final Map<String, Object> loadedAsts = new LinkedHashMap<>();
    private final Map<Object, Type> annotations = new IdentityHashMap<>();
    private final Map<Object, Symbol> symbolBindings = new IdentityHashMap<>();
    private final SemanticErrors errors = new SemanticErrors();

    /** Guarda el AST de un módulo importado para que la generación de C3D lo emita. */
    public void recordAst(String importPath, Object ast) {
        loadedAsts.put(importPath, ast);
    }

    public boolean isLoaded(String importPath) {
        return loadedModules.contains(importPath);
    }

    public void markLoaded(String importPath) {
        loadedModules.add(importPath);
    }

    /** Anota el tipo resuelto de un nodo AST (expresión) para que el emisor C3D lo lea. */
    public void annotate(Object node, Type type) {
        annotations.put(node, type);
    }

    public Type typeOf(Object node) {
        return annotations.get(node);
    }

    /** Liga un nodo de declaración (variable/parámetro) con su {@link Symbol} para que C3D fije su Pos_memory. */
    public void bindSymbol(Object node, Symbol symbol) {
        symbolBindings.put(node, symbol);
    }

    public Symbol symbolOf(Object node) {
        return symbolBindings.get(node);
    }
}
