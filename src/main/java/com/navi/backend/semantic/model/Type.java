package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.TypeKind;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Type {
    private final TypeKind kind;
    private final String name;
    private final List<Integer> dimensions;
}