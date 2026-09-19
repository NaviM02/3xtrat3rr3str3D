package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.Language;
import com.navi.backend.semantic.enums.SymbolKind;
import lombok.Getter;

@Getter
public class Symbol {
    private final String name;
    private final SymbolKind kind;
    private final Language language;
    private final Type type;
    private final Scope scope;
    private final int line;
    private final int column;
    //
    private final SymbolModifiers modifiers;
    private final FunctionSignature functionSignature;

    public Symbol(String name, SymbolKind kind, Language language, Type type, Scope scope, int line, int column,
                  SymbolModifiers modifiers, FunctionSignature functionSignature) {
        this.name = name;
        this.kind = kind;
        this.language = language;
        this.type = type;
        this.scope = scope;
        this.line = line;
        this.column = column;
        //
        this.modifiers = modifiers;
        this.functionSignature = functionSignature;
    }
}