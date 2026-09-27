package com.navi.backend.c3d.lat;

import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.BinaryExpression;
import com.navi.backend.ast.lat.expressions.FunctionCallExpression;
import com.navi.backend.ast.lat.expressions.MemberAccessExpression;
import com.navi.backend.ast.lat.expressions.ObjectCreationExpression;
import com.navi.backend.ast.lat.expressions.UnaryExpression;
import com.navi.backend.ast.lat.expressions.VariableExpression;
import com.navi.backend.ast.lat.expressions.literals.BooleanLiteral;
import com.navi.backend.ast.lat.expressions.literals.CharLiteral;
import com.navi.backend.ast.lat.expressions.literals.DecimalLiteral;
import com.navi.backend.ast.lat.expressions.literals.NumberLiteral;
import com.navi.backend.ast.lat.expressions.literals.StringLiteral;
import com.navi.backend.ast.lat.global.FunctionBody;
import com.navi.backend.ast.lat.global.FunctionDeclaration;
import com.navi.backend.ast.lat.global.GlobalVariableSection;
import com.navi.backend.ast.lat.global.ImportDeclaration;
import com.navi.backend.ast.lat.global.LocalVariableSection;
import com.navi.backend.ast.lat.global.Parameter;
import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.lat.declarations.ArrayDeclaration;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.statements.AssignmentStatement;
import com.navi.backend.ast.lat.statements.BlockStatement;
import com.navi.backend.ast.lat.statements.BreakStatement;
import com.navi.backend.ast.lat.statements.ContinueStatement;
import com.navi.backend.ast.lat.statements.DoWhileStatement;
import com.navi.backend.ast.lat.statements.ElseIfStatement;
import com.navi.backend.ast.lat.statements.ForStatement;
import com.navi.backend.ast.lat.statements.FunctionCallStatement;
import com.navi.backend.ast.lat.statements.IfStatement;
import com.navi.backend.ast.lat.statements.IncrementStatement;
import com.navi.backend.ast.lat.statements.PrintStatement;
import com.navi.backend.ast.lat.statements.ReadStatement;
import com.navi.backend.ast.lat.statements.ReturnStatement;
import com.navi.backend.ast.lat.statements.Statement;
import com.navi.backend.ast.lat.statements.WhileStatement;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.SemanticContext;

/**
 * Genera C3D para Lat siguiendo los apuntes de clase. Locales/parámetros usan
 * {@code stack[BP + off]} y las variables globales {@code stack[GP + off]};
 * los objetos (clases importadas de Z) viven en el heap. Booleanos
 * materializados con saltos y bifurcaciones con {@code if a op b goto L}.
 *
 * <p>Dispatcher delgado: la lógica vive en {@link LatDeclarationC3D},
 * {@link LatStatementC3D} y {@link LatExpressionC3D}; {@link LatC3DResolver}
 * centraliza direcciones, layout y asignación. Este visitante conserva el
 * programa y delega el accept de los hijos a través de {@code this}.</p>
 */
public class LatC3DVisitor implements AstLatVisitor<String> {

    private final C3DEmitter emitter;
    private final LatC3DResolver resolver;
    private final LatExpressionC3D expressions;
    private final LatStatementC3D statements;
    private final LatDeclarationC3D declarations;

    public LatC3DVisitor(SemanticContext context, C3DEmitter emitter) {
        this.emitter = emitter;
        this.resolver = new LatC3DResolver(context, emitter, this);
        this.expressions = new LatExpressionC3D(context, emitter, resolver, this);
        this.statements = new LatStatementC3D(emitter, context, resolver, expressions, this);
        this.declarations = new LatDeclarationC3D(context, emitter, resolver, this);
    }

    public void generate(Program program) {
        program.accept(this);
    }

    @Override
    public String visit(Program node) {
        if (node.getGlobalVariables() != null) {
            emitter.entryLabel("globals_init");
            emitter.enterFrame("globals_init");
            node.getGlobalVariables().accept(this);
            emitter.returnVoid();
            emitter.exitFrame();
        }
        if (node.getFunctions() != null) for (FunctionDeclaration f : node.getFunctions()) f.accept(this);
        emitter.entryLabel("main");
        emitter.enterFrame("main");
        if (node.getMainStatements() != null) for (Statement s : node.getMainStatements()) s.accept(this);
        emitter.halt();
        emitter.exitFrame();
        return null;
    }

    // ---------------------------------------------------------------- declaraciones

    @Override public String visit(GlobalVariableSection node) { return declarations.globalVariableSection(node); }
    @Override public String visit(LocalVariableSection node) { return declarations.localVariableSection(node); }
    @Override public String visit(VariableDeclaration node) { return declarations.variableDeclaration(node); }
    @Override public String visit(ArrayDeclaration node) { return declarations.arrayDeclaration(node); }
    @Override public String visit(FunctionDeclaration node) { return declarations.functionDeclaration(node); }
    @Override public String visit(FunctionBody node) { return declarations.functionBody(node); }

    // ---------------------------------------------------------------- sentencias

    @Override public String visit(BlockStatement node) { return statements.block(node); }
    @Override public String visit(AssignmentStatement node) { return statements.assignment(node); }
    @Override public String visit(IfStatement node) { return statements.ifStatement(node); }
    @Override public String visit(WhileStatement node) { return statements.whileStatement(node); }
    @Override public String visit(DoWhileStatement node) { return statements.doWhile(node); }
    @Override public String visit(ForStatement node) { return statements.forStatement(node); }
    @Override public String visit(ReturnStatement node) { return statements.returnStatement(node); }
    @Override public String visit(IncrementStatement node) { return statements.increment(node); }
    @Override public String visit(PrintStatement node) { return statements.print(node); }
    @Override public String visit(ReadStatement node) { return statements.read(node); }
    @Override public String visit(FunctionCallStatement node) { return statements.callStatement(node); }
    @Override public String visit(BreakStatement node) { return statements.breakStatement(node); }
    @Override public String visit(ContinueStatement node) { return statements.continueStatement(node); }

    // ---------------------------------------------------------------- expresiones

    @Override public String visit(BinaryExpression node) { return expressions.binary(node); }
    @Override public String visit(UnaryExpression node) { return expressions.unary(node); }
    @Override public String visit(VariableExpression node) { return expressions.variable(node); }
    @Override public String visit(ArrayAccessExpression node) { return expressions.arrayAccess(node); }
    @Override public String visit(MemberAccessExpression node) { return expressions.memberAccess(node); }
    @Override public String visit(FunctionCallExpression node) { return expressions.functionCall(node); }
    @Override public String visit(ObjectCreationExpression node) { return expressions.objectCreation(node); }

    @Override public String visit(BooleanLiteral node) { return expressions.booleanLiteral(node); }
    @Override public String visit(CharLiteral node) { return expressions.charLiteral(node); }
    @Override public String visit(DecimalLiteral node) { return expressions.decimalLiteral(node); }
    @Override public String visit(NumberLiteral node) { return expressions.numberLiteral(node); }
    @Override public String visit(StringLiteral node) { return expressions.stringLiteral(node); }

    // ---------------------------------------------------------------- pasivos

    @Override public String visit(ImportDeclaration node) { return null; }
    @Override public String visit(Parameter node) { return null; }
    @Override public String visit(ElseIfStatement node) { return null; }
    @Override public String visit(ArrayInitializer node) { return null; }
    @Override public String visit(ExpressionInitializer node) { return null; }
    @Override public String visit(StructInitializer node) { return null; }
    @Override public String visit(StructFieldInitializer node) { return null; }
}
