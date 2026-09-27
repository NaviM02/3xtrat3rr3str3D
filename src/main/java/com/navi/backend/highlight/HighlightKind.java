package com.navi.backend.highlight;

// categoria lexica, independiente del lenguaje; la UI le pone el color
public enum HighlightKind {
    KEYWORD,
    TYPE,
    BOOLEAN,
    NUMBER,
    STRING,
    CHAR,
    COMMENT,
    OPERATOR,
    PUNCTUATION,
    IDENTIFIER
}
