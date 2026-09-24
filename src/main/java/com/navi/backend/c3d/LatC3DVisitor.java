package com.navi.backend.c3d;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.ArrayDeclaration;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.BinaryExpression;
import com.navi.backend.ast.lat.expressions.BinaryOperator;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.expressions.FunctionCallExpression;
import com.navi.backend.ast.lat.expressions.MemberAccessExpression;
import com.navi.backend.ast.lat.expressions.ObjectCreationExpression;
import com.navi.backend.ast.lat.expressions.UnaryExpression;
import com.navi.backend.ast.lat.expressions.UnaryOperator;
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
import com.navi.backend.semantic.AggregateType;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.Type;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Genera C3D para Lat siguiendo los apuntes de clase. Locales/parámetros usan
 * {@code stack[BP + off]} y las variables globales {@code stack[GP + off]};
 * los objetos (clases importadas de Z) viven en el heap. Booleanos
 * materializados con saltos y bifurcaciones con {@code if a op b goto L}.
 */
public class LatC3DVisitor implements AstLatVisitor<String> {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final Deque<String> breakLabels = new ArrayDeque<>();
    private final Deque<String> continueLabels = new ArrayDeque<>();

    private boolean globalSection = false;

    public LatC3DVisitor(SemanticContext context, C3DEmitter emitter) {
        this.context = context;
        this.emitter = emitter;
    }

    public void generate(Program program) {
        program.accept(this);
    }

    @Override
    public String visit(Program node) {
        if (node.getGlobalVariables() != null) node.getGlobalVariables().accept(this);
        if (node.getFunctions() != null) for (FunctionDeclaration f : node.getFunctions()) f.accept(this);
        emitter.entryLabel("main");
        emitter.enterFrame("main");
        if (node.getMainStatements() != null) for (Statement s : node.getMainStatements()) s.accept(this);
        emitter.halt();
        emitter.exitFrame();
        return null;
    }

    @Override public String visit(ImportDeclaration node) { return null; }

    @Override
    public String visit(GlobalVariableSection node) {
        emitter.resume();
        globalSection = true;
        for (Declaration d : node.getDeclarations()) d.accept(this);
        globalSection = false;
        return null;
    }

    @Override
    public String visit(LocalVariableSection node) {
        for (Declaration d : node.getDeclarations()) d.accept(this);
        return null;
    }

    @Override
    public String visit(VariableDeclaration node) {
        setPos(node, declare(node.getName()));
        if (node.getInitializer() != null) {
            String value = initializerValue(node.getInitializer());
            if (value != null) emitter.storeVar(node.getName(), value);
        }
        return null;
    }

    @Override
    public String visit(ArrayDeclaration node) {
        setPos(node, declare(node.getName()));
        emitter.reserve(arrayCells(node.getSizes()) - 1);
        if (node.getInitializer() != null) {
            String base = emitter.varAddr(node.getName());
            int i = 0;
            for (AstLatNode el : node.getInitializer().getElements()) {
                String value = el instanceof Expression e ? e.accept(this) : null;
                if (value != null) {
                    String addr = emitter.binary("+", base, String.valueOf(i));
                    emitter.stackStoreAt(addr, value);
                }
                i++;
            }
        }
        return null;
    }

