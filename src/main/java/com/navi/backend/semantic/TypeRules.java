package com.navi.backend.semantic;

/**
 * Reglas de tipo compartidas por los tres checkers semánticos (Lat/Y/Z).
 * Centraliza la validación de operadores/condiciones/retornos y la redacción de
 * los mensajes de error, de modo que los tres lenguajes se comporten igual.
 *
 * <p>No conoce ASTs: solo opera sobre {@link Type} ya resueltos y reporta a
 * {@link SemanticErrors}. Las reglas de compatibilidad en sí viven en
 * {@link TypeCompat}.</p>
 */
public final class TypeRules {

    private final SemanticContext context;

    public TypeRules(SemanticContext context) {
        this.context = context;
    }

    public void error(int line, int col, String msg) {
        context.getErrors().report(line, col, msg);
    }

    /** Reporta {@code msg} y devuelve {@link Type#ERROR} (para usar en expresiones). */
    public Type fail(int line, int col, String msg) {
        error(line, col, msg);
        return Type.ERROR;
    }

    // ---------------------------------------------------------------- operadores

    /** Condición de si/dientras/para: debe ser booleana. */
    public void requireBool(Type t, int line, int col) {
        if (!t.isBoolean()) error(line, col, "La condición debe ser booleana, se obtuvo " + t);
    }

    /** {@code &&} / {@code ||}: ambos operandos booleanos. */
    public Type boolOperands(Type l, Type r, int line, int col) {
        if (l.isBoolean() && r.isBoolean()) return Type.BOOLEAN;
        return fail(line, col, "El operador lógico requiere booleanos: " + l + " y " + r);
    }

    /** {@code ==} / {@code !=}. */
    public Type equality(Type l, Type r, int line, int col) {
        if (TypeCompat.comparable(l, r)) return Type.BOOLEAN;
        return fail(line, col, "Tipos no comparables: " + l + " y " + r);
    }

    /** {@code <} {@code <=} {@code >} {@code >=}: operandos numéricos. */
    public Type relational(Type l, Type r, int line, int col) {
        if (TypeCompat.isNumeric(l) && TypeCompat.isNumeric(r)) return Type.BOOLEAN;
        return fail(line, col, "La comparación requiere operandos numéricos");
    }

    /** {@code -} {@code *} {@code /} {@code %}. */
    public Type arithmetic(Type l, Type r, int line, int col) {
        Type promoted = TypeCompat.promoteNumeric(l, r);
        if (promoted == null) {
            return fail(line, col, "Operación aritmética con operandos no numéricos: " + l + " y " + r);
        }
        return promoted;
    }

    /** {@code +}: concatena si alguno es string, si no aritmética. */
    public Type addition(Type l, Type r, int line, int col) {
        if (l.isString() || r.isString()) return Type.STRING;
        return arithmetic(l, r, line, col);
    }

    /** {@code !}: requiere booleano. */
    public Type negation(Type t, int line, int col) {
        if (t.isBoolean()) return Type.BOOLEAN;
        return fail(line, col, "La negación ! requiere un booleano");
    }

    /** {@code -} / {@code +} unarios: requieren numérico. */
    public Type unaryNumeric(Type t, int line, int col) {
        if (TypeCompat.isNumeric(t)) return t;
        return fail(line, col, "El operador unario requiere un operando numérico");
    }

    /** {@code ++} / {@code --} (como expresión o sentencia): requieren numérico. */
    public Type incrementOperand(Type t, int line, int col) {
        if (TypeCompat.isNumeric(t)) return t;
        return fail(line, col, "++/-- requiere un operando numérico");
    }

    // ---------------------------------------------------------------- asignaciones

    /** Asignación simple. Devuelve true si es válida; si no, reporta el error. */
    public boolean assign(Type target, Type value, int line, int col) {
        if (TypeCompat.canAssign(target, value)) return true;
        error(line, col, "No se puede asignar " + value + " a " + target);
        return false;
    }

    /** Asignación compuesta ({@code +=}, {@code -=}, ...): operandos numéricos. */
    public boolean compoundAssign(Type target, Type value, int line, int col) {
        if (TypeCompat.isNumeric(target) && TypeCompat.isNumeric(value)) return true; // todo: ver si puede usarse string
        error(line, col, "La asignación compuesta requiere operandos numéricos");
        return false;
    }

    /** Elemento de un inicializador de arreglo contra el tipo base. */
    public void checkElement(Type base, Type actual, int line, int col) {
        if (!TypeCompat.canAssign(base, actual)) {
            error(line, col, "Elemento de arreglo " + actual + " no asignable a " + base);
        }
    }

    // ---------------------------------------------------------------- sentencias

    /**
     * Sentencia de retorno ({@code reddere}/{@code retornar}/{@code return}).
     *
     * @param declared     tipo de retorno de la función actual (null fuera de ella)
     * @param hasValue     la sentencia trae expresión
     * @param actual       tipo de esa expresión (si {@code hasValue})
     * @param voidReturnMsg mensaje cuando no se puede retornar valor
     *                       ("La función no retorna valor" / "El método no retorna valor")
     */
    public void checkReturn(Type declared, boolean hasValue, Type actual, int line, int col, String voidReturnMsg) {
        if (!hasValue) {
            if (declared != null && !declared.isVoid()) {
                error(line, col, "Se esperaba un valor de retorno de tipo " + declared);
            }
            return;
        }
        if (declared == null || declared.isVoid()) {
            error(line, col, voidReturnMsg);
        } else if (!TypeCompat.canAssign(declared, actual)) {
            error(line, col, "Tipo de retorno " + actual + " no asignable a " + declared);
        }
    }

    // ---------------------------------------------------------------- accesos

    /**
     * Tipo del elemento de un arreglo tras un único indexado. Conserva el rank:
     * {@code int[][]} -> {@code int[]} -> {@code int}. Devuelve {@link Type#ERROR}
     * si no es un arreglo.
     */
    public Type arrayElementType(Type arr) {
        if (arr == null || !arr.isArray()) return Type.ERROR;
        if (arr.getDimensions() > 1) return Type.array(arr.getElementType(), arr.getDimensions() - 1);
        return arr.getElementType();
    }

    /** Índice de arreglo: idx numérico y arr efectivamente un arreglo. Devuelve el tipo elemento. */
    public Type arrayElement(Type arr, Type idx, int line, int col) {
        if (!TypeCompat.isNumeric(idx)) {
            error(line, col, "El índice de un arreglo debe ser numérico");
        }
        if (arr.isArray()) return arrayElementType(arr);
        error(line, col, "Acceso por índice a un no-arreglo: " + arr);
        return Type.ERROR;
    }

    /** Campo de struct/clase. */
    public Type memberOf(Type owner, String member, int line, int col) {
        if (owner.isStruct() || owner.isClass()) {
            AggregateType agg = context.getTypeTable().resolve(owner.getName());
            Field field = agg == null ? null : agg.findField(member);
            if (field == null) {
                error(line, col, "Campo '" + member + "' no existe en " + owner);
                return Type.ERROR;
            }
            return field.getType();
        }
        error(line, col, "Acceso a miembro en un tipo no agregado: " + owner);
        return Type.ERROR;
    }

    /** Tipo de un literal de valor crudo (Y/Z). */
    public Type literalType(Object value) {
        if (value instanceof Integer) return Type.INT;
        if (value instanceof Double || value instanceof Float) return Type.DOUBLE;
        if (value instanceof Character) return Type.CHAR;
        if (value instanceof String) return Type.STRING;
        if (value instanceof Boolean) return Type.BOOLEAN;
        return Type.ERROR;
    }
}
