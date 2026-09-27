package com.navi.backend.c3d.lat;

import com.navi.backend.ast.lat.AstLatNode;
import com.navi.backend.ast.lat.declarations.ArrayInitializer;
import com.navi.backend.ast.lat.declarations.initializers.ExpressionInitializer;
import com.navi.backend.ast.lat.declarations.initializers.Initializer;
import com.navi.backend.ast.lat.declarations.initializers.StructFieldInitializer;
import com.navi.backend.ast.lat.declarations.initializers.StructInitializer;
import com.navi.backend.ast.lat.expressions.ArrayAccessExpression;
import com.navi.backend.ast.lat.expressions.Expression;
import com.navi.backend.ast.lat.expressions.MemberAccessExpression;
import com.navi.backend.ast.lat.expressions.VariableExpression;
import com.navi.backend.ast.lat.expressions.literals.NumberLiteral;
import com.navi.backend.ast.lat.visitors.AstLatVisitor;
import com.navi.backend.c3d.C3DEmitter;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Field;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// direcciones, layout y asignacion de Lat para C3D (lo compartido entre
// declaraciones, sentencias y expresiones)
class LatC3DResolver {

    private final SemanticContext context;
    private final C3DEmitter emitter;
    private final AstLatVisitor<String> visitor;

    LatC3DResolver(SemanticContext context, C3DEmitter emitter, AstLatVisitor<String> visitor) {
        this.context = context;
        this.emitter = emitter;
        this.visitor = visitor;
    }

    // ---------------------------------------------------------------- inicializadores

    String initializerValue(Initializer init) {
        if (init instanceof ExpressionInitializer ei) return ei.getExpression().accept(visitor);
        return null; // struct initializer no produce un valor unico (se emite campo a campo)
    }

    // emite un literal de struct escribiendo cada campo en structAddr
    // los campos arreglo no soportados se omiten; los no listados quedan en 0/null
    void emitStructInitializer(StructInitializer init, Expression target, Type structType) {
        if (target == null || structType == null || !structType.isStruct()) return;

        AggregateType agg = context.getTypeTable().resolve(structType.getName());
        if (agg == null) return;

        int index = 0;
        for (StructFieldInitializer field : init.getFields()) {
            Field declared = field.getName() != null
                    ? agg.findField(field.getName())
                    : (index < agg.getFields().size() ? agg.getFields().get(index) : null);
            index++;
            if (declared == null) continue;
            if (!(field.getValue() instanceof ExpressionInitializer ei)) continue;
            if (declared.getType() != null && (declared.getType().isArray() || declared.getType().isAggregate())) continue;

            int off = fieldOffset(structType, declared.getName());
            String addr = emitter.binary("+", structAddr(target), String.valueOf(off));
            emitter.stackStoreAt(addr, ei.getExpression().accept(visitor));
        }

        initializeStructDefaults(agg, target, init);
    }

    // deja en 0/null los campos escalares que el literal no menciona
    private void initializeStructDefaults(AggregateType agg, Expression target, StructInitializer init) {
        Set<String> provided = new HashSet<>();
        int index = 0;
        for (StructFieldInitializer field : init.getFields()) {
            if (field.getName() != null) provided.add(field.getName());
            else if (index < agg.getFields().size()) provided.add(agg.getFields().get(index).getName());
            index++;
        }

        for (Field field : agg.getFields()) {
            if (provided.contains(field.getName())) continue;

            Type type = field.getType();
            if (type == null || type.isArray() || type.isAggregate()) continue;

            int off = fieldOffset(Type.struct(agg.getName()), field.getName());
            String addr = emitter.binary("+", structAddr(target), String.valueOf(off));
            emitter.stackStoreAt(addr, type.isString() ? "null" : "0");
        }
    }

    void assignTo(Expression target, String value) {
        if (target instanceof VariableExpression v) {
            emitter.storeVar(v.getName(), value);
        } else if (target instanceof ArrayAccessExpression a) {
            emitter.stackStoreAt(arrayElementAddr(a), value);
        } else if (target instanceof MemberAccessExpression m) {
            Type owner = context.typeOf(m.getObject());
            if (owner != null && owner.isStruct()) {
                int off = fieldOffset(owner, m.getMember());
                emitter.stackStoreAt(emitter.binary("+", structAddr(m.getObject()), String.valueOf(off)), value);
            } else {
                int off = fieldIndex(owner, m.getMember());
                emitter.heapStore(m.getObject().accept(visitor), String.valueOf(off), value);
            }
        }
    }

    // ---------------------------------------------------------------- direcciones

    // direccion del elemento de un arreglo, aplanando indices
    String arrayElementAddr(ArrayAccessExpression node) {
        List<String> indices = new ArrayList<>();
        Expression current = node;
        while (current instanceof ArrayAccessExpression access) {
            indices.add(0, access.getIndex().accept(visitor));
            current = access.getArray();
        }
        String base = arrayBaseAddr(current);
        return emitter.addressOffset(base, arrayDimsOf(current), indices);
    }

