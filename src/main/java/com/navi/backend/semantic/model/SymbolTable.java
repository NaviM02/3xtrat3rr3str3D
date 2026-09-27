package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.ScopeKind;
import lombok.Getter;

import java.util.List;

// tabla de simbolos: arbol de Scope con currentScope que se entra/sale con enter/exitScope
@Getter
public class SymbolTable {
    private final Scope globalScope;
    private Scope currentScope;

    public SymbolTable() {
        globalScope = new Scope(ScopeKind.GLOBAL, null);
        currentScope = globalScope;
    }

    public void define(Symbol symbol) {
        currentScope.define(symbol);
    }

    public boolean defineUnique(Symbol symbol) {
        return currentScope.defineUnique(symbol);
    }

    // define un callable validando que no haya duplicado con la misma firma
    public void defineCallable(Symbol callable) {
        List<Symbol> existing = currentScope.resolveOverloads(callable.getName());
        if (existing != null) {
            for (Symbol s : existing) {
                if (s.getSignature() != null && s.getSignature().sameParams(callable.getSignature())) {
                    throw new SemanticError("Callable duplicado: " + callable.getName());
                }
            }
        }
        currentScope.define(callable);
    }

    // define un callable en un scope cualquiera (util para memberScope de una clase)
    public void defineCallableIn(Scope scope, Symbol callable) {
        List<Symbol> existing = scope.resolveOverloads(callable.getName());
        if (existing != null) {
            for (Symbol s : existing) {
                if (s.getSignature() != null && s.getSignature().sameParams(callable.getSignature())) {
                    throw new SemanticError("Callable duplicado: " + callable.getName());
                }
            }
        }
        scope.define(callable);
    }

    public Symbol resolve(String name) {
        return currentScope.resolve(name);
    }

    public List<Symbol> resolveCallable(String name) {
        return currentScope.resolveCallable(name);
    }

    public Scope enterScope(ScopeKind kind) {
        Scope scope = new Scope(kind, currentScope);
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
