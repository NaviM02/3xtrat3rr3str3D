package com.navi.backend.semantic;

import com.navi.backend.semantic.model.Module;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ModuleRegistry {
    private final Map<String, Module> modules = new LinkedHashMap<>();

    public boolean register(Module module) {
        if (modules.containsKey(module.getName())) return false;
        modules.put(module.getName(), module);
        return true;
    }

    public Module resolve(String name) {
        return modules.get(name);
    }

    public boolean contains(String name) {
        return modules.containsKey(name);
    }
}