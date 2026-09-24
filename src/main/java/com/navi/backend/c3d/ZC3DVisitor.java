package com.navi.backend.c3d;

import com.navi.backend.ast.z.AstZNode;
import com.navi.backend.ast.z.declarations.ArrayDimensions;
import com.navi.backend.ast.z.declarations.ArrayInitializer;
import com.navi.backend.ast.z.declarations.ClassDeclaration;
import com.navi.backend.ast.z.declarations.ClassMember;
import com.navi.backend.ast.z.declarations.ConstructorDeclaration;
import com.navi.backend.ast.z.declarations.ExpressionInitializer;
import com.navi.backend.ast.z.declarations.FieldDeclaration;
import com.navi.backend.ast.z.declarations.Initializer;
import com.navi.backend.ast.z.declarations.MethodDeclaration;
import com.navi.backend.ast.z.declarations.Parameter;
import com.navi.backend.ast.z.declarations.VariableDeclaration;
import com.navi.backend.ast.z.declarations.VariableDeclarator;
import com.navi.backend.ast.z.declarations.ZType;
import com.navi.backend.ast.z.expressions.ArrayAccessExpression;
import com.navi.backend.ast.z.expressions.ArrayCreationExpression;
import com.navi.backend.ast.z.expressions.AssignmentExpression;
import com.navi.backend.ast.z.expressions.BinaryExpression;
import com.navi.backend.ast.z.expressions.BinaryOperator;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.expressions.ExpressionList;
import com.navi.backend.ast.z.expressions.FunctionCallExpression;
import com.navi.backend.ast.z.expressions.MemberAccessExpression;
import com.navi.backend.ast.z.expressions.NullExpression;
import com.navi.backend.ast.z.expressions.ObjectCreationExpression;
import com.navi.backend.ast.z.expressions.ReadExpression;
import com.navi.backend.ast.z.expressions.TernaryExpression;
import com.navi.backend.ast.z.expressions.UnaryExpression;
import com.navi.backend.ast.z.expressions.UnaryOperator;
import com.navi.backend.ast.z.expressions.VariableExpression;
import com.navi.backend.ast.z.expressions.literals.LiteralExpression;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.ast.z.statements.AssignmentOperator;
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.statements.BreakStatement;
import com.navi.backend.ast.z.statements.ContinueStatement;
import com.navi.backend.ast.z.statements.DefaultCase;
import com.navi.backend.ast.z.statements.DoWhileStatement;
import com.navi.backend.ast.z.statements.ElseClause;
import com.navi.backend.ast.z.statements.ElseIfClause;
import com.navi.backend.ast.z.statements.ExpressionStatement;
import com.navi.backend.ast.z.statements.ForStatement;
import com.navi.backend.ast.z.statements.IfStatement;
import com.navi.backend.ast.z.statements.PrintStatement;
import com.navi.backend.ast.z.statements.PrintlnStatement;
import com.navi.backend.ast.z.statements.ReadlnStatement;
import com.navi.backend.ast.z.statements.ReturnStatement;
import com.navi.backend.ast.z.statements.Statement;
import com.navi.backend.ast.z.statements.SwitchCase;
import com.navi.backend.ast.z.statements.SwitchStatement;
import com.navi.backend.ast.z.statements.VariableDeclarationStatement;
import com.navi.backend.ast.z.statements.WhileStatement;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.Type;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Genera C3D para Z siguiendo los apuntes de clase. Locales/parámetros viven en
 * el stack ({@code stack[BP + off]}); los objetos y arreglos viven en el heap
 * ({@code t = HP; HP = HP + size} y {@code heap[base + off]}). El receptor
 * {@code this} es el parámetro 0 del marco. Booleanos materializados con saltos.
 */
public class ZC3DVisitor implements AstZVisitor<String> {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final Deque<String> breakLabels = new ArrayDeque<>();
    private final Deque<String> continueLabels = new ArrayDeque<>();

    private AggregateType currentClass;
    private final List<VariableDeclarator> fieldInitializers = new ArrayList<>();

    public ZC3DVisitor(SemanticContext context, C3DEmitter emitter) {
        this.context = context;
        this.emitter = emitter;
    }

    public void generate(ProgramZ program) {
        program.accept(this);
    }

    @Override
    public String visit(ProgramZ node) {
        if (node.getClassDeclaration() != null) node.getClassDeclaration().accept(this);
        return null;
    }

    @Override
    public String visit(ClassDeclaration node) {
        currentClass = context.getTypeTable().resolve(node.getName());
        fieldInitializers.clear();
        if (node.getMembers() != null) for (ClassMember m : node.getMembers()) m.accept(this);
        currentClass = null;
        return null;
    }

