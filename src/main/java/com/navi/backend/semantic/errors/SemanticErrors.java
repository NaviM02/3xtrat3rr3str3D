package com.navi.backend.semantic.errors;

import java.util.ArrayList;
import java.util.List;

// junta los errores semanticos de una corrida
// va dentro del SemanticContext, no es estatico
public class SemanticErrors {
    private final List<String> errors = new ArrayList<>();
    private String sourceName = "";

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? "" : sourceName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void report(int line, int column, String message) {
        String source = sourceName.isBlank() ? "" : sourceName + ":";
        errors.add(source + "[" + line + ":" + column + "] " + message);
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
