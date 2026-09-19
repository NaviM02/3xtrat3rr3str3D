package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Module {
    private final String name;
    private final Language language;
    private final Scope scope;

    public Module(String name, Language language, Scope scope) {
        this.name = name;
        this.language = language;
        this.scope = scope;
    }
}