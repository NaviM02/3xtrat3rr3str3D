package com.navi.backend.c3d;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// traduce las cuartetas finales a un archivo C autocontenido y compilable
// modo int: si todo es entero/booleano usa long long y operadores nativos
// modo Val: si hay string/char/decimal/null/read usa un valor etiquetado Val
// llamado: args en stack[SP + i]; enter fija BP = SP; el retorno viaja en RET
public class CGenerator {

    private final List<Quad> quads;
    private final Set<String> functionNames = new LinkedHashSet<>();
    private boolean intMode;
    private int explicitGlobalSize = -1;

    public CGenerator(List<Quad> quads) {
        this.quads = quads;
    }

    public CGenerator(List<Quad> quads, int globalSize) {
        this.quads = quads;
        this.explicitGlobalSize = globalSize;
    }

    private static final class CFunc {
        final String name;
        final int frameSize;
        final List<Quad> body;

        CFunc(String name, int frameSize, List<Quad> body) {
            this.name = name;
            this.frameSize = frameSize;
            this.body = body;
        }
    }

    public String generate() {
        intMode = detectIntMode();
        List<CFunc> funcs = splitFunctions();
        for (CFunc f : funcs) functionNames.add(f.name);

        StringBuilder sb = new StringBuilder();
        if (intMode) {
            sb.append("/* Generado desde las cuartetas C3D. Compilar: gcc -o programa programa.c */\n");
            sb.append("#include <stdio.h>\n\n");
            sb.append("static long long stack[262144];\n");
            sb.append("static long long heap[262144];\n");
            sb.append("static long long BP = 0, GP = 0, HP = 0, SP = 0;\n");
            sb.append("static long long RET;\n");
        } else {
            sb.append(ValRuntime.build(quads));
        }

        sb.append("\n/* ---- prototipos ---- */\n");
        for (CFunc f : funcs) sb.append("static void ").append(fn(f.name)).append("(void);\n");
        sb.append("\n/* ---- funciones ---- */\n");
        for (CFunc f : funcs) emitFunction(sb, f);

        sb.append("\nint main(void) {\n");
        sb.append("    GP = 0; HP = 0; BP = 0; SP = ").append(globalSize()).append(";\n");
        if (functionNames.contains("globals_init")) sb.append("    ").append(fn("globals_init")).append("();\n");
        if (functionNames.contains("main")) sb.append("    ").append(fn("main")).append("();\n");
        sb.append("    return 0;\n}\n");
        return sb.toString();
    }

    // ---------------------------------------------------------------- modo

    // true si ninguna cuarteta usa string/char/decimal/null/read
    private boolean detectIntMode() {
        for (Quad q : quads) {
            if ("read".equals(q.getOp())) return false;
            if (isNonIntLiteral(q.getResult())) return false;
            for (String a : q.getArgs()) {
                if (isNonIntLiteral(a)) return false;
            }
        }
        return true;
    }

    private static boolean isNonIntLiteral(String s) {
        if (s == null) return false;
        return s.startsWith("\"") || s.startsWith("'") || "null".equals(s) || s.contains(".");
    }

    // ---------------------------------------------------------------- particion

    private List<CFunc> splitFunctions() {
        List<CFunc> funcs = new ArrayList<>();
        int i = 0;
        while (i < quads.size()) {
            Quad q = quads.get(i);
            if ("enter".equals(q.getOp())) {
                String name = q.getArgs().get(0);
                int size = q.getArgs().size() > 1 ? parseInt(q.getArgs().get(1), -1) : -1;
                List<Quad> body = new ArrayList<>();
                i++;
                while (i < quads.size() && !"enter".equals(quads.get(i).getOp())) {
                    body.add(quads.get(i));
                    i++;
                }
                if (size < 0) size = frameSizeOf(body);
                funcs.add(new CFunc(name, size, body));
            } else {
                i++;
            }
        }
        return funcs;
    }

    private int frameSizeOf(List<Quad> body) {
        int max = -1;
        for (Quad q : body) {
            if ("+".equals(q.getOp()) && !q.getArgs().isEmpty() && "BP".equals(q.getArgs().get(0))) {
                int off = parseInt(q.getArgs().get(1), -1);
                if (off > max) max = off;
            }
        }
        return max + 1;
    }

