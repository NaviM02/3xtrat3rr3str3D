package com.navi.backend.semantic;

import java.util.ArrayList;
import java.util.List;

/**
 * Recolector de errores semánticos. Es parte del {@link SemanticContext} (no
 * estado estático), así cada corrida tiene su lista propia y se acumulan todos
 * los errores antes de abortar.
 */
public class SemanticErrors {
    private final List<String> errors = new ArrayList<>();

    public void report(int line, int column, String message) {
        errors.add("[" + line + ":" + column + "] " + message);
    }

    public void report(String message) {
        errors.add(message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return List.copyOf(errors);
    }

    public void clear() {
        errors.clear();
    }
}
