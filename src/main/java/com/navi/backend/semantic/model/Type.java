package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.TypeKind;
import lombok.Getter;

import java.util.Objects;

// tipo canonico, igual para Lat/Y/Z: C3D solo maneja un sistema de tipos
// INT/DOUBLE/CHAR/STRING/BOOLEAN/VOID son singletons; ARRAY lleva elementType y dimensions
// STRUCT/CLASS llevan name; ERROR sirve para seguir chequeando tras un tipo no resuelto
@Getter
public final class Type {

    public static final Type ERROR = new Type(TypeKind.ERROR, null, null, 0);
    public static final Type NULL = new Type(TypeKind.NULL, null, null, 0);
    public static final Type VOID = new Type(TypeKind.VOID, null, null, 0);
    public static final Type BOOLEAN = new Type(TypeKind.BOOLEAN, null, null, 0);
    public static final Type INT = new Type(TypeKind.INT, null, null, 0);
    public static final Type DOUBLE = new Type(TypeKind.DOUBLE, null, null, 0);
    public static final Type CHAR = new Type(TypeKind.CHAR, null, null, 0);
    public static final Type STRING = new Type(TypeKind.STRING, null, null, 0);

    private final TypeKind kind;
    private final String name;       // nombre de struct/class, null en el resto
    private final Type elementType;  // tipo base de un arreglo, null en el resto
    private final int dimensions;    // rank de un arreglo, 0 en el resto

    private Type(TypeKind kind, String name, Type elementType, int dimensions) {
        this.kind = kind;
        this.name = name;
        this.elementType = elementType;
        this.dimensions = dimensions;
    }

    public static Type array(Type elementType, int dimensions) {
        return new Type(TypeKind.ARRAY, null, elementType, dimensions);
    }

    public static Type struct(String name) {
        return new Type(TypeKind.STRUCT, name, null, 0);
    }

    public static Type classType(String name) {
        return new Type(TypeKind.CLASS, name, null, 0);
    }

    public boolean isError() {
        return kind == TypeKind.ERROR;
    }

    public boolean isNull() {
        return kind == TypeKind.NULL;
    }

    public boolean isVoid() {
        return kind == TypeKind.VOID;
    }

    public boolean isBoolean() {
        return kind == TypeKind.BOOLEAN;
    }

    public boolean isInt() {
        return kind == TypeKind.INT;
    }

    public boolean isDouble() {
        return kind == TypeKind.DOUBLE;
    }

    public boolean isChar() {
        return kind == TypeKind.CHAR;
    }

    public boolean isString() {
        return kind == TypeKind.STRING;
    }

    public boolean isArray() {
        return kind == TypeKind.ARRAY;
    }

    public boolean isStruct() {
        return kind == TypeKind.STRUCT;
    }

    public boolean isClass() {
        return kind == TypeKind.CLASS;
    }

    // primitivos simples (sin void/error/arreglo/agregado)
    public boolean isPrimitive() {
        return kind == TypeKind.BOOLEAN || kind == TypeKind.INT || kind == TypeKind.DOUBLE
                || kind == TypeKind.CHAR || kind == TypeKind.STRING;
    }

    // struct o class: tipo con layout (campos/miembros)
    public boolean isAggregate() {
        return kind == TypeKind.STRUCT || kind == TypeKind.CLASS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type other)) return false;
        if (kind != other.kind) return false;
        if (kind == TypeKind.ARRAY) {
            return dimensions == other.dimensions && Objects.equals(elementType, other.elementType);
        }
        if (kind == TypeKind.STRUCT || kind == TypeKind.CLASS) {
            return Objects.equals(name, other.name);
        }
        return true; // primitivos y void/error se distinguen por kind
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, name, elementType, dimensions);
    }

    @Override
    public String toString() {
        return switch (kind) {
            case ARRAY -> elementType + "[]".repeat(dimensions);
            case STRUCT -> "struct " + name;
            case CLASS -> "class " + name;
            default -> kind.name().toLowerCase();
        };
    }
}
