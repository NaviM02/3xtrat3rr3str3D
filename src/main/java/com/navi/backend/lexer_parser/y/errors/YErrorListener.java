package com.navi.backend.lexer_parser.y.errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class YErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e) {

        System.err.println(
                "SYNTAX ERROR [" +
                line + ":" +
                charPositionInLine +
                "] " +
                msg
        );
    }
}