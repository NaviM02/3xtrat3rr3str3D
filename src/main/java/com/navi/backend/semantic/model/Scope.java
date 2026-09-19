package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.ScopeKind;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Scope {
    private final int id;
    private final ScopeKind kind;
    private final Scope parent;

    private final Map<String, Symbol> symbols = new LinkedHashMap<>();
    private final List<Scope> children = new ArrayList<>();

    public Scope(int id, ScopeKind kind, Scope parent) {
        this.id = id;
        this.kind = kind;
        this.parent = parent;
    }

    public boolean define(Symbol symbol) {
        if (symbols.containsKey(symbol.getName())) return false;
        symbols.put(symbol.getName(), symbol);
        return true;
    }

    public Symbol resolveLocal(String name) {
        return symbols.get(name);
    }

    public Symbol resolve(String name) {
        Symbol symbol = resolveLocal(name);
        if (symbol != null) return symbol;
        if (parent != null) return parent.resolve(name);
        return null;
    }

    public void addChild(Scope scope) {
        children.add(scope);
    }
}