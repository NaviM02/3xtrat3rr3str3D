package com.navi.backend.highlight;

// rango a resaltar: start y length son offsets en el texto fuente
public record HighlightSpan(int start, int length, HighlightKind kind) {
}
