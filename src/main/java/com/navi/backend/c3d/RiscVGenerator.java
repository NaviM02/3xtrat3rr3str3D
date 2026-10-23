package com.navi.backend.c3d;

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

// Traduce las cuartetas finales a ensamblador RISC-V RV32, usando
// el Frame Pointer (s0/fp) con offsets negativos.
//
// 1 celda C3D = 1 palabra = 4 bytes.
// Convencion de marco:
//   -4(fp)  -> ra
//   -8(fp)  -> s0 previo
//   -12(fp) -> primer dato (parametro 0, celda BP+0)
// Prologo: addi sp,sp,-FRAME; sw ra,FRAME-4(sp); sw s0,FRAME-8(sp); addi s0,sp,FRAME
// Epilogo: lw ra,FRAME-4(sp); lw s0,FRAME-8(sp); addi sp,sp,FRAME; ret
//
// globales en .data (_globals); heap en .data (_heap) con puntero _HP;
// argumentos en a0..a7, retorno en a0; ecall estilo MARS/RARS: numero en a7 y
// argumento en a0 (1 int, 4 string, 11 char, 5 read int, 10 exit). RARS soporta
// lectura por stdin, que Venus no. .string admite UTF-8 (acentos) en RARS.
//
// Nota: si se quiere ejecutar como ELF de Linux RV64 haria falta otra variante
// (sd/ld de 8 bytes, syscalls 64/63/93 y .option norelax); ver AGENTS.md.
public class RiscVGenerator {

    private static final int WORD = 4;
    private static final int HEADER = 8;      // ra + s0 previo (para el tamano del marco)
    private static final int DATA_START = 12; // bytes bajo fp del primer dato
    private static final int HEAP_WORDS = 262144;

    private final List<Quad> quads;
    private final int explicitGlobalSize;
    private final SemanticContext context;

    private final Set<String> functionNames = new LinkedHashSet<>();
    private final Map<String, String> stringLabels = new LinkedHashMap<>();
    private final Set<String> stringTemps = new LinkedHashSet<>();
    private final Set<String> charTemps = new LinkedHashSet<>();
    private final Map<String, Boolean> addressTemps = new LinkedHashMap<>();
    private final Map<String, Type> callReturnTypes = new LinkedHashMap<>();
    private final Map<String, List<Type>> callParamTypes = new LinkedHashMap<>();
    private final Set<String> receiverCalls = new LinkedHashSet<>();
    private final Map<String, Quad> defOf = new LinkedHashMap<>();
    private final Map<Quad, String> readBuffers = new IdentityHashMap<>();

    public RiscVGenerator(List<Quad> quads) {
        this(quads, -1, null);
    }

    public RiscVGenerator(List<Quad> quads, int globalSize) {
        this(quads, globalSize, null);
    }

    // context permite tipar el valor de un print (p.ej. un metodo que retorna String)
    public RiscVGenerator(List<Quad> quads, int globalSize, SemanticContext context) {
        this.quads = quads;
        this.explicitGlobalSize = globalSize;
        this.context = context;
    }

    // una funcion del C3D: su marco, parametros y cuerpo
    private static final class RFunc {
        final String name;
        final int frameCells;
        final List<Quad> body;
        final Map<String, Integer> tempSlots = new LinkedHashMap<>();
        int paramCount;
        int tempCount;
        int frameBytes;

        RFunc(String name, int frameCells, List<Quad> body) {
            this.name = name;
            this.frameCells = frameCells;
            this.body = body;
        }
    }

    public String generate() {
        classifyAddresses();
        collectStrings();
        collectReadBuffers();
        buildDefs();
        buildCallReturnTypes();
        List<RFunc> funcs = splitFunctions();
        for (RFunc f : funcs) functionNames.add(f.name);
        assignParamCounts(funcs);
        assignTemps(funcs);

        for (Quad q : quads) {
            if ("+".equals(q.getOp()) && q.getType() != null && q.getType().isString()) {
                needsConcat = true;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        emitData(sb);
        emitText(sb, funcs);
        return sb.toString();
    }

    private boolean needsConcat = false;
    private boolean needsReadString = false;

    // ---------------------------------------------------------------- analisis

    // marca que temporales guardan direcciones (celdas) vs valores; se usa para
    // escalar por 4 el offset de una suma de direcciones. La base de una suma de
    // direcciones siempre es el primer operando (así lo emite el C3D).
    private void classifyAddresses() {
        addressTemps.put("BP", true);
        addressTemps.put("GP", true);
        addressTemps.put("HP", true);

        // seed: toda direccion usada directamente en un load/store
        for (Quad q : quads) {
            if (isLoadStore(q.getOp())) {
                String addr = q.getArgs().get(0);
                if (isTemp(addr)) addressTemps.put(addr, true);
            }
        }

        // fixpoint: propagar direccion a traves de = y + (la base de un + address)
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Quad q : quads) {
                String res = q.getResult();
                if (!isTemp(res)) continue;
                boolean addr;
                switch (q.getOp()) {
                    case "=" -> addr = isAddress(q.getArgs().get(0));
                    case "+" -> addr = isAddress(q.getArgs().get(0)) || isAddress(q.getArgs().get(1));
                    case "stack_load", "heap_load" -> addr = isBaseOfAddressAdd(res);
                    default -> addr = false;
                }
                if (addr && !Boolean.TRUE.equals(addressTemps.get(res))) {
                    addressTemps.put(res, true);
                    changed = true;
                }
            }
        }
    }

