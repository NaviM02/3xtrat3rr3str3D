package com.navi.backend.semantic.y;

import com.navi.backend.ast.y.declarations.ArrayDeclaration;
import com.navi.backend.ast.y.declarations.ArrayDimensions;
import com.navi.backend.ast.y.declarations.ArrayInitializer;
import com.navi.backend.ast.y.declarations.ArrayParameter;
import com.navi.backend.ast.y.declarations.ExpressionInitializer;
import com.navi.backend.ast.y.declarations.FunctionDeclaration;
import com.navi.backend.ast.y.declarations.NormalParameter;
import com.navi.backend.ast.y.declarations.Parameter;
import com.navi.backend.ast.y.declarations.StructureDeclaration;
import com.navi.backend.ast.y.declarations.StructureField;
import com.navi.backend.ast.y.declarations.StructureInitializer;
import com.navi.backend.ast.y.declarations.StructureParameter;
import com.navi.backend.ast.y.declarations.VariableDeclaration;
import com.navi.backend.ast.y.declarations.YType;
import com.navi.backend.ast.y.expressions.ArrayAccessExpression;
import com.navi.backend.ast.y.expressions.BinaryExpression;
import com.navi.backend.ast.y.expressions.FunctionCallExpression;
import com.navi.backend.ast.y.expressions.MemberAccessExpression;
import com.navi.backend.ast.y.expressions.ReadExpression;
import com.navi.backend.ast.y.expressions.UnaryExpression;
import com.navi.backend.ast.y.expressions.VariableExpression;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.y.statements.AssignmentStatement;
import com.navi.backend.ast.y.statements.BreakStatement;
import com.navi.backend.ast.y.statements.ContinueStatement;
import com.navi.backend.ast.y.statements.DefaultCase;
import com.navi.backend.ast.y.statements.DoWhileStatement;
import com.navi.backend.ast.y.statements.ElseClause;
import com.navi.backend.ast.y.statements.ElseIfClause;
import com.navi.backend.ast.y.statements.ExpressionStatement;
import com.navi.backend.ast.y.statements.ForStatement;
import com.navi.backend.ast.y.statements.IfStatement;
import com.navi.backend.ast.y.statements.IncrementStatement;
import com.navi.backend.ast.y.statements.PrintStatement;
import com.navi.backend.ast.y.statements.ReadStatement;
import com.navi.backend.ast.y.statements.ReturnStatement;
import com.navi.backend.ast.y.statements.SwitchCase;
import com.navi.backend.ast.y.statements.SwitchStatement;
import com.navi.backend.ast.y.statements.WhileStatement;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.Field;
import com.navi.backend.semantic.FunctionSignature;
import com.navi.backend.semantic.Scope;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.SymbolKind;
import com.navi.backend.semantic.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Pasada de declaraciones de Y. Registra estructuras (TypeTable) y funciones
 * (scope global) en tres sub-fases para soportar referencias entre structs.
 */
public class YDeclarationVisitor implements AstYVisitor<Void> {

    private final SemanticContext context;
    private final YTypeResolver types;
    private final Definitions defs;

    public YDeclarationVisitor(SemanticContext context) {
        this.context = context;
        this.types = new YTypeResolver(context);
        this.defs = new Definitions(context);
    }

    public void build(ProgramY program) {
        program.accept(this);
    }

    @Override
    public Void visit(ProgramY node) {
        Scope global = context.getSymbolTable().getGlobalScope();

        if (node.getStructures() != null) {
            for (StructureDeclaration s : node.getStructures()) {
                registerStructName(s, global);
            }
        }
        if (node.getFunctions() != null) {
            for (FunctionDeclaration f : node.getFunctions()) {
                f.accept(this);
            }
        }
        if (node.getStructures() != null) {
            for (StructureDeclaration s : node.getStructures()) {
                fillStructFields(s);
            }
        }
        return null;
    }

    private void registerStructName(StructureDeclaration node, Scope global) {
        AggregateType agg = new AggregateType(node.getName(), false, global);
        if (!context.getTypeTable().register(agg)) {
            context.getErrors().report(node.getLine(), node.getColumn(), "Estructura duplicada: " + node.getName());
        }
    }

