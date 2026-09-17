package com.navi;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.global.Program;
import com.navi.backend.ast.y.visitors.ProgramVisitor;
import com.navi.backend.lexer_parser.y.IndentationTokenSource;
import com.navi.backend.lexer_parser.y.YLexer;
import com.navi.backend.lexer_parser.y.YParser;
import com.navi.backend.lexer_parser.y.errors.YErrorListener;
import com.navi.backend.lexer_parser.z.ZLexer;
import com.navi.backend.lexer_parser.z.ZParser;
import com.navi.backend.lexer_parser.z.errors.ZErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Main {
    public static void main(String[] args) {
        String codigo = """
                // definición de estructuras globales, la sección es opcional
                %estructuras
                estructura MiEstructura:
                    cadena nombre
                
                estructura Persona:\s
                     entero edad
                     cadena nombre
                     flotante promedio // numero con decimales
                     caracter letra
                     /*\s
                       la expresión para definir  arreglos\s
                       obligatoriamente debe ser constante
                       unicamente dentro de la definición de una estructura
                     */
                     entero miArray[10]\s
                
                	// es posible anidar estructuras
                     MiEstructura miEstructura\s
                
                
                
                // definición de un funciones, la seccion es obligatoria
                %funciones
                /*
                    Importante
                    Todos los tipos de datos primitivos se pasan por valor
                    Los arreglos se pasan asi:
                           [] entero miArray\s
                           ejemplo: definir miFuncion([] enterro miArray):
                           Los arreglos se van a pasar exclusivamente por referencia
                           Es decir que la función recibe un acceso directo\s
                           a la ubicación original del arreglo en la memoria.\s
                           Los arreglos a bajo nivel deben estar aplanados.
                    Las estructuras se pasan asi:
                           {} MiEstructura miEstructura
                           ejemplo:\s
                           Al igual que los arreglos,\s
                           se pasan exclusivamente por referencia
                */
                // Función sin retorno, con un parámetro. Los parametros son opcionales
                definir funcionSinRetorno(entero miEntero):
                   miEntero = 90 * 10
                
                // Función con retorno de tipo entero
                definir funcionConRetorno(entero miEntero) -> entero :
                    miEntero = 10 + 10
                    retornar 160
                    
                definir funcionPrueba():
                    entero numeros[5] = {10, 20, 30, 40, 50}
                    entero resultado
                    resultado = numeros[0] + numeros[1] // 10 + 20 = 30
                    numeros[2] = numeros[0] * 3 // numeros[2] ahora vale 30
                    
                    // Se pueden declarar estructuras dentro de las funciones
                    estructura Punto:
                        entero x
                        entero y
                        flotante promedio
                    
                    Punto p1 = {10, 20, 85.5}
                    Punto p2 = {5, 15, 90.0}
                    flotante sumaPromedios
                    entero distanciaCuadrada
                    
                    sumaPromedios = p1.promedio + p2.promedio
                    
                    Punto p3
                    p3 = p1\s
                    
                    entero sinInicializacion
                    entero edadUsuario = 25
                    flotante temperatura = 36.6
                    caracter inicial = 'A'
                    bool bandera = verdadero
                    bool bandera2 = falso     
                    cadena saludos =  "Saludos zetarianos"
                    entero numeros[10]
                    entero matriz[3][3]
                    Persona alumno1
                    alumno1.nombre = "Yennifer"
                
                definir funcionCondicionales():
                    si(edad > 18) entonces
                        imprimir("Codigo si es mayor de edad")
                
                        si(condicion == verdadero) entonces
                            imprimir("Otra condicion")
                
                        imprimir("Esto siempre se imprime")
                    sino (edad == 18) entonces  // opcional
                        imprimir("Codigo si tiene exactamente 18")
                    contrario  // opcional
                        imprimir("Codigo si es menor de edad")
                
                    elegir(opcion) :
                        caso 1:
                            // Código para la opción 1
                            x = 10
                            romper
                        caso 2:
                            // Código para la opción 2
                            x = 20
                            romper
                        siempre:
                            // Código por defecto
                            x = 30
                            romper
                
                    para(entero i = 0; i < 10; i++):
                        si(i == 3) entonces
                            continuar // Salta esta iteración cuando i es 3

                        si(i == 8) entonces
                            romper    // Sale del ciclo por completo cuando i es 8

                
                    entero contador = 0
                    mientras(contador < 5) hacer:
                        contador++
                
                        si(contador == 2) entonces
                            continuar // Salta el resto del ciclo y vuelve a evaluar
                
                    entero intentos = 0
                    hacer:
                        intentos++
                
                        si(intentos == 4) entonces
                            romper // Rompe el ciclo inmediatamente
                
                    mientras(intentos < 10)
                
                    imprimir("Imprimir")
                    leer()
                    cadena x = leer()
                    x = leer()
                
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

        System.out.println("\n========== FIN DEL PARSER ==========");

        YParser.ProgramContext tree = parser.program();

        ProgramVisitor visitor = new ProgramVisitor();
        Program program = (Program) visitor.visit(tree);

        System.out.println("\n========== AST ==========");
        printAst(program, 0);
        System.out.println("\n========== FIN AST ==========");
    }

    private static void printAst(AstYNode node, int level) {
        if (node == null) {
            return;
        }

        System.out.println("  ".repeat(level) + node.getNodeLabel());

        for (AstYNode child : node.getChildren()) {
            printAst(child, level + 1);
        }
    }
}