package com.navi.backend.semantic;

import lombok.Getter;
import lombok.Setter;

/**
 * Símbolo semántico. Guarda tipo/ámbito y, una vez que el generador de C3D
 * asigna el marco, la posición de memoria ({@link #posMemory}) de la variable o
 * parámetro dentro del stack/área global (ver apuntes de clase).
 */
@Getter
public class Symbol {
    private final String name;
    private final SymbolKind kind;
    private final Type type;                 // variable/param: tipo declarado; function/method: retorno; ctor: tipo clase
    private final FunctionSignature signature; // solo callables; null en el resto
    private final boolean reference;         // Y: arreglos/structs por referencia; Z: objetos son punteros
    private final Scope scope;               // ámbito donde se define
    private final AggregateType owner;       // clase dueña (METHOD/CONSTRUCTOR); null en el resto
    private final int line;
    private final int column;
    @Setter
    private int posMemory = -1;              // offset en stack/global; -1 mientras no se asigna

    public Symbol(String name, SymbolKind kind, Type type, FunctionSignature signature,
                  boolean reference, Scope scope, AggregateType owner, int line, int column) {
        this.name = name;
        this.kind = kind;
        this.type = type;
        this.signature = signature;
        this.reference = reference;
        this.scope = scope;
        this.owner = owner;
        this.line = line;
        this.column = column;
    }

    public boolean isCallable() {
        return signature != null;
    }
}