    private int globalSize() {
        if (explicitGlobalSize >= 0) return explicitGlobalSize;
        int max = -1;
        for (Quad q : quads) {
            if ("+".equals(q.getOp()) && !q.getArgs().isEmpty() && "GP".equals(q.getArgs().get(0))) {
                int off = parseInt(q.getArgs().get(1), -1);
                if (off > max) max = off;
            }
        }
        return max + 1;
    }

    // ---------------------------------------------------------------- funcion

    private void emitFunction(StringBuilder sb, CFunc f) {
        sb.append("static void ").append(fn(f.name)).append("(void) {\n");
        TreeSet<Integer> temps = new TreeSet<>();
        for (Quad q : f.body) collectTemps(q, temps);
        for (int t : temps) {
            sb.append("    ").append(intMode ? "long long" : "Val").append(" t").append(t).append(";\n");
        }
        boolean hasLeave = f.body.stream().anyMatch(q -> "leave".equals(q.getOp()));
        if (hasLeave) {
            sb.append("    long long __oldBP = 0;\n");
            sb.append("    __oldBP = BP; BP = SP; SP += ").append(f.frameSize).append(";\n");
        } else {
            sb.append("    BP = SP; SP += ").append(f.frameSize).append(";\n");
        }
        for (Quad q : f.body) emitQuad(sb, q);
        sb.append("}\n\n");
    }

    private void collectTemps(Quad q, Set<Integer> out) {
        collectTemp(q.getResult(), out);
        for (String a : q.getArgs()) collectTemp(a, out);
    }

    private void collectTemp(String s, Set<Integer> out) {
        if (isTemp(s)) out.add(Integer.parseInt(s.substring(1)));
    }

    private void emitQuad(StringBuilder sb, Quad q) {
        switch (q.getOp()) {
            case "label" -> {
                String name = q.getArgs().get(0);
                if (!functionNames.contains(name)) sb.append(name).append(":;\n");
            }
            case "goto" -> sb.append("    goto ").append(q.getArgs().get(0)).append(";\n");
            case "leave" -> sb.append("    SP = BP; BP = __oldBP;\n");
            case "=" -> emitAssign(sb, q.getResult(), q.getArgs().get(0));
            case "neg" -> sb.append("    ").append(q.getResult()).append(" = ")
                    .append(intMode ? "-" + expr(q.getArgs().get(0))
                            : "op('-', mk_int(0), " + val(q.getArgs().get(0)) + ")")
                    .append(";\n");
            case "+", "-", "*", "/", "%" -> emitBinary(sb, q);
            case "if" -> emitIf(sb, q);
            case "stack_load" -> sb.append("    ").append(q.getResult()).append(" = stack[").append(loadAddr(q)).append("];\n");
            case "stack_store" -> sb.append("    stack[").append(loadAddr(q)).append("] = ").append(value(q.getResult())).append(";\n");
            case "heap_load" -> sb.append("    ").append(q.getResult()).append(" = heap[").append(loadAddr(q)).append("];\n");
            case "heap_store" -> sb.append("    heap[").append(loadAddr(q)).append("] = ").append(value(q.getResult())).append(";\n");
            case "call" -> emitCall(sb, q);
            case "print" -> {
                if (intMode) {
                    sb.append("    printf(\"%lld\\n\", ").append(expr(q.getArgs().get(0))).append(");\n");
                } else {
                    sb.append("    prn(").append(val(q.getArgs().get(0))).append("); putchar('\\n');\n");
                }
            }
            case "read" -> {
                if (q.getResult() != null) sb.append("    ").append(q.getResult()).append(" = rd();\n");
                else sb.append("    rd();\n");
            }
            case "return" -> {
                if (q.getArgs().isEmpty()) sb.append("    return;\n");
                else sb.append("    RET = ").append(value(q.getArgs().get(0))).append("; return;\n");
            }
            case "halt" -> sb.append("    return;\n");
            default -> sb.append("    /* op no soportado: ").append(q.getOp()).append(" */\n");
        }
    }

    private void emitAssign(StringBuilder sb, String target, String arg) {
        if (intMode) {
            sb.append("    ").append(target).append(" = ").append(expr(arg)).append(";\n");
        } else if (isReg(target)) {
            sb.append("    ").append(target).append(" = ").append(intExpr(arg)).append(";\n");
        } else {
            sb.append("    ").append(target).append(" = ").append(val(arg)).append(";\n");
        }
    }

