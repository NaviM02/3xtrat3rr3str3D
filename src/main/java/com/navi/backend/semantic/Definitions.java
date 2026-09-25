package com.navi.backend.semantic;

/**
 * Alta de símbolos compartida por los seis visitantes (declaraciones + semántica
 * de Lat/Y/Z). Centraliza el patrón repetido {@code new Symbol -> defineUnique ->
 * reportar duplicado -> bindSymbol} y el registro de callables con su manejo de
 * sobrecargas/errores.
 */
public final class Definitions {

    private final SemanticContext context;

    public Definitions(SemanticContext context) {
        this.context = context;
    }

    /**
     * Define una variable en el ámbito actual y la liga a su nodo AST
     * (para que el C3D fije su Pos_memory).
     */
    public Symbol variable(Object node, String name, Type type, int line, int col) {
        Symbol s = new Symbol(name, SymbolKind.VARIABLE, type, null, false,
                context.getSymbolTable().getCurrentScope(), null, line, col);
        if (!context.getSymbolTable().defineUnique(s)) {
            context.getErrors().report(line, col, "Variable duplicada: " + name);
        }
        context.bindSymbol(node, s);
        return s;
    }

    /**
     * Define un parámetro en el ámbito actual y lo liga a su nodo AST.
     *
     * @param reference Y: arreglos/structs pasados por referencia
     */
    public Symbol parameter(Object node, String name, Type type, boolean reference, int line, int col) {
        Symbol s = new Symbol(name, SymbolKind.PARAMETER, type, null, reference,
                context.getSymbolTable().getCurrentScope(), null, line, col);
        if (!context.getSymbolTable().defineUnique(s)) {
            context.getErrors().report(line, col, "Parámetro duplicado: " + name);
        }
        context.bindSymbol(node, s);
        return s;
    }

    /** Registra una función en el ámbito global (Lat/Y). */
    public void callable(Symbol fn) {
        try {
            context.getSymbolTable().defineCallable(fn);
        } catch (RuntimeException e) {
            context.getErrors().report(fn.getLine(), fn.getColumn(), e.getMessage());
        }
    }

    /** Registra un método/constructor en el scope de miembros de su clase (Z). */
    public void callableIn(Scope scope, Symbol member) {
        try {
            context.getSymbolTable().defineCallableIn(scope, member);
        } catch (RuntimeException e) {
            context.getErrors().report(member.getLine(), member.getColumn(), e.getMessage());
        }
    }
}
