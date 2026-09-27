package com.navi.backend.semantic.errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

// listener de errores de parseo que junta los mensajes en una lista
public class CollectingErrorListener extends BaseErrorListener {

    // tope de errores para no llenar la consola
    private static final int MAX_ERRORS = 50;

    private final List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e) {
        if (errors.size() >= MAX_ERRORS) return;

        String entry = "[" + line + ":" + charPositionInLine + "] " + msg;

        // a veces se re-reporta el mismo token, no lo repetimos
        if (!errors.isEmpty() && errors.get(errors.size() - 1).equals(entry)) return;

        errors.add(entry);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }
}
