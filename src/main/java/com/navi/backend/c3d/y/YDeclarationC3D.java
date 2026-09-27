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
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;

import java.util.List;

/**
 * Emisión C3D de declaraciones/funciones de Y. Las variables locales y structs
 * viven en el stack; los parámetros arreglo/struct se marcan por referencia.
 */
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
                if (name != null) resolver.setPos(p, emitter.declareParam(name, reference));
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
            resolver.setPos(node, emitter.declareLocalArray(node.getName(), cells, dims));
        } else {
            resolver.setPos(node, emitter.declareLocal(node.getName()));
            Symbol symbol = context.symbolOf(node);
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
