package com.navi.backend.c3d.z;

import com.navi.backend.ast.z.declarations.ClassDeclaration;
import com.navi.backend.ast.z.declarations.ClassMember;
import com.navi.backend.ast.z.declarations.ConstructorDeclaration;
import com.navi.backend.ast.z.declarations.ExpressionInitializer;
import com.navi.backend.ast.z.declarations.FieldDeclaration;
import com.navi.backend.ast.z.declarations.MethodDeclaration;
import com.navi.backend.ast.z.declarations.Parameter;
import com.navi.backend.ast.z.declarations.VariableDeclaration;
import com.navi.backend.ast.z.declarations.VariableDeclarator;
import com.navi.backend.ast.z.declarations.ZType;
import com.navi.backend.ast.z.expressions.Expression;
import com.navi.backend.ast.z.statements.BlockStatement;
import com.navi.backend.ast.z.statements.SuperCallStatement;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Field;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// emision C3D de clases, metodos y constructores de Z; la clase actual va en
// ZResolverC3D para resolver campos de this, los inicializadores van en el ctor
class ZDeclarationC3D {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final ZResolverC3D resolver;
    private final AstZVisitor<String> visitor;
    private final List<VariableDeclarator> fieldInitializers = new ArrayList<>();

    ZDeclarationC3D(SemanticContext context, C3DEmitter emitter, ZResolverC3D resolver, AstZVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.resolver = resolver;
        this.visitor = visitor;
    }

    String classDeclaration(ClassDeclaration node) {
        resolver.setCurrentClass(context.getTypeTable().resolve(node.getName()));
        fieldInitializers.clear();
        boolean hasConstructor = false;
        if (node.getMembers() != null) {
            for (ClassMember member : node.getMembers()) {
                if (member instanceof FieldDeclaration field) {
                    for (VariableDeclarator variable : field.getVariables()) {
                        if (variable.getInitializer() != null) fieldInitializers.add(variable);
                    }
                } else if (member instanceof ConstructorDeclaration) {
                    hasConstructor = true;
                    member.accept(visitor);
                } else {
                    member.accept(visitor);
                }
            }
        }
        if (!hasConstructor) defaultConstructor();
        resolver.setCurrentClass(null);
        return null;
    }

    String fieldDeclaration(FieldDeclaration node) {
        return null;
    }

    String methodDeclaration(MethodDeclaration node) {
        List<Type> params = resolveParams(node.getParameters());
        String label = C3DEmitter.methodLabel(resolver.currentClass().getName(), node.getName(), params);
        emitter.entryLabel(label);
        emitter.enterFrame(label);
        emitter.declareParam("this");
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) resolver.setPos(p, emitter.declareParam(p.getName()));
        }
        node.getBody().accept(visitor);
        emitter.returnVoid();
        emitter.exitFrame();
        return null;
    }

    String constructorDeclaration(ConstructorDeclaration node) {
        List<Type> params = resolveParams(node.getParameters());
        String label = C3DEmitter.ctorLabel(resolver.currentClass().getName(), params);
        emitter.entryLabel(label);
        emitter.enterFrame(label);
        emitter.declareParam("this");
        if (node.getParameters() != null) {
            for (Parameter p : node.getParameters()) resolver.setPos(p, emitter.declareParam(p.getName()));
        }
        SuperCallStatement superCall = leadingSuper(node.getBody());
        if (superCall != null) emitSuperCall(superCall);
        else initializeParent();
        initializeOwnFields();
        initializeNullFields();
        node.getBody().accept(visitor);
        emitter.returnVoid();
        emitter.exitFrame();
        return null;
    }

    // super(...) si aparece como primera instruccion; si no, se omite y se llama
    // al constructor sin argumentos del padre
    private SuperCallStatement leadingSuper(BlockStatement body) {
        if (body == null || body.getStatements() == null || body.getStatements().isEmpty()) return null;
        return body.getStatements().get(0) instanceof SuperCallStatement sc ? sc : null;
    }

    // emite la llamada al constructor del padre con los argumentos de super(...)
    private void emitSuperCall(SuperCallStatement node) {
        AggregateType cls = resolver.currentClass();
        if (cls == null || cls.getSuperClass() == null) return;
        AggregateType parent = cls.getSuperClass();

        List<String> argPlaces = new ArrayList<>();
        List<Type> argTypes = new ArrayList<>();
        for (Expression a : node.getArguments()) {
            argPlaces.add(a.accept(visitor));
            Type t = context.typeOf(a);
            argTypes.add(t == null ? Type.ERROR : t);
        }
        Symbol ctor = parent.findConstructor(argTypes, context.getTypeTable());
        if (ctor == null) return; // error ya reportado en semantica

        String label = C3DEmitter.ctorLabel(parent.getName(), ctor.getSignature().getParameters());
        List<String> callArgs = new ArrayList<>();
        callArgs.add(resolver.thisPlace());
        callArgs.addAll(argPlaces);
        emitter.callVoid(label, callArgs);
    }

    private void defaultConstructor() {
        AggregateType cls = resolver.currentClass();
        if (cls == null) return;
        String label = C3DEmitter.ctorLabel(cls.getName(), List.of());
        emitter.entryLabel(label);
        emitter.enterFrame(label);
        emitter.declareParam("this");
        initializeParent();
        initializeOwnFields();
        initializeNullFields();
        emitter.returnVoid();
        emitter.exitFrame();
    }

    // Cuando no hay super(...) explicito se invoca el constructor sin argumentos del padre.
    private void initializeParent() {
        AggregateType parent = resolver.currentClass() == null ? null : resolver.currentClass().getSuperClass();
        if (parent == null) return;
        var constructor = parent.findConstructor(List.of(), context.getTypeTable());
        if (constructor == null) return;
        String label = C3DEmitter.ctorLabel(parent.getName(), constructor.getSignature().getParameters());
        emitter.callVoid(label, List.of(resolver.thisPlace()));
    }

    private void initializeOwnFields() {
        for (VariableDeclarator variable : fieldInitializers) {
            if (variable.getInitializer() instanceof ExpressionInitializer initializer) {
                int offset = resolver.fieldIndex(resolver.currentClass(), variable.getName());
                emitter.heapStore(resolver.thisPlace(), String.valueOf(offset),
                        initializer.getExpression().accept(visitor));
            }
        }
    }

    // deja en null los campos de referencia sin inicializador (si no la celda
    // del heap queda en 0); los primitivos ya valen 0 por defecto
    private void initializeNullFields() {
        AggregateType cls = resolver.currentClass();

        if (cls == null) return;

        Set<String> initialized = new HashSet<>();

        for (VariableDeclarator v : fieldInitializers) initialized.add(v.getName());

        for (Field field : cls.getFields()) {
            if (initialized.contains(field.getName())) continue;

            Type type = field.getType();

            if (type == null || !needsNullDefault(type)) continue;

            int idx = resolver.fieldIndex(cls, field.getName());
            emitter.heapStore(resolver.thisPlace(), String.valueOf(idx), emitter.literal("null"));
        }
    }

    private static boolean needsNullDefault(Type type) {
        return type.isString() || type.isArray() || type.isAggregate();
    }

    String variableDeclaration(VariableDeclaration node) {
        for (VariableDeclarator v : node.getVariables()) {
            resolver.setPos(v, emitter.declareLocal(v.getName()));
            if (v.getInitializer() instanceof ExpressionInitializer ei) {
                emitter.storeVar(v.getName(), ei.getExpression().accept(visitor));
            }
        }
        return null;
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
}
