package com.navi.backend.semantic.y;

import com.navi.backend.ast.y.declarations.YType;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Type;

import java.util.Locale;

// resuelve tipos de Y: primitivas (entero, cadena, ...) y nombres de struct
// contra la TypeTable; lo comparten declaraciones y semantica
public final class YTypeResolver {

    private final SemanticContext context;

    public YTypeResolver(SemanticContext context) {
        this.context = context;
    }

    public Type resolve(YType node) {
        String value = node.getName().toLowerCase(Locale.ROOT);
        return switch (value) {
            case "entero" -> Type.INT;
            case "flotante" -> Type.DOUBLE;
            case "cadena" -> Type.STRING;
            case "caracter" -> Type.CHAR;
            case "bool" -> Type.BOOLEAN;
            default -> {
                AggregateType agg = context.getTypeTable().resolve(node.getName());
                if (agg == null) {
                    context.getErrors().report(node.getLine(), node.getColumn(),
                            "Tipo no definido: " + node.getName());
                    yield Type.ERROR;
                }
                yield Type.struct(node.getName());
            }
        };
    }
}
