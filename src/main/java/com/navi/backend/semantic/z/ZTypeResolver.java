package com.navi.backend.semantic.z;

import com.navi.backend.ast.z.declarations.ZType;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Type;

/**
 * Resuelve los tipos de Z: primitivas ({@code int}, {@code String}, ...) y nombres de
 * clase contra la {@link com.navi.backend.semantic.TypeTable}, aplicando las
 * dimensiones de arreglo si el {@link ZType} las declara.
 * Compartido por la pasada de declaraciones y la semántica.
 */
public final class ZTypeResolver {

    private final SemanticContext context;

    public ZTypeResolver(SemanticContext context) {
        this.context = context;
    }

    public Type resolve(ZType node) {
        String name = node.getName();
        Type base = switch (name) {
            case "int" -> Type.INT;
            case "double" -> Type.DOUBLE;
            case "char" -> Type.CHAR;
            case "boolean" -> Type.BOOLEAN;
            case "String" -> Type.STRING;
            case "void" -> Type.VOID;
            default -> {
                AggregateType agg = context.getTypeTable().resolve(name);
                if (agg == null) {
                    context.getErrors().report(node.getLine(), node.getColumn(), "Tipo no definido: " + name);
                    yield Type.ERROR;
                }
                yield Type.classType(name);
            }
        };
        if (node.getArrayDimensions() != null && node.getArrayDimensions().getDimensions() > 0) {
            return Type.array(base, node.getArrayDimensions().getDimensions());
        }
        return base;
    }
}
