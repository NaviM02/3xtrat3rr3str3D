package com.navi.backend.c3d;

import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Traduccion directa de C3D: temporales tipados, memoria sin tags y saltos explicitos. */
public class CGenerator {
    private final List<Quad> quads;
    private final int explicitGlobalSize;
    private final Map<String, Type> types = new LinkedHashMap<>();
    private final Map<String, Type> returns = new LinkedHashMap<>();
    private final Map<String, List<Type>> parameters = new LinkedHashMap<>();
    private final Set<String> receivers = new LinkedHashSet<>();
    private final Map<String, List<Quad>> functions = new LinkedHashMap<>();
    private final Map<String, Integer> frames = new LinkedHashMap<>();
    private String currentFunction;

    public CGenerator(List<Quad> quads) {
        this(quads, -1, null);
    }

    public CGenerator(List<Quad> quads, int globalSize) {
        this(quads, globalSize, null);
    }

    public CGenerator(List<Quad> quads, int globalSize, SemanticContext context) {
        this.quads = quads;
        this.explicitGlobalSize = globalSize;
        if (context != null) collectSignatures(context);
        analyze();
    }

    private void collectSignatures(SemanticContext context) {
        for (AggregateType aggregate : context.getTypeTable().getTypes().values()) {
            if (!aggregate.isClass()) continue;
            for (List<Symbol> overloads : aggregate.getMemberScope().getSymbols().values()) {
                for (Symbol symbol : overloads) {
                    if (symbol.getSignature() == null) continue;
                    List<Type> params = symbol.getSignature().getParameters();
                    String label;
                    if (symbol.getKind() == SymbolKind.METHOD) {
                        label = C3DEmitter.methodLabel(aggregate.getName(), symbol.getName(), params);
                    } else if (symbol.getKind() == SymbolKind.CONSTRUCTOR) {
                        label = C3DEmitter.ctorLabel(aggregate.getName(), params);
                    } else continue;
                    returns.put(label, symbol.getSignature().getReturnType());
                    parameters.put(label, params);
                    receivers.add(label);
                }
            }
        }
        for (List<Symbol> overloads : context.getSymbolTable().getGlobalScope().getSymbols().values()) {
            for (Symbol symbol : overloads) {
                if (symbol.getKind() != SymbolKind.FUNCTION || symbol.getSignature() == null) continue;
                returns.put(symbol.getName(), symbol.getSignature().getReturnType());
                List<Type> params = new ArrayList<>();
                for (Type param : symbol.getSignature().getParameters()) {
                    params.add(param);
                    if (param.isArray()) for (int k = 1; k < param.getDimensions(); k++) params.add(Type.INT);
                }
                parameters.put(symbol.getName(), params);
            }
        }
    }

    private void analyze() {
        List<Quad> body = null;
        for (Quad q : quads) {
            if ("enter".equals(q.getOp())) {
                String name = q.getArgs().get(0);
                body = new ArrayList<>();
                functions.put(name, body);
                frames.put(name, q.getArgs().size() > 1 ? Integer.parseInt(q.getArgs().get(1)) : 0);
            } else if (body != null) body.add(q);
            if (definesTemp(q) && q.getType() != null) types.put(q.getResult(), q.getType());
        }
        // Las asignaciones de booleanos pueden definir el mismo temporal en dos ramas.
        boolean changed;
        do {
            changed = false;
            for (Quad q : quads) {
                if (!definesTemp(q) || types.containsKey(q.getResult())) continue;
                Type type = switch (q.getOp()) {
                    case "=" , "neg" -> typeOf(q.getArgs().get(0));
                    case "call" -> returns.get(q.getArgs().get(0));
                    case "itof" -> Type.DOUBLE;
                    case "ftoi" -> Type.INT;
                    case "+", "-", "*", "/", "%" -> {
                        Type a = typeOf(q.getArgs().get(0)), b = typeOf(q.getArgs().get(1));
                        yield a == null || b == null ? null : a.isDouble() || b.isDouble() ? Type.DOUBLE : Type.INT;
                    }
                    default -> null;
                };
                if (type != null) {
                    types.put(q.getResult(), type);
                    changed = true;
                }
            }
        } while (changed);
    }

