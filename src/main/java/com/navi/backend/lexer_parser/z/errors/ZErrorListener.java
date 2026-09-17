package com.navi.backend.lexer_parser.z.errors;

import com.navi.backend.lexer_parser.z.ZParser;
import org.antlr.v4.runtime.*;

public class ZErrorListener extends BaseErrorListener {

    private boolean errors = false;

    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e
    ) {
        errors = true;

        Token token = (Token) offendingSymbol;

        String tokenText = token != null
                ? token.getText()
                : "<desconocido>";

        System.err.println();
        System.err.println("Error sintáctico");
        System.err.println(
                "   Línea: " + line +
                        ", columna: " + charPositionInLine
        );
        System.err.println(
                "   Token inesperado: '" + tokenText + "'"
        );

        if (recognizer instanceof ZParser parser) {

            ParserRuleContext context = parser.getContext();

            if (isInsideClass(context)) {
                System.err.println(
                        "   No se permiten instrucciones directamente " +
                                "dentro del cuerpo de una clase."
                );
                System.err.println(
                        "   Se esperaba un atributo, constructor o método."
                );
            } else {
                System.err.println(
                        "   Detalle: " + msg
                );
            }
        }
    }

    public boolean hasErrors() {
        return errors;
    }

    private boolean isInsideClass(ParserRuleContext context) {
        ParserRuleContext current = context;

        while (current != null) {
            if (current instanceof ZParser.ClassDeclarationContext) {
                return true;
            }

            current = current.getParent() instanceof ParserRuleContext
                    ? (ParserRuleContext) current.getParent()
                    : null;
        }

        return false;
    }
}