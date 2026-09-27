package com.navi.backend.semantic.support;

import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.model.Scope;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;

// alta de simbolos que usan los visitantes de los 3 lenguajes
// centraliza el patron new Symbol -> defineUnique -> bindSymbol
public final class Definitions {

    private final SemanticContext context;

    public Definitions(SemanticContext context) {
        this.context = context;
    }

    // define una variable en el scope actual y la liga a su nodo AST
    public Symbol variable(Object node, String name, Type type, int line, int col) {
        Symbol s = new Symbol(name, SymbolKind.VARIABLE, type, null, false,
                context.getSymbolTable().getCurrentScope(), null, line, col);
        if (!context.getSymbolTable().defineUnique(s)) {
            context.getErrors().report(line, col, "Variable duplicada: " + name);
        }
        context.bindSymbol(node, s);
        return s;
    }

    // define un parametro y lo liga a su nodo AST. reference = Y por referencia
    public Symbol parameter(Object node, String name, Type type, boolean reference, int line, int col) {
        Symbol s = new Symbol(name, SymbolKind.PARAMETER, type, null, reference,
                context.getSymbolTable().getCurrentScope(), null, line, col);
        if (!context.getSymbolTable().defineUnique(s)) {
            context.getErrors().report(line, col, "Parámetro duplicado: " + name);
        }
        context.bindSymbol(node, s);
        return s;
    }

    // registra una funcion en el scope global (Lat/Y)
    public void callable(Symbol fn) {
        try {
            context.getSymbolTable().defineCallable(fn);
        } catch (RuntimeException e) {
            context.getErrors().report(fn.getLine(), fn.getColumn(), e.getMessage());
        }
    }

    // registra un metodo/constructor en el scope de su clase (Z)
    public void callableIn(Scope scope, Symbol member) {
        try {
            context.getSymbolTable().defineCallableIn(scope, member);
        } catch (RuntimeException e) {
            context.getErrors().report(member.getLine(), member.getColumn(), e.getMessage());
        }
    }
}
