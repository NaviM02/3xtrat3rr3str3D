package com.navi.backend.c3d;

import com.navi.backend.ast.y.AstYNode;
import com.navi.backend.ast.y.declarations.ArrayDeclaration;
import com.navi.backend.ast.y.declarations.ArrayDimensions;
import com.navi.backend.ast.y.declarations.ArrayInitializer;
import com.navi.backend.ast.y.declarations.ArrayParameter;
import com.navi.backend.ast.y.declarations.ExpressionInitializer;
import com.navi.backend.ast.y.declarations.FunctionDeclaration;
import com.navi.backend.ast.y.declarations.Initializer;
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
import com.navi.backend.ast.y.expressions.BinaryOperator;
import com.navi.backend.ast.y.expressions.Expression;
import com.navi.backend.ast.y.expressions.FunctionCallExpression;
import com.navi.backend.ast.y.expressions.MemberAccessExpression;
import com.navi.backend.ast.y.expressions.ReadExpression;
import com.navi.backend.ast.y.expressions.UnaryExpression;
import com.navi.backend.ast.y.expressions.UnaryOperator;
import com.navi.backend.ast.y.expressions.VariableExpression;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.y.statements.AssignmentOperator;
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
import com.navi.backend.ast.y.statements.Statement;
import com.navi.backend.ast.y.statements.SwitchCase;
import com.navi.backend.ast.y.statements.SwitchStatement;
import com.navi.backend.ast.y.statements.WhileStatement;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.Type;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Genera C3D para Y siguiendo los apuntes de clase: variables y structs en el
 * stack ({@code stack[BP + off]}), booleanos materializados con saltos y
 * bifurcaciones con {@code if a op b goto L}. Los arreglos/structs por
 * referencia guardan una dirección en su slot.
 */
public class YC3DVisitor implements AstYVisitor<String> {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final Deque<String> breakLabels = new ArrayDeque<>();
    private final Deque<String> continueLabels = new ArrayDeque<>();

    public YC3DVisitor(SemanticContext context, C3DEmitter emitter) {
        this.context = context;
        this.emitter = emitter;
    }

    public void generate(ProgramY program) {
        program.accept(this);
    }

    @Override
    public String visit(ProgramY node) {
        if (node.getFunctions() != null) for (FunctionDeclaration f : node.getFunctions()) f.accept(this);
        return null;
    }

