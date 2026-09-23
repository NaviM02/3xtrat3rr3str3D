package com.navi.backend.semantic;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Registro de tipos agregados (struct de Y y class de Z) por nombre canónico.
 * Namespace único y plano: una struct y una class con el mismo nombre colisionan.
 */
@Getter
public class TypeTable {
    private final Map<String, AggregateType> types = new LinkedHashMap<>();

    public boolean register(AggregateType type) {
        if (types.containsKey(type.getName())) return false;
        types.put(type.getName(), type);
        return true;
    }

    public AggregateType resolve(String name) {
        return types.get(name);
    }

    public boolean contains(String name) {
        return types.containsKey(name);
    }
}
