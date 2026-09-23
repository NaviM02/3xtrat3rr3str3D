package com.navi.backend.semantic;

import lombok.Getter;

import java.util.HashSet;
import java.util.IdentityHashMap;
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
    private final Map<Object, Type> annotations = new IdentityHashMap<>();
    private final SemanticErrors errors = new SemanticErrors();

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
}