    public String generate() {
        StringBuilder out = new StringBuilder("""
                /* Generado desde C3D. Compilar: gcc -std=c11 programa.c -lm -o programa */
                #include <stdio.h>
                #include <stdlib.h>
                #include <stdint.h>
                #include <inttypes.h>
                #include <string.h>
                #include <math.h>

                /* Una celda reserva ocho bytes; el tipo lo determina cada cuarteta.
                   memcpy permite cargas/almacenamientos tipados sin aliasing ni tags. */
                unsigned char stack[262144][8], heap[262144][8], RET[8];
                int64_t BP = 0, GP = 0, HP = 1, SP = 0;

                """);
        for (String name : functions.keySet()) out.append("void ").append(fn(name)).append("(void);\n");
        for (var entry : functions.entrySet()) emitFunction(out, entry.getKey(), entry.getValue());
        out.append("\nint main(void) {\n    SP = ").append(globalSize()).append(";\n");
        if (functions.containsKey("globals_init")) line(out, fn("globals_init") + "();");
        if (functions.containsKey("main")) line(out, fn("main") + "();");
        line(out, "return 0;");
        return out.append("}\n").toString();
    }

    private void emitFunction(StringBuilder out, String name, List<Quad> body) {
        currentFunction = name;
        out.append("\nvoid ").append(fn(name)).append("(void) {\n");
        Set<String> temps = new LinkedHashSet<>();
        for (Quad q : body) {
            if (isTemp(q.getResult())) temps.add(q.getResult());
            for (String arg : q.getArgs()) if (isTemp(arg)) temps.add(arg);
        }
        for (String temp : temps) line(out, cType(typeOf(temp)) + " " + temp + ";");
        if (body.stream().anyMatch(q -> "leave".equals(q.getOp()))) line(out, "int64_t oldBP = BP;");
        line(out, "BP = SP; SP += " + frames.get(name) + ";");
        for (Quad q : body) emitQuad(out, q);
        out.append("}\n");
    }

    private void emitQuad(StringBuilder out, Quad q) {
        List<String> a = q.getArgs();
        String r = q.getResult();
        switch (q.getOp()) {
            case "label" -> {
                if (!functions.containsKey(a.get(0))) out.append(a.get(0)).append(":;\n");
            }
            case "goto" -> line(out, "goto " + a.get(0) + ";");
            case "leave" -> line(out, "SP = BP; BP = oldBP;");
            case "=" -> line(out, r + " = " + expr(a.get(0), typeOf(r)) + ";");
            case "neg" -> line(out, r + " = -" + expr(a.get(0)) + ";");
            case "itof", "ftoi" -> line(out, r + " = (" + cType(typeOf(r)) + ") " + expr(a.get(0)) + ";");
            case "+", "-", "*", "/", "%" -> {
                if ("+".equals(q.getOp()) && isString(typeOf(r))) emitConcat(out, q);
                else if ("%".equals(q.getOp()) && isDouble(typeOf(r))) {
                    line(out, r + " = fmod(" + expr(a.get(0)) + ", " + expr(a.get(1)) + ");");
                } else {
                    Type type = typeOf(r);
                    line(out, r + " = " + expr(a.get(0), type) + " " + q.getOp() + " " + expr(a.get(1), type) + ";");
                }
            }
            case "if" -> {
                String left = expr(a.get(0)), right = expr(a.get(2));
                if (isString(typeOf(a.get(0))) && isString(typeOf(a.get(2)))) {
                    left = "strcmp(" + safeString(left) + ", " + safeString(right) + ")";
                    right = "0";
                }
                line(out, "if (" + left + " " + a.get(1) + " " + right + ") goto " + a.get(3) + ";");
            }
            case "stack_load", "heap_load" -> line(out, "memcpy(&" + r + ", "
                    + memory(q) + "[" + expr(a.get(0)) + "], sizeof " + r + ");");
            case "stack_store", "heap_store" -> store(out, memory(q) + "[" + expr(a.get(0)) + "]", r, typeOf(r));
            case "call" -> emitCall(out, q);
            case "print" -> {
                Type type = q.getType() != null ? q.getType() : typeOf(a.get(0));
                String value = expr(a.get(0));
                if (isString(type)) line(out, "printf(\"%s\", " + safeString(value) + ");");
                else if (isDouble(type)) line(out, "printf(\"%g\", (double) " + value + ");");
                else if (type != null && type.isChar()) line(out, "putchar((unsigned char) " + value + ");");
                else line(out, "printf(\"%\" PRId64, (int64_t) " + value + ");");
            }
            case "read" -> emitRead(out, q);
            case "return" -> {
                if (!a.isEmpty()) {
                    Type type = returns.getOrDefault(currentFunction, typeOf(a.get(0)));
                    store(out, "RET", a.get(0), type);
                }
                line(out, "return;");
            }
            case "halt" -> line(out, "exit(0);");
            default -> throw new IllegalArgumentException("Cuarteta C no soportada: " + q.getOp());
        }
    }

