package com.navi.backend.semantic;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ámbito léxico. Guarda los símbolos por nombre; los callables se agrupan en
 * lista para soportar sobrecarga (Z). No existe {@code MODULE}: los imports son
 * {@code import *} y todos los símbolos importados caen en el scope global.
 */
@Getter
public class Scope {
    private final ScopeKind kind;
    private final Scope parent;
    private final Map<String, List<Symbol>> symbols = new LinkedHashMap<>();
    private final List<Scope> children = new ArrayList<>();

    public Scope(ScopeKind kind, Scope parent) {
        this.kind = kind;
        this.parent = parent;
    }

    /** Agrega un símbolo (permite sobrecarga de callables; la dedup la valida el caller). */
    public void define(Symbol symbol) {
        symbols.computeIfAbsent(symbol.getName(), k -> new ArrayList<>()).add(symbol);
    }

    /** Define un nombre que debe ser único en este scope (variables, params). */
    public boolean defineUnique(Symbol symbol) {
        if (symbols.containsKey(symbol.getName())) return false;
        define(symbol);
        return true;
    }

    /** Símbolos con ese nombre solo en este scope (o null). */
    public List<Symbol> resolveOverloads(String name) {
        return symbols.get(name);
    }

    /** Resuelve por nombre recorriendo la cadena de ámbitos (devuelve el primero). */
    public Symbol resolve(String name) {
        List<Symbol> local = symbols.get(name);
        if (local != null && !local.isEmpty()) return local.get(0);
        if (parent != null) return parent.resolve(name);
        return null;
    }

    /** Sobrecargas de un callable por nombre recorriendo la cadena (o null). */
    public List<Symbol> resolveCallable(String name) {
        List<Symbol> local = symbols.get(name);
        if (local != null && !local.isEmpty()) return local;
        if (parent != null) return parent.resolveCallable(name);
        return null;
    }

    public void addChild(Scope scope) {
        children.add(scope);
    }
}
