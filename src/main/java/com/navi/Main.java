package com.navi;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.ast.z.visitors.ProgramVisitor;
import com.navi.backend.lexer_parser.z.ZLexer;
import com.navi.backend.lexer_parser.z.ZParser;
import com.navi.backend.lexer_parser.z.errors.ZErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Main {

    public static void main(String[] args) throws Exception {

        String source = """
                public class Persona {

                    int edad = 25;
                    double altura = 1.75;
                    char inicial = 'A';
                    boolean esEstudiante = true;
                    int[] calificaciones = new int[5];
                    String[] nombres = {"Carlos", "Ana", "Pedro"};
                    int[][] matriz = new int[3][3];

                    public Persona(String nombreParametro, int edadParametro) {
                        nombre = nombreParametro;
                        edad = edadParametro;
                    }

                    public Persona() {
                        nombre = "Sin nombre";
                        edad = 0;
                    }

                    public void saludar() {
                        println("Hola " + nombre);
                    }

                    public void prueba() {
                        Persona alumno1;
                        alumno1 = new Persona("Carlos", 20);
                        Persona profesor = new Persona("Ana", 45);

                        int a = 10;
                        int b = 3;
                        int suma = a + b;
                        int resta = a - b;
                        int multiplicacion = a * b;
                        int division = a / b;
                        int modulo = a % b;

                        a++;
                        b--;

                        boolean esMayor = (a > b);
                        boolean esIgual = (a == 10);
                        boolean condicionAnd = (a > 5 && b < 10);
                        boolean condicionOr = (a > 20 || b > 0);
                        boolean condicionNot = !(a == 10);

                        int x = 5;
                        x += 3;
                        x -= 2;
                        x *= 2;

                        int edad = 20;
                        String mensaje = (edad >= 18)
                                ? "Es mayor de edad"
                                : "Es menor de edad";

                        int numero = 7;
                        int esParOImpar = (numero % 2 == 0) ? 0 : 1;

                        int[] numeros = {10, 20, 30, 40, 50};
                        int resultado;

                        resultado = numeros[0] + numeros[1];
                        numeros[2] = numeros[0] * 3;

                        Persona p1 = new Persona("Carlos", 25);
                        Persona p2 = new Persona("Ana", 30);

                        int sumaEdades;
                        String mensajeClasificacion;

                        sumaEdades = p1.edad + p2.edad;

                        mensajeClasificacion =
                                (p1.edad >= 18) ? "Adulto" : "Menor";

                        int anioNac = 2026 - p1.obtenerEdad();

                        if (p1 == null) {
                            print("Es posible comparar si un objeto es nulo");
                        }
                    }

                    public void condicionales() {

                        int edad = 18;
                        int opcion = 2;

                        if (edad > 18) {
                            println("Es mayor de edad.");
                        } else if (edad == 18) {
                            println("Justo tiene 18 años!");
                        } else {
                            println("Es menor de edad.");
                        }

                        if (edad >= 18)
                            println("Es adulto");
                        else
                            println("Es menor");

                        if (condicion == true)
                            if (otraCondicion == false)
                                print("hola");

                        switch (opcion) {
                            case 1:
                                println("Opción 1 seleccionada");
                                break;

                            case 2:
                                println("Opción 2 seleccionada");

                            default:
                                println("Opción no válida");
                                break;
                        }

                        for (int i = 0; i < 5; i++) {
                            println("Iteración número: " + i);

                            if (i == 2) {
                                continue;
                            }

                            if (i == 4) {
                                break;
                            }
                        }

                        for (;;) {
                            println("Este bucle nunca termina a menos que use un break...");
                            break;
                        }

                        int contador = 0;

                        while (contador < 3) {
                            println("Contador while: " + contador);
                            contador++;

                            if (contador == 1) {
                                continue;
                            }
                        }

                        int intentos = 0;

                        do {
                            intentos++;
                            println("Intento número: " + intentos);

                            if (intentos == 2) {
                                break;
                            }
                        } while (intentos < 5);

                        println("Imprimo con un salto de linea");
                        print("Imprimo sin un salto de linea");
                        readln();
                    }

                    public int calcularAnioNacimiento(int anioActual) {
                        return anioActual - edad;
                    }
                }
                """;

        System.out.println("=================================");
        System.out.println("       PRUEBA DE ZETARIANO");
        System.out.println("=================================\n");

        // -------------------------------------------------
        // 1. Lexer
        // -------------------------------------------------

        CharStream input = CharStreams.fromString(source);

        ZLexer lexer = new ZLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        tokens.fill();

        System.out.println("========== TOKENS ==========");

        for (Token token : tokens.getTokens()) {

            String tokenName =
                    ZLexer.VOCABULARY.getSymbolicName(token.getType());

            if (tokenName == null) {
                tokenName =
                        ZLexer.VOCABULARY.getLiteralName(token.getType());
            }

            System.out.printf(
                    "[%d:%d] %-20s -> '%s'%n",
                    token.getLine(),
                    token.getCharPositionInLine(),
                    tokenName,
                    token.getText()
            );
        }

        System.out.println("\n========== FIN TOKENS ==========");

        // -------------------------------------------------
        // 2. Parser
        // -------------------------------------------------

        tokens.seek(0);

        ZParser parser = new ZParser(tokens);

        ZErrorListener errorListener = new ZErrorListener();

        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        System.out.println("\n========== PARSER ==========");

        ZParser.ProgramContext tree = parser.program();

        System.out.println("\n========== FIN DEL PARSER ==========");

        // -------------------------------------------------
        // 3. AST
        // -------------------------------------------------

        if (errorListener.hasErrors()) {

            System.out.println(
                    "\n========== AST NO GENERADO =========="
            );

            System.out.println(
                    "El programa tiene errores sintácticos."
            );

            return;
        }

        ProgramVisitor visitor = new ProgramVisitor();

        ProgramZ program = (ProgramZ) visitor.visit(tree);

        System.out.println("\n========== AST ==========");

        printAst(program, 0);

        System.out.println("\n========== FIN AST ==========");
    }

    private static void printAst(AstZNode node, int level) {

        if (node == null) {
            return;
        }

        System.out.println(
                "  ".repeat(level) + node.getNodeLabel()
        );

        for (AstZNode child : node.getChildren()) {
            printAst(child, level + 1);
        }
    }
}