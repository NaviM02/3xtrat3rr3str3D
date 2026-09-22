package com.navi.backend.semantic.z;

import com.navi.backend.ast.z.declarations.*;
import com.navi.backend.ast.z.expressions.*;
import com.navi.backend.ast.z.expressions.literals.LiteralExpression;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.ast.z.statements.*;
import com.navi.backend.ast.z.visitors.AstZVisitor;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.enums.Language;
import com.navi.backend.semantic.enums.ScopeKind;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.enums.TypeKind;
import com.navi.backend.semantic.enums.Visibility;
import com.navi.backend.semantic.model.FunctionSignature;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.SymbolModifiers;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ZDeclarationVisitor implements AstZVisitor<Void> {
    private final SemanticContext context;

    public ZDeclarationVisitor(SemanticContext context) {
        this.context = context;
    }

    public void build(ProgramZ program) {
        program.accept(this);
    }

    @Override
    public Void visit(ProgramZ node) {
        if (node.getClassDeclaration() != null) {
            node.getClassDeclaration().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ClassDeclaration node) {
        Type classType = new Type(TypeKind.CLASS, node.getName(), List.of());

        if (!context.getTypeRegistry().register(classType)) {
            throw new IllegalStateException("Clase Z duplicada: " + node.getName());
        }

        Symbol classSymbol = new Symbol(
                node.getName(),
                SymbolKind.CLASS,
                Language.Z,
                classType,
                context.getSymbolTable().getCurrentScope(),
                node.getLine(),
                node.getColumn(),
                new SymbolModifiers(Visibility.PUBLIC, false),
                null
        );

        define(classSymbol);

        context.getSymbolTable().enterScope(ScopeKind.CLASS);

        if (node.getMembers() != null) {
            for (ClassMember member : node.getMembers()) {
                member.accept(this);
            }
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(ClassMember node) {
        return null;
    }

    @Override
    public Void visit(FieldDeclaration node) {
        Type type = resolveType(node.getType());

        if (node.getVariables() != null) {
            for (VariableDeclarator variable : node.getVariables()) {
                SymbolKind kind = type.getKind() == TypeKind.ARRAY ? SymbolKind.ARRAY : SymbolKind.FIELD;

                Symbol symbol = new Symbol(
                        variable.getName(),
                        kind,
                        Language.Z,
                        type,
                        context.getSymbolTable().getCurrentScope(),
                        variable.getLine(),
                        variable.getColumn(),
                        modifiers(node.isPublicAccess()),
                        null
                );

                define(symbol);

                if (variable.getInitializer() != null) {
                    variable.getInitializer().accept(this);
                }
            }
        }

        return null;
    }

    @Override
    public Void visit(ConstructorDeclaration node) {
        Type constructorType = new Type(TypeKind.CLASS, node.getName(), List.of());
        List<Type> parameterTypes = resolveParameterTypes(node.getParameters());

        FunctionSignature signature = new FunctionSignature(parameterTypes, constructorType);

        Symbol constructor = new Symbol(
                node.getName(),
                SymbolKind.CONSTRUCTOR,
                Language.Z,
                constructorType,
                context.getSymbolTable().getCurrentScope(),
                node.getLine(),
                node.getColumn(),
                modifiers(node.isPublicAccess()),
                signature
        );

        define(constructor);

        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);

        if (node.getParameters() != null) {
            for (Parameter parameter : node.getParameters()) {
                parameter.accept(this);
            }
        }

        if (node.getBody() != null) {
            node.getBody().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(MethodDeclaration node) {
        Type returnType = node.getReturnType() == null
                ? new Type(TypeKind.VOID, "void", List.of())
                : resolveType(node.getReturnType());

        List<Type> parameterTypes = resolveParameterTypes(node.getParameters());

        FunctionSignature signature = new FunctionSignature(parameterTypes, returnType);

        Symbol method = new Symbol(
                node.getName(),
                SymbolKind.METHOD,
                Language.Z,
                returnType,
                context.getSymbolTable().getCurrentScope(),
                node.getLine(),
                node.getColumn(),
                modifiers(node.isPublicAccess()),
                signature
        );

        define(method);

        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);

        if (node.getParameters() != null) {
            for (Parameter parameter : node.getParameters()) {
                parameter.accept(this);
            }
        }

        if (node.getBody() != null) {
            node.getBody().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(Parameter node) {
        Type type = resolveType(node.getType());

        Symbol symbol = new Symbol(
                node.getName(),
                SymbolKind.PARAMETER,
                Language.Z,
                type,
                context.getSymbolTable().getCurrentScope(),
                node.getLine(),
                node.getColumn(),
                SymbolModifiers.defaults(),
                null
        );

        define(symbol);
        return null;
    }

    @Override
    public Void visit(ZType node) {
        return null;
    }

    @Override
    public Void visit(ArrayDimensions node) {
        return null;
    }

    @Override
    public Void visit(VariableDeclarator node) {
        if (node.getInitializer() != null) {
            node.getInitializer().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(VariableDeclaration node) {
        Type type = resolveType(node.getType());

        if (node.getVariables() != null) {
            for (VariableDeclarator variable : node.getVariables()) {
                SymbolKind kind = type.getKind() == TypeKind.ARRAY
                        ? SymbolKind.ARRAY
                        : SymbolKind.VARIABLE;

                Symbol symbol = new Symbol(
                        variable.getName(),
                        kind,
                        Language.Z,
                        type,
                        context.getSymbolTable().getCurrentScope(),
                        variable.getLine(),
                        variable.getColumn(),
                        SymbolModifiers.defaults(),
                        null
                );

                define(symbol);

                if (variable.getInitializer() != null) {
                    variable.getInitializer().accept(this);
                }
            }
        }

        return null;
    }

    @Override
    public Void visit(Initializer node) {
        return null;
    }

    @Override
    public Void visit(ExpressionInitializer node) {
        return null;
    }

    @Override
    public Void visit(ArrayInitializer node) {
        return null;
    }

    @Override
    public Void visit(VariableDeclarationStatement node) {
        if (node.getDeclaration() != null) {
            node.getDeclaration().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(BlockStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);

        if (node.getStatements() != null) {
            for (Statement statement : node.getStatements()) {
                statement.accept(this);
            }
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(ForStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);

        if (node.getInitializer() != null) {
            node.getInitializer().accept(this);
        }

        if (node.getBlock() != null) {
            node.getBlock().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(IfStatement node) {
        if (node.getThenBranch() != null) {
            node.getThenBranch().accept(this);
        }

        if (node.getElseIfClauses() != null) {
            for (ElseIfClause clause : node.getElseIfClauses()) {
                clause.accept(this);
            }
        }

        if (node.getElseBranch() != null) {
            node.getElseBranch().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ElseIfClause node) {
        if (node.getBranch() != null) {
            node.getBranch().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ElseClause node) {
        if (node.getBranch() != null) {
            node.getBranch().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(WhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);

        if (node.getBody() != null) {
            node.getBody().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);

        if (node.getBody() != null) {
            node.getBody().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(SwitchStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);

        if (node.getCases() != null) {
            for (SwitchCase switchCase : node.getCases()) {
                switchCase.accept(this);
            }
        }

        if (node.getDefaultCase() != null) {
            node.getDefaultCase().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(SwitchCase node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);

        if (node.getStatements() != null) {
            for (Statement statement : node.getStatements()) {
                statement.accept(this);
            }
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(DefaultCase node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);

        if (node.getStatements() != null) {
            for (Statement statement : node.getStatements()) {
                statement.accept(this);
            }
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override public Void visit(ExpressionStatement node) { return null; }
    @Override public Void visit(BreakStatement node) { return null; }
    @Override public Void visit(ContinueStatement node) { return null; }
    @Override public Void visit(PrintStatement node) { return null; }
    @Override public Void visit(PrintlnStatement node) { return null; }
    @Override public Void visit(ReadlnStatement node) { return null; }
    @Override public Void visit(ReturnStatement node) { return null; }

    @Override public Void visit(ArrayAccessExpression node) { return null; }
    @Override public Void visit(AssignmentExpression node) { return null; }
    @Override public Void visit(BinaryExpression node) { return null; }
    @Override public Void visit(FunctionCallExpression node) { return null; }
    @Override public Void visit(MemberAccessExpression node) { return null; }
    @Override public Void visit(UnaryExpression node) { return null; }
    @Override public Void visit(VariableExpression node) { return null; }
    @Override public Void visit(TernaryExpression node) { return null; }
    @Override public Void visit(ObjectCreationExpression node) { return null; }
    @Override public Void visit(ArrayCreationExpression node) { return null; }
    @Override public Void visit(NullExpression node) { return null; }
    @Override public Void visit(ReadExpression node) { return null; }
    @Override public Void visit(ExpressionList node) { return null; }
    @Override public Void visit(LiteralExpression node) { return null; }

    private void define(Symbol symbol) {
        if (!context.getSymbolTable().define(symbol)) {
            throw new IllegalStateException(
                    "Identificador Z duplicado: " + symbol.getName()
            );
        }
    }

    private SymbolModifiers modifiers(boolean publicAccess) {
        return new SymbolModifiers(
                publicAccess ? Visibility.PUBLIC : Visibility.PACKAGE,
                false
        );
    }

    private List<Type> resolveParameterTypes(List<Parameter> parameters) {
        List<Type> types = new ArrayList<>();

        if (parameters != null) {
            for (Parameter parameter : parameters) {
                types.add(resolveType(parameter.getType()));
            }
        }

        return types;
    }

    private Type resolveType(ZType node) {
        String name = node.getName();
        String value = name.toLowerCase(Locale.ROOT);

        TypeKind kind = switch (value) {
            case "int" -> TypeKind.INT;
            case "double" -> TypeKind.DOUBLE;
            case "char" -> TypeKind.CHAR;
            case "boolean" -> TypeKind.BOOLEAN;
            case "string" -> TypeKind.STRING;
            case "void" -> TypeKind.VOID;
            default -> TypeKind.CLASS;
        };

        if (node.getArrayDimensions() != null) {
            int count = node.getArrayDimensions().getDimensions();
            List<Integer> dimensions = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                dimensions.add(0);
            }

            return new Type(TypeKind.ARRAY, name, dimensions);
        }

        return new Type(kind, name, List.of());
    }
}