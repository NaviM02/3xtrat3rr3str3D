package com.navi.backend.semantic.model;

import com.navi.backend.semantic.enums.AccessModifier;
import com.navi.backend.semantic.enums.ScopeKind;
import com.navi.backend.semantic.support.AccessControl;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// tipo agregado: estructura (Y) o clase (Z); fields declarados conservan el orden del layout
@Getter
public class AggregateType {
    private final String name;
    private final boolean isClass;
    private final String superClassName;
    private final int line;
    private final int column;
    private final List<Field> fields = new ArrayList<>();
    private final Scope memberScope;
    @Setter
    private AggregateType superClass;
    @Setter
    private int typeId;

    public AggregateType(String name, boolean isClass, Scope parentScope) {
        this(name, isClass, parentScope, null, 0, 0);
    }

    public AggregateType(String name, boolean isClass, Scope parentScope,
                         String superClassName, int line, int column) {
        this.name = name;
        this.isClass = isClass;
        this.superClassName = superClassName;
        this.line = line;
        this.column = column;
        this.memberScope = new Scope(isClass ? ScopeKind.CLASS : ScopeKind.STRUCT, parentScope);
    }

    public boolean isStruct() {
        return !isClass;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public Field findDeclaredField(String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) return field;
        }
        return null;
    }

    public Field findField(String name) {
        Field declared = findDeclaredField(name);
        if (declared != null) return declared;
        return superClass == null ? null : superClass.findField(name);
    }

    // offset de un campo dentro del payload; los objetos Z reservan la celda 0 para su tag
    public int fieldOffset(String name) {
        Field declared = findDeclaredField(name);
        if (declared == null) return superClass == null ? 0 : superClass.fieldOffset(name);

        int offset = superClass == null ? 0 : superClass.fieldStorageSize();
        for (Field field : fields) {
            if (field.getName().equals(name)) return offset;
            offset += cellSize(field);
        }
        return offset;
    }

    // total de celdas de datos, sin el tag de tipo de objetos Z
    public int fieldStorageSize() {
        int total = superClass == null ? 0 : superClass.fieldStorageSize();
        for (Field field : fields) total += cellSize(field);
        return total;
    }

    // tamaño para un agregado por valor (struct) o un payload de clase (sin tag)
    public int size() {
        return Math.max(fieldStorageSize(), 1);
    }

    public int objectSize() {
        return isClass ? Math.max(fieldStorageSize() + 1, 1) : size();
    }

    public List<Field> allFields() {
        List<Field> all = new ArrayList<>();
        if (superClass != null) all.addAll(superClass.allFields());
        all.addAll(fields);
        return all;
    }

    private static int cellSize(Field field) {
        if (field.getType() != null && field.getType().isArray()) {
            int count = 1;
            int dims = field.getArrayDims().isEmpty() ? 1 : field.getArrayDims().size();
            for (int i = 0; i < dims; i++) {
                int d = i < field.getArrayDims().size() ? field.getArrayDims().get(i) : 1;
                count *= Math.max(d, 1);
            }
            return count;
        }
        return 1;
    }

    public Symbol findDeclaredMethod(String name, List<Type> argTypes, TypeTable types) {
        List<Symbol> candidates = memberScope.resolveOverloads(name);
        if (candidates == null) return null;
        for (Symbol symbol : candidates) {
            if (symbol.getKind() == com.navi.backend.semantic.enums.SymbolKind.METHOD
                    && symbol.getSignature() != null
                    && (types == null ? symbol.getSignature().matches(argTypes)
                    : symbol.getSignature().matches(argTypes, types))) {
                return symbol;
            }
        }
        return null;
    }

    public Symbol findMethod(String name, List<Type> argTypes) {
        return findMethod(name, argTypes, null);
    }

    public Symbol findMethod(String name, List<Type> argTypes, TypeTable types) {
        for (AggregateType current = this; current != null; current = current.superClass) {
            Symbol method = current.findDeclaredMethod(name, argTypes, types);
            if (method != null) return method;
        }
        return null;
    }

    // primer metodo heredable con esa firma, usado para validar @Override
    public Symbol findInheritedMethod(String name, List<Type> argTypes, TypeTable types) {
        for (AggregateType current = superClass; current != null; current = current.superClass) {
            List<Symbol> candidates = current.memberScope.resolveOverloads(name);
            if (candidates == null) continue;
            for (Symbol method : candidates) {
                if (method.getKind() != com.navi.backend.semantic.enums.SymbolKind.METHOD
                        || method.getSignature() == null
                        || !method.getSignature().getParameters().equals(argTypes)
                        || method.getAccessModifier() == AccessModifier.PRIVATE
                        || method.getAccessModifier() == AccessModifier.DEFAULT) continue;
                return method;
            }
        }
        return null;
    }

    public Symbol findConstructor(List<Type> argTypes) {
        return findConstructor(argTypes, null);
    }

    public Symbol findConstructor(List<Type> argTypes, TypeTable types) {
        List<Symbol> candidates = memberScope.resolveOverloads(name);
        if (candidates == null) return null;
        for (Symbol symbol : candidates) {
            if (symbol.getKind() == com.navi.backend.semantic.enums.SymbolKind.CONSTRUCTOR
                    && symbol.getSignature() != null
                    && (types == null ? symbol.getSignature().matches(argTypes)
                    : symbol.getSignature().matches(argTypes, types))) {
                return symbol;
            }
        }
        return null;
    }

    public boolean isSubtypeOf(AggregateType candidateParent) {
        for (AggregateType current = this; current != null; current = current.superClass) {
            if (current == candidateParent) return true;
        }
        return false;
    }
}
