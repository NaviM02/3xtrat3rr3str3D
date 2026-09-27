package com.navi.backend.c3d;

import com.navi.backend.semantic.model.Type;
import com.navi.backend.semantic.enums.TypeKind;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// emisor de cuartetas: crea temporales t0.. y etiquetas L0..
// stack: cada funcion abre marco con enter y lo cierra con leave (junto al return)
// params y locales en stack[BP + off], offsets 0-based; globales en stack[GP + off]
// heap: HP reserva celdas (t = HP; HP = HP + size) y se accede con heap[base + off]
// comparaciones/logicos se materializan a 0/1 con saltos; ramas: if a op b goto L
// tras return/goto/halt se descartan cuartetas hasta la proxima etiqueta alcanzable
// render() asegura goto explicito al final de cada bloque y colapsa etiquetas vacias
public class C3DEmitter {

    // marco de activacion: nombres de params/locales a su offset
    private static final class Frame {
        final Map<String, Integer> slots = new LinkedHashMap<>();
        final Set<String> references = new HashSet<>(); // parametros por referencia (Y)
        final Map<String, List<Integer>> arrayDims = new LinkedHashMap<>(); // tamanos de arreglos locales/globales
        int next = 0; // siguiente offset libre (0-based)
        String label;    // etiqueta del marco (para parchear el enter)
        int enterIndex = -1;
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

    // ---- marcos ----

    // abre el marco y emite enter (el tamano se fija al cerrar)
    public void enterFrame(String label) {
        Frame f = new Frame();
        f.label = label;
        frames.push(f);
        emit(new Quad("enter", null, label));
        if (!quads.isEmpty() && "enter".equals(quads.get(quads.size() - 1).getOp())) {
            f.enterIndex = quads.size() - 1;
        }
    }

    // cierra el marco y fija el tamano del enter (el leave va junto al return)
    public void exitFrame() {
        if (!frames.isEmpty()) {
            Frame f = frames.pop();
            if (f.enterIndex >= 0) {
                quads.set(f.enterIndex, new Quad("enter", null, f.label, String.valueOf(Math.max(f.next, 0))));
            }
        }
    }

    // declara un parametro y devuelve su Pos_memory
    public int declareParam(String name) {
        return declareParam(name, false);
    }

    // declara un parametro; reference = el slot guarda una direccion (Y)
    public int declareParam(String name, boolean reference) {
        Frame f = frames.peek();
        int offset = f.next++;
        f.slots.put(name, offset);
        if (reference) f.references.add(name);
        return offset;
    }

    // true si el parametro se pasa por referencia
    public boolean isReference(String name) {
        return !frames.isEmpty() && frames.peek().references.contains(name);
    }

    // declara un local y devuelve su Pos_memory
    public int declareLocal(String name) {
        Frame f = frames.peek();
        int offset = f.next++;
        f.slots.put(name, offset);
        return offset;
    }

    // declara un arreglo local reservando celdas contiguas y guarda sus dimensiones
    public int declareLocalArray(String name, int cells, List<Integer> dims) {
        Frame f = frames.peek();
        int offset = f.next++;
        f.slots.put(name, offset);
        if (dims != null) f.arrayDims.put(name, dims);
        if (cells > 1) f.next += cells - 1;
        return offset;
    }

    // declara una global y devuelve su Pos_memory
    public int declareGlobal(String name) {
        int offset = globalFrame.next++;
        globalFrame.slots.put(name, offset);
        return offset;
    }

    // declara un arreglo global reservando celdas contiguas
    public int declareGlobalArray(String name, int cells, List<Integer> dims) {
        int offset = globalFrame.next++;
        globalFrame.slots.put(name, offset);
        if (dims != null) globalFrame.arrayDims.put(name, dims);
        if (cells > 1) globalFrame.next += cells - 1;
        return offset;
    }

    // reserva celdas extra en el marco (arreglos/structs en stack)
    public void reserve(int count) {
        if (count > 0 && !frames.isEmpty()) frames.peek().next += count;
    }

    // reserva celdas extra en el area global
    public void reserveGlobal(int count) {
        if (count > 0) globalFrame.next += count;
    }

    // dimensiones conocidas de un arreglo para aplanar indices
    public List<Integer> arrayDims(String name) {
        if (!frames.isEmpty()) {
            List<Integer> local = frames.peek().arrayDims.get(name);
            if (local != null) return local;
        }
        return globalFrame.arrayDims.get(name);
    }

    // celdas totales del area global
    public int globalSize() {
        return globalFrame.next;
    }

    public Integer localOffset(String name) {
        return frames.isEmpty() ? null : frames.peek().slots.get(name);
    }

    public Integer globalOffset(String name) {
        return globalFrame.slots.get(name);
    }

    // ---- stack ----

    // direccion de stack: t = base + offset
    public String stackAddr(String base, int offset) {
        return binary("+", base, String.valueOf(offset));
    }