    @Override
    public String visit(FunctionDeclaration node) {
        emitter.entryLabel(node.getName());
        emitter.enterFrame(node.getName());
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) setPos(p, emitter.declareParam(p.getName()));
        }
        if (node.getBody() != null) node.getBody().accept(this);
        emitter.returnVoid();
        emitter.exitFrame();
        return null;
    }

    @Override
    public String visit(FunctionBody node) {
        if (node.getLocalVariables() != null) node.getLocalVariables().accept(this);
        if (node.getBody() != null) node.getBody().accept(this);
        return null;
    }

    @Override public String visit(Parameter node) { return null; }

    // ---------------------------------------------------------------- statements

    @Override
    public String visit(BlockStatement node) {
        for (Statement s : node.getStatements()) s.accept(this);
        return null;
    }

    @Override
    public String visit(AssignmentStatement node) {
        String value = initializerValue(node.getInitializer());
        if (value != null) assignTo(node.getTarget(), value);
        return null;
    }

    @Override
    public String visit(IfStatement node) {
        String cond = node.getCondition().accept(this);
        boolean hasElseIf = node.getElseIfStatements() != null && !node.getElseIfStatements().isEmpty();
        if (!hasElseIf && node.getElseBlock() == null) {
            String thenLabel = emitter.newLabel();
            String endLabel = emitter.newLabel();
            emitter.ifGoto(cond, "==", "1", thenLabel);
            emitter.jump(endLabel);
            emitter.label(thenLabel);
            node.getThenBlock().accept(this);
            emitter.label(endLabel);
            return null;
        }
        String endLabel = emitter.newLabel();
        String thenLabel = emitter.newLabel();
        String current = emitter.newLabel();
        emitter.ifGoto(cond, "==", "1", thenLabel);
        emitter.jump(current);
        emitter.label(thenLabel);
        node.getThenBlock().accept(this);
        emitter.jump(endLabel);

        if (node.getElseIfStatements() != null) {
            for (ElseIfStatement e : node.getElseIfStatements()) {
                emitter.label(current);
                String eThen = emitter.newLabel();
                current = emitter.newLabel();
                String c = e.getCondition().accept(this);
                emitter.ifGoto(c, "==", "1", eThen);
                emitter.jump(current);
                emitter.label(eThen);
                e.getBlock().accept(this);
                emitter.jump(endLabel);
            }
        }
        emitter.label(current);
        if (node.getElseBlock() != null) node.getElseBlock().accept(this);
        emitter.label(endLabel);
        return null;
    }

    @Override public String visit(ElseIfStatement node) { return null; }

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
        node.getBlock().accept(this);
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
        node.getBlock().accept(this);
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
    public String visit(ReturnStatement node) {
        if (node.getExpression() != null) emitter.returnValue(node.getExpression().accept(this));
        else emitter.returnVoid();
        return null;
    }

    @Override
    public String visit(IncrementStatement node) {
        String old = node.getTarget().accept(this);
        String op = node.getOperator() == UnaryOperator.POST_INCREMENT ? "+" : "-";
        assignTo(node.getTarget(), emitter.binary(op, old, "1"));
        return null;
    }

    @Override
    public String visit(PrintStatement node) {
        for (Expression e : node.getExpressions()) emitter.print(e.accept(this));
        return null;
    }

    @Override
    public String visit(ReadStatement node) {
        if (node.getTarget() != null) assignTo(node.getTarget(), emitter.read());
        else emitter.readDiscard();
        return null;
    }

    @Override
    public String visit(FunctionCallStatement node) {
        emitCall(node.getCallee(), node.getArguments(), true);
        return null;
    }

    @Override public String visit(BreakStatement node) { if (!breakLabels.isEmpty()) emitter.jump(breakLabels.peek()); return null; }
    @Override public String visit(ContinueStatement node) { if (!continueLabels.isEmpty()) emitter.jump(continueLabels.peek()); return null; }

    // ---------------------------------------------------------------- expresiones

    @Override
    public String visit(BinaryExpression node) {
        switch (node.getOperator()) {
            case AND -> { return materializeAnd(node.getLeft(), node.getRight()); }
            case OR -> { return materializeOr(node.getLeft(), node.getRight()); }
            case EQUAL, NOT_EQUAL, LESS, LESS_EQUAL, GREATER, GREATER_EQUAL -> {
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
    public String visit(VariableExpression node) {
        return emitter.loadVar(node.getName());
    }

    @Override
    public String visit(ArrayAccessExpression node) {
        String base = emitter.varAddr(node.getArray() instanceof VariableExpression v ? v.getName() : null);
        if (base == null) base = node.getArray().accept(this);
        String idx = node.getIndex().accept(this);
        return emitter.stackLoadAt(emitter.binary("+", base, idx));
    }

    @Override
    public String visit(MemberAccessExpression node) {
        String obj = node.getObject().accept(this);
        Type owner = context.typeOf(node.getObject());
        int off = fieldIndex(owner, node.getMember());
        if (owner != null && owner.isStruct()) {
            return emitter.stackLoadAt(emitter.binary("+", structAddr(node.getObject()), String.valueOf(off)));
        }
        return emitter.heapLoad(obj, String.valueOf(off));
    }

    @Override
    public String visit(FunctionCallExpression node) {
        Type t = context.typeOf(node);
        return emitCall(node.getCallee(), node.getArguments(), t != null && t.isVoid());
    }

    @Override
    public String visit(ObjectCreationExpression node) {
        List<String> args = evalArgs(node.getArguments());
        AggregateType agg = context.getTypeTable().resolve(node.getType());
        List<Type> argTypes = argTypes(node.getArguments());
        String ctor = agg != null && agg.findConstructor(argTypes) != null
                ? C3DEmitter.ctorLabel(node.getType(), agg.findConstructor(argTypes).getSignature().getParameters())
                : node.getType() + "_init";
        String obj = emitter.heapAlloc(String.valueOf(objectSize(node.getType())));
        List<String> callArgs = new ArrayList<>();
        callArgs.add(obj);
        callArgs.addAll(args);
        emitter.callVoid(ctor, callArgs);
        return obj;
    }

    @Override public String visit(BooleanLiteral node) { return emitter.literal(node.isValue() ? "1" : "0"); }
    @Override public String visit(CharLiteral node) { return emitter.literal("'" + node.getValue() + "'"); }
    @Override public String visit(DecimalLiteral node) { return emitter.literal(String.valueOf(node.getValue())); }
    @Override public String visit(NumberLiteral node) { return emitter.literal(String.valueOf(node.getValue())); }
    @Override public String visit(StringLiteral node) { return emitter.literal("\"" + node.getValue() + "\""); }

    @Override public String visit(ArrayInitializer node) { return null; }
    @Override public String visit(ExpressionInitializer node) { return null; }
    @Override public String visit(StructInitializer node) { return null; }
    @Override public String visit(StructFieldInitializer node) { return null; }

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

    private String emitCall(Expression callee, List<Expression> args, boolean isVoid) {
        List<String> argPlaces = evalArgs(args);
        if (callee instanceof VariableExpression ve) {
            if (isVoid) {
                emitter.callVoid(ve.getName(), argPlaces);
                return null;
            }
            return emitter.call(ve.getName(), argPlaces);
        }
        if (callee instanceof MemberAccessExpression ma) {
            String objPlace = ma.getObject().accept(this);
            Type objType = context.typeOf(ma.getObject());
            String label = methodLabel(objType, ma.getMember(), argTypes(args));
            List<String> callArgs = new ArrayList<>();
            callArgs.add(objPlace);
            callArgs.addAll(argPlaces);
            if (isVoid) {
                emitter.callVoid(label, callArgs);
                return null;
            }
            return emitter.call(label, callArgs);
        }
        return emitter.literal("0");
    }

    private String methodLabel(Type objType, String name, List<Type> argTypes) {
        if (objType == null || !objType.isClass()) return "unknown_" + name;
        AggregateType agg = context.getTypeTable().resolve(objType.getName());
        if (agg == null) return objType.getName() + "_" + name;
        Symbol m = agg.findMethod(name, argTypes);
        if (m == null) return objType.getName() + "_" + name;
        return C3DEmitter.methodLabel(objType.getName(), name, m.getSignature().getParameters());
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

    private String initializerValue(Initializer init) {
        if (init instanceof ExpressionInitializer ei) return ei.getExpression().accept(this);
        return null; // struct initializer no se emite (requiere layout; pendiente)
    }

    private void assignTo(Expression target, String value) {
        if (target instanceof VariableExpression v) {
            emitter.storeVar(v.getName(), value);
        } else if (target instanceof ArrayAccessExpression a) {
            String base = a.getArray() instanceof VariableExpression v ? emitter.varAddr(v.getName()) : a.getArray().accept(this);
            String idx = a.getIndex().accept(this);
            emitter.stackStoreAt(emitter.binary("+", base, idx), value);
        } else if (target instanceof MemberAccessExpression m) {
            Type owner = context.typeOf(m.getObject());
            int off = fieldIndex(owner, m.getMember());
            if (owner != null && owner.isStruct()) {
                emitter.stackStoreAt(emitter.binary("+", structAddr(m.getObject()), String.valueOf(off)), value);
            } else {
                emitter.heapStore(m.getObject().accept(this), String.valueOf(off), value);
            }
        }
    }

    private String structAddr(Expression obj) {
        if (obj instanceof VariableExpression v) return emitter.varAddr(v.getName());
        if (obj instanceof MemberAccessExpression m) {
            int off = fieldIndex(context.typeOf(m.getObject()), m.getMember());
            return emitter.binary("+", structAddr(m.getObject()), String.valueOf(off));
        }
        return obj.accept(this);
    }

    private int declare(String name) {
        return globalSection ? emitter.declareGlobal(name) : emitter.declareLocal(name);
    }

    /** Celdas de un arreglo de tamaño constante (1 si el tamaño es dinámico/desconocido). */
    private int arrayCells(List<Expression> sizes) {
        if (sizes == null || sizes.isEmpty()) return 1;
        int total = 1;
        for (Expression e : sizes) {
            if (e instanceof NumberLiteral n && n.getValue() > 0) total *= n.getValue();
            else return 1;
        }
        return total;
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
            case LESS_EQUAL -> "<=";
            case GREATER -> ">";
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
            default -> "?";
        };
    }
}
