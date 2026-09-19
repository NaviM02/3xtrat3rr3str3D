package com.navi.backend.semantic.errors;

import java.util.ArrayList;
import java.util.List;

public class SemanticErrors {
    private static List<String> errors = new ArrayList<>();

    public static boolean hasErrors() {
        return !errors.isEmpty();
    }

    public static List<String> getErrors() {
        return List.copyOf(errors);
    }

    public static void reportError(SemanticException exception) {
        errors.add(exception.getMessage());
    }

    public static void cleanErrors() {
        errors = new ArrayList<>();
    }
}
