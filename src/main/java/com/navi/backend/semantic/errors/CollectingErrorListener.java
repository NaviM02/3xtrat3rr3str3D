package com.navi.backend.semantic.errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Listener de errores de parseo que acumula mensajes en vez de imprimirlos.
 * Lo usan Main y {@link FileModuleLoader} para Lat, Y y Z.
 */
public class CollectingErrorListener extends BaseErrorListener {

    /** Tope de errores reportados para que un archivo muy roto no llene la consola. */
    private static final int MAX_ERRORS = 50;

    private final List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e) {
        if (errors.size() >= MAX_ERRORS) return;

        String entry = "[" + line + ":" + charPositionInLine + "] " + msg;

        // La estrategia de recuperación puede re-reportar el mismo token: evitar ruido.
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