    private void fillStructFields(StructureDeclaration node) {
        AggregateType agg = context.getTypeTable().resolve(node.getName());
        if (agg == null || node.getFields() == null) return;
        for (StructureField field : node.getFields()) {
            agg.addField(new Field(field.getName(), resolveFieldType(field)));
        }
    }

    @Override
    public Void visit(StructureDeclaration node) {
        return null; // llenado en visit(ProgramY)
    }

    @Override
    public Void visit(FunctionDeclaration node) {
        Type returnType = node.getReturnType() == null ? Type.VOID : types.resolve(node.getReturnType());
        List<Type> paramTypes = new ArrayList<>();
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) paramTypes.add(paramType(p));
        }
        Symbol fn = new Symbol(node.getName(), SymbolKind.FUNCTION, returnType,
                new FunctionSignature(paramTypes, returnType), false,
                context.getSymbolTable().getGlobalScope(), null, node.getLine(), node.getColumn());
        defs.callable(fn);
        return null;
    }

    private Type resolveFieldType(StructureField field) {
        Type base = types.resolve(field.getType());
        ArrayDimensions dims = field.getArrayDimensions();
        if (dims == null || dims.getDimensions() == null || dims.getDimensions().isEmpty()) return base;
        return Type.array(base, dims.getDimensions().size());
    }

    private Type paramType(Parameter p) {
        if (p instanceof NormalParameter n) return types.resolve(n.getType());
        if (p instanceof ArrayParameter a) return Type.array(types.resolve(a.getType()), 1);
        if (p instanceof StructureParameter s) {
            AggregateType agg = context.getTypeTable().resolve(s.getStructureName());
            if (agg == null) {
                context.getErrors().report(s.getLine(), s.getColumn(), "Estructura no definida: " + s.getStructureName());
                return Type.ERROR;
            }
            return Type.struct(s.getStructureName());
        }
        return Type.ERROR;
    }

    @Override public Void visit(StructureField node) { return null; }
    @Override public Void visit(YType node) { return null; }
    @Override public Void visit(ArrayDimensions node) { return null; }
    @Override public Void visit(ArrayDeclaration node) { return null; }
    @Override public Void visit(ArrayInitializer node) { return null; }
    @Override public Void visit(ExpressionInitializer node) { return null; }
    @Override public Void visit(StructureInitializer node) { return null; }
    @Override public Void visit(ArrayParameter node) { return null; }
    @Override public Void visit(NormalParameter node) { return null; }
    @Override public Void visit(StructureParameter node) { return null; }
    @Override public Void visit(VariableDeclaration node) { return null; }

    @Override public Void visit(AssignmentStatement node) { return null; }
    @Override public Void visit(BreakStatement node) { return null; }
    @Override public Void visit(ContinueStatement node) { return null; }
    @Override public Void visit(DefaultCase node) { return null; }
    @Override public Void visit(DoWhileStatement node) { return null; }
    @Override public Void visit(ElseClause node) { return null; }
    @Override public Void visit(ElseIfClause node) { return null; }
    @Override public Void visit(ExpressionStatement node) { return null; }
    @Override public Void visit(ForStatement node) { return null; }
    @Override public Void visit(IfStatement node) { return null; }
    @Override public Void visit(IncrementStatement node) { return null; }
    @Override public Void visit(PrintStatement node) { return null; }
    @Override public Void visit(ReadStatement node) { return null; }
    @Override public Void visit(ReturnStatement node) { return null; }
    @Override public Void visit(SwitchCase node) { return null; }
    @Override public Void visit(SwitchStatement node) { return null; }
    @Override public Void visit(WhileStatement node) { return null; }

    @Override public Void visit(ArrayAccessExpression node) { return null; }
    @Override public Void visit(BinaryExpression node) { return null; }
    @Override public Void visit(FunctionCallExpression node) { return null; }
    @Override public Void visit(MemberAccessExpression node) { return null; }
    @Override public Void visit(ReadExpression node) { return null; }
    @Override public Void visit(UnaryExpression node) { return null; }
    @Override public Void visit(VariableExpression node) { return null; }
    @Override public Void visit(LiteralExpression node) { return null; }
}
