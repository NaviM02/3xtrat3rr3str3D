package com.navi.backend.semantic.lat;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.ArrayDeclaration;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.BinaryExpression;
import com.navi.backend.ast.lat.expressions.Expression;
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
import com.navi.backend.semantic.Definitions;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Type;
import com.navi.backend.semantic.TypeRules;

/**
 * Pasada semántica de Lat: valida tipos y anota cada expresión con su {@link Type}.
 * Dispatcher delgado: la lógica vive en {@link LatStatementChecker} (sentencias)
 * y {@link LatExpressionChecker} (expresiones); este visitante conserva el
 * programa, las secciones globales y el no-op de los nodos pasivos.
 */
public class LatSemanticVisitor implements AstLatVisitor<Type> {

    private final SemanticContext context;
    private final TypeRules rules;
    private final LatTypeResolver types;
    private final LatStatementChecker statements;
    private final LatExpressionChecker expressions;

    public LatSemanticVisitor(SemanticContext context) {
        this.context = context;
        this.rules = new TypeRules(context);
        this.types = new LatTypeResolver(context);
        Definitions defs = new Definitions(context);
        // `this` como recorrido: los checkers delegan el accept de los hijos aquí.
        this.expressions = new LatExpressionChecker(context, rules, this);
        this.statements = new LatStatementChecker(context, rules, types, defs, expressions, this);
    }

    public void build(Program program) {
        program.accept(this);
    }

    @Override
    public Type visit(Program node) {
        if (node.getGlobalVariables() != null) {
            for (Declaration d : node.getGlobalVariables().getDeclarations()) {
                checkGlobalInitializer(d);
            }
        }
        if (node.getFunctions() != null) {
            for (FunctionDeclaration f : node.getFunctions()) {
                f.accept(this);
            }
        }
        if (node.getMainStatements() != null) {
            for (Statement s : node.getMainStatements()) {
                s.accept(this);
            }
        }
        return null;
    }

    private void checkGlobalInitializer(Declaration d) {
        if (d instanceof VariableDeclaration v) {
            Type expected = types.resolve(v.getType(), v.getLine(), v.getColumn());
            if (v.getInitializer() != null) {
                expressions.checkInitializer(v.getInitializer(), expected, v.getLine(), v.getColumn());
            }
        } else if (d instanceof ArrayDeclaration a) {
            Type base = types.resolve(a.getType(), a.getLine(), a.getColumn());
            if (a.getInitializer() != null) {
                for (AstLatNode el : a.getInitializer().getElements()) {
                    Type actual = el instanceof Expression e ? e.accept(this) : Type.ERROR;
                    rules.checkElement(base, actual, el.getLine(), el.getColumn());
                }
            }
        }
    }

    // ---------------------------------------------------------------- delegación

    @Override public Type visit(FunctionDeclaration node) { return statements.functionDeclaration(node); }
    @Override public Type visit(FunctionBody node) { return statements.functionBody(node); }
    @Override public Type visit(LocalVariableSection node) { return statements.localVariableSection(node); }
    @Override public Type visit(VariableDeclaration node) { return statements.variableDeclaration(node); }
    @Override public Type visit(ArrayDeclaration node) { return statements.arrayDeclaration(node); }

    @Override public Type visit(BlockStatement node) { return statements.block(node); }
    @Override public Type visit(AssignmentStatement node) { return statements.assignment(node); }
    @Override public Type visit(IfStatement node) { return statements.ifStatement(node); }
    @Override public Type visit(ElseIfStatement node) { return statements.elseIf(node); }
    @Override public Type visit(WhileStatement node) { return statements.whileStatement(node); }
    @Override public Type visit(DoWhileStatement node) { return statements.doWhile(node); }
    @Override public Type visit(ForStatement node) { return statements.forStatement(node); }
    @Override public Type visit(ReturnStatement node) { return statements.returnStatement(node); }
    @Override public Type visit(IncrementStatement node) { return statements.increment(node); }
    @Override public Type visit(PrintStatement node) { return statements.print(node); }
    @Override public Type visit(ReadStatement node) { return statements.read(node); }
    @Override public Type visit(FunctionCallStatement node) { return statements.callStatement(node); }

    @Override public Type visit(BinaryExpression node) { return expressions.binary(node); }
    @Override public Type visit(UnaryExpression node) { return expressions.unary(node); }
    @Override public Type visit(VariableExpression node) { return expressions.variable(node); }
    @Override public Type visit(ArrayAccessExpression node) { return expressions.arrayAccess(node); }
    @Override public Type visit(MemberAccessExpression node) { return expressions.memberAccess(node); }
    @Override public Type visit(FunctionCallExpression node) { return expressions.functionCall(node); }
    @Override public Type visit(ObjectCreationExpression node) { return expressions.objectCreation(node); }

    @Override public Type visit(BooleanLiteral node) { return expressions.constant(node, Type.BOOLEAN); }
    @Override public Type visit(CharLiteral node) { return expressions.constant(node, Type.CHAR); }
    @Override public Type visit(DecimalLiteral node) { return expressions.constant(node, Type.DOUBLE); }
    @Override public Type visit(NumberLiteral node) { return expressions.constant(node, Type.INT); }
    @Override public Type visit(StringLiteral node) { return expressions.constant(node, Type.STRING); }

    @Override public Type visit(BreakStatement node) { return null; }
    @Override public Type visit(ContinueStatement node) { return null; }
    @Override public Type visit(GlobalVariableSection node) { return null; }
    @Override public Type visit(ImportDeclaration node) { return null; }
    @Override public Type visit(Parameter node) { return null; }
    @Override public Type visit(ArrayInitializer node) { return Type.ERROR; }
    @Override public Type visit(ExpressionInitializer node) { return Type.ERROR; }
    @Override public Type visit(StructInitializer node) { return Type.ERROR; }
    @Override public Type visit(StructFieldInitializer node) { return Type.ERROR; }
}
