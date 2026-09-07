package com.navi;

import com.navi.backend.lexer_parser.y.IndentationTokenSource;
import com.navi.backend.lexer_parser.y.YLexer;
import com.navi.backend.lexer_parser.y.YParser;
import com.navi.backend.lexer_parser.y.errors.YErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {
    public static void main(String[] args) {
        String codigo = """
                // definición de estructuras globales, la sección es opcional
                %estructuras
                estructura MiEstructura:
                    cadena nombre
                
                estructura Persona:
                     entero edad
                     cadena nombre
                     flotante promedio // numero con decimales
                     caracter letra
                     /*
                       la expresión para definir  arreglos
                       obligatoriamente debe ser constante
                       unicamente dentro de la definición de una estructura
                     */
                
                     entero miArray[10]
                
                	 // es posible anidar estructuras
                     MiEstructura miEstructura
                
                %funciones
                definir funcionSinRetorno(entero miEntero):
                 miEntero = 90 * 10
                 si (miEntero < 0) entonces
                   miEntero = 10
                
                
                """;

        CharStream input = CharStreams.fromString(codigo);

        YLexer lexer = new YLexer(input);

        IndentationTokenSource indentationSource =
                new IndentationTokenSource(lexer);

        indentationSource.setIndentationErrorListener(
                (line, message) -> {

                    System.err.println(
                            "ERROR LÉXICO ["
                                    + line
                                    + "] "
                                    + message
                    );
                }
        );

        CommonTokenStream tokens =
                new CommonTokenStream(indentationSource);

        YParser parser =
                new YParser(tokens);

        parser.removeErrorListeners();

        parser.addErrorListener(new YErrorListener());

        tokens.fill();

        System.out.println(
                "========== TOKENS =========="
        );

        for (var token : tokens.getTokens()) {

            String symbolicName =
                    YLexer.VOCABULARY
                            .getSymbolicName(
                                    token.getType()
                            );

            System.out.printf(
                    "[%d:%d] %-15s -> '%s'%n",
                    token.getLine(),
                    token.getCharPositionInLine(),
                    symbolicName,
                    token.getText()
            );
        }

        System.out.println(
                "\n========== FIN TOKENS =========="
        );

        parser.program();

        System.out.println("\n========== FIN DEL PARSER ==========");
    }
}