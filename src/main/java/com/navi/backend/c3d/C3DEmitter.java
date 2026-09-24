package com.navi.backend.c3d;

import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeKind;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Emisor de C3D alineado con los apuntes de clase. Acumula cuartetas y asigna
 * temporales ({@code t0, t1, ...}) y etiquetas ({@code L0, L1, ...}).
 *
 * <h2>Stack</h2>
 * Cada función/método/constructor abre un marco con {@code enter} y lo cierra
 * con {@code leave} (emitido junto a cada {@code return}). Los parámetros y
 * locales viven en el stack y se acceden con el Base Pointer:
 * <pre>
 *   t = BP + offset      # offset secuencial 0-based (params y luego locales)
 *   t2 = stack[t]        # lectura
 *   stack[t] = v         # escritura
 * </pre>
 * Las variables globales usan un área global direccionada por {@code GP}:
 * {@code t = GP + offset; t2 = stack[t]}.
 *
 * <h2>Heap</h2>
 * El heap se modela con el puntero {@code HP}: {@code new} reserva celdas
 * ({@code t = HP; HP = HP + size}) y los miembros/elementos se leen y escriben
 * con {@code t = base + off; ... heap[t]}.
 *
 * <h2>Booleanos y control</h2>
 * Las comparaciones/lógicos se materializan a 0/1 con saltos y las bifurcaciones
 * usan {@code if a op b goto L} (nunca un "if_not").
 *
 * <h2>Código inalcanzable</h2>
 * Tras {@code return}/{@code goto}/{@code halt} se descartan las cuartetas
 * siguientes hasta la próxima etiqueta alcanzable.
 */
public class C3DEmitter {

    /** Marco de activación: nombres de parámetros/locales a su offset. */
    private static final class Frame {
        final Map<String, Integer> slots = new LinkedHashMap<>();
        final Set<String> references = new HashSet<>(); // params por referencia (arreglos/structs de Y)
        int next = 0; // siguiente offset libre (0-based, params y luego locales)
    }

    private final List<Quad> quads = new ArrayList<>();
    private int tempCount = 0;
    private int labelCount = 0;
    private boolean unreachable = false;
    private final Set<String> targetedLabels = new HashSet<>();

    private final Frame globalFrame = new Frame();
    private final Deque<Frame> frames = new ArrayDeque<>();

    public String newTemp() {
        return "t" + tempCount++;
    }

    public String newLabel() {
        return "L" + labelCount++;
    }

    // ---- marcos de ejecución ----

    /** Abre un marco de activación y emite {@code enter}. */
    public void enterFrame(String label) {
        frames.push(new Frame());
        emit(new Quad("enter", null, label));
    }

    /** Cierra el marco actual (el {@code leave} se emite junto al return). */
    public void exitFrame() {
        if (!frames.isEmpty()) frames.pop();
    }

    /** Declara un parámetro en el marco actual y devuelve su Pos_memory. */
    public int declareParam(String name) {
        return declareParam(name, false);
    }

    /** Declara un parámetro; {@code reference} indica que el slot guarda una dirección (Y). */
    public int declareParam(String name, boolean reference) {
        Frame f = frames.peek();
        int offset = f.next++;
        f.slots.put(name, offset);
        if (reference) f.references.add(name);
        return offset;
    }

    /** True si el parámetro del marco actual se pasa por referencia. */
    public boolean isReference(String name) {
        return !frames.isEmpty() && frames.peek().references.contains(name);
    }

    /** Declara un local en el marco actual y devuelve su Pos_memory. */
    public int declareLocal(String name) {
        Frame f = frames.peek();
        int offset = f.next++;
        f.slots.put(name, offset);
        return offset;
    }

    /** Declara una variable global y devuelve su Pos_memory. */
    public int declareGlobal(String name) {
        int offset = globalFrame.next++;
        globalFrame.slots.put(name, offset);
        return offset;
    }

    /** Reserva {@code count} celdas adicionales en el marco actual (arreglos en stack). */
    public void reserve(int count) {
        if (count > 0 && !frames.isEmpty()) frames.peek().next += count;
    }

    public Integer localOffset(String name) {
        return frames.isEmpty() ? null : frames.peek().slots.get(name);
    }

