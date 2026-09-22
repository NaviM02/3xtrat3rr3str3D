package com.navi.backend.semantic.lat;

import com.navi.backend.ast.lat.declarations.ArrayDeclaration;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.Declaration;
import com.navi.backend.ast.lat.declarations.VariableDeclaration;
import com.navi.backend.ast.lat.declarations.initializers.*;
import com.navi.backend.ast.lat.expressions.*;
import com.navi.backend.ast.lat.expressions.literals.*;
import com.navi.backend.ast.lat.global.*;
import com.navi.backend.ast.lat.statements.*;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.enums.Language;
import com.navi.backend.semantic.enums.ScopeKind;
import com.navi.backend.semantic.enums.SymbolKind;
import com.navi.backend.semantic.enums.TypeKind;
import com.navi.backend.semantic.model.FunctionSignature;
import com.navi.backend.semantic.model.Module;
import com.navi.backend.semantic.model.Scope;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.SymbolModifiers;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LatDeclarationVisitor implements AstLatVisitor<Void> {
    private final SemanticContext context;

    public LatDeclarationVisitor(SemanticContext context) {
        this.context = context;
    }

    public void build(Program program) {
        program.accept(this);
    }

    @Override
    public Void visit(Program node) {
        if (node.getImports() != null) {
            for (ImportDeclaration declaration : node.getImports()) {
                declaration.accept(this);
            }
        }

        if (node.getGlobalVariables() != null) {
            node.getGlobalVariables().accept(this);
        }

        if (node.getFunctions() != null) {
            for (FunctionDeclaration function : node.getFunctions()) {
                function.accept(this);
            }
        }

        if (node.getMainStatements() != null) {
            for (Statement statement : node.getMainStatements()) {
                statement.accept(this);
            }
        }

        return null;
    }

    @Override
    public Void visit(ImportDeclaration node) {
        Scope moduleScope = context.getSymbolTable().enterScope(ScopeKind.MODULE);

        Module module = new Module(node.getPath(), Language.LAT, moduleScope);
        if (!context.getModuleRegistry().register(module)) {
            context.getSymbolTable().exitScope();
            throw new IllegalStateException("Modulo LAT duplicado: " + node.getPath());
        }

        Symbol symbol = new Symbol(
                node.getPath(),
                SymbolKind.MODULE,
                Language.LAT,
                null,
                context.getSymbolTable().getGlobalScope(),
                node.getLine(),
                node.getColumn(),
                SymbolModifiers.defaults(),
                null
        );

        if (!context.getSymbolTable().getGlobalScope().define(symbol)) {
            context.getSymbolTable().exitScope();
            throw new IllegalStateException("Modulo LAT duplicado: " + node.getPath());
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(GlobalVariableSection node) {
        if (node.getDeclarations() != null) {
            for (Declaration declaration : node.getDeclarations()) {
                declaration.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(LocalVariableSection node) {
        if (node.getDeclarations() != null) {
            for (Declaration declaration : node.getDeclarations()) {
                declaration.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration node) {
        Type returnType = resolveType(node.getReturnType(), node.getLine(), node.getColumn());

        List<Type> parameterTypes = new ArrayList<>();
        if (node.getParameters() != null) {
            for (Parameter parameter : node.getParameters()) {
                parameterTypes.add(resolveType(parameter.getType(), parameter.getLine(), parameter.getColumn()));
            }
        }

        FunctionSignature signature = new FunctionSignature(parameterTypes, returnType);

        Symbol function = new Symbol(
                node.getName(),
                SymbolKind.FUNCTION,
                Language.LAT,
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

        if (node.getBody() != null) {
            node.getBody().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(FunctionBody node) {
        if (node.getLocalVariables() != null) {
            node.getLocalVariables().accept(this);
        }

        if (node.getBody() != null) {
            node.getBody().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Parameter node) {
        Type type = resolveType(node.getType(), node.getLine(), node.getColumn());

        Symbol symbol = new Symbol(
                node.getName(),
                SymbolKind.PARAMETER,
                Language.LAT,
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
        Type type = resolveType(node.getType(), node.getLine(), node.getColumn());

        Symbol symbol = new Symbol(
                node.getName(),
                SymbolKind.VARIABLE,
                Language.LAT,
                type,
                context.getSymbolTable().getCurrentScope(),
                node.getLine(),
                node.getColumn(),
                SymbolModifiers.defaults(),
                null
        );

        define(symbol);

        if (node.getInitializer() != null) {
            node.getInitializer().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ArrayDeclaration node) {
        Type elementType = resolveType(node.getType(), node.getLine(), node.getColumn());
        Type arrayType = new Type(TypeKind.ARRAY, node.getType(), List.of());

        Symbol symbol = new Symbol(
                node.getName(),
                SymbolKind.ARRAY,
                Language.LAT,
                arrayType,
                context.getSymbolTable().getCurrentScope(),
                node.getLine(),
                node.getColumn(),
                SymbolModifiers.defaults(),
                null
        );

        define(symbol);

        if (node.getSize() != null) {
            node.getSize().accept(this);
        }

        if (node.getInitializer() != null) {
            node.getInitializer().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ArrayInitializer node) {
        if (node.getValues() != null) {
            for (Expression expression : node.getValues()) {
                expression.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(ExpressionInitializer node) {
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(StructInitializer node) {
        if (node.getFields() != null) {
            for (StructFieldInitializer field : node.getFields()) {
                field.accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(StructFieldInitializer node) {
        if (node.getValue() != null) {
            node.getValue().accept(this);
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
        if (node.getThenBlock() != null) {
            node.getThenBlock().accept(this);
        }

        if (node.getElseIfStatements() != null) {
            for (ElseIfStatement elseIf : node.getElseIfStatements()) {
                elseIf.accept(this);
            }
        }

        if (node.getElseBlock() != null) {
            node.getElseBlock().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ElseIfStatement node) {
        if (node.getBlock() != null) {
            node.getBlock().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(WhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);

        if (node.getBlock() != null) {
            node.getBlock().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    @Override
    public Void visit(DoWhileStatement node) {
        context.getSymbolTable().enterScope(ScopeKind.LOOP);

        if (node.getBlock() != null) {
            node.getBlock().accept(this);
        }

        context.getSymbolTable().exitScope();
        return null;
    }

    private void define(Symbol symbol) {
        if (!context.getSymbolTable().define(symbol)) {
            throw new IllegalStateException(
                    "Identificador LAT duplicado: " + symbol.getName()
            );
        }
    }

    private Type resolveType(String name, int line, int column) {
        if (name == null) {
            return new Type(TypeKind.VOID, "void", List.of());
        }

        String value = name.toLowerCase(Locale.ROOT);

        return switch (value) {
            case "numerus" -> new Type(TypeKind.INT, name, List.of());
            case "decimalis" -> new Type(TypeKind.DOUBLE, name, List.of());
            case "littera" -> new Type(TypeKind.CHAR, name, List.of());
            case "textum" -> new Type(TypeKind.STRING, name, List.of());
            case "bool", "boolean" -> new Type(TypeKind.BOOLEAN, name, List.of());
            case "void" -> new Type(TypeKind.VOID, name, List.of());
            default -> new Type(TypeKind.STRUCT, name, List.of());
        };
    }

    @Override public Void visit(AssignmentStatement node) { return null; }
    @Override public Void visit(BreakStatement node) { return null; }
    @Override public Void visit(ContinueStatement node) { return null; }
    @Override public Void visit(FunctionCallStatement node) { return null; }
    @Override public Void visit(IncrementStatement node) { return null; }
    @Override public Void visit(PrintStatement node) { return null; }
    @Override public Void visit(ReadStatement node) { return null; }
    @Override public Void visit(ReturnStatement node) { return null; }

    @Override public Void visit(ArrayAccessExpression node) { return null; }
    @Override public Void visit(BinaryExpression node) { return null; }
    @Override public Void visit(FunctionCallExpression node) { return null; }
    @Override public Void visit(MemberAccessExpression node) { return null; }
    @Override public Void visit(ObjectCreationExpression node) { return null; }
    @Override public Void visit(UnaryExpression node) { return null; }
    @Override public Void visit(VariableExpression node) { return null; }

    @Override public Void visit(BooleanLiteral node) { return null; }
    @Override public Void visit(CharLiteral node) { return null; }
    @Override public Void visit(DecimalLiteral node) { return null; }
    @Override public Void visit(NumberLiteral node) { return null; }
    @Override public Void visit(StringLiteral node) { return null; }
}