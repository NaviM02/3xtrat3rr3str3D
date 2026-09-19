package com.navi.backend.semantic;

import com.navi.backend.semantic.enums.ScopeKind;
import com.navi.backend.semantic.model.Scope;
import com.navi.backend.semantic.model.Symbol;
import lombok.Getter;

@Getter
public class SymbolTable {
    private final Scope globalScope;
    private Scope currentScope;

    private int nextScopeId = 0;

    public SymbolTable() {
        globalScope = new Scope(nextScopeId++, ScopeKind.GLOBAL, null);
        currentScope = globalScope;
    }

    public boolean define(Symbol symbol) {
        return currentScope.define(symbol);
    }

    public Symbol resolve(String name) {
        return currentScope.resolve(name);
    }

    public Symbol resolveLocal(String name) {
        return currentScope.resolveLocal(name);
    }

    public Scope enterScope(ScopeKind kind) {
        Scope scope = new Scope(nextScopeId++, kind, currentScope);
        currentScope.addChild(scope);
        currentScope = scope;
        return scope;
    }

    public void exitScope() {
        if (currentScope.getParent() != null) {
            currentScope = currentScope.getParent();
        }
    }
}