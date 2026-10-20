package com.navi.backend.semantic.rules;

import com.navi.backend.semantic.model.Type;
import com.navi.backend.semantic.model.TypeTable;

// reglas de compatibilidad/promocion de tipos, compartidas por los tres checkers
// ensanchamiento numerico CHAR -> INT -> DOUBLE
public final class TypeCompat {

    private TypeCompat() {
    }

    public static boolean isNumeric(Type type) {
        return type != null && (type.isInt() || type.isDouble() || type.isChar());
    }

    public static boolean isLogical(Type type) {
        return type != null && type.isBoolean();
    }

    // tipo comun de una operacion numerica; null si no son compatibles
    public static Type promoteNumeric(Type a, Type b) {
        if (a == null || b == null) return null;
        if (!isNumeric(a) || !isNumeric(b)) return null;
        if (a.isDouble() || b.isDouble()) return Type.DOUBLE;
        if (a.isInt() || b.isInt()) return Type.INT;
        return Type.CHAR;
    }

    // se puede asignar value a target?
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

    public static boolean canAssign(Type target, Type value, TypeTable types) {
        if (canAssign(target, value)) return true;
        return target != null && value != null && target.isClass() && value.isClass()
                && types != null && types.isSubtype(value.getName(), target.getName());
    }

    // se pueden comparar estos dos tipos?
    public static boolean comparable(Type a, Type b) {
        if (a == null || b == null) return false;
        if (a.equals(b)) return true;
        if (a.isNull() && b.isClass()) return true;
        if (b.isNull() && a.isClass()) return true;
        return isNumeric(a) && isNumeric(b);
    }

    public static boolean comparable(Type a, Type b, TypeTable types) {
        if (comparable(a, b)) return true;
        return canAssign(a, b, types) || canAssign(b, a, types);
    }
}
