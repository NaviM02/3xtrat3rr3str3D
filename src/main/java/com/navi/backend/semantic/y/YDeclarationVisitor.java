package com.navi.backend.semantic.y;

import com.navi.backend.ast.y.declarations.*;
import com.navi.backend.ast.y.expressions.*;
import com.navi.backend.ast.y.expressions.literals.LiteralExpression;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.y.statements.*;
import com.navi.backend.ast.y.visitors.AstYVisitor;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.enums.Language;
import com.navi.backend.semantic.enums.ScopeKind;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.enums.TypeKind;
import com.navi.backend.semantic.model.FunctionSignature;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.SymbolModifiers;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class YDeclarationVisitor implements AstYVisitor<Void> {
    private final SemanticContext context;

    public YDeclarationVisitor(SemanticContext context) {
        this.context = context;
    }

    public void build(ProgramY program) {
        program.accept(this);
    }

    @Override
    public Void visit(ProgramY node) {
        if (node.getStructures() != null) {
            for (StructureDeclaration structure : node.getStructures()) {
                structure.accept(this);
            }
        }

        if (node.getFunctions() != null) {
            for (FunctionDeclaration function : node.getFunctions()) {
                function.accept(this);
            }
        }

        return null;
    }

    @Override
    public Void visit(StructureDeclaration node) {
        Type type = new Type(TypeKind.STRUCT, node.getName(), List.of());

        if (!context.getTypeRegistry().register(type)) {
            throw new IllegalStateException("Estructura Y duplicada: " + node.getName());
        }

        context.getSymbolTable().enterScope(ScopeKind.STRUCT);

        if (node.getFields() != null) {
            for (StructureField field : node.getFields()) {
                field.accept(this);
            }
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(StructureField node) {
        Type type = resolveType(node.getType(), node.getArrayDimensions());

        Symbol symbol = new Symbol(
                node.getName(),
                type.getKind() == TypeKind.ARRAY ? SymbolKind.ARRAY : SymbolKind.FIELD,
                Language.Y,
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
    public Void visit(FunctionDeclaration node) {
        Type returnType = node.getReturnType() == null
                ? new Type(TypeKind.VOID, "void", List.of())
                : resolveType(node.getReturnType());

        List<Type> parameterTypes = new ArrayList<>();

        if (node.getParameters() != null) {
            for (Parameter parameter : node.getParameters()) {
                parameterTypes.add(resolveParameterType(parameter));
            }
        }

        FunctionSignature signature = new FunctionSignature(parameterTypes, returnType);

        Symbol function = new Symbol(
                node.getName(),
                SymbolKind.FUNCTION,
                Language.Y,
                returnType,
                context.getSymbolTable().getCurrentScope(),
                node.getLine(),
                node.getColumn(),
                SymbolModifiers.defaults(),
                signature
        );

        define(function);

        context.getSymbolTable().enterScope(ScopeKind.FUNCTION);

        if (node.getParameters() != null) {
            for (Parameter parameter : node.getParameters()) {
                parameter.accept(this);
            }
        }

        if (node.getStatements() != null) {
            for (Statement statement : node.getStatements()) {
                statement.accept(this);
            }
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(NormalParameter node) {
        Type type = resolveType(node.getType());

        Symbol symbol = new Symbol(
                node.getName(),
                SymbolKind.PARAMETER,
                Language.Y,
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
    public Void visit(ArrayParameter node) {
        Type type = new Type(TypeKind.ARRAY, node.getType().getName(), List.of());

        Symbol symbol = new Symbol(
                node.getName(),
                SymbolKind.PARAMETER,
                Language.Y,
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
    public Void visit(StructureParameter node) {
        Type type = new Type(TypeKind.STRUCT, node.getStructureName(), List.of());

        Symbol symbol = new Symbol(
                node.getName(),
                SymbolKind.PARAMETER,
                Language.Y,
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
    public Void visit(VariableDeclaration node) {
        Type type = resolveType(node.getType(), node.getArrayDeclaration());

        SymbolKind kind = type.getKind() == TypeKind.ARRAY
                ? SymbolKind.ARRAY
                : SymbolKind.VARIABLE;

        Symbol symbol = new Symbol(
                node.getName(),
                kind,
                Language.Y,
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
    public Void visit(ArrayDeclaration node) {
        if (node.getDimensions() != null) {
            for (Expression dimension : node.getDimensions()) {
                dimension.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(ArrayDimensions node) {
        return null;
    }

    @Override
    public Void visit(YType node) {
        return null;
    }

    @Override
    public Void visit(AssignmentStatement node) {
        return null;
    }

    @Override
    public Void visit(BreakStatement node) {
        return null;
    }

    @Override
    public Void visit(ContinueStatement node) {
        return null;
    }

    @Override
    public Void visit(ExpressionStatement node) {
        return null;
    }

    @Override
    public Void visit(ForStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);

        if (node.getInitializer() != null) {
            node.getInitializer().accept(this);
        }

        if (node.getStatements() != null) {
            for (Statement statement : node.getStatements()) {
                statement.accept(this);
            }
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(IfStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.BLOCK);

        if (node.getStatements() != null) {
            for (Statement statement : node.getStatements()) {
                statement.accept(this);
            }
        }

        context.getSymbolTable().exitScope();

        if (node.getElseIfClauses() != null) {
            for (ElseIfClause clause : node.getElseIfClauses()) {
                clause.accept(this);
            }
        }

        if (node.getElseClause() != null) {
            node.getElseClause().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ElseIfClause node) {
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
    public Void visit(ElseClause node) {
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
    public Void visit(WhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);

        if (node.getStatements() != null) {
            for (Statement statement : node.getStatements()) {
                statement.accept(this);
            }
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);

        if (node.getStatements() != null) {
            for (Statement statement : node.getStatements()) {
                statement.accept(this);
            }
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

    @Override public Void visit(IncrementStatement node) { return null; }
    @Override public Void visit(PrintStatement node) { return null; }
    @Override public Void visit(ReadStatement node) { return null; }
    @Override public Void visit(ReturnStatement node) { return null; }

    @Override public Void visit(ArrayAccessExpression node) { return null; }
    @Override public Void visit(BinaryExpression node) { return null; }
    @Override public Void visit(FunctionCallExpression node) { return null; }
    @Override public Void visit(MemberAccessExpression node) { return null; }
    @Override public Void visit(ReadExpression node) { return null; }
    @Override public Void visit(UnaryExpression node) { return null; }
    @Override public Void visit(VariableExpression node) { return null; }
    @Override public Void visit(LiteralExpression node) { return null; }

    @Override
    public Void visit(ExpressionInitializer node) {
        return null;
    }

    @Override
    public Void visit(ArrayInitializer node) {
        return null;
    }

    @Override
    public Void visit(StructureInitializer node) {
        return null;
    }

    private void define(Symbol symbol) {
        if (!context.getSymbolTable().define(symbol)) {
            throw new IllegalStateException(
                    "Identificador Y duplicado: " + symbol.getName()
            );
        }
    }

    private Type resolveParameterType(Parameter parameter) {
        if (parameter instanceof NormalParameter normal) {
            return resolveType(normal.getType());
        }

        if (parameter instanceof ArrayParameter array) {
            return new Type(TypeKind.ARRAY, array.getType().getName(), List.of());
        }

        StructureParameter structure = (StructureParameter) parameter;
        return new Type(TypeKind.STRUCT, structure.getStructureName(), List.of());
    }

    private Type resolveType(YType node, ArrayDeclaration arrayDeclaration) {
        Type base = resolveType(node);

        if (arrayDeclaration == null) {
            return base;
        }

        List<Integer> dimensions = new ArrayList<>();

        if (arrayDeclaration.getDimensions() != null) {
            for (int i = 0; i < arrayDeclaration.getDimensions().size(); i++) {
                dimensions.add(0);
            }
        }

        return new Type(TypeKind.ARRAY, node.getName(), dimensions);
    }

    private Type resolveType(YType node, ArrayDimensions dimensions) {
        Type base = resolveType(node);

        if (dimensions == null) {
            return base;
        }

        return new Type(TypeKind.ARRAY, node.getName(), dimensions.getDimensions());
    }

    private Type resolveType(YType node) {
        String name = node.getName();
        String value = name.toLowerCase(Locale.ROOT);

        return switch (value) {
            case "entero" -> new Type(TypeKind.INT, name, List.of());
            case "flotante" -> new Type(TypeKind.DOUBLE, name, List.of());
            case "caracter" -> new Type(TypeKind.CHAR, name, List.of());
            case "bool" -> new Type(TypeKind.BOOLEAN, name, List.of());
            case "cadena" -> new Type(TypeKind.STRING, name, List.of());
            default -> new Type(TypeKind.STRUCT, name, List.of());
        };
    }
}