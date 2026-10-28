package com.navi.backend.c3d.y;

import com.navi.backend.ast.y.declarations.ArrayInitializer;
import com.navi.backend.ast.y.declarations.ArrayParameter;
import com.navi.backend.ast.y.declarations.ExpressionInitializer;
import com.navi.backend.ast.y.declarations.FunctionDeclaration;
import com.navi.backend.ast.y.declarations.NormalParameter;
import com.navi.backend.ast.y.declarations.Parameter;
import com.navi.backend.ast.y.declarations.StructureParameter;
import com.navi.backend.ast.y.declarations.VariableDeclaration;
import com.navi.backend.ast.y.statements.Statement;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.c3d.StructLayoutC3D;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.List;

// emision C3D de declaraciones/funciones de Y; locales y structs en el stack
// y los parametros arreglo/struct se marcan por referencia
class YDeclarationC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final YResolverC3D resolver;
    private final AstYVisitor<String> visitor;

    YDeclarationC3D(SemanticContext context, C3DEmitter emitter, YResolverC3D resolver, AstYVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.resolver = resolver;
        this.visitor = visitor;
    }

    String functionDeclaration(FunctionDeclaration node) {
        emitter.entryLabel(node.getName());
        emitter.enterFrame(node.getName());
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) {
                String name = null;
                boolean reference = false;
                if (p instanceof NormalParameter np) name = np.getName();
                else if (p instanceof ArrayParameter ap) { name = ap.getName(); reference = true; }
                else if (p instanceof StructureParameter sp) { name = sp.getName(); reference = true; }
                if (name != null) {
                    Symbol ps = context.symbolOf(p);
                    resolver.setPos(p, emitter.declareParam(name, reference, ps == null ? null : ps.getType()));
                    // arreglo multi-D: dimensiones dims[1..N-1] como params ocultos
                    if (p instanceof ArrayParameter ap && ap.getRank() > 1) {
                        List<Integer> dimOffsets = new ArrayList<>();
                        for (int k = 1; k < ap.getRank(); k++) {
                            dimOffsets.add(emitter.declareParam(name + "_dim" + k, false,
                                    com.navi.backend.semantic.model.Type.INT));
                        }
                        resolver.recordParamDims(name, dimOffsets);
                    }
                }
            }
        }
        if (node.getStatements() != null) for (Statement s : node.getStatements()) s.accept(visitor);
        emitter.returnVoid();
        emitter.exitFrame();
        return null;
    }

    String variableDeclaration(VariableDeclaration node) {
        if (node.getArrayDeclaration() != null) {
            int cells = resolver.arrayCells(node.getArrayDeclaration().getDimensions());
            List<Integer> dims = resolver.constantDims(node.getArrayDeclaration().getDimensions());
            Symbol arraySymbol = context.symbolOf(node);
            Type elementType = arraySymbol != null && arraySymbol.getType().isArray()
                    ? arraySymbol.getType().getElementType() : null;
            // Reserva e inicializa todas las celdas de cada struct del arreglo local.
            if (elementType != null && elementType.isStruct()) {
                cells *= StructLayoutC3D.size(context.getTypeTable(), elementType);
            }
            resolver.setPos(node, emitter.declareLocalArray(node.getName(), cells, dims,
                    arraySymbol == null ? null : arraySymbol.getType()));
            if (elementType != null && elementType.isStruct()) {
                String base = emitter.varAddr(node.getName());
                for (int i = 0; i < cells; i++) {
                    emitter.stackStoreAt(emitter.binary("+", base, String.valueOf(i)), "0");
                }
            }
        } else {
            Symbol symbol = context.symbolOf(node);
            resolver.setPos(node, emitter.declareLocal(node.getName(),
                    symbol == null ? null : symbol.getType()));
            if (symbol != null && symbol.getType() != null && symbol.getType().isStruct()) {
                int extra = resolver.structSize(symbol.getType().getName()) - 1;
                if (extra > 0) emitter.reserve(extra);
            }
        }
        if (node.getInitializer() != null) {
            if (node.getInitializer() instanceof ExpressionInitializer ei) {
                emitter.storeVar(node.getName(), ei.getExpression().accept(visitor));
            } else if (node.getInitializer() instanceof ArrayInitializer ai) {
                String base = emitter.varAddr(node.getName());
                resolver.storeArrayInitializer(base, ai.getElements(), 0);
            }
        }
        return null;
    }
}
