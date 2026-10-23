package com.navi.backend.semantic.support;

import com.navi.backend.semantic.enums.AccessModifier;
import com.navi.backend.semantic.model.AggregateType;

// Reglas de visibilidad para miembros de clases Z (no hay paquetes):
//  - public:     accesible desde cualquier clase.
//  - private:    solo dentro de la misma clase.
//  - (nada):     sin paquetes se comporta como private: solo dentro de la misma clase.
//  - protected:  solo la propia clase y sus subclases.
public final class AccessControl {

    private AccessControl() {
    }

    public static boolean canAccess(AccessModifier modifier, AggregateType declaringClass, AggregateType accessingClass) {
        if (modifier == AccessModifier.PUBLIC) return true;
        if (declaringClass == null) return false;
        // Llamada desde fuera de una clase (Lat/Y top-level): solo public.
        if (accessingClass == null) return false;

        return switch (modifier) {
            case PUBLIC -> true;
            case PRIVATE, DEFAULT -> declaringClass == accessingClass;
            case PROTECTED -> accessingClass.isSubtypeOf(declaringClass);
        };
    }

    // Un método sobrescrito no puede reducir visibilidad (Java no lo permite).
    public static boolean isAtLeastAsVisible(AccessModifier candidate, AccessModifier overridden) {
        return rank(candidate) >= rank(overridden);
    }

    private static int rank(AccessModifier modifier) {
        return switch (modifier) {
            case PRIVATE, DEFAULT -> 0;
            case PROTECTED -> 2;
            case PUBLIC -> 3;
        };
    }
}
