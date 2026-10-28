package com.navi.backend.c3d.riscv;

import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.c3d.Quad;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Analiza cuartetas y prepara metadatos requeridos por la emisión RV32. */
final class RiscVProgramAnalysis {
    final List<Quad> quads;
    final int explicitGlobalSize;
    final SemanticContext context;
    final Set<String> functionNames = new LinkedHashSet<>();
    final Map<String, String> stringLabels = new LinkedHashMap<>();
    final Set<String> stringTemps = new LinkedHashSet<>();
    final Set<String> charTemps = new LinkedHashSet<>();
    final Map<String, Boolean> addressTemps = new LinkedHashMap<>();
    final Map<String, Type> callReturnTypes = new LinkedHashMap<>();
    final Map<String, List<Type>> callParamTypes = new LinkedHashMap<>();
    final Set<String> receiverCalls = new LinkedHashSet<>();
    final Map<String, Quad> defOf = new LinkedHashMap<>();
    final Map<Quad, String> readBuffers = new IdentityHashMap<>();
    final List<RiscVFunction> functions;
    boolean needsConcat;
    boolean needsReadString;

    RiscVProgramAnalysis(List<Quad> quads, int explicitGlobalSize, SemanticContext context) {
        this.quads = quads;
        this.explicitGlobalSize = explicitGlobalSize;
        this.context = context;

        buildCallReturnTypes();
        classifyAddresses();
        collectStrings();
        collectReadBuffers();
        buildDefs();
        functions = splitFunctions();
        for (RiscVFunction function : functions) functionNames.add(function.name);
        assignParamCounts();
        assignTemps();
        needsConcat = quads.stream().anyMatch(q -> "+".equals(q.getOp())
                && q.getType() != null && q.getType().isString());
    }

    int globalSize() {
        if (explicitGlobalSize >= 0) return explicitGlobalSize;
        int max = -1;
        for (Quad q : quads) {
            if ("+".equals(q.getOp()) && !q.getArgs().isEmpty() && "GP".equals(q.getArgs().get(0))) {
                max = Math.max(max, parseInt(q.getArgs().get(1), -1));
            }
        }
        return max + 1;
    }

    boolean isAddress(String value) {
        return value != null && ("BP".equals(value) || "GP".equals(value) || "HP".equals(value)
                || Boolean.TRUE.equals(addressTemps.get(value)));
    }

    Type typeOfTemp(String temp) {
        Quad def = defOf.get(temp);
        if (def == null) return null;
        if (def.getType() != null) return def.getType();
        if ("call".equals(def.getOp())) return callReturnTypes.get(def.getArgs().get(0));
        if ("=".equals(def.getOp())) {
            String arg = def.getArgs().get(0);
            if (isString(arg)) return Type.STRING;
            if (isChar(arg)) return Type.CHAR;
            if (isDoubleLiteral(arg)) return Type.DOUBLE;
            if (isTemp(arg)) return typeOfTemp(arg);
        }
        return null;
    }

    Type typeOfPlace(String place) {
        if (place == null) return null;
        if (isTemp(place)) {
            Type type = typeOfTemp(place);
            if (type != null) return type;
        }
        if (isString(place)) return Type.STRING;
        if (isChar(place)) return Type.CHAR;
        if (isDoubleLiteral(place)) return Type.DOUBLE;
        if ("null".equals(place)) return Type.NULL;
        return null;
    }

