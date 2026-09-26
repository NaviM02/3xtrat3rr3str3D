package com.navi.backend.highlight;

/**
 * Rango de resaltado: {@code start} y {@code length} son offsets en el texto
 * fuente (mismas unidades que el documento del editor).
 */
public record HighlightSpan(int start, int length, HighlightKind kind) {
}
