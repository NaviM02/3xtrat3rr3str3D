package com.navi.backend.semantic.loading;

import com.navi.backend.semantic.enums.Language;
import lombok.Getter;

// parsea un import de Lat tipo "carpeta.Objeto1.z"
// el ultimo segmento es la extension (y o z), el resto es la ruta
@Getter
public class ImportPath {
    private final String raw;
    private final String modulePath; // todo menos el ultimo segmento
    private final Language language; // Y o Z segun la extension; null si no sirve

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