    public Integer globalOffset(String name) {
        return globalFrame.slots.get(name);
    }

    // ---- stack ----

    /** Dirección de stack: {@code t = base + offset}. */
    public String stackAddr(String base, int offset) {
        return binary("+", base, String.valueOf(offset));
    }

    public String stackLoad(String base, int offset) {
        String addr = stackAddr(base, offset);
        String t = newTemp();
        emit(new Quad("stack_load", t, addr));
        return t;
    }

    public void stackStore(String base, int offset, String value) {
        String addr = stackAddr(base, offset);
        emit(new Quad("stack_store", value, addr));
    }

    public String stackLoadAt(String addr) {
        String t = newTemp();
        emit(new Quad("stack_load", t, addr));
        return t;
    }

    public void stackStoreAt(String addr, String value) {
        emit(new Quad("stack_store", value, addr));
    }

    /** Lee una variable (local/param en {@code BP} o global en {@code GP}). */
    public String loadVar(String name) {
        Integer local = localOffset(name);
        if (local != null) return stackLoad("BP", local);
        Integer global = globalOffset(name);
        if (global != null) return stackLoad("GP", global);
        return name;
    }

    /** Escribe una variable (local/param en {@code BP} o global en {@code GP}). */
    public void storeVar(String name, String value) {
        Integer local = localOffset(name);
        if (local != null) {
            stackStore("BP", local, value);
            return;
        }
        Integer global = globalOffset(name);
        if (global != null) {
            stackStore("GP", global, value);
            return;
        }
        assign(name, value);
    }

    /** Dirección de la variable (para structs por valor): {@code t = base + offset}. */
    public String varAddr(String name) {
        Integer local = localOffset(name);
        if (local != null) return stackAddr("BP", local);
        Integer global = globalOffset(name);
        if (global != null) return stackAddr("GP", global);
        return name;
    }

    // ---- heap ----

    /** Reserva {@code size} celdas: {@code t = HP; HP = HP + size}. Devuelve la dirección base. */
    public String heapAlloc(String size) {
        String t = newTemp();
        emit(new Quad("=", t, "HP"));
        emit(new Quad("+", "HP", "HP", size));
        return t;
    }

    /** Dirección de heap: {@code t = base + offset}. */
    public String heapAddr(String base, String offset) {
        return binary("+", base, offset);
    }

    public String heapLoad(String base, String offset) {
        String addr = heapAddr(base, offset);
        String t = newTemp();
        emit(new Quad("heap_load", t, addr));
        return t;
    }

    public void heapStore(String base, String offset, String value) {
        String addr = heapAddr(base, offset);
        emit(new Quad("heap_store", value, addr));
    }

    public String heapLoadAt(String addr) {
        String t = newTemp();
        emit(new Quad("heap_load", t, addr));
        return t;
    }

    public void heapStoreAt(String addr, String value) {
        emit(new Quad("heap_store", value, addr));
    }

    // ---- expresiones (producen un lugar) ----

    public String literal(String formatted) {
        String t = newTemp();
        emit(new Quad("=", t, formatted));
        return t;
    }

    public String binary(String op, String l, String r) {
        String t = newTemp();
        emit(new Quad(op, t, l, r));
        return t;
    }

    public String unaryNeg(String operand) {
        String t = newTemp();
        emit(new Quad("neg", t, operand));
        return t;
    }

    public String call(String function, List<String> args) {
        String t = newTemp();
        List<String> all = new ArrayList<>();
        all.add(function);
        all.addAll(args);
        emit(new Quad("call", t, all));
        return t;
    }

    /**
     * Materializa una comparación a un temporal 0/1 usando saltos (estilo apuntes):
     * <pre>
     *   if l op r goto Ltrue
     *   goto Lfalse
     *   Ltrue: t = 1; goto Lend
     *   Lfalse: t = 0
     *   Lend:
     * </pre>
     */
    public String materializeComparison(String l, String op, String r) {
        String t = newTemp();
        String lTrue = newLabel();
        String lFalse = newLabel();
        String lEnd = newLabel();
        ifGoto(l, op, r, lTrue);
        jump(lFalse);
        label(lTrue);
        assign(t, "1");
        jump(lEnd);
        label(lFalse);
        assign(t, "0");
        label(lEnd);
        return t;
    }