    private String arrayBaseAddr(Expression array) {
        if (array instanceof VariableExpression v) return emitter.varAddr(v.getName());
        if (array instanceof MemberAccessExpression m) return memberAddr(m);
        return array.accept(visitor);
    }

    private String memberAddr(MemberAccessExpression m) {
        Type owner = context.typeOf(m.getObject());
        if (owner != null && owner.isStruct()) {
            return emitter.binary("+", structAddr(m.getObject()), String.valueOf(fieldOffset(owner, m.getMember())));
        }
        return emitter.heapAddr(m.getObject().accept(visitor), String.valueOf(fieldIndex(owner, m.getMember())));
    }

    // dimensiones de un arreglo (variable o campo de struct)
    private List<Integer> arrayDimsOf(Expression array) {
        if (array instanceof VariableExpression v) return emitter.arrayDims(v.getName());
        if (array instanceof MemberAccessExpression m) {
            Type owner = context.typeOf(m.getObject());
            if (owner != null) {
                AggregateType agg = context.getTypeTable().resolve(owner.getName());
                if (agg != null) {
                    Field field = agg.findField(m.getMember());
                    if (field != null) return field.getArrayDims();
                }
            }
        }
        return null;
    }

    String structAddr(Expression obj) {
        if (obj instanceof VariableExpression v) return emitter.varAddr(v.getName());
        if (obj instanceof MemberAccessExpression m) {
            int off = fieldOffset(context.typeOf(m.getObject()), m.getMember());
            return emitter.binary("+", structAddr(m.getObject()), String.valueOf(off));
        }
        return obj.accept(visitor);
    }

    // direccion (no valor) de una expresion que ocupa celdas contiguas
    String addressOf(Expression e) {
        if (e instanceof VariableExpression v) return emitter.varAddr(v.getName());
        if (e instanceof ArrayAccessExpression a) return arrayElementAddr(a);
        if (e instanceof MemberAccessExpression m) return memberAddr(m);
        return e.accept(visitor);
    }

    // ---------------------------------------------------------------- layout

    int fieldIndex(Type owner, String member) {
        if (owner == null) return 0;
        AggregateType agg = context.getTypeTable().resolve(owner.getName());
        if (agg == null) return 0;
        for (int i = 0; i < agg.getFields().size(); i++) {
            if (agg.getFields().get(i).getName().equals(member)) return i;
        }
        return 0;
    }

    // offset en celdas de un campo (los arreglo ocupan varias celdas)
    int fieldOffset(Type owner, String member) {
        if (owner == null) return 0;
        AggregateType agg = context.getTypeTable().resolve(owner.getName());
        if (agg == null) return 0;
        return agg.fieldOffset(member);
    }

    // tamano en celdas de un struct (1 si no se conoce)
    int structSize(String name) {
        AggregateType agg = context.getTypeTable().resolve(name);
        return agg == null ? 1 : agg.size();
    }

    int objectSize(String className) {
        AggregateType agg = context.getTypeTable().resolve(className);
        return agg == null ? 1 : Math.max(1, agg.getFields().size());
    }

    // ---------------------------------------------------------------- arreglos

    // celdas de un arreglo de tamano constante (1 si es dinamico/desconocido)
    int arrayCells(List<Expression> sizes) {
        if (sizes == null || sizes.isEmpty()) return 1;
        int total = 1;
        for (Expression e : sizes) {
            if (e instanceof NumberLiteral n && n.getValue() > 0) total *= n.getValue();
            else return 1;
        }
        return total;
    }

    // dimensiones constantes de un arreglo, o null si no se pueden calcular
    List<Integer> constantDims(List<Expression> sizes) {
        if (sizes == null || sizes.isEmpty()) return null;
        List<Integer> dims = new ArrayList<>();
        for (Expression e : sizes) {
            if (e instanceof NumberLiteral n && n.getValue() > 0) dims.add(n.getValue());
            else return null;
        }
        return dims;
    }

    // aplana y almacena un inicializador de arreglo (soporta anidados)
    int storeArrayInitializer(String base, List<AstLatNode> elements, int start) {
        if (elements == null) return start;
        int i = start;
        for (AstLatNode element : elements) {
            if (element instanceof ArrayInitializer nested) {
                i = storeArrayInitializer(base, nested.getElements(), i);
            } else if (element instanceof Expression expression) {
                String value = expression.accept(visitor);
                emitter.stackStoreAt(emitter.binary("+", base, String.valueOf(i)), value);
                i++;
            }
        }
        return i;
    }

    void setPos(Object node, int offset) {
        Symbol s = context.symbolOf(node);
        if (s != null) s.setPosMemory(offset);
    }
}
