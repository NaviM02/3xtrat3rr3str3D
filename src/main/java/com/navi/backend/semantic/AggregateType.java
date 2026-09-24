package com.navi.backend.semantic;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Tipo agregado: estructura (Y) o clase (Z). Es la unidad de layout que usa el
 * generador de C3D para acceso a miembros, literales de struct y {@code new}.
 *
 * <ul>
 *   <li>{@link #fields}: lista ordenada de campos (da offsets por índice).</li>
 *   <li>{@link #memberScope}: scope de miembros; en una clase guarda métodos y
 *       constructores (soportando sobrecarga). En una struct queda vacío.</li>
 * </ul>
 */
@Getter
public class AggregateType {
    private final String name;
    private final boolean isClass;
    private final List<Field> fields = new ArrayList<>();
    private final Scope memberScope;

    public AggregateType(String name, boolean isClass, Scope parentScope) {
        this.name = name;
        this.isClass = isClass;
        this.memberScope = new Scope(isClass ? ScopeKind.CLASS : ScopeKind.STRUCT, parentScope);
    }

    public boolean isStruct() {
        return !isClass;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public Field findField(String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) return field;
        }
        return null;
    }

    public Symbol findMethod(String name, List<Type> argTypes) {
        return resolveCallable(memberScope, name, argTypes);
    }

    public Symbol findConstructor(List<Type> argTypes) {
        return resolveCallable(memberScope, name, argTypes);
    }

    private Symbol resolveCallable(Scope scope, String name, List<Type> argTypes) {
        List<Symbol> candidates = scope.resolveOverloads(name);
        if (candidates == null) return null;
        for (Symbol symbol : candidates) {
            if (symbol.getSignature() != null && symbol.getSignature().matches(argTypes)) {
                return symbol;
            }
        }
        return null;
    }
}
