package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.Visibility;
import lombok.Getter;

@Getter
public class SymbolModifiers {
    private final Visibility visibility;
    private final boolean staticModifier;

    public SymbolModifiers(Visibility visibility, boolean staticModifier) {
        this.visibility = visibility;
        this.staticModifier = staticModifier;
    }

    public static SymbolModifiers defaults() {
        return new SymbolModifiers(Visibility.PUBLIC, false);
    }
}