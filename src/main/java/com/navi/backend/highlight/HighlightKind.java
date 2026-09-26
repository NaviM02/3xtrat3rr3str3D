package com.navi.backend.highlight;

/**
 * Categoría léxica de un fragmento de código, independiente del lenguaje.
 * La UI decide el color de cada categoría.
 */
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