    private void emitCall(StringBuilder out, Quad q) {
        List<String> args = q.getArgs();
        String name = args.get(0);
        List<Type> params = parameters.getOrDefault(name, List.of());
        int receiver = receivers.contains(name) ? 1 : 0;
        for (int k = 1; k < args.size(); k++) {
            int p = k - 1 - receiver;
            Type type = p >= 0 && p < params.size() ? params.get(p) : typeOf(args.get(k));
            store(out, "stack[SP + " + (k - 1) + "]", args.get(k), type);
        }
        line(out, fn(name) + "();");
        if (q.getResult() != null) line(out, "memcpy(&" + q.getResult() + ", RET, sizeof " + q.getResult() + ");");
    }

    private void store(StringBuilder out, String destination, String value, Type type) {
        line(out, "{ " + cType(type) + " value = " + expr(value, type) + "; memcpy("
                + destination + ", &value, sizeof value); }");
    }

    private void emitConcat(StringBuilder out, Quad q) {
        String a = q.getArgs().get(0), b = q.getArgs().get(1), r = q.getResult();
        line(out, "{");
        String left = textOperand(out, a, "left"), right = textOperand(out, b, "right");
        line(out, "size_t size = strlen(" + left + ") + strlen(" + right + ") + 1;");
        line(out, r + " = malloc(size);");
        line(out, "if (!" + r + ") exit(1);");
        line(out, "snprintf(" + r + ", size, \"%s%s\", " + left + ", " + right + ");");
        line(out, "}");
    }

    private String textOperand(StringBuilder out, String value, String name) {
        Type type = typeOf(value);
        if (isString(type)) return safeString(expr(value));
        if (type != null && type.isNull()) return "\"null\"";
        line(out, "char " + name + "[64];");
        String format = isDouble(type) ? "\"%g\", (double) "
                : type != null && type.isChar() ? "\"%c\", (int) " : "\"%\" PRId64, (int64_t) ";
        line(out, "snprintf(" + name + ", sizeof " + name + ", " + format + expr(value) + ");");
        return name;
    }

    private void emitRead(StringBuilder out, Quad q) {
        Type type = q.getType() == null ? Type.INT : q.getType();
        String r = q.getResult();
        line(out, "{");
        line(out, "char input[256] = {0};");
        line(out, "if (fgets(input, sizeof input, stdin)) input[strcspn(input, \"\\r\\n\")] = 0;");
        if (r != null) {
            if (isString(type)) {
                line(out, r + " = malloc(strlen(input) + 1);");
                line(out, "if (!" + r + ") exit(1);");
                line(out, "strcpy(" + r + ", input);");
            } else line(out, r + " = " + (isDouble(type) ? "strtod(input, NULL)"
                    : type.isChar() ? "(unsigned char) input[0]" : "strtoll(input, NULL, 10)") + ";");
        }
        line(out, "}");
    }

    private Type typeOf(String value) {
        if (value == null) return null;
        if (types.containsKey(value)) return types.get(value);
        if (isTemp(value)) return null;
        if (value.startsWith("\"")) return Type.STRING;
        if (value.startsWith("'")) return Type.CHAR;
        if ("null".equals(value)) return Type.NULL;
        if (value.matches("-?(?:[0-9]+\\.[0-9]*|[0-9]*\\.[0-9]+)(?:[eE][+-]?[0-9]+)?")) return Type.DOUBLE;
        return Type.INT;
    }

    private String expr(String value) {
        return "null".equals(value) ? "0" : value;
    }

    private String expr(String value, Type target) {
        String result = expr(value);
        if (isString(target) && typeOf(value) != null && typeOf(value).isNull()) return "NULL";
        return "(" + cType(target) + ") " + result;
    }

    private int globalSize() {
        if (explicitGlobalSize >= 0) return explicitGlobalSize;
        int size = 0;
        for (Quad q : quads) {
            if ("+".equals(q.getOp()) && "GP".equals(q.getArgs().get(0))) {
                size = Math.max(size, Integer.parseInt(q.getArgs().get(1)) + 1);
            }
        }
        return size;
    }

    private String cType(Type type) {
        return isString(type) ? "char *" : isDouble(type) ? "double" : "int64_t";
    }

    private boolean isDouble(Type type) { return type != null && type.isDouble(); }
    private boolean isString(Type type) { return type != null && type.isString(); }
    private boolean isTemp(String value) { return value != null && value.matches("t[0-9]+"); }
    private boolean definesTemp(Quad q) {
        return isTemp(q.getResult()) && !"stack_store".equals(q.getOp()) && !"heap_store".equals(q.getOp());
    }
    private String memory(Quad q) { return q.getOp().startsWith("stack") ? "stack" : "heap"; }
    private String safeString(String value) {
        return value.startsWith("\"") ? value : "(" + value + " ? " + value + " : \"null\")";
    }
    private String fn(String name) { return "c3d_" + name; }
    private void line(StringBuilder out, String text) { out.append("    ").append(text).append('\n'); }
}
