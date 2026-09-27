package com.navi.backend.c3d.lat;

import com.navi.backend.ast.lat.declarations.ArrayDeclaration;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.VariableExpression;
import com.navi.backend.ast.lat.global.FunctionBody;
import com.navi.backend.ast.lat.global.FunctionDeclaration;
import com.navi.backend.ast.lat.global.GlobalVariableSection;
import com.navi.backend.ast.lat.global.LocalVariableSection;
import com.navi.backend.ast.lat.global.Parameter;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;

import java.util.List;

// emision C3D de declaraciones/funciones de Lat; globalSection decide
// si la declaracion es global o local
class LatDeclarationC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final LatC3DResolver resolver;
    private final AstLatVisitor<String> visitor;

    private boolean globalSection = false;

    LatDeclarationC3D(SemanticContext context, C3DEmitter emitter, LatC3DResolver resolver, AstLatVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.resolver = resolver;
        this.visitor = visitor;
    }

    String globalVariableSection(GlobalVariableSection node) {
        emitter.resume();
        globalSection = true;
        for (Declaration d : node.getDeclarations()) d.accept(visitor);
        globalSection = false;
        return null;
    }

    String localVariableSection(LocalVariableSection node) {
        for (Declaration d : node.getDeclarations()) d.accept(visitor);
        return null;
    }

    String variableDeclaration(VariableDeclaration node) {
        resolver.setPos(node, declare(node.getName()));
        Symbol symbol = context.symbolOf(node);
        if (symbol != null && symbol.getType() != null && symbol.getType().isStruct()) {
            int extra = resolver.structSize(symbol.getType().getName()) - 1;
            if (extra > 0) {
                if (globalSection) emitter.reserveGlobal(extra);
                else emitter.reserve(extra);
            }
        }
        if (node.getInitializer() != null) {
            if (node.getInitializer() instanceof StructInitializer si) {
                resolver.emitStructInitializer(si, structTarget(node.getName()), symbolType(symbol));
            } else {
                String value = resolver.initializerValue(node.getInitializer());
                if (value != null) emitter.storeVar(node.getName(), value);
            }
        }
        return null;
    }

    private com.navi.backend.ast.lat.expressions.Expression structTarget(String name) {
        return new VariableExpression(0, 0, name);
    }

    private com.navi.backend.semantic.model.Type symbolType(Symbol symbol) {
        return symbol == null ? null : symbol.getType();
    }

    String arrayDeclaration(ArrayDeclaration node) {
        int cells = resolver.arrayCells(node.getSizes());
        List<Integer> dims = resolver.constantDims(node.getSizes());
        int offset = globalSection
                ? emitter.declareGlobalArray(node.getName(), cells, dims)
                : emitter.declareLocalArray(node.getName(), cells, dims);
        resolver.setPos(node, offset);
        if (node.getInitializer() != null) {
            String base = emitter.varAddr(node.getName());
            resolver.storeArrayInitializer(base, node.getInitializer().getElements(), 0);
        }
        return null;
    }

    String functionDeclaration(FunctionDeclaration node) {
        emitter.entryLabel(node.getName());
        emitter.enterFrame(node.getName());
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) resolver.setPos(p, emitter.declareParam(p.getName()));
        }
        if (node.getBody() != null) node.getBody().accept(visitor);
        emitter.returnVoid();
        emitter.exitFrame();
        return null;
    }

    String functionBody(FunctionBody node) {
        if (node.getLocalVariables() != null) node.getLocalVariables().accept(visitor);
        if (node.getBody() != null) node.getBody().accept(visitor);
        return null;
    }

    private int declare(String name) {
        return globalSection ? emitter.declareGlobal(name) : emitter.declareLocal(name);
    }
}