    private boolean isBaseOfAddressAdd(String res) {
        return isBaseOfAddressAdd(res, new LinkedHashSet<>());
    }

    private boolean isBaseOfAddressAdd(String res, Set<String> seen) {
        if (res == null || !seen.add(res)) return false;
        for (Quad q : quads) {
            if (!"+".equals(q.getOp()) || !res.equals(q.getArgs().get(0))) continue;
            String result = q.getResult();
            if (isAddress(result) || isDirectAddress(result)
                    || isBaseOfAddressAdd(result, seen)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDirectAddress(String temp) {
        for (Quad q : quads) {
            if (isLoadStore(q.getOp()) && !q.getArgs().isEmpty() && temp.equals(q.getArgs().get(0))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLoadStore(String op) {
        return "stack_load".equals(op) || "heap_load".equals(op)
                || "stack_store".equals(op) || "heap_store".equals(op);
    }

    // cuarteta que define cada temporal (para seguir el tipo de un print)
    private void buildDefs() {
        for (Quad q : quads) {
            if (isTemp(q.getResult())) defOf.put(q.getResult(), q);
        }
    }

    // tipo de retorno de cada funcion/metodo/constructor a partir de su etiqueta C3D
    private void buildCallReturnTypes() {
        if (context == null) return;
        for (AggregateType agg : context.getTypeTable().getTypes().values()) {
            if (!agg.isClass()) continue;
            for (List<Symbol> overloads : agg.getMemberScope().getSymbols().values()) {
                for (Symbol s : overloads) {
                    if (s.getSignature() == null) continue;
                    String label;
                    if (s.getKind() == SymbolKind.METHOD) {
                        label = C3DEmitter.methodLabel(agg.getName(), s.getName(),
                                s.getSignature().getParameters());
                    } else if (s.getKind() == SymbolKind.CONSTRUCTOR) {
                        label = C3DEmitter.ctorLabel(agg.getName(), s.getSignature().getParameters());
                    } else {
                        continue;
                    }
                    callReturnTypes.put(label, s.getSignature().getReturnType());
                    callParamTypes.put(label, s.getSignature().getParameters());
                    receiverCalls.add(label);
                }
            }
        }
        for (List<Symbol> overloads : context.getSymbolTable().getGlobalScope().getSymbols().values()) {
            for (Symbol s : overloads) {
                if (s.getKind() == SymbolKind.FUNCTION && s.getSignature() != null) {
                    callReturnTypes.put(s.getName(), s.getSignature().getReturnType());
                    callParamTypes.put(s.getName(), s.getSignature().getParameters());
                }
            }
        }
    }

    // tipo del valor de un temporal siguiendo copias y llamadas
    private Type typeOfTemp(String temp) {
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

    private Type typeOfPlace(String place) {
        if (place == null) return null;
        if (isTemp(place)) {
            Type t = typeOfTemp(place);
            if (t != null) return t;
        }
        if (isString(place)) return Type.STRING;
        if (isChar(place)) return Type.CHAR;
        if (isDoubleLiteral(place)) return Type.DOUBLE;
        if ("null".equals(place)) return Type.NULL;
        return null;
    }

    private static boolean isDoubleLiteral(String s) {
        if (s == null || isString(s) || isChar(s)) return false;
        if (!s.contains(".")) return false;
        try {
            Double.parseDouble(s.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int floatBits(String literal) {
        return Float.floatToRawIntBits((float) Double.parseDouble(literal.trim()));
    }

    private void collectStrings() {
        int i = 0;
        for (Quad q : quads) {
            if (!"=".equals(q.getOp()) || !isTemp(q.getResult())) continue;
            String arg = q.getArgs().get(0);
            if (isString(arg)) {
                if (!stringLabels.containsKey(arg)) stringLabels.put(arg, ".Lstr" + i++);
                stringTemps.add(q.getResult());
            } else if (isChar(arg)) {
                charTemps.add(q.getResult());
            }
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

    private List<RFunc> splitFunctions() {
        List<RFunc> funcs = new ArrayList<>();
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
                funcs.add(new RFunc(name, size, body));
            } else {
                i++;
            }
        }
        return funcs;
    }

    private void assignParamCounts(List<RFunc> funcs) {
        Map<String, Integer> byName = new LinkedHashMap<>();
        for (RFunc f : funcs) byName.put(f.name, 0);
        for (Quad q : quads) {
            if ("call".equals(q.getOp()) && !q.getArgs().isEmpty()) {
                String fn = q.getArgs().get(0);
                int argc = q.getArgs().size() - 1;
                Integer cur = byName.get(fn);
                if (cur != null && argc > cur) byName.put(fn, argc);
            }
        }
        for (RFunc f : funcs) f.paramCount = byName.getOrDefault(f.name, 0);
    }

    private void assignTemps(List<RFunc> funcs) {
        for (RFunc f : funcs) {
            Set<String> temps = new LinkedHashSet<>();
            for (Quad q : f.body) collectTemps(q, temps);
            int idx = f.frameCells;
            for (String t : temps) f.tempSlots.put(t, dataOff(idx++));
            f.tempCount = temps.size();
            f.frameBytes = align16(HEADER + (f.frameCells + f.tempCount) * WORD);
        }
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

    // ---------------------------------------------------------------- emision

    private void emitData(StringBuilder sb) {
        sb.append(".data\n");
        sb.append(".align 2\n");
        // Venus no soporta .zero; .space reserva los mismos bytes
        sb.append("_globals: .space ").append(Math.max(globalSize() * WORD, WORD)).append("\n");
        sb.append("_heap:    .space ").append(HEAP_WORDS * WORD).append("\n");
        sb.append("_HP:      .word 0\n");
        for (Map.Entry<String, String> e : stringLabels.entrySet()) {
            sb.append(e.getValue()).append(": .string ").append(e.getKey()).append("\n");
        }
        for (String buffer : readBuffers.values()) {
            sb.append(buffer).append(": .space 256\n");
        }
        if (needsConcat) sb.append(".Lnull: .string \"null\"\n");
        sb.append("\n");
    }

    private void emitText(StringBuilder sb, List<RFunc> funcs) {
        sb.append(".text\n");
        sb.append(".globl _start\n");
        sb.append("_start:\n");
        sb.append("    la t0, _heap\n");
        sb.append("    la t1, _HP\n");
        sb.append("    sw t0, 0(t1)\n");
        if (functionNames.contains("globals_init")) sb.append("    jal ra, globals_init\n");
        if (functionNames.contains("main")) sb.append("    jal ra, main\n");
        sb.append("    li a7, 10\n");
        sb.append("    ecall\n\n");

        if (needsConcat) emitNumberToStringHelpers(sb);
        if (needsConcat) emitConcatHelper(sb);
        if (needsReadString) emitReadStringHelper(sb);
        for (RFunc f : funcs) emitFunction(sb, f);
    }

    private void emitNumberToStringHelpers(StringBuilder sb) {
        sb.append("# a0 = entero -> a0 = cadena en el heap\n");
        sb.append("_itoa:\n");
        sb.append("    la t0, _HP\n");
        sb.append("    lw t1, 0(t0)\n");
        sb.append("    mv t2, t1\n");
        sb.append("    mv t3, a0\n");
        sb.append("    bge t3, zero, _itoa_pos\n");
        sb.append("    li t4, 45\n");
        sb.append("    sb t4, 0(t2)\n");
        sb.append("    addi t2, t2, 1\n");
        sb.append("    sub t3, zero, t3\n");
        sb.append("_itoa_pos:\n");
        sb.append("    mv t4, t2\n");
        sb.append("    bne t3, zero, _itoa_loop\n");
        sb.append("    li t5, 48\n");
        sb.append("    sb t5, 0(t2)\n");
        sb.append("    addi t2, t2, 1\n");
        sb.append("    j _itoa_enddigits\n");
        sb.append("_itoa_loop:\n");
        sb.append("    li t5, 10\n");
        sb.append("    rem t6, t3, t5\n");
        sb.append("    div t3, t3, t5\n");
        sb.append("    addi t6, t6, 48\n");
        sb.append("    sb t6, 0(t2)\n");
        sb.append("    addi t2, t2, 1\n");
        sb.append("    bne t3, zero, _itoa_loop\n");
        sb.append("_itoa_enddigits:\n");
        sb.append("    mv t5, t2\n");
        sb.append("    addi t2, t2, -1\n");
        sb.append("_itoa_rev:\n");
        sb.append("    bge t4, t2, _itoa_done\n");
        sb.append("    lb t6, 0(t4)\n");
        sb.append("    lb a1, 0(t2)\n");
        sb.append("    sb a1, 0(t4)\n");
        sb.append("    sb t6, 0(t2)\n");
        sb.append("    addi t4, t4, 1\n");
        sb.append("    addi t2, t2, -1\n");
        sb.append("    j _itoa_rev\n");
        sb.append("_itoa_done:\n");
        sb.append("    sb zero, 0(t5)\n");
        sb.append("    addi t5, t5, 1\n");
        sb.append("    la t0, _HP\n");
        sb.append("    sw t5, 0(t0)\n");
        sb.append("    mv a0, t1\n");
        sb.append("    ret\n\n");

        sb.append("# a0 = caracter -> a0 = cadena en el heap\n");
        sb.append("_ctoa:\n");
        sb.append("    la t0, _HP\n");
        sb.append("    lw t1, 0(t0)\n");
        sb.append("    sb a0, 0(t1)\n");
        sb.append("    sb zero, 1(t1)\n");
        sb.append("    addi t2, t1, 2\n");
        sb.append("    sw t2, 0(t0)\n");
        sb.append("    mv a0, t1\n");
        sb.append("    ret\n\n");

        sb.append("# a0 = bits de float -> a0 = cadena en el heap\n");
        sb.append("_ftoa:\n");
        sb.append("    addi sp, sp, -16\n");
        sb.append("    sw ra, 12(sp)\n");
        sb.append("    sw s0, 8(sp)\n");
        sb.append("    sw s1, 4(sp)\n");
        sb.append("    mv s0, a0\n");
        sb.append("    bge s0, zero, _ftoa_abs\n");
        sb.append("    li t0, 45\n");
        sb.append("    la t1, _HP\n");
        sb.append("    lw t2, 0(t1)\n");
        sb.append("    sb t0, 0(t2)\n");
        sb.append("    addi t2, t2, 1\n");
        sb.append("    sw t2, 0(t1)\n");
        sb.append("    li t0, 0x7fffffff\n");
        sb.append("    and s0, s0, t0\n");
        sb.append("_ftoa_abs:\n");
        sb.append("    fmv.w.x f0, s0\n");
        sb.append("    fcvt.w.s a0, f0\n");
        sb.append("    jal ra, _itoa\n");
        sb.append("    mv s1, a0\n");
        sb.append("    fmv.w.x f0, s0\n");
        sb.append("    fcvt.w.s t0, f0\n");
        sb.append("    fcvt.s.w f1, t0\n");
        sb.append("    fsub.s f2, f0, f1\n");
        sb.append("    li t0, 10000\n");
        sb.append("    fcvt.s.w f3, t0\n");
        sb.append("    fmul.s f2, f2, f3\n");
        sb.append("    fcvt.w.s t3, f2\n");
        sb.append("    ble t3, zero, _ftoa_nofrac\n");
        sb.append("    li t0, 10000\n");
        sb.append("    blt t3, t0, _ftoa_frac\n");
        sb.append("    li t3, 9999\n");
        sb.append("_ftoa_frac:\n");
        sb.append("    la t0, _HP\n");
        sb.append("    lw t1, 0(t0)\n");
        sb.append("    addi t1, t1, -1\n");
        sb.append("    li t2, 46\n");
        sb.append("    sb t2, 0(t1)\n");
        sb.append("    addi t1, t1, 1\n");
        sb.append("    mv t4, t1\n");
        sb.append("    li t5, 1000\n");
        sb.append("_ftoa_digits:\n");
        sb.append("    div t6, t3, t5\n");
        sb.append("    addi t6, t6, 48\n");
        sb.append("    sb t6, 0(t1)\n");
        sb.append("    addi t1, t1, 1\n");
        sb.append("    rem t3, t3, t5\n");
        sb.append("    li t2, 10\n");
        sb.append("    div t5, t5, t2\n");
        sb.append("    bne t5, zero, _ftoa_digits\n");
        sb.append("_ftoa_trim:\n");
        sb.append("    addi t1, t1, -1\n");
        sb.append("    ble t1, t4, _ftoa_trim_keep\n");
        sb.append("    lb t2, 0(t1)\n");
        sb.append("    li t6, 48\n");
        sb.append("    bne t2, t6, _ftoa_trim_keep\n");
        sb.append("    j _ftoa_trim\n");
        sb.append("_ftoa_trim_keep:\n");
        sb.append("    addi t1, t1, 1\n");
        sb.append("    sb zero, 0(t1)\n");
        sb.append("    addi t1, t1, 1\n");
        sb.append("    la t0, _HP\n");
        sb.append("    sw t1, 0(t0)\n");
        sb.append("_ftoa_nofrac:\n");
        sb.append("    mv a0, s1\n");
        sb.append("    lw ra, 12(sp)\n");
        sb.append("    lw s0, 8(sp)\n");
        sb.append("    lw s1, 4(sp)\n");
        sb.append("    addi sp, sp, 16\n");
        sb.append("    ret\n\n");
    }

    private void emitReadStringHelper(StringBuilder sb) {
        sb.append("# a0 = buffer, a1 = maximo de caracteres -> a0 = buffer sin salto de linea\n");
        sb.append("_read_string:\n");
        sb.append("    li a7, 8\n");
        sb.append("    ecall\n");
        sb.append("    mv t0, a0\n");
        sb.append("_rs_scan:\n");
        sb.append("    lb t1, 0(t0)\n");
        sb.append("    beq t1, zero, _rs_scan_end\n");
        sb.append("    addi t0, t0, 1\n");
        sb.append("    j _rs_scan\n");
        sb.append("_rs_scan_end:\n");
        sb.append("    beq t0, a0, _rs_done\n");
        sb.append("    addi t0, t0, -1\n");
        sb.append("    lb t1, 0(t0)\n");
        sb.append("    li t2, 10\n");
        sb.append("    bne t1, t2, _rs_done\n");
        sb.append("    sb zero, 0(t0)\n");
        sb.append("_rs_done:\n");
        sb.append("    ret\n\n");
    }

    // concatenacion de dos cadenas: a0 = izq, a1 = der -> a0 = nueva cadena.
    // Reserva en el heap (_HP) y copia ambos textos + terminador.
    private void emitConcatHelper(StringBuilder sb) {
        sb.append("# a0 = izq, a1 = der -> a0 = nueva cadena\n");
        sb.append("_concat:\n");
        sb.append("    mv t0, a0\n");
        sb.append("    mv t1, a1\n");
        sb.append("    li t2, 0\n");
        sb.append("_cc_len1:\n");
        sb.append("    add t5, t0, t2\n");
        sb.append("    lb t4, 0(t5)\n");
        sb.append("    beq t4, zero, _cc_len1d\n");
        sb.append("    addi t2, t2, 1\n");
        sb.append("    j _cc_len1\n");
        sb.append("_cc_len1d:\n");
        sb.append("    li t3, 0\n");
        sb.append("_cc_len2:\n");
        sb.append("    add t5, t1, t3\n");
        sb.append("    lb t4, 0(t5)\n");
        sb.append("    beq t4, zero, _cc_len2d\n");
        sb.append("    addi t3, t3, 1\n");
        sb.append("    j _cc_len2\n");
        sb.append("_cc_len2d:\n");
        sb.append("    add t4, t2, t3\n");
        sb.append("    addi t4, t4, 1\n");
        sb.append("    la t5, _HP\n");
        sb.append("    lw t6, 0(t5)\n");
        sb.append("    add t4, t6, t4\n");
        sb.append("    sw t4, 0(t5)\n");
        sb.append("    li t4, 0\n");
        sb.append("_cc_cp1:\n");
        sb.append("    beq t4, t2, _cc_cp1d\n");
        sb.append("    add t5, t0, t4\n");
        sb.append("    lb a2, 0(t5)\n");
        sb.append("    add t5, t6, t4\n");
        sb.append("    sb a2, 0(t5)\n");
        sb.append("    addi t4, t4, 1\n");
        sb.append("    j _cc_cp1\n");
        sb.append("_cc_cp1d:\n");
        sb.append("    li t4, 0\n");
        sb.append("_cc_cp2:\n");
        sb.append("    beq t4, t3, _cc_cp2d\n");
        sb.append("    add t5, t1, t4\n");
        sb.append("    lb a2, 0(t5)\n");
        sb.append("    add t5, t6, t2\n");
        sb.append("    add t5, t5, t4\n");
        sb.append("    sb a2, 0(t5)\n");
        sb.append("    addi t4, t4, 1\n");
        sb.append("    j _cc_cp2\n");
        sb.append("_cc_cp2d:\n");
        sb.append("    add t5, t6, t2\n");
        sb.append("    add t5, t5, t3\n");
        sb.append("    sb zero, 0(t5)\n");
        sb.append("    mv a0, t6\n");
        sb.append("    ret\n\n");
    }

    private void emitFunction(StringBuilder sb, RFunc f) {
        sb.append(f.name).append(":\n");
        sb.append("    addi sp, sp, -").append(f.frameBytes).append("\n");
        sb.append("    sw ra, ").append(f.frameBytes - 4).append("(sp)\n");
        sb.append("    sw s0, ").append(f.frameBytes - 8).append("(sp)\n");
        sb.append("    addi s0, sp, ").append(f.frameBytes).append("\n");
        for (int k = 0; k < f.paramCount; k++) {
            sb.append("    sw a").append(k).append(", ").append(dataOff(k)).append("(s0)\n");
        }

        for (Quad q : f.body) emitQuad(sb, q, f);

        sb.append(f.name).append("_epilogo:\n");
        sb.append("    lw ra, ").append(f.frameBytes - 4).append("(sp)\n");
        sb.append("    lw s0, ").append(f.frameBytes - 8).append("(sp)\n");
        sb.append("    addi sp, sp, ").append(f.frameBytes).append("\n");
        sb.append("    ret\n\n");
    }

    private void emitQuad(StringBuilder sb, Quad q, RFunc f) {
        switch (q.getOp()) {
            case "label" -> {
                String name = q.getArgs().get(0);
                if (!functionNames.contains(name)) sb.append(name).append(":\n");
            }
            case "goto" -> sb.append("    j ").append(q.getArgs().get(0)).append("\n");
            case "leave" -> { /* el epilogo restaura ra/s0/sp */ }
            case "=" -> emitAssign(sb, q, f);
            case "neg" -> {
                load(sb, "t0", q.getArgs().get(0), f);
                if (q.getType() != null && q.getType().isDouble()) {
                    sb.append("    li t1, 0x80000000\n");
                    sb.append("    xor t0, t0, t1\n");
                } else {
                    sb.append("    sub t0, zero, t0\n");
                }
                store(sb, q.getResult(), "t0", f);
            }
            case "itof" -> {
                load(sb, "t0", q.getArgs().get(0), f);
                sb.append("    fcvt.s.w f0, t0\n");
                sb.append("    fmv.x.w t0, f0\n");
                store(sb, q.getResult(), "t0", f);
            }
            case "ftoi" -> {
                load(sb, "t0", q.getArgs().get(0), f);
                sb.append("    fmv.w.x f0, t0\n");
                sb.append("    fcvt.w.s t0, f0\n");
                store(sb, q.getResult(), "t0", f);
            }
            case "+", "-", "*", "/", "%" -> emitArith(sb, q, f);
            case "if" -> emitIf(sb, q, f);
            case "stack_load", "heap_load" -> {
                load(sb, "t0", q.getArgs().get(0), f);
                sb.append("    lw t0, 0(t0)\n");
                store(sb, q.getResult(), "t0", f);
            }
            case "stack_store", "heap_store" -> {
                load(sb, "t0", q.getArgs().get(0), f);
                load(sb, "t1", q.getResult(), f);
                sb.append("    sw t1, 0(t0)\n");
            }
            case "call" -> emitCall(sb, q, f);
            case "print" -> emitPrint(sb, q, f);
            case "read" -> emitRead(sb, q, f);
            case "return" -> {
                if (!q.getArgs().isEmpty()) {
                    String value = q.getArgs().get(0);
                    Type rt = callReturnTypes.get(f.name);
                    Type vt = typeOfPlace(value);
                    load(sb, "a0", value, f);
                    if (rt != null && rt.isDouble() && (vt == null || !vt.isDouble())) {
                        sb.append("    fcvt.s.w f0, a0\n");
                        sb.append("    fmv.x.w a0, f0\n");
                    } else if (rt != null && rt.isInt() && vt != null && vt.isDouble()) {
                        sb.append("    fmv.w.x f0, a0\n");
                        sb.append("    fcvt.w.s a0, f0\n");
                    }
                }
                sb.append("    j ").append(f.name).append("_epilogo\n");
            }
            case "halt" -> {
                sb.append("    li a7, 10\n");
                sb.append("    ecall\n");
            }
            default -> sb.append("    # op sin soporte: ").append(q.getOp()).append("\n");
        }
    }

    private void emitAssign(StringBuilder sb, Quad q, RFunc f) {
        String arg = q.getArgs().get(0);
        if ("HP".equals(arg)) {
            sb.append("    la t0, _HP\n");
            sb.append("    lw t0, 0(t0)\n");
            sb.append("    addi t0, t0, 3\n");
            sb.append("    li t1, -4\n");
            sb.append("    and t0, t0, t1\n");
            sb.append("    la t1, _HP\n");
            sb.append("    sw t0, 0(t1)\n");
            store(sb, q.getResult(), "t0", f);
            return;
        }
        load(sb, "t0", arg, f);
        store(sb, q.getResult(), "t0", f);
    }

    private void emitArith(StringBuilder sb, Quad q, RFunc f) {
        String op = q.getOp();
        String res = q.getResult();
        String a = q.getArgs().get(0);
        String b = q.getArgs().get(1);

        if ("+".equals(op) && "HP".equals(res)) {
            sb.append("    la t0, _HP\n");
            sb.append("    lw t1, 0(t0)\n");
            if (isLiteral(b)) {
                sb.append("    addi t1, t1, ").append(WORD * parseInt(b)).append("\n");
            } else {
                load(sb, "t2", b, f);
                sb.append("    slli t2, t2, 2\n");
                sb.append("    add t1, t1, t2\n");
            }
            sb.append("    sw t1, 0(t0)\n");
            return;
        }

        if ("+".equals(op) && isAddress(res)) {
            emitAddressAdd(sb, res, a, b, f);
            return;
        }

        if ("+".equals(op) && q.getType() != null && q.getType().isString()) {
            loadAsString(sb, "a0", a, f);
            sb.append("    mv a2, a0\n");
            loadAsString(sb, "a0", b, f);
            sb.append("    mv a1, a0\n");
            sb.append("    mv a0, a2\n");
            sb.append("    jal ra, _concat\n");
            store(sb, res, "a0", f);
            return;
        }

        if (isDoubleArith(q, a, b)) {
            emitFloatArith(sb, q, f);
            return;
        }

        load(sb, "t0", a, f);
        load(sb, "t1", b, f);
        String insn = switch (op) {
            case "+" -> "add";
            case "-" -> "sub";
            case "*" -> "mul";
            case "/" -> "div";
            case "%" -> "rem";
            default -> "add";
        };
        sb.append("    ").append(insn).append(" t0, t0, t1\n");
        store(sb, res, "t0", f);
    }

    private boolean isDoubleArith(Quad q, String a, String b) {
        if ("+".equals(q.getOp()) && isAddress(q.getResult())) return false;
        if (q.getType() != null) return q.getType().isDouble();
        Type ta = typeOfPlace(a);
        Type tb = typeOfPlace(b);
        return (ta != null && ta.isDouble()) || (tb != null && tb.isDouble());
    }

    private void emitFloatArith(StringBuilder sb, Quad q, RFunc f) {
        String op = q.getOp();
        String a = q.getArgs().get(0);
        String b = q.getArgs().get(1);
        loadFloat(sb, "f0", a, f);
        loadFloat(sb, "f1", b, f);
        if ("%".equals(op)) {
            sb.append("    fcvt.w.s t0, f0\n");
            sb.append("    fcvt.w.s t1, f1\n");
            sb.append("    rem t0, t0, t1\n");
            sb.append("    fcvt.s.w f2, t0\n");
        } else {
            String insn = switch (op) {
                case "+" -> "fadd.s";
                case "-" -> "fsub.s";
                case "*" -> "fmul.s";
                case "/" -> "fdiv.s";
                default -> "fadd.s";
            };
            sb.append("    ").append(insn).append(" f2, f0, f1\n");
        }
        sb.append("    fmv.x.w t0, f2\n");
        store(sb, q.getResult(), "t0", f);
    }

    private void loadFloat(StringBuilder sb, String freg, String operand, RFunc f) {
        load(sb, "t0", operand, f);
        Type t = typeOfPlace(operand);
        if (t != null && t.isDouble()) {
            sb.append("    fmv.w.x ").append(freg).append(", t0\n");
        } else {
            sb.append("    fcvt.s.w ").append(freg).append(", t0\n");
        }
    }

    private void loadAsString(StringBuilder sb, String reg, String operand, RFunc f) {
        Type t = typeOfPlace(operand);
        if (t != null && t.isString()) {
            load(sb, reg, operand, f);
            return;
        }
        if (t != null && t.isNull()) {
            sb.append("    la ").append(reg).append(", .Lnull\n");
            return;
        }
        load(sb, "a0", operand, f);
        if (t != null && t.isChar()) {
            sb.append("    jal ra, _ctoa\n");
        } else if (t != null && t.isDouble()) {
            sb.append("    jal ra, _ftoa\n");
        } else {
            sb.append("    jal ra, _itoa\n");
        }
        if (!"a0".equals(reg)) sb.append("    mv ").append(reg).append(", a0\n");
    }

    private void emitAddressAdd(StringBuilder sb, String res, String a, String b, RFunc f) {
        if ("BP".equals(a)) {
            sb.append("    addi t0, s0, ").append(dataOff(parseInt(b))).append("\n");
        } else if ("GP".equals(a)) {
            sb.append("    la t0, _globals\n");
            sb.append("    addi t0, t0, ").append(WORD * parseInt(b)).append("\n");
        } else if ("HP".equals(a)) {
            sb.append("    la t0, _HP\n");
            sb.append("    lw t0, 0(t0)\n");
            sb.append("    addi t0, t0, ").append(WORD * parseInt(b)).append("\n");
        } else {
            load(sb, "t0", a, f);
            if (isLiteral(b)) {
                sb.append("    addi t0, t0, ").append(WORD * parseInt(b)).append("\n");
            } else {
                load(sb, "t1", b, f);
                sb.append("    slli t1, t1, 2\n");
                sb.append("    add t0, t0, t1\n");
            }
        }
        store(sb, res, "t0", f);
    }

    private int branchLabel = 0;

    private void emitIf(StringBuilder sb, Quad q, RFunc f) {
        String a = q.getArgs().get(0);
        String op = q.getArgs().get(1);
        String b = q.getArgs().get(2);
        String label = q.getArgs().get(3);
        Type ta = typeOfPlace(a);
        Type tb = typeOfPlace(b);
        boolean useFloat = (ta != null && ta.isDouble()) || (tb != null && tb.isDouble());
        String skip = "__br" + (branchLabel++);
        if (useFloat) {
            loadFloat(sb, "f0", a, f);
            loadFloat(sb, "f1", b, f);
            switch (op) {
                case "==" -> sb.append("    feq.s t2, f0, f1\n");
                case "!=" -> {
                    sb.append("    feq.s t2, f0, f1\n");
                    sb.append("    xori t2, t2, 1\n");
                }
                case "<" -> sb.append("    flt.s t2, f0, f1\n");
                case "<=" -> sb.append("    fle.s t2, f0, f1\n");
                case ">" -> sb.append("    flt.s t2, f1, f0\n");
                case ">=" -> sb.append("    fle.s t2, f1, f0\n");
                default -> {
                    sb.append("    # comparacion float sin soporte: ").append(op).append("\n");
                    return;
                }
            }
            sb.append("    beq t2, zero, ").append(skip).append("\n");
            sb.append("    j ").append(label).append("\n");
            sb.append(skip).append(":\n");
            return;
        }
        load(sb, "t0", a, f);
        load(sb, "t1", b, f);
        // las branches tienen alcance corto (12 bits): salto la branch INVERSA a una
        // etiqueta local y hago el salto lejano con `j` (alcance amplio).
        String inv = switch (op) {
            case "==" -> "bne t0, t1";
            case "!=" -> "beq t0, t1";
            case "<" -> "bge t0, t1";
            case ">=" -> "blt t0, t1";
            case ">" -> "bge t1, t0";
            case "<=" -> "blt t1, t0";
            default -> null;
        };
        if (inv == null) {
            sb.append("    # comparacion sin soporte: ").append(op).append("\n");
            return;
        }
        sb.append("    ").append(inv).append(", ").append(skip).append("\n");
        sb.append("    j ").append(label).append("\n");
        sb.append(skip).append(":\n");
    }

    private void emitCall(StringBuilder sb, Quad q, RFunc f) {
        List<String> args = q.getArgs();
        String func = args.get(0);
        List<Type> params = callParamTypes.get(func);
        boolean hasReceiver = receiverCalls.contains(func);
        for (int k = 1; k < args.size(); k++) {
            String arg = args.get(k);
            load(sb, "t0", arg, f);
            int pi = hasReceiver ? k - 2 : k - 1;
            Type p = params != null && pi >= 0 && pi < params.size() ? params.get(pi) : null;
            Type at = typeOfPlace(arg);
            if (p != null && p.isDouble() && (at == null || !at.isDouble())) {
                sb.append("    fcvt.s.w f0, t0\n");
                sb.append("    fmv.x.w t0, f0\n");
            } else if (p != null && p.isInt() && at != null && at.isDouble()) {
                sb.append("    fmv.w.x f0, t0\n");
                sb.append("    fcvt.w.s t0, f0\n");
            }
            sb.append("    mv a").append(k - 1).append(", t0\n");
        }
        sb.append("    jal ra, ").append(func).append("\n");
        if (q.getResult() != null) store(sb, q.getResult(), "a0", f);
    }

    private void emitPrint(StringBuilder sb, Quad q, RFunc f) {
        String val = q.getArgs().get(0);
        // MARS/RARS: numero de syscall en a7, argumento en a0
        load(sb, "a0", val, f);
        Type t = q.getType();
        if (t == null && isTemp(val)) t = typeOfTemp(val);
        if (t == null) t = typeOfPlace(val);
        if (t != null && t.isDouble()) {
            sb.append("    fmv.w.x f10, a0\n");
            sb.append("    li a7, 2\n");
        } else if ((t != null && t.isString()) || stringTemps.contains(val)) {
            sb.append("    li a7, 4\n");
        } else if ((t != null && t.isChar()) || charTemps.contains(val)) {
            sb.append("    li a7, 11\n");
        } else {
            sb.append("    li a7, 1\n");
        }
        sb.append("    ecall\n");
        sb.append("    li a7, 11\n");
        sb.append("    li a0, 10\n");
        sb.append("    ecall\n"); // salto de linea
    }

    private void emitRead(StringBuilder sb, Quad q, RFunc f) {
        Type t = q.getType() == null ? Type.INT : q.getType();
        if (t.isString()) {
            String buffer = readBuffers.get(q);
            if (buffer == null) buffer = ".Lbuf0";
            sb.append("    la a0, ").append(buffer).append("\n");
            sb.append("    li a1, 255\n");
            sb.append("    jal ra, _read_string\n");
            if (q.getResult() != null) store(sb, q.getResult(), "a0", f);
            return;
        }
        if (t.isDouble()) {
            sb.append("    li a7, 6\n");
            sb.append("    ecall\n");
            sb.append("    fmv.x.w a0, f10\n");
            if (q.getResult() != null) store(sb, q.getResult(), "a0", f);
            return;
        }
        if (t.isChar()) {
            sb.append("    li a7, 12\n");
            sb.append("    ecall\n");
            if (q.getResult() != null) store(sb, q.getResult(), "a0", f);
            return;
        }
        sb.append("    li a7, 5\n");
        sb.append("    ecall\n");
        if (q.getResult() != null) store(sb, q.getResult(), "a0", f);
    }

    // ---------------------------------------------------------------- helpers

    private void load(StringBuilder sb, String rd, String operand, RFunc f) {
        Integer off = f.tempSlots.get(operand);
        if (off != null) {
            sb.append("    lw ").append(rd).append(", ").append(off).append("(s0)\n");
            return;
        }
        if ("BP".equals(operand)) {
            sb.append("    addi ").append(rd).append(", s0, 0\n");
        } else if ("GP".equals(operand)) {
            sb.append("    la ").append(rd).append(", _globals\n");
        } else if ("HP".equals(operand)) {
            sb.append("    la ").append(rd).append(", _HP\n");
            sb.append("    lw ").append(rd).append(", 0(").append(rd).append(")\n");
        } else if (isString(operand)) {
            sb.append("    la ").append(rd).append(", ").append(stringLabels.get(operand)).append("\n");
        } else if (isDoubleLiteral(operand)) {
            sb.append("    li ").append(rd).append(", ").append(floatBits(operand)).append("\n");
        } else if (isChar(operand)) {
            sb.append("    li ").append(rd).append(", ").append(charValue(operand)).append("\n");
        } else if ("null".equals(operand)) {
            sb.append("    li ").append(rd).append(", 0\n");
        } else {
            sb.append("    li ").append(rd).append(", ").append(operand).append("\n");
        }
    }

    private void store(StringBuilder sb, String operand, String rs, RFunc f) {
        Integer off = f.tempSlots.get(operand);
        if (off != null) {
            sb.append("    sw ").append(rs).append(", ").append(off).append("(s0)\n");
        } else if ("HP".equals(operand)) {
            sb.append("    la t6, _HP\n");
            sb.append("    sw ").append(rs).append(", 0(t6)\n");
        } else {
            sb.append("    # store a ").append(operand).append(" sin soporte\n");
        }
    }

    private static int dataOff(int cell) {
        return -(DATA_START + cell * WORD);
    }

    private static int align16(int bytes) {
        return (bytes + 15) & ~15;
    }

    private boolean isAddress(String s) {
        return s != null && ("BP".equals(s) || "GP".equals(s) || "HP".equals(s)
                || Boolean.TRUE.equals(addressTemps.get(s)));
    }

    private static boolean isTemp(String s) {
        if (s == null || s.length() < 2 || s.charAt(0) != 't') return false;
        for (int i = 1; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    private static boolean isLiteral(String s) {
        if (s == null) return false;
        try {
            Integer.parseInt(s.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isString(String s) {
        return s != null && s.startsWith("\"") && s.endsWith("\"");
    }

    private static boolean isChar(String s) {
        return s != null && s.startsWith("'") && s.endsWith("'") && s.length() >= 3;
    }

    private static int charValue(String s) {
        return s.charAt(1);
    }

    private static int parseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    private static int parseInt(String s) {
        return parseInt(s, 0);
    }

    private void collectTemps(Quad q, Set<String> out) {
        if (isTemp(q.getResult())) out.add(q.getResult());
        for (String a : q.getArgs()) if (isTemp(a)) out.add(a);
    }
}
