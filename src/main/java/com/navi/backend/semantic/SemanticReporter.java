package com.navi.backend.semantic;

import java.util.List;
import java.util.Map;

/**
 * Produce un volcado legible del contexto semántico (errores, tabla de tipos y
 * tabla de símbolos) para verificar el análisis.
 */
public final class SemanticReporter {

    private SemanticReporter() {
    }

    public static String report(SemanticContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("==================== ANÁLISIS SEMÁNTICO ====================\n");

        List<String> errors = ctx.getErrors().getErrors();
        sb.append("Errores (").append(errors.size()).append("):\n");
        for (String e : errors) {
            sb.append("  ").append(e).append("\n");
        }
        if (errors.isEmpty()) {
            sb.append("  (ninguno)\n");
        }

        sb.append("\n--- Tabla de tipos (struct/class) ---\n");
        if (ctx.getTypeTable().getTypes().isEmpty()) {
            sb.append("  (vacía)\n");
        }
        for (AggregateType t : ctx.getTypeTable().getTypes().values()) {
            sb.append("  ").append(t.isClass() ? "class" : "struct").append(" ").append(t.getName()).append(" {\n");
            for (Field f : t.getFields()) {
                sb.append("      ").append(f.getType()).append(" ").append(f.getName()).append("\n");
            }
            for (Map.Entry<String, List<Symbol>> e : t.getMemberScope().getSymbols().entrySet()) {
                for (Symbol m : e.getValue()) {
                    sb.append("      ").append(m.getKind()).append(" ").append(m.getName()).append(sig(m)).append("\n");
                }
            }
            sb.append("  }\n");
        }

        sb.append("\n--- Tabla de símbolos (scopes) ---\n");
        dumpScope(ctx.getSymbolTable().getGlobalScope(), sb, 0);
        return sb.toString();
    }

    private static String sig(Symbol s) {
        if (s.getSignature() == null) return "";
        return "(" + s.getSignature().getParameters() + ") -> " + s.getSignature().getReturnType();
    }

    private static void dumpScope(Scope scope, StringBuilder sb, int indent) {
        String pad = "  ".repeat(indent);
        sb.append(pad).append("[").append(scope.getKind()).append("]\n");
        for (Map.Entry<String, List<Symbol>> e : scope.getSymbols().entrySet()) {
            for (Symbol s : e.getValue()) {
                sb.append(pad).append("  ").append(s.getName())
                        .append(" : ").append(s.getKind())
                        .append(" : ").append(s.getType());
                if (s.getSignature() != null) {
                    sb.append(sig(s));
                }
                if (s.getKind() == SymbolKind.VARIABLE || s.getKind() == SymbolKind.PARAMETER) {
                    sb.append(" | Pos_memory: ").append(s.getPosMemory());
                }
                sb.append("\n");
            }
        }
        for (Scope child : scope.getChildren()) {
            dumpScope(child, sb, indent + 1);
        }
    }
}
