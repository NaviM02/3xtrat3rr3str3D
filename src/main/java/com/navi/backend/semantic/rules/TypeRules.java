package com.navi.backend.semantic.rules;

import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.errors.SemanticErrors;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Field;
import com.navi.backend.semantic.model.Type;

// reglas de tipo compartidas por Lat/Y/Z (operadores, condiciones, retornos)
// no conoce ASTs, solo tipos ya resueltos; la compatibilidad vive en TypeCompat
public final class TypeRules {

    private final SemanticContext context;

    public TypeRules(SemanticContext context) {
        this.context = context;
    }

    public void error(int line, int col, String msg) {
        context.getErrors().report(line, col, msg);
    }

    // reporta el error y devuelve ERROR (para expresiones)
    public Type fail(int line, int col, String msg) {
        error(line, col, msg);
        return Type.ERROR;
    }

    // ---------------------------------------------------------------- operadores

    // condicion de si/dientras/para: debe ser booleana
    public void requireBool(Type t, int line, int col) {
        if (!t.isBoolean()) error(line, col, "La condición debe ser booleana, se obtuvo " + t);
    }

    // && / || : ambos operandos booleanos
    public Type boolOperands(Type l, Type r, int line, int col) {
        if (l.isBoolean() && r.isBoolean()) return Type.BOOLEAN;
        return fail(line, col, "El operador lógico requiere booleanos: " + l + " y " + r);
    }

    // == / !=
    public Type equality(Type l, Type r, int line, int col) {
        if (TypeCompat.comparable(l, r)) return Type.BOOLEAN;
        return fail(line, col, "Tipos no comparables: " + l + " y " + r);
    }

    // < <= > >= : operandos numericos
    public Type relational(Type l, Type r, int line, int col) {
        if (TypeCompat.isNumeric(l) && TypeCompat.isNumeric(r)) return Type.BOOLEAN;
        return fail(line, col, "La comparación requiere operandos numéricos");
    }

    // - * / %
    public Type arithmetic(Type l, Type r, int line, int col) {
        Type promoted = TypeCompat.promoteNumeric(l, r);
        if (promoted == null) {
            return fail(line, col, "Operación aritmética con operandos no numéricos: " + l + " y " + r);
        }
        return promoted;
    }

    // + : concatena si hay string, si no aritmetica
    public Type addition(Type l, Type r, int line, int col) {
        if (l.isString() || r.isString()) return Type.STRING;
        return arithmetic(l, r, line, col);
    }

    // ! : requiere booleano
    public Type negation(Type t, int line, int col) {
        if (t.isBoolean()) return Type.BOOLEAN;
        return fail(line, col, "La negación ! requiere un booleano");
    }

    // - / + unarios: requieren numerico
    public Type unaryNumeric(Type t, int line, int col) {
        if (TypeCompat.isNumeric(t)) return t;
        return fail(line, col, "El operador unario requiere un operando numérico");
    }

    // ++ / -- : requieren numerico
    public Type incrementOperand(Type t, int line, int col) {
        if (TypeCompat.isNumeric(t)) return t;
        return fail(line, col, "++/-- requiere un operando numérico");
    }

    // ---------------------------------------------------------------- asignaciones

    // asignacion simple; true si es valida
    public boolean assign(Type target, Type value, int line, int col) {
        if (TypeCompat.canAssign(target, value)) return true;
        error(line, col, "No se puede asignar " + value + " a " + target);
        return false;
    }

    // asignacion compuesta (+=, -=, ...): operandos numericos
    public boolean compoundAssign(Type target, Type value, int line, int col) {
        if (TypeCompat.isNumeric(target) && TypeCompat.isNumeric(value)) return true; // todo: ver si puede usarse string
        error(line, col, "La asignación compuesta requiere operandos numéricos");
        return false;
    }

    // elemento de un inicializador de arreglo contra el tipo base
    public void checkElement(Type base, Type actual, int line, int col) {
        if (!TypeCompat.canAssign(base, actual)) {
            error(line, col, "Elemento de arreglo " + actual + " no asignable a " + base);
        }
    }

    // ---------------------------------------------------------------- sentencias

    // sentencia de retorno (reddere/retornar/return)
    // declared: tipo de retorno actual (null fuera de funcion); voidReturnMsg: mensaje si no admite valor
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

    // tipo tras un indexado, conserva el rank (int[][] -> int[] -> int); ERROR si no es arreglo
    public Type arrayElementType(Type arr) {
        if (arr == null || !arr.isArray()) return Type.ERROR;
        if (arr.getDimensions() > 1) return Type.array(arr.getElementType(), arr.getDimensions() - 1);
        return arr.getElementType();
    }

    // indice de arreglo: idx numerico y arr arreglo; devuelve el tipo elemento
    public Type arrayElement(Type arr, Type idx, int line, int col) {
        if (!TypeCompat.isNumeric(idx)) {
            error(line, col, "El índice de un arreglo debe ser numérico");
        }
        if (arr.isArray()) return arrayElementType(arr);
        error(line, col, "Acceso por índice a un no-arreglo: " + arr);
        return Type.ERROR;
    }

    // indice constante contra dimension conocida; solo aplica si ambos son constantes
    public void checkIndex(int dim, int index, int line, int col) {
        if (index < 0 || index >= dim) {
            error(line, col, "Índice " + index + " fuera de rango (dimensión " + dim + ")");
        }
    }

    // campo de struct/clase
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

    // tipo de un literal crudo (Y/Z)
    public Type literalType(Object value) {
        if (value instanceof Integer) return Type.INT;
        if (value instanceof Double || value instanceof Float) return Type.DOUBLE;
        if (value instanceof Character) return Type.CHAR;
        if (value instanceof String) return Type.STRING;
        if (value instanceof Boolean) return Type.BOOLEAN;
        return Type.ERROR;
    }
}