    @Override
    public String visit(FunctionDeclaration node) {
        emitter.entryLabel(node.getName());
        emitter.enterFrame(node.getName());
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) {
                String name = null;
                boolean reference = false;
                if (p instanceof NormalParameter np) name = np.getName();
                else if (p instanceof ArrayParameter ap) { name = ap.getName(); reference = true; }
                else if (p instanceof StructureParameter sp) { name = sp.getName(); reference = true; }
                if (name != null) setPos(p, emitter.declareParam(name, reference));
            }
        }
        if (node.getStatements() != null) for (Statement s : node.getStatements()) s.accept(this);
        emitter.returnVoid();
        emitter.exitFrame();
        return null;
    }

    @Override
    public String visit(VariableDeclaration node) {
        setPos(node, emitter.declareLocal(node.getName()));
        if (node.getArrayDeclaration() != null) {
            emitter.reserve(arrayCells(node.getArrayDeclaration().getDimensions()) - 1);
        }
        if (node.getInitializer() != null) {
            if (node.getInitializer() instanceof ExpressionInitializer ei) {
                emitter.storeVar(node.getName(), ei.getExpression().accept(this));
            } else if (node.getInitializer() instanceof ArrayInitializer ai) {
                String base = emitter.varAddr(node.getName());
                int i = 0;
                for (AstYNode el : ai.getElements()) {
                    if (el instanceof Expression e) {
                        String addr = emitter.binary("+", base, String.valueOf(i));
                        emitter.stackStoreAt(addr, e.accept(this));
                    }
                    i++;
                }
            }
        }
        return null;
    }

    @Override
    public String visit(AssignmentStatement node) {
        String value = node.getValue().accept(this);
        if (node.getOperator() == AssignmentOperator.ASSIGN) {
            assignTo(node.getTarget(), value);
        } else {
            String op = node.getOperator() == AssignmentOperator.PLUS_ASSIGN ? "+"
                    : node.getOperator() == AssignmentOperator.MINUS_ASSIGN ? "-" : "*";
            String current = node.getTarget().accept(this);
            assignTo(node.getTarget(), emitter.binary(op, current, value));
        }
        return null;
    }

    @Override
    public String visit(IncrementStatement node) {
        String current = node.getTarget().accept(this);
        String fresh = emitter.binary(node.isIncrement() ? "+" : "-", current, "1");
        assignTo(node.getTarget(), fresh);
        return null;
    }

    @Override
    public String visit(IfStatement node) {
        String cond = node.getCondition().accept(this);
        boolean hasElseIf = node.getElseIfClauses() != null && !node.getElseIfClauses().isEmpty();
        if (!hasElseIf && node.getElseClause() == null) {
            String thenLabel = emitter.newLabel();
            String endLabel = emitter.newLabel();
            emitter.ifGoto(cond, "==", "1", thenLabel);
            emitter.jump(endLabel);
            emitter.label(thenLabel);
            for (Statement s : node.getStatements()) s.accept(this);
            emitter.label(endLabel);
            return null;
        }
        String endLabel = emitter.newLabel();
        String thenLabel = emitter.newLabel();
        String current = emitter.newLabel();
        emitter.ifGoto(cond, "==", "1", thenLabel);
        emitter.jump(current);
        emitter.label(thenLabel);
        for (Statement s : node.getStatements()) s.accept(this);
        emitter.jump(endLabel);

        if (node.getElseIfClauses() != null) {
            for (ElseIfClause c : node.getElseIfClauses()) {
                emitter.label(current);
                String cThen = emitter.newLabel();
                current = emitter.newLabel();
                String cc = c.getCondition().accept(this);
                emitter.ifGoto(cc, "==", "1", cThen);
                emitter.jump(current);
                emitter.label(cThen);
                for (Statement s : c.getStatements()) s.accept(this);
                emitter.jump(endLabel);
            }
        }
        emitter.label(current);
        if (node.getElseClause() != null) node.getElseClause().accept(this);
        emitter.label(endLabel);
        return null;
    }

    @Override public String visit(ElseIfClause node) { return null; }

    @Override
    public String visit(ElseClause node) {
        for (Statement s : node.getStatements()) s.accept(this);
        return null;
    }

    @Override
    public String visit(WhileStatement node) {
        String startLabel = emitter.newLabel();
        String bodyLabel = emitter.newLabel();
        String endLabel = emitter.newLabel();
        emitter.label(startLabel);
        String cond = node.getCondition().accept(this);
        emitter.ifGoto(cond, "==", "1", bodyLabel);
        emitter.jump(endLabel);
        emitter.label(bodyLabel);
        breakLabels.push(endLabel);
        continueLabels.push(startLabel);
        for (Statement s : node.getStatements()) s.accept(this);
        continueLabels.pop();
        breakLabels.pop();
        emitter.jump(startLabel);
        emitter.label(endLabel);
        return null;
    }

    @Override
    public String visit(DoWhileStatement node) {
        String startLabel = emitter.newLabel();
        String contLabel = emitter.newLabel();
        String endLabel = emitter.newLabel();
        emitter.label(startLabel);
        breakLabels.push(endLabel);
        continueLabels.push(contLabel);
        for (Statement s : node.getStatements()) s.accept(this);
        continueLabels.pop();
        breakLabels.pop();
        emitter.label(contLabel);
        String cond = node.getCondition().accept(this);
        emitter.ifGoto(cond, "==", "1", startLabel);
        emitter.jump(endLabel);
        emitter.label(endLabel);
        return null;
    }

    @Override
    public String visit(ForStatement node) {
        String startLabel = emitter.newLabel();
        String bodyLabel = emitter.newLabel();
        String contLabel = emitter.newLabel();
        String endLabel = emitter.newLabel();
        if (node.getInitializer() != null) node.getInitializer().accept(this);
        emitter.label(startLabel);
        if (node.getCondition() != null) {
            String cond = node.getCondition().accept(this);
            emitter.ifGoto(cond, "==", "1", bodyLabel);
            emitter.jump(endLabel);
        }
        emitter.label(bodyLabel);
        breakLabels.push(endLabel);
        continueLabels.push(contLabel);
        for (Statement s : node.getStatements()) s.accept(this);
        continueLabels.pop();
        breakLabels.pop();
        emitter.label(contLabel);
        if (node.getUpdate() != null) node.getUpdate().accept(this);
        emitter.jump(startLabel);
        emitter.label(endLabel);
        return null;
    }

    @Override
    public String visit(SwitchStatement node) {
        String endLabel = emitter.newLabel();
        String value = node.getExpression().accept(this);
        breakLabels.push(endLabel);
        String current = null;
        if (node.getCases() != null) {
            for (SwitchCase c : node.getCases()) {
                if (current != null) emitter.label(current);
                String caseLabel = emitter.newLabel();
                current = emitter.newLabel();
                String caseValue = c.getExpression().accept(this);
                emitter.ifGoto(value, "==", caseValue, caseLabel);
                emitter.jump(current);
                emitter.label(caseLabel);
                for (Statement s : c.getStatements()) s.accept(this);
                emitter.jump(endLabel);
            }
        }
        if (current != null) emitter.label(current);
        if (node.getDefaultCase() != null) node.getDefaultCase().accept(this);
        emitter.label(endLabel);
        breakLabels.pop();
        return null;
    }

    @Override public String visit(SwitchCase node) { return null; }
    @Override public String visit(DefaultCase node) { for (Statement s : node.getStatements()) s.accept(this); return null; }

    @Override
    public String visit(ReturnStatement node) {
        if (node.getExpression() != null) emitter.returnValue(node.getExpression().accept(this));
        else emitter.returnVoid();
        return null;
    }

    @Override
    public String visit(PrintStatement node) {
        for (Expression e : node.getExpressions()) emitter.print(e.accept(this));
        return null;
    }

    @Override public String visit(ReadStatement node) { emitter.readDiscard(); return null; }
    @Override public String visit(BreakStatement node) { if (!breakLabels.isEmpty()) emitter.jump(breakLabels.peek()); return null; }
    @Override public String visit(ContinueStatement node) { if (!continueLabels.isEmpty()) emitter.jump(continueLabels.peek()); return null; }
    @Override public String visit(ExpressionStatement node) { node.getExpression().accept(this); return null; }

    @Override public String visit(StructureDeclaration node) { return null; }
    @Override public String visit(StructureField node) { return null; }
    @Override public String visit(ArrayDeclaration node) { return null; }
    @Override public String visit(ArrayDimensions node) { return null; }
    @Override public String visit(ArrayInitializer node) { return null; }
    @Override public String visit(ArrayParameter node) { return null; }
    @Override public String visit(ExpressionInitializer node) { return null; }
    @Override public String visit(NormalParameter node) { return null; }
    @Override public String visit(StructureInitializer node) { return null; }
    @Override public String visit(StructureParameter node) { return null; }
    @Override public String visit(YType node) { return null; }

    // ---------------------------------------------------------------- expresiones

    @Override
    public String visit(BinaryExpression node) {
        switch (node.getOperator()) {
            case AND -> { return materializeAnd(node.getLeft(), node.getRight()); }
            case OR -> { return materializeOr(node.getLeft(), node.getRight()); }
            case EQUAL, NOT_EQUAL, LESS, GREATER, LESS_EQUAL, GREATER_EQUAL -> {
                String l = node.getLeft().accept(this);
                String r = node.getRight().accept(this);
                return emitter.materializeComparison(l, relop(node.getOperator()), r);
            }
            default -> {
                String l = node.getLeft().accept(this);
                String r = node.getRight().accept(this);
                return emitter.binary(arith(node.getOperator()), l, r);
            }
        }
    }

    @Override
    public String visit(UnaryExpression node) {
        if (node.getOperator() == UnaryOperator.NOT) return materializeNot(node.getExpression());
        if (node.getOperator() == UnaryOperator.NEGATE) return emitter.unaryNeg(node.getExpression().accept(this));
        String op = node.getOperator() == UnaryOperator.POST_INCREMENT ? "+" : "-";
        return postIncDec(node.getExpression(), op);
    }

    private String postIncDec(Expression target, String op) {
        String old = target.accept(this);
        assignTo(target, emitter.binary(op, old, "1"));
        return old;
    }

    @Override
    public String visit(VariableExpression node) {
        return emitter.loadVar(node.getName());
    }

    @Override
    public String visit(ArrayAccessExpression node) {
        String base = arrayBase(node.getArray());
        String idx = node.getIndex().accept(this);
        return emitter.stackLoadAt(emitter.binary("+", base, idx));
    }

    @Override
    public String visit(MemberAccessExpression node) {
        String base = structAddr(node.getObject());
        int off = fieldIndex(context.typeOf(node.getObject()), node.getMember());
        return emitter.stackLoadAt(emitter.binary("+", base, String.valueOf(off)));
    }

    @Override
    public String visit(FunctionCallExpression node) {
        List<String> args = new ArrayList<>();
        if (node.getArguments() != null) for (Expression a : node.getArguments()) args.add(a.accept(this));
        if (node.getFunction() instanceof VariableExpression ve) {
            Type t = context.typeOf(node);
            if (t != null && t.isVoid()) {
                emitter.callVoid(ve.getName(), args);
                return null;
            }
            return emitter.call(ve.getName(), args);
        }
        return emitter.literal("0");
    }

    @Override
    public String visit(ReadExpression node) {
        return emitter.read();
    }

    @Override
    public String visit(LiteralExpression node) {
        return emitter.literal(C3DEmitter.formatLiteral(node.getValue()));
    }

    // ---------------------------------------------------------------- booleanos

    private String materializeAnd(Expression left, Expression right) {
        String result = emitter.newTemp();
        String lFalse = emitter.newLabel();
        String lTrue = emitter.newLabel();
        String lCheck = emitter.newLabel();
        String lEnd = emitter.newLabel();
        String l = left.accept(this);
        emitter.ifGoto(l, "==", "1", lCheck);
        emitter.jump(lFalse);
        emitter.label(lCheck);
        String r = right.accept(this);
        emitter.ifGoto(r, "==", "1", lTrue);
        emitter.jump(lFalse);
        emitter.label(lTrue);
        emitter.assign(result, "1");
        emitter.jump(lEnd);
        emitter.label(lFalse);
        emitter.assign(result, "0");
        emitter.label(lEnd);
        return result;
    }

    private String materializeOr(Expression left, Expression right) {
        String result = emitter.newTemp();
        String lTrue = emitter.newLabel();
        String lFalse = emitter.newLabel();
        String lCheck = emitter.newLabel();
        String lEnd = emitter.newLabel();
        String l = left.accept(this);
        emitter.ifGoto(l, "==", "1", lTrue);
        emitter.jump(lCheck);
        emitter.label(lCheck);
        String r = right.accept(this);
        emitter.ifGoto(r, "==", "1", lTrue);
        emitter.jump(lFalse);
        emitter.label(lTrue);
        emitter.assign(result, "1");
        emitter.jump(lEnd);
        emitter.label(lFalse);
        emitter.assign(result, "0");
        emitter.label(lEnd);
        return result;
    }

    private String materializeNot(Expression operand) {
        String result = emitter.newTemp();
        String lTrue = emitter.newLabel();
        String lFalse = emitter.newLabel();
        String lEnd = emitter.newLabel();
        String v = operand.accept(this);
        emitter.ifGoto(v, "==", "0", lTrue);
        emitter.jump(lFalse);
        emitter.label(lTrue);
        emitter.assign(result, "1");
        emitter.jump(lEnd);
        emitter.label(lFalse);
        emitter.assign(result, "0");
        emitter.label(lEnd);
        return result;
    }

    // ---------------------------------------------------------------- helpers

    private void assignTo(Expression target, String value) {
        if (target instanceof VariableExpression v) {
            emitter.storeVar(v.getName(), value);
        } else if (target instanceof ArrayAccessExpression a) {
            String base = arrayBase(a.getArray());
            String idx = a.getIndex().accept(this);
            emitter.stackStoreAt(emitter.binary("+", base, idx), value);
        } else if (target instanceof MemberAccessExpression m) {
            String base = structAddr(m.getObject());
            int off = fieldIndex(context.typeOf(m.getObject()), m.getMember());
            emitter.stackStoreAt(emitter.binary("+", base, String.valueOf(off)), value);
        }
    }

    /** Dirección base de un arreglo: los locales son por valor; los params por referencia guardan la dirección. */
    private String arrayBase(Expression array) {
        if (array instanceof VariableExpression v) {
            return emitter.isReference(v.getName()) ? emitter.loadVar(v.getName()) : emitter.varAddr(v.getName());
        }
        return array.accept(this);
    }

    /** Dirección de un struct en el stack (encadenando offsets para miembros anidados). */
    private String structAddr(Expression obj) {
        if (obj instanceof VariableExpression v) {
            return emitter.isReference(v.getName()) ? emitter.loadVar(v.getName()) : emitter.varAddr(v.getName());
        }
        if (obj instanceof MemberAccessExpression m) {
            String parent = structAddr(m.getObject());
            int off = fieldIndex(context.typeOf(m.getObject()), m.getMember());
            return emitter.binary("+", parent, String.valueOf(off));
        }
        return obj.accept(this);
    }

    private int fieldIndex(Type owner, String member) {
        if (owner == null) return 0;
        AggregateType agg = context.getTypeTable().resolve(owner.getName());
        if (agg == null) return 0;
        for (int i = 0; i < agg.getFields().size(); i++) {
            if (agg.getFields().get(i).getName().equals(member)) return i;
        }
        return 0;
    }

    private void setPos(Object node, int offset) {
        Symbol s = context.symbolOf(node);
        if (s != null) s.setPosMemory(offset);
    }

    /** Celdas de un arreglo de tamaño constante (1 si el tamaño es dinámico/desconocido). */
    private int arrayCells(List<Expression> dims) {
        if (dims == null || dims.isEmpty()) return 1;
        int total = 1;
        for (Expression e : dims) {
            if (e instanceof LiteralExpression le && le.getValue() instanceof Integer n && n > 0) total *= n;
            else return 1;
        }
        return total;
    }

    private String relop(BinaryOperator op) {
        return switch (op) {
            case EQUAL -> "==";
            case NOT_EQUAL -> "!=";
            case LESS -> "<";
            case GREATER -> ">";
            case LESS_EQUAL -> "<=";
            case GREATER_EQUAL -> ">=";
            default -> "?";
        };
    }

    private String arith(BinaryOperator op) {
        return switch (op) {
            case ADD -> "+";
            case SUBTRACT -> "-";
            case MULTIPLY -> "*";
            case DIVIDE -> "/";
            case MODULO -> "%";
            default -> "?";
        };
    }
}
