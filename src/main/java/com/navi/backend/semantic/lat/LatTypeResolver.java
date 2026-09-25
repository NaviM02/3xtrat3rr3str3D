package com.navi.backend.semantic.lat;

import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Type;

import java.util.Locale;

/**
 * Resuelve los tipos de Lat: keywords primitivas ({@code numerus}, {@code textum}, ...)
 * y nombres de agregados (struct/class) contra la {@link com.navi.backend.semantic.TypeTable}.
 * Compartido por la pasada de declaraciones y la semántica (antes, una copia en cada una).
 */
public final class LatTypeResolver {

    private final SemanticContext context;

    public LatTypeResolver(SemanticContext context) {
        this.context = context;
    }

    public Type resolve(String name, int line, int col) {
        if (name == null) return Type.VOID;
        String value = name.toLowerCase(Locale.ROOT);
        return switch (value) {
            case "numerus" -> Type.INT;
            case "decimalis" -> Type.DOUBLE;
            case "textum" -> Type.STRING;
            case "littera" -> Type.CHAR;
            case "bool", "boolean" -> Type.BOOLEAN;
            case "void" -> Type.VOID;
            default -> {
                AggregateType agg = context.getTypeTable().resolve(name);
                if (agg == null) {
                    context.getErrors().report(line, col, "Tipo no definido: " + name);
                    yield Type.ERROR;
                }
                yield agg.isClass() ? Type.classType(name) : Type.struct(name);
            }
        };
    }
}
