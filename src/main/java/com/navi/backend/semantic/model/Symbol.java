package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.SymbolKind;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// simbolo semantico: tipo, ambito y (cuando C3D asigna el marco) su posMemory en stack/global
@Getter
public class Symbol {
    private final String name;
    private final SymbolKind kind;
    private final Type type;                 // var/param: tipo; function/method: retorno; ctor: clase
    private final FunctionSignature signature; // solo callables, null en el resto
    private final boolean reference;         // Y: arreglos/structs por ref; Z: objetos son punteros
    private final Scope scope;               // ambito donde se define
    private final AggregateType owner;       // clase dueña (METHOD/CONSTRUCTOR), null en el resto
    private final int line;
    private final int column;
    @Setter
    private int posMemory = -1;              // offset en stack/global, -1 hasta que se asigna
    @Setter
    private List<Integer> arraySizes = List.of(); // tamaños si es arreglo, vacio si se desconocen

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
