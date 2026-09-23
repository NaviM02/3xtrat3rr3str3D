package com.navi.backend.semantic;

import lombok.Getter;

/**
 * Parseo semántico de un import de Lat ({@code import carpeta.Objeto1.z}).
 *
 * <p>Se acepta cualquier path; la validación es semántica: el último segmento
 * separado por '.' debe ser {@code y} o {@code z} (la extensión). El resto es la
 * ruta del módulo (los puntos se mapean a separador de archivo en el loader).</p>
 */
@Getter
public class ImportPath {
    private final String raw;
    private final String modulePath; // todo excepto el último segmento
    private final Language language; // Y o Z según la extensión; null si es inválida

    public ImportPath(String raw) {
        this.raw = raw;
        String normalized = raw == null ? "" : raw.trim();
        int lastDot = normalized.lastIndexOf('.');
        if (lastDot <= 0 || lastDot == normalized.length() - 1) {
            this.modulePath = normalized;
            this.language = null;
            return;
        }
        this.modulePath = normalized.substring(0, lastDot);
        String ext = normalized.substring(lastDot + 1);
        this.language = switch (ext) {
            case "y" -> Language.Y;
            case "z" -> Language.Z;
            default -> null;
        };
    }

    public boolean isValid() {
        return language != null;
    }

    public boolean isY() {
        return language == Language.Y;
    }

    public boolean isZ() {
        return language == Language.Z;
    }
}
