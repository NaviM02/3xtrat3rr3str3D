package com.navi.backend.semantic.model;

import com.navi.backend.semantic.errors.SemanticErrors;
import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// registro de tipos agregados (struct de Y, class de Z) por nombre
// namespace plano: struct y class con el mismo nombre colisionan
@Getter
public class TypeTable {
    private final Map<String, AggregateType> types = new LinkedHashMap<>();
    private int nextClassId = 1;

    public boolean register(AggregateType type) {
        if (types.containsKey(type.getName())) return false;
        if (type.isClass()) type.setTypeId(nextClassId++);
        types.put(type.getName(), type);
        return true;
    }

    // Enlaza cada clase con su superclase (debe existir y ser clase) y luego
    // detecta ciclos en la cadena de herencia.
    public void resolveInheritance(SemanticErrors errors) {
        for (AggregateType type : types.values()) {
            if (!type.isClass() || type.getSuperClassName() == null) continue;
            AggregateType parent = resolve(type.getSuperClassName());
            if (parent == null || !parent.isClass()) {
                errors.report(type.getLine(), type.getColumn(),
                        "Superclase no definida: " + type.getSuperClassName());
            } else {
                type.setSuperClass(parent);
            }
        }

        for (AggregateType type : types.values()) {
            Set<AggregateType> path = new HashSet<>();
            AggregateType current = type;
            while (current != null) {
                if (!path.add(current)) {
                    errors.report(type.getLine(), type.getColumn(),
                            "Ciclo de herencia detectado para la clase " + type.getName());
                    type.setSuperClass(null);
                    break;
                }
                current = current.getSuperClass();
            }
        }
    }

    public AggregateType resolve(String name) {
        return types.get(name);
    }

    public boolean contains(String name) {
        return types.containsKey(name);
    }

    public boolean isSubtype(String childName, String parentName) {
        AggregateType child = resolve(childName);
        AggregateType parent = resolve(parentName);
        return child != null && parent != null && child.isSubtypeOf(parent);
    }
}
