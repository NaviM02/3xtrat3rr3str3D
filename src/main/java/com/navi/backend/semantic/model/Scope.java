package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.ScopeKind;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// ambito lexico; los callables van en lista para soportar sobrecarga (Z)
// no hay MODULE: los imports son import * y caen todos en el scope global
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

    // agrega un simbolo (permite sobrecarga; la dedup la valida quien llama)
    public void define(Symbol symbol) {
        symbols.computeIfAbsent(symbol.getName(), k -> new ArrayList<>()).add(symbol);
    }

    // define un nombre unico en este scope (variables, params)
    public boolean defineUnique(Symbol symbol) {
        if (symbols.containsKey(symbol.getName())) return false;
        define(symbol);
        return true;
    }

    // simbolos con ese nombre solo en este scope (o null)
    public List<Symbol> resolveOverloads(String name) {
        return symbols.get(name);
    }

    // resuelve por nombre recorriendo la cadena; devuelve el primero
    public Symbol resolve(String name) {
        List<Symbol> local = symbols.get(name);
        if (local != null && !local.isEmpty()) return local.get(0);
        if (parent != null) return parent.resolve(name);
        return null;
    }

    // sobrecargas de un callable recorriendo la cadena (o null)
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
