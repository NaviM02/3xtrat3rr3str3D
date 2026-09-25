package com.navi.backend.c3d;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Traduce la lista final de {@link Quad cuartetas} a un único archivo C
 * autocontenido y compilable, con el patrón del ejemplo de clase: cuartetas →
 * sentencias C directas, temporales como variables C y runtime mínimo.
 *
 * <p>Si el programa solo maneja enteros/booleanos (sin cadenas, decimales,
 * caracteres, {@code null} ni {@code read}), se emite en <b>modo int</b> con
 * {@code long long} y operadores C nativos, idéntico en espíritu al ejemplo. En
 * caso contrario se usa un valor etiquetado {@code Val} y solo las ayudas que
 * las cuartetas realmente usan.
 *
 * <p>Convención de llamadas: el llamador coloca los argumentos en
 * {@code stack[SP + i]}; el {@code enter} fija {@code BP = SP} y reserva el
 * marco; el retorno viaja por {@code RET}.
 */
public class CGenerator {

    private final List<Quad> quads;
    private final Set<String> functionNames = new LinkedHashSet<>();
    private boolean intMode;

    public CGenerator(List<Quad> quads) {
        this.quads = quads;
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
            sb.append(runtimeWithVal());
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

    /** El programa es "solo enteros" si ninguna cuarteta usa cadenas/char/decimal/null/read. */
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

    // ---------------------------------------------------------------- partición

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
        int max = -1;
        for (Quad q : quads) {
            if ("+".equals(q.getOp()) && !q.getArgs().isEmpty() && "GP".equals(q.getArgs().get(0))) {
                int off = parseInt(q.getArgs().get(1), -1);
                if (off > max) max = off;
            }
        }
        return max + 1;
    }

