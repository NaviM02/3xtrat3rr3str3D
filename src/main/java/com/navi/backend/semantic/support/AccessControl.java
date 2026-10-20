package com.navi.backend.semantic.support;

import com.navi.backend.semantic.enums.AccessModifier;
import com.navi.backend.semantic.model.AggregateType;

// Reglas de visibilidad para miembros de clases Z (no hay paquetes):
//  - public:     accesible desde cualquier clase.
//  - private:    solo dentro de la misma clase.
//  - (nada):     sin paquetes, todas comparten el paquete por defecto: accesible desde cualquier clase.
//  - protected:  solo la propia clase y sus subclases.
public final class AccessControl {

    private AccessControl() {
    }

    public static boolean canAccess(AccessModifier modifier, AggregateType declaringClass, AggregateType accessingClass) {
        if (modifier == AccessModifier.PUBLIC) return true;
        if (declaringClass == null || accessingClass == null) return false;

        return switch (modifier) {
            case PUBLIC -> true;
            case PRIVATE -> declaringClass == accessingClass;
            case DEFAULT -> true;
            case PROTECTED -> accessingClass.isSubtypeOf(declaringClass);
        };
    }

    // Un método sobrescrito no puede reducir visibilidad (Java no lo permite).
    public static boolean isAtLeastAsVisible(AccessModifier candidate, AccessModifier overridden) {
        return rank(candidate) >= rank(overridden);
    }

    private static int rank(AccessModifier modifier) {
        return switch (modifier) {
            case PRIVATE -> 0;
            case DEFAULT -> 1;
            case PROTECTED -> 2;
            case PUBLIC -> 3;
        };
    }
}
