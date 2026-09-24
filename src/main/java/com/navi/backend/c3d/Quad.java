package com.navi.backend.c3d;

import java.util.List;

/**
 * Cuarteta de código de tres direcciones: (operador, operando 1, operando 2,
 * resultado). {@code op} es la operación, {@code result} el destino y
 * {@code args} los operandos; el significado depende de {@code op}
 * (ver {@link #toString()}).
 *
 * <p>Vocabulario alineado con los apuntes de clase:
 * <ul>
 *   <li>{@code t = a op b} binarias aritméticas</li>
 *   <li>{@code t = BP + off} dirección de stack; {@code t = base + off} dirección de heap</li>
 *   <li>{@code t = stack[addr]} / {@code stack[addr] = t}</li>
 *   <li>{@code t = heap[addr]} / {@code heap[addr] = t}</li>
 *   <li>{@code if a op b goto L} salto condicional</li>
 *   <li>{@code goto L}, {@code L:}, {@code call}, {@code return}, {@code halt}</li>
 * </ul>
 */
public class Quad {

    private final String op;
    private final String result;   // puede ser null (p.ej. goto, label, print)
    private final List<String> args;

    public Quad(String op, String result, String... args) {
        this.op = op;
        this.result = result;
        this.args = List.of(args);
    }

    public Quad(String op, String result, List<String> args) {
        this.op = op;
        this.result = result;
        this.args = List.copyOf(args);
    }

    public String getOp() {
        return op;
    }

    public List<String> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return switch (op) {
            case "=" -> result + " = " + args.get(0);
            case "neg" -> result + " = - " + args.get(0);
            case "if" -> "if " + args.get(0) + " " + args.get(1) + " " + args.get(2) + " goto " + args.get(3);
            case "goto" -> "goto " + args.get(0);
            case "label" -> args.get(0) + ":";
            case "stack_load" -> result + " = stack[" + args.get(0) + "]";
            case "stack_store" -> "stack[" + args.get(0) + "] = " + result;
            case "heap_load" -> result + " = heap[" + args.get(0) + "]";
            case "heap_store" -> "heap[" + args.get(0) + "] = " + result;
            case "call" -> (result == null ? "" : result + " = ") + "call " + String.join(", ", args);
            case "enter" -> "enter " + args.get(0);
            case "leave" -> "leave";
            case "print" -> "print " + args.get(0);
            case "read" -> result == null ? "read" : result + " = read";
            case "return" -> "return" + (args.isEmpty() ? "" : " " + args.get(0));
            case "halt" -> "halt";
            default -> result + " = " + args.get(0) + " " + op + " " + args.get(1);
        };
    }
}