    private void classifyAddresses() {
        addressTemps.put("BP", true);
        addressTemps.put("GP", true);
        addressTemps.put("HP", true);
        for (Quad q : quads) {
            // Una direccion pasada por referencia puede no tener load/store en el llamador.
            if ("call".equals(q.getOp())) {
                String name = q.getArgs().get(0);
                List<Type> params = callParamTypes.get(name);
                int arg = receiverCalls.contains(name) ? 2 : 1;
                if (params != null) for (Type param : params) {
                    if (arg >= q.getArgs().size()) break;
                    if (param.isArray() || param.isStruct()) addressTemps.put(q.getArgs().get(arg), true);
                    arg++;
                }
            }
            if (isLoadStore(q.getOp())) {
                String addr = q.getArgs().get(0);
                if (isTemp(addr)) addressTemps.put(addr, true);
            }
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Quad q : quads) {
                String res = q.getResult();
                if (!isTemp(res)) continue;
                boolean addr = switch (q.getOp()) {
                    case "=" -> isAddress(q.getArgs().get(0));
                    case "+" -> isAddress(q.getArgs().get(0)) || isAddress(q.getArgs().get(1));
                    case "stack_load", "heap_load" -> isBaseOfAddressAdd(res, new LinkedHashSet<>());
                    default -> false;
                };
                if (addr && !Boolean.TRUE.equals(addressTemps.get(res))) {
                    addressTemps.put(res, true);
                    changed = true;
                }
            }
        }
    }

    private boolean isBaseOfAddressAdd(String res, Set<String> seen) {
        if (res == null || !seen.add(res)) return false;
        for (Quad q : quads) {
            if (!"+".equals(q.getOp()) || !res.equals(q.getArgs().get(0))) continue;
            String result = q.getResult();
            if (isAddress(result) || isDirectAddress(result) || isBaseOfAddressAdd(result, seen)) return true;
        }
        return false;
    }

    private boolean isDirectAddress(String temp) {
        for (Quad q : quads) {
            if (isLoadStore(q.getOp()) && !q.getArgs().isEmpty() && temp.equals(q.getArgs().get(0))) return true;
        }
        return false;
    }

    private static boolean isLoadStore(String op) {
        return "stack_load".equals(op) || "heap_load".equals(op)
                || "stack_store".equals(op) || "heap_store".equals(op);
    }

    private void buildDefs() {
        for (Quad q : quads) if (isTemp(q.getResult())) defOf.put(q.getResult(), q);
    }

    private void buildCallReturnTypes() {
        if (context == null) return;
        for (AggregateType agg : context.getTypeTable().getTypes().values()) {
            if (!agg.isClass()) continue;
            for (List<Symbol> overloads : agg.getMemberScope().getSymbols().values()) {
                for (Symbol symbol : overloads) {
                    if (symbol.getSignature() == null) continue;
                    String label;
                    if (symbol.getKind() == SymbolKind.METHOD) {
                        label = C3DEmitter.methodLabel(agg.getName(), symbol.getName(),
                                symbol.getSignature().getParameters());
                    } else if (symbol.getKind() == SymbolKind.CONSTRUCTOR) {
                        label = C3DEmitter.ctorLabel(agg.getName(), symbol.getSignature().getParameters());
                    } else continue;
                    callReturnTypes.put(label, symbol.getSignature().getReturnType());
                    callParamTypes.put(label, symbol.getSignature().getParameters());
                    receiverCalls.add(label);
                }
            }
        }
        for (List<Symbol> overloads : context.getSymbolTable().getGlobalScope().getSymbols().values()) {
            for (Symbol symbol : overloads) {
                if (symbol.getKind() == SymbolKind.FUNCTION && symbol.getSignature() != null) {
                    callReturnTypes.put(symbol.getName(), symbol.getSignature().getReturnType());
                    List<Type> params = new ArrayList<>();
                    for (Type param : symbol.getSignature().getParameters()) {
                        params.add(param);
                        // Y/Lat intercalan dimensiones ocultas tras cada parametro arreglo multi-D.
                        if (param.isArray()) for (int k = 1; k < param.getDimensions(); k++) params.add(Type.INT);
                    }
                    callParamTypes.put(symbol.getName(), params);
                }
            }
        }
    }

    private void collectStrings() {
        int i = 0;
        for (Quad q : quads) {
            if (!"=".equals(q.getOp()) || !isTemp(q.getResult())) continue;
            String arg = q.getArgs().get(0);
            if (isString(arg)) {
                if (!stringLabels.containsKey(arg)) stringLabels.put(arg, ".Lstr" + i++);
                stringTemps.add(q.getResult());
            } else if (isChar(arg)) charTemps.add(q.getResult());
        }
    }

    private void collectReadBuffers() {
        int i = 0;
        for (Quad q : quads) {
            if ("read".equals(q.getOp()) && q.getType() != null && q.getType().isString()) {
                readBuffers.put(q, ".Lbuf" + i++);
                needsReadString = true;
            }
        }
    }

    private List<RiscVFunction> splitFunctions() {
        List<RiscVFunction> result = new ArrayList<>();
        int i = 0;
        while (i < quads.size()) {
            Quad q = quads.get(i++);
            if (!"enter".equals(q.getOp())) continue;
            String name = q.getArgs().get(0);
            int size = q.getArgs().size() > 1 ? parseInt(q.getArgs().get(1), -1) : -1;
            List<Quad> body = new ArrayList<>();
            while (i < quads.size() && !"enter".equals(quads.get(i).getOp())) body.add(quads.get(i++));
            if (size < 0) size = frameSizeOf(body);
            result.add(new RiscVFunction(name, size, body));
        }
        return result;
    }

    private void assignParamCounts() {
        Map<String, Integer> byName = new LinkedHashMap<>();
        for (RiscVFunction function : functions) byName.put(function.name, 0);
        for (Quad q : quads) {
            if (!"call".equals(q.getOp()) || q.getArgs().isEmpty()) continue;
            String fn = q.getArgs().get(0);
            int argc = q.getArgs().size() - 1;
            Integer current = byName.get(fn);
            if (current != null && argc > current) byName.put(fn, argc);
        }
        for (RiscVFunction function : functions) function.paramCount = byName.getOrDefault(function.name, 0);
    }

    private void assignTemps() {
        for (RiscVFunction function : functions) {
            Set<String> temps = new LinkedHashSet<>();
            for (Quad q : function.body) {
                if (isTemp(q.getResult())) temps.add(q.getResult());
                for (String arg : q.getArgs()) if (isTemp(arg)) temps.add(arg);
            }
            int idx = function.frameCells;
            for (String temp : temps) function.tempSlots.put(temp, RiscVFrameLayout.dataOffset(idx++));
            function.tempCount = temps.size();
            function.frameBytes = RiscVFrameLayout.frameBytes(function.frameCells, function.tempCount);
        }
    }

    private int frameSizeOf(List<Quad> body) {
        int max = -1;
        for (Quad q : body) {
            if ("+".equals(q.getOp()) && !q.getArgs().isEmpty() && "BP".equals(q.getArgs().get(0))) {
                max = Math.max(max, parseInt(q.getArgs().get(1), -1));
            }
        }
        return max + 1;
    }

    private static boolean isTemp(String value) {
        if (value == null || value.length() < 2 || value.charAt(0) != 't') return false;
        for (int i = 1; i < value.length(); i++) if (!Character.isDigit(value.charAt(i))) return false;
        return true;
    }

    static boolean isDoubleLiteral(String value) {
        if (value == null || isString(value) || isChar(value) || !value.contains(".")) return false;
        try {
            Double.parseDouble(value.trim());
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    private static boolean isString(String value) {
        return value != null && value.startsWith("\"") && value.endsWith("\"");
    }

    private static boolean isChar(String value) {
        return value != null && value.startsWith("'") && value.endsWith("'") && value.length() >= 3;
    }

    private static int parseInt(String value, int fallback) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }
}