    // ---- statements (emiten sin producir lugar) ----

    public void assign(String target, String value) {
        emit(new Quad("=", target, value));
    }

    public void callVoid(String function, List<String> args) {
        List<String> all = new ArrayList<>();
        all.add(function);
        all.addAll(args);
        emit(new Quad("call", null, all));
    }

    public void label(String name) {
        emit(new Quad("label", null, name));
    }

    /** Etiqueta de entrada (función/método/main): alcanzable aunque ningún salto la referencie. */
    public void entryLabel(String name) {
        targetedLabels.add(name);
        label(name);
    }

    /** Salto condicional {@code if left op right goto label}. */
    public void ifGoto(String left, String op, String right, String label) {
        emit(new Quad("if", null, left, op, right, label));
    }

    public void jump(String label) {
        emit(new Quad("goto", null, label));
    }

    public void print(String value) {
        emit(new Quad("print", null, value));
    }

    public String read() {
        String t = newTemp();
        emit(new Quad("read", t));
        return t;
    }

    public void readDiscard() {
        emit(new Quad("read", null));
    }

    public void returnVoid() {
        leaveIfInFrame();
        emit(new Quad("return", null));
    }

    public void returnValue(String value) {
        leaveIfInFrame();
        emit(new Quad("return", null, value));
    }

    public void halt() {
        emit(new Quad("halt", null));
    }

    /** Vuelve a habilitar la emisión tras un corte de flujo (nueva sección alcanzable). */
    public void resume() {
        unreachable = false;
    }

    public void emit(Quad quad) {
        String op = quad.getOp();
        if ("label".equals(op)) {
            String name = quad.getArgs().get(0);
            if (unreachable && !targetedLabels.contains(name)) return; // etiqueta muerta
            quads.add(quad);
            unreachable = false;
            return;
        }
        if (unreachable) return;
        quads.add(quad);
        switch (op) {
            case "goto" -> {
                targetedLabels.add(quad.getArgs().get(0));
                unreachable = true;
            }
            case "if" -> targetedLabels.add(quad.getArgs().get(3));
            case "return", "halt" -> unreachable = true;
            default -> { }
        }
    }

    private void leaveIfInFrame() {
        if (!frames.isEmpty()) emit(new Quad("leave", null));
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        for (Quad q : quads) {
            sb.append(q).append('\n');
        }
        return sb.toString();
    }

    // ---- helpers estáticos de nombres ----

    /** Formatea un literal de Y/Z a texto C3D. Los string/char de Z ya vienen entrecomillados. */
    public static String formatLiteral(Object value) {
        if (value instanceof String s) {
            boolean quoted = s.length() >= 2
                    && ((s.startsWith("\"") && s.endsWith("\"")) || (s.startsWith("'") && s.endsWith("'")));
            return quoted ? s : "\"" + s + "\"";
        }
        if (value instanceof Character c) return "'" + c + "'";
        if (value instanceof Boolean b) return b ? "1" : "0";
        return String.valueOf(value);
    }

    /** Nombre C3D de un tipo semántico (para desambiguar sobrecarga). */
    public static String typeName(Type t) {
        if (t == null) return "void";
        TypeKind kind = t.getKind();
        return switch (kind) {
            case INT -> "int";
            case DOUBLE -> "double";
            case CHAR -> "char";
            case BOOLEAN -> "boolean";
            case STRING -> "string";
            case ARRAY -> "array";
            case STRUCT, CLASS -> t.getName();
            default -> kind.name().toLowerCase();
        };
    }

    public static String methodLabel(String owner, String name, List<Type> params) {
        StringBuilder sb = new StringBuilder(owner).append('_').append(name);
        for (Type p : params) sb.append('_').append(typeName(p));
        return sb.toString();
    }

    public static String ctorLabel(String owner, List<Type> params) {
        StringBuilder sb = new StringBuilder(owner).append("_init");
        for (Type p : params) sb.append('_').append(typeName(p));
        return sb.toString();
    }
}
