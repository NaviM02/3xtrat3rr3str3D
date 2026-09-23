package com.navi.backend.semantic;

/**
 * Reglas de compatibilidad/promoción de tipos (la "tabla de compatibilidad"
 * que exige el enunciado). Los tres checkers comparten esta única utilidad.
 *
 * <p>Regla básica implementada: ensanchamiento numérico {@code CHAR -> INT -> DOUBLE}.
 * El resto de reglas (concatenación con string, etc.) se agregan aquí según el
 * enunciado.</p>
 */
public final class TypeCompat {

    private TypeCompat() {
    }

    public static boolean isNumeric(Type type) {
        return type != null && (type.isInt() || type.isDouble() || type.isChar());
    }

    public static boolean isLogical(Type type) {
        return type != null && type.isBoolean();
    }

    /** Tipo común de una operación numérica binaria, o null si no es compatible. */
    public static Type promoteNumeric(Type a, Type b) {
        if (a == null || b == null) return null;
        if (!isNumeric(a) || !isNumeric(b)) return null;
        if (a.isDouble() || b.isDouble()) return Type.DOUBLE;
        if (a.isInt() || b.isInt()) return Type.INT;
        return Type.CHAR;
    }

    /** ¿Se puede asignar {@code value} a una variable de tipo {@code target}? */
    public static boolean canAssign(Type target, Type value) {
        if (target == null || value == null) return false;
        if (target.equals(value)) return true;
        if (value.isNull() && target.isClass()) return true;
        if (isNumeric(target) && isNumeric(value)) {
            Type promoted = promoteNumeric(target, value);
            return promoted != null && promoted.equals(target);
        }
        return false;
    }

    /** ¿Se pueden comparar (==, !=, <, >, ...) estos dos tipos? */
    public static boolean comparable(Type a, Type b) {
        if (a == null || b == null) return false;
        if (a.equals(b)) return true;
        if (a.isNull() && b.isClass()) return true;
        if (b.isNull() && a.isClass()) return true;
        return isNumeric(a) && isNumeric(b);
    }
}
