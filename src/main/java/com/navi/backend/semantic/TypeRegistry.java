package com.navi.backend.semantic;

import com.navi.backend.semantic.model.Type;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class TypeRegistry {
    private final Map<String, Type> types = new LinkedHashMap<>();

    public boolean register(Type type) {
        if (types.containsKey(type.getName())) return false;
        types.put(type.getName(), type);
        return true;
    }

    public Type resolve(String name) {
        return types.get(name);
    }

    public boolean contains(String name) {
        return types.containsKey(name);
    }
}