    // direccion de un elemento de arreglo con indices aplanados (row-major)
    // si no se conocen dimensiones (param arreglo de Y) se usa solo el primer indice
    public String addressOffset(String base, List<Integer> dims, List<String> indices) {
        if (indices == null || indices.isEmpty()) return base;
        if (dims == null || dims.size() != indices.size()) {
            return binary("+", base, indices.get(0));
        }
        String acc = indices.get(0);
        for (int k = 1; k < indices.size(); k++) {
            acc = binary("+", binary("*", acc, String.valueOf(dims.get(k))), indices.get(k));
        }
        return binary("+", base, acc);
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

    // lee una variable (local en BP o global en GP)
    public String loadVar(String name) {
        Integer local = localOffset(name);
        if (local != null) return stackLoad("BP", local);
        Integer global = globalOffset(name);
        if (global != null) return stackLoad("GP", global);
        return name;
    }

    // escribe una variable (local en BP o global en GP)
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

    // direccion de la variable (para structs por valor)
    public String varAddr(String name) {
        Integer local = localOffset(name);
        if (local != null) return stackAddr("BP", local);
        Integer global = globalOffset(name);
        if (global != null) return stackAddr("GP", global);
        return name;
    }

    // ---- heap ----

    // reserva size celdas: t = HP; HP = HP + size. devuelve la direccion base
    public String heapAlloc(String size) {
        String t = newTemp();
        emit(new Quad("=", t, "HP"));
        emit(new Quad("+", "HP", "HP", size));
        return t;
    }

    // direccion de heap: t = base + offset
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

    // ---- expresiones (devuelven un lugar) ----

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

    // materializa una comparacion a un temporal 0/1 con saltos
    // if l op r goto Ltrue; goto Lfalse; Ltrue: t=1; goto Lend; Lfalse: t=0; Lend:
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

    // ---- statements (no devuelven lugar) ----

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

    // etiqueta de entrada; alcanzable aunque ningun salto la referencie
    public void entryLabel(String name) {
        targetedLabels.add(name);
        label(name);
    }

    // salto condicional: if left op right goto label
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

    // rehabilita la emision tras un corte de flujo
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

    // renderiza el C3D final: colapsa etiquetas vacias y asegura goto explicito
    public String render() {
        StringBuilder sb = new StringBuilder();
        for (Quad q : finalQuads()) {
            sb.append(q).append('\n');
        }
        return sb.toString();
    }

    // cuartetas finales (etiquetas colapsadas, saltos explicitos)
    public List<Quad> finalQuads() {
        return insertExplicitJumps(collapseEmptyLabels(quads));
    }

    // colapsa etiquetas vacias: si una label sigue a otra, los saltos se reubican
    // en la siguiente y la cuarteta se descarta (solo labels generadas L<n>)
    private static List<Quad> collapseEmptyLabels(List<Quad> source) {
        Map<String, String> alias = new LinkedHashMap<>();
        for (int i = 0; i + 1 < source.size(); i++) {
            Quad cur = source.get(i);
            Quad next = source.get(i + 1);
            if ("label".equals(cur.getOp()) && "label".equals(next.getOp())) {
                String dead = cur.getArgs().get(0);
                String kept = next.getArgs().get(0);
                if (!dead.equals(kept)) alias.put(dead, kept);
            }
        }
        if (alias.isEmpty()) return source;

        List<Quad> out = new ArrayList<>(source.size());
        for (Quad q : source) {
            String op = q.getOp();
            if ("label".equals(op)) {
                if (alias.containsKey(q.getArgs().get(0))) continue; // etiqueta vacía
                out.add(q);
            } else if ("goto".equals(op)) {
                String target = resolveAlias(alias, q.getArgs().get(0));
                out.add(target.equals(q.getArgs().get(0)) ? q : new Quad("goto", null, target));
            } else if ("if".equals(op)) {
                String target = resolveAlias(alias, q.getArgs().get(3));
                out.add(target.equals(q.getArgs().get(3)) ? q
                        : new Quad("if", null, q.getArgs().get(0), q.getArgs().get(1),
                                q.getArgs().get(2), target));
            } else {
                out.add(q);
            }
        }
        return out;
    }

    // sigue la cadena de alias (siempre hacia adelante, sin ciclos)
    private static String resolveAlias(Map<String, String> alias, String name) {
        String current = name;
        while (alias.containsKey(current)) current = alias.get(current);
        return current;
    }

    // no confia en el fall-through: si el bloque anterior no cierra con
    // goto/return/halt, inserta un goto a la etiqueta siguiente
    private static List<Quad> insertExplicitJumps(List<Quad> source) {
        List<Quad> out = new ArrayList<>(source.size());
        for (int i = 0; i < source.size(); i++) {
            Quad q = source.get(i);
            if ("label".equals(q.getOp()) && i > 0) {
                String prevOp = source.get(i - 1).getOp();
                if (!"goto".equals(prevOp) && !"return".equals(prevOp) && !"halt".equals(prevOp)) {
                    out.add(new Quad("goto", null, q.getArgs().get(0)));
                }
            }
            out.add(q);
        }
        return out;
    }

    // helpers de nombres

    // formatea un literal de Y/Z a texto C3D (los string/char de Z ya vienen entre comillas)
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

    // nombre C3D de un tipo (para desambiguar sobrecarga)
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