    // ---------------------------------------------------------------- función

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
                            : "op('-', I(0), " + val(q.getArgs().get(0)) + ")")
                    .append(";\n");
            case "+", "-", "*", "/", "%" -> emitBinary(sb, q);
            case "if" -> emitIf(sb, q);
            case "stack_load" -> sb.append("    ").append(q.getResult()).append(" = stack[").append(loadAddr(q)).append("];\n");
            case "stack_store" -> sb.append("    stack[").append(loadAddr(q)).append("] = ").append(value(q.getResult())).append(";\n");
            case "heap_load" -> sb.append("    ").append(q.getResult()).append(" = heap[").append(loadAddr(q)).append("];\n");
            case "heap_store" -> sb.append("    heap[").append(loadAddr(q)).append("] = ").append(value(q.getResult())).append(";\n");
            case "call" -> emitCall(sb, q);
            case "print" -> sb.append("    ").append(intMode
                    ? "printf(\"%lld\", " + expr(q.getArgs().get(0)) + ")"
                    : "prn(" + val(q.getArgs().get(0)) + ")").append(";\n");
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

    /** Dirección de un acceso a memoria: en modo int es un valor, en modo Val hay que desenvolverlo. */
    private String loadAddr(Quad q) {
        return intMode ? expr(q.getArgs().get(0)) : "IDX(" + val(q.getArgs().get(0)) + ")";
    }

    /** Valor almacenado: en modo int es directo; en modo Val usa el valor. */
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

    /** Modo int: todo operando es una expresión entera C. */
    private static String expr(String operand) {
        if (isReg(operand) || isTemp(operand)) return operand;
        return operand; // literal numérico
    }

    /** Modo Val: operando como {@code Val}. */
    private static String val(String operand) {
        if (isReg(operand)) return "I(" + operand + ")";
        if (isTemp(operand)) return operand;
        if ("null".equals(operand)) return "NIL";
        if (operand.startsWith("\"")) return "S(" + operand + ")";
        if (operand.startsWith("'")) return "CH(" + operand + ")";
        if (operand.contains(".")) return "D(" + operand + ")";
        return "I(" + operand + ")";
    }

    /** Modo Val: operando como entero C (para direcciones/registros). */
    private static String intExpr(String operand) {
        if (isReg(operand)) return operand;
        if (isTemp(operand)) return "IDX(" + operand + ")";
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

    // ---------------------------------------------------------------- runtime C (modo Val)

    private String runtimeWithVal() {
        boolean useAdd = false, useOp = false, useCmp = false, usePrint = false, useRead = false;
        for (Quad q : quads) {
            switch (q.getOp()) {
                case "+" -> useAdd = true;
                case "-", "*", "/", "%", "neg" -> useOp = true;
                case "if" -> useCmp = true;
                case "print" -> usePrint = true;
                case "read" -> useRead = true;
                default -> { }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/* Generado desde las cuartetas C3D. Compilar: gcc -o programa programa.c */\n");
        sb.append("#include <stdio.h>\n");
        if (useAdd || useCmp || useRead) sb.append("#include <string.h>\n#include <stdlib.h>\n");
        sb.append('\n').append(VAL_TYPE);
        if (useAdd || useCmp) sb.append(AS_STR);
        if (useAdd) sb.append(ADD);
        if (useOp) sb.append(OP);
        if (useCmp) sb.append(CMP);
        if (usePrint) sb.append(PRINT);
        if (useRead) sb.append(READ);
        return sb.toString();
    }

    private static final String VAL_TYPE = """
            typedef struct { int t; union { long long i; double d; char c; char *s; } u; } Val;
            #define I(x)    ((Val){0,{.i=(x)}})
            #define D(x)    ((Val){1,{.d=(x)}})
            #define CH(x)   ((Val){2,{.c=(x)}})
            #define S(x)    ((Val){3,{.s=(x)}})
            #define NIL     ((Val){4,{.i=0}})
            #define IDX(v)  ((int)(v).u.i)
            #define AS_D(v) ((v).t==1 ? (v).u.d : (double)(v).u.i)

            static Val stack[262144];
            static Val heap[262144];
            static long long BP = 0, GP = 0, HP = 0, SP = 0;
            static Val RET;

            """;

    private static final String AS_STR = """
            static const char *as_str(Val v) {
                static char b[64];
                switch (v.t) {
                    case 3: return v.u.s;
                    case 2: snprintf(b, sizeof b, "%c", v.u.c); return b;
                    case 1: snprintf(b, sizeof b, "%g", v.u.d); return b;
                    case 4: return "null";
                    default: snprintf(b, sizeof b, "%lld", v.u.i); return b;
                }
            }

            """;

    private static final String ADD = """
            /* suma: numérica o concatenación de cadenas */
            static Val add(Val a, Val b) {
                if (a.t == 3 || b.t == 3) {
                    char *x = strdup(as_str(a));
                    const char *y = as_str(b);
                    char *r = (char *)malloc(strlen(x) + strlen(y) + 1);
                    strcpy(r, x); strcat(r, y); free(x);
                    return S(r);
                }
                if (a.t == 1 || b.t == 1) return D(AS_D(a) + AS_D(b));
                return I(a.u.i + b.u.i);
            }

            """;

    private static final String OP = """
            /* aritmética: '-' resta, '*' producto, '/' división, '%' módulo */
            static Val op(char k, Val a, Val b) {
                if (a.t == 1 || b.t == 1) {
                    double x = AS_D(a), y = AS_D(b);
                    switch (k) {
                        case '-': return D(x - y);
                        case '*': return D(x * y);
                        case '/': return D(x / y);
                        default:  return D((double)((long long)x % (long long)y));
                    }
                }
                switch (k) {
                    case '-': return I(a.u.i - b.u.i);
                    case '*': return I(a.u.i * b.u.i);
                    case '/': return I(a.u.i / b.u.i);
                    default:  return I(a.u.i % b.u.i);
                }
            }

            """;

    private static final String CMP = """
            /* comparación: -1 menor, 0 igual, 1 mayor */
            static int cmp(Val a, Val b) {
                if (a.t == 3 || b.t == 3) {
                    char *x = strdup(as_str(a)), *y = strdup(as_str(b));
                    int r = strcmp(x, y); free(x); free(y);
                    return r < 0 ? -1 : (r > 0 ? 1 : 0);
                }
                if (a.t == 4 || b.t == 4) return (a.t == 4 && b.t == 4) ? 0 : (a.t == 4 ? -1 : 1);
                double x = AS_D(a), y = AS_D(b);
                return x < y ? -1 : (x > y ? 1 : 0);
            }

            """;

    private static final String PRINT = """
            static void prn(Val v) {
                switch (v.t) {
                    case 3: fputs(v.u.s, stdout); break;
                    case 2: putchar(v.u.c); break;
                    case 1: printf("%g", v.u.d); break;
                    case 4: fputs("null", stdout); break;
                    default: printf("%lld", v.u.i); break;
                }
            }

            """;

    private static final String READ = """
            static Val rd(void) {
                static char b[4096];
                fflush(stdout);
                if (!fgets(b, sizeof b, stdin)) b[0] = '\\0';
                size_t n = strlen(b);
                while (n > 0 && (b[n-1] == '\\n' || b[n-1] == '\\r')) b[--n] = '\\0';
                char *e;
                long long i = strtoll(b, &e, 10);
                if (e != b && *e == '\\0') return I(i);
                double d = strtod(b, &e);
                if (e != b && *e == '\\0') return D(d);
                return S(b);
            }

            """;
}