    @Override
    public String visit(FieldDeclaration node) {
        for (VariableDeclarator v : node.getVariables()) {
            if (v.getInitializer() != null) fieldInitializers.add(v);
        }
        return null;
    }

    @Override
    public String visit(MethodDeclaration node) {
        List<Type> params = resolveParams(node.getParameters());
        String label = C3DEmitter.methodLabel(currentClass.getName(), node.getName(), params);
        emitter.entryLabel(label);
        emitter.enterFrame(label);
        emitter.declareParam("this");
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) setPos(p, emitter.declareParam(p.getName()));
        }
        node.getBody().accept(this);
        emitter.returnVoid();
        emitter.exitFrame();
        return null;
    }

    @Override
    public String visit(ConstructorDeclaration node) {
        List<Type> params = resolveParams(node.getParameters());
        String label = C3DEmitter.ctorLabel(currentClass.getName(), params);
        emitter.entryLabel(label);
        emitter.enterFrame(label);
        emitter.declareParam("this");
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) setPos(p, emitter.declareParam(p.getName()));
        }
        for (VariableDeclarator v : fieldInitializers) {
            if (v.getInitializer() instanceof ExpressionInitializer ei) {
                int idx = fieldIndex(currentClass, v.getName());
                if (idx >= 0) emitter.heapStore(thisPlace(), String.valueOf(idx), ei.getExpression().accept(this));
            }
        }
        node.getBody().accept(this);
        emitter.returnVoid();
        emitter.exitFrame();
        return null;
    }

    @Override public String visit(ClassMember node) { return null; }
    @Override public String visit(Parameter node) { return null; }
    @Override public String visit(ZType node) { return null; }
    @Override public String visit(ArrayDimensions node) { return null; }
    @Override public String visit(VariableDeclarator node) { return null; }
    @Override public String visit(Initializer node) { return null; }
    @Override public String visit(ExpressionInitializer node) { return null; }
    @Override public String visit(ArrayInitializer node) { return null; }

    // ---------------------------------------------------------------- statements

    @Override
    public String visit(BlockStatement node) {
        for (Statement s : node.getStatements()) s.accept(this);
        return null;
    }

    @Override
    public String visit(VariableDeclarationStatement node) {
        node.getDeclaration().accept(this);
        return null;
    }

    @Override
    public String visit(VariableDeclaration node) {
        for (VariableDeclarator v : node.getVariables()) {
            setPos(v, emitter.declareLocal(v.getName()));
            if (v.getInitializer() instanceof ExpressionInitializer ei) {
                emitter.storeVar(v.getName(), ei.getExpression().accept(this));
            }
        }
        return null;
    }

    @Override
    public String visit(ExpressionStatement node) {
        node.getExpression().accept(this);
        return null;
    }

    @Override
    public String visit(IfStatement node) {
        String cond = node.getCondition().accept(this);
        boolean hasElseIf = node.getElseIfClauses() != null && !node.getElseIfClauses().isEmpty();
        if (!hasElseIf && node.getElseBranch() == null) {
            String thenLabel = emitter.newLabel();
            String endLabel = emitter.newLabel();
            emitter.ifGoto(cond, "==", "1", thenLabel);
            emitter.jump(endLabel);
            emitter.label(thenLabel);
            node.getThenBranch().accept(this);
            emitter.label(endLabel);
            return null;
        }
        String endLabel = emitter.newLabel();
        String thenLabel = emitter.newLabel();
        String current = emitter.newLabel();
        emitter.ifGoto(cond, "==", "1", thenLabel);
        emitter.jump(current);
        emitter.label(thenLabel);
        node.getThenBranch().accept(this);
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
                c.getBranch().accept(this);
                emitter.jump(endLabel);
            }
        }
        emitter.label(current);
        if (node.getElseBranch() != null) node.getElseBranch().accept(this);
        emitter.label(endLabel);
        return null;
    }

    @Override public String visit(ElseIfClause node) { return null; }
    @Override public String visit(ElseClause node) { node.getBranch().accept(this); return null; }

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
        node.getBody().accept(this);
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
        node.getBody().accept(this);
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
        if (node.getCondition() instanceof Expression e) {
            String cond = e.accept(this);
            emitter.ifGoto(cond, "==", "1", bodyLabel);
            emitter.jump(endLabel);
        }
        emitter.label(bodyLabel);
        breakLabels.push(endLabel);
        continueLabels.push(contLabel);
        if (node.getBlock() != null) node.getBlock().accept(this);
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

    @Override public String visit(PrintStatement node) { for (Expression e : node.getArguments()) emitter.print(e.accept(this)); return null; }
    @Override public String visit(PrintlnStatement node) { for (Expression e : node.getArguments()) emitter.print(e.accept(this)); return null; }
    @Override public String visit(ReadlnStatement node) { emitter.readDiscard(); return null; }
    @Override public String visit(BreakStatement node) { if (!breakLabels.isEmpty()) emitter.jump(breakLabels.peek()); return null; }
    @Override public String visit(ContinueStatement node) { if (!continueLabels.isEmpty()) emitter.jump(continueLabels.peek()); return null; }

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
        return switch (node.getOperator()) {
            case NOT -> materializeNot(node.getExpression());
            case NEGATE -> emitter.unaryNeg(node.getExpression().accept(this));
            case POSITIVE -> node.getExpression().accept(this);
            case POST_INCREMENT -> postIncDec(node.getExpression(), "+");
            case POST_DECREMENT -> postIncDec(node.getExpression(), "-");
        };
    }

    private String postIncDec(Expression target, String op) {
        String old = target.accept(this);
        assignTo(target, emitter.binary(op, old, "1"));
        return old;
    }

    @Override
    public String visit(AssignmentExpression node) {
        String value = node.getValue().accept(this);
        if (node.getOperator() == AssignmentOperator.ASSIGN) {
            assignTo(node.getTarget(), value);
        } else {
            String op = node.getOperator() == AssignmentOperator.PLUS_ASSIGN ? "+"
                    : node.getOperator() == AssignmentOperator.MINUS_ASSIGN ? "-" : "*";
            String current = node.getTarget().accept(this);
            assignTo(node.getTarget(), emitter.binary(op, current, value));
        }
        return value;
    }

    @Override
    public String visit(TernaryExpression node) {
        String result = emitter.newTemp();
        String cond = node.getCondition().accept(this);
        String thenLabel = emitter.newLabel();
        String elseLabel = emitter.newLabel();
        String endLabel = emitter.newLabel();
        emitter.ifGoto(cond, "==", "1", thenLabel);
        emitter.jump(elseLabel);
        emitter.label(thenLabel);
        emitter.assign(result, node.getThenExpression().accept(this));
        emitter.jump(endLabel);
        emitter.label(elseLabel);
        emitter.assign(result, node.getElseExpression().accept(this));
        emitter.label(endLabel);
        return result;
    }

    @Override
    public String visit(VariableExpression node) {
        return place(node.getName());
    }

    @Override
    public String visit(ArrayAccessExpression node) {
        String base = node.getArray().accept(this);
        String idx = node.getIndex().accept(this);
        return emitter.heapLoadAt(emitter.binary("+", base, idx));
    }

    @Override
    public String visit(MemberAccessExpression node) {
        String base = node.getObject().accept(this);
        int off = fieldIndex(context.typeOf(node.getObject()), node.getMember());
        return emitter.heapLoad(base, String.valueOf(off));
    }

    @Override
    public String visit(FunctionCallExpression node) {
        List<String> argPlaces = evalArgs(node.getArguments());
        if (node.getFunction() instanceof VariableExpression ve) {
            String label = resolveMethodLabel(currentClass.getName(), ve.getName(), node.getArguments());
            List<String> callArgs = new ArrayList<>();
            callArgs.add(thisPlace());
            callArgs.addAll(argPlaces);
            return emitter.call(label, callArgs);
        }
        if (node.getFunction() instanceof MemberAccessExpression ma) {
            String obj = ma.getObject().accept(this);
            Type objType = context.typeOf(ma.getObject());
            String label = resolveMethodLabel(objType == null ? "" : objType.getName(), ma.getMember(), node.getArguments());
            List<String> callArgs = new ArrayList<>();
            callArgs.add(obj);
            callArgs.addAll(argPlaces);
            return emitter.call(label, callArgs);
        }
        return emitter.literal("0");
    }

    private String resolveMethodLabel(String owner, String name, List<Expression> args) {
        AggregateType agg = context.getTypeTable().resolve(owner);
        List<Type> argTypes = argTypes(args);
        if (agg == null) return owner + "_" + name;
        Symbol m = agg.findMethod(name, argTypes);
        if (m == null) return owner + "_" + name;
        return C3DEmitter.methodLabel(owner, name, m.getSignature().getParameters());
    }

    @Override
    public String visit(ObjectCreationExpression node) {
        List<String> argPlaces = evalArgs(node.getArguments());
        AggregateType agg = context.getTypeTable().resolve(node.getTypeName());
        List<Type> argTypes = argTypes(node.getArguments());
        String ctor = C3DEmitter.ctorLabel(node.getTypeName(), agg != null && agg.findConstructor(argTypes) != null
                ? agg.findConstructor(argTypes).getSignature().getParameters() : List.of());
        String obj = emitter.heapAlloc(String.valueOf(objectSize(node.getTypeName())));
        List<String> callArgs = new ArrayList<>();
        callArgs.add(obj);
        callArgs.addAll(argPlaces);
        emitter.callVoid(ctor, callArgs);
        return obj;
    }

    @Override
    public String visit(ArrayCreationExpression node) {
        String size = null;
        if (node.getDimensions() != null) {
            for (Expression d : node.getDimensions()) {
                String v = d.accept(this);
                size = size == null ? v : emitter.binary("*", size, v);
            }
        }
        return emitter.heapAlloc(size == null ? "0" : size);
    }

    @Override
    public String visit(NullExpression node) {
        return emitter.literal("null");
    }

    @Override
    public String visit(ReadExpression node) {
        return emitter.read();
    }

    @Override
    public String visit(ExpressionList node) {
        String last = null;
        for (Expression e : node.getExpressions()) last = e.accept(this);
        return last;
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
            if (emitter.localOffset(v.getName()) != null) {
                emitter.storeVar(v.getName(), value);
                return;
            }
            int idx = currentClass == null ? -1 : fieldIndex(currentClass, v.getName());
            if (idx >= 0) emitter.heapStore(thisPlace(), String.valueOf(idx), value);
            else emitter.storeVar(v.getName(), value);
        } else if (target instanceof ArrayAccessExpression a) {
            String base = a.getArray().accept(this);
            String idx = a.getIndex().accept(this);
            emitter.heapStoreAt(emitter.binary("+", base, idx), value);
        } else if (target instanceof MemberAccessExpression m) {
            String base = m.getObject().accept(this);
            int off = fieldIndex(context.typeOf(m.getObject()), m.getMember());
            emitter.heapStore(base, String.valueOf(off), value);
        }
    }

    /** Lugar de una variable: local/param del stack, campo del objeto actual (heap) o nombre suelto. */
    private String place(String name) {
        if (emitter.localOffset(name) != null) return emitter.loadVar(name);
        int idx = currentClass == null ? -1 : fieldIndex(currentClass, name);
        if (idx >= 0) return emitter.heapLoad(thisPlace(), String.valueOf(idx));
        return emitter.loadVar(name);
    }

    /** Receptor del método actual: parámetro 0 del marco. */
    private String thisPlace() {
        return emitter.localOffset("this") != null ? emitter.loadVar("this") : "this";
    }

    private List<Type> resolveParams(List<Parameter> parameters) {
        List<Type> types = new ArrayList<>();
        if (parameters != null) for (Parameter p : parameters) types.add(resolveType(p.getType()));
        return types;
    }

    private Type resolveType(ZType node) {
        String name = node.getName();
        Type base = switch (name) {
            case "int" -> Type.INT;
            case "double" -> Type.DOUBLE;
            case "char" -> Type.CHAR;
            case "boolean" -> Type.BOOLEAN;
            case "String" -> Type.STRING;
            case "void" -> Type.VOID;
            default -> {
                AggregateType a = context.getTypeTable().resolve(name);
                yield a == null ? Type.ERROR : Type.classType(name);
            }
        };
        if (node.getArrayDimensions() != null && node.getArrayDimensions().getDimensions() > 0) {
            return Type.array(base, node.getArrayDimensions().getDimensions());
        }
        return base;
    }

    private List<String> evalArgs(List<Expression> args) {
        List<String> places = new ArrayList<>();
        if (args != null) for (Expression a : args) places.add(a.accept(this));
        return places;
    }

    private List<Type> argTypes(List<Expression> args) {
        List<Type> types = new ArrayList<>();
        if (args != null) for (Expression a : args) {
            Type t = context.typeOf(a);
            types.add(t == null ? Type.ERROR : t);
        }
        return types;
    }

    private int fieldIndex(Type owner, String member) {
        if (owner == null || !owner.isClass()) return 0;
        return fieldIndex(context.getTypeTable().resolve(owner.getName()), member);
    }

    private int fieldIndex(AggregateType agg, String member) {
        if (agg == null) return 0;
        for (int i = 0; i < agg.getFields().size(); i++) {
            if (agg.getFields().get(i).getName().equals(member)) return i;
        }
        return 0;
    }

    private int objectSize(String className) {
        AggregateType agg = context.getTypeTable().resolve(className);
        return agg == null ? 1 : Math.max(1, agg.getFields().size());
    }

    private void setPos(Object node, int offset) {
        Symbol s = context.symbolOf(node);
        if (s != null) s.setPosMemory(offset);
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
