package com.navi.backend.c3d;

import lombok.Getter;

import java.util.List;

// cuarteta de tres direcciones: (op, operandos, resultado)
// el significado depende de op; ver toString()
// ops: aritmeticas (t = a op b), direcciones (t = BP+off / base+off)
// stack_load/store, heap_load/store, if a op b goto L, goto, label, call, return, halt
@Getter
public class Quad {

    private final String op;
    private final String result;   // puede ser null (goto, label, print, ...)
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
            case "enter" -> args.size() > 1 ? "enter " + args.get(0) + ", " + args.get(1) : "enter " + args.get(0);
            case "leave" -> "leave";
            case "print" -> "print " + args.get(0);
            case "read" -> result == null ? "read" : result + " = read";
            case "return" -> "return" + (args.isEmpty() ? "" : " " + args.get(0));
            case "halt" -> "halt";
            default -> result + " = " + args.get(0) + " " + op + " " + args.get(1);
        };
    }
}