    private void emitBinary(StringBuilder sb, Quad q) {
        String op = q.getOp();
        String target = q.getResult();
        String l = q.getArgs().get(0);
        String r = q.getArgs().get(1);
        if (intMode) {
            sb.append("    ").append(target).append(" = ").append(expr(l)).append(' ')
                    .append(op).append(' ').append(expr(r)).append(";\n");
        } else if (isReg(target)) {
            sb.append("    ").append(target).append(" = ").append(intExpr(l)).append(' ')
                    .append(op).append(' ').append(intExpr(r)).append(";\n");
        } else if ("+".equals(op)) {
            sb.append("    ").append(target).append(" = add(").append(val(l)).append(", ")
                    .append(val(r)).append(");\n");
        } else {
            sb.append("    ").append(target).append(" = op('").append(op).append("', ")
                    .append(val(l)).append(", ").append(val(r)).append(");\n");
        }
    }

    private void emitIf(StringBuilder sb, Quad q) {
        if (intMode) {
            sb.append("    if (").append(expr(q.getArgs().get(0))).append(' ')
                    .append(q.getArgs().get(1)).append(' ').append(expr(q.getArgs().get(2)))
                    .append(") goto ").append(q.getArgs().get(3)).append(";\n");
        } else {
            sb.append("    if (cmp(").append(val(q.getArgs().get(0))).append(", ")
                    .append(val(q.getArgs().get(2))).append(") ").append(cmpOp(q.getArgs().get(1)))
                    .append(" 0) goto ").append(q.getArgs().get(3)).append(";\n");
        }
    }

    private void emitCall(StringBuilder sb, Quad q) {
        List<String> args = q.getArgs();
        String func = args.get(0);
        for (int k = 1; k < args.size(); k++) {
            sb.append("    stack[SP + ").append(k - 1).append("] = ").append(value(args.get(k))).append(";\n");
        }
        sb.append("    ").append(fn(func)).append("();\n");
        if (q.getResult() != null) {
            sb.append("    ").append(q.getResult()).append(" = RET;\n");
        }
    }

    // direccion de un acceso a memoria (en modo Val hay que desenvolver)
    private String loadAddr(Quad q) {
        return intMode ? expr(q.getArgs().get(0)) : "as_index(" + val(q.getArgs().get(0)) + ")";
    }

    // valor a almacenar: directo en modo int, Val en modo Val
    private String value(String operand) {
        return intMode ? expr(operand) : val(operand);
    }

    // ---------------------------------------------------------------- helpers

    private static String fn(String name) {
        return "__fn_" + name;
    }

    private static boolean isReg(String s) {
        return "BP".equals(s) || "GP".equals(s) || "HP".equals(s);
    }

    private static boolean isTemp(String s) {
        if (s == null || s.length() < 2 || s.charAt(0) != 't') return false;
        for (int i = 1; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    // modo int: el operando ya es una expresion entera C
    private static String expr(String operand) {
        if (isReg(operand) || isTemp(operand)) return operand;
        return operand; // literal numerico
    }

    // modo Val: el operando como Val
    private static String val(String operand) {
        if (isReg(operand)) return "mk_int(" + operand + ")";
        if (isTemp(operand)) return operand;
        if ("null".equals(operand)) return "mk_null()";
        if (operand.startsWith("\"")) return "mk_str(" + operand + ")";
        if (operand.startsWith("'")) return "mk_chr(" + operand + ")";
        if (operand.contains(".")) return "mk_dbl(" + operand + ")";
        return "mk_int(" + operand + ")";
    }

    // modo Val: el operando como entero C (para direcciones/registros)
    private static String intExpr(String operand) {
        if (isReg(operand)) return operand;
        if (isTemp(operand)) return "as_index(" + operand + ")";
        return operand;
    }

    private static String cmpOp(String op) {
        return switch (op) {
            case "==" -> "==";
            case "!=" -> "!=";
            case "<" -> "<";
            case "<=" -> "<=";
            case ">" -> ">";
            case ">=" -> ">=";
            default -> "!=";
        };
    }

    private static int parseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

}
