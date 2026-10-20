package com.navi.backend.c3d;

import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;

import java.util.ArrayList;
import java.util.List;

// Emite despacho dinámico con el tag de clase guardado en heap[receiver].
public final class MethodDispatchC3D {

    private MethodDispatchC3D() {
    }

    public static String call(SemanticContext context, C3DEmitter emitter,
                              AggregateType staticClass, String methodName, List<Type> argumentTypes,
                              String receiver, List<String> arguments, boolean returnsVoid) {
        if (staticClass == null) return null;
        Symbol staticMethod = staticClass.findMethod(methodName, argumentTypes, context.getTypeTable());
        if (staticMethod == null) return null;

        // Subclases que redefinen el método: cada una compara su tag en runtime.
        List<DispatchCase> cases = new ArrayList<>();
        for (AggregateType runtimeClass : context.getTypeTable().getTypes().values()) {
            if (!runtimeClass.isClass() || runtimeClass == staticClass || !runtimeClass.isSubtypeOf(staticClass)) continue;
            Symbol implementation = runtimeClass.findMethod(methodName, argumentTypes, context.getTypeTable());
            if (implementation != null && implementation.getOwner() != staticMethod.getOwner()) {
                cases.add(new DispatchCase(runtimeClass, implementation, emitter.newLabel()));
            }
        }

        if (cases.isEmpty()) return invoke(emitter, staticMethod, receiver, arguments, returnsVoid);

        // Carga el tag de clase desde heap[receiver] y salta a la implementación
        // correspondiente; si no coincide con ninguna subclase, usa la del padre.
        String result = returnsVoid ? null : emitter.newTemp();
        String runtimeTag = emitter.heapLoad(receiver, "0");
        String fallback = emitter.newLabel();
        String end = emitter.newLabel();

        for (DispatchCase dispatchCase : cases) {
            emitter.ifGoto(runtimeTag, "==", String.valueOf(dispatchCase.runtimeClass().getTypeId()),
                    dispatchCase.label());
        }
        emitter.jump(fallback);

        for (DispatchCase dispatchCase : cases) {
            emitter.label(dispatchCase.label());
            String value = invoke(emitter, dispatchCase.method(), receiver, arguments, returnsVoid);
            if (!returnsVoid) emitter.assign(result, value);
            emitter.jump(end);
        }

        emitter.label(fallback);
        String value = invoke(emitter, staticMethod, receiver, arguments, returnsVoid);
        if (!returnsVoid) emitter.assign(result, value);
        emitter.label(end);
        return result;
    }

    private static String invoke(C3DEmitter emitter, Symbol method, String receiver,
                                 List<String> arguments, boolean returnsVoid) {
        List<String> callArguments = new ArrayList<>();
        callArguments.add(receiver);
        if (arguments != null) callArguments.addAll(arguments);
        String label = C3DEmitter.methodLabel(method.getOwner().getName(), method.getName(),
                method.getSignature().getParameters());
        if (returnsVoid) {
            emitter.callVoid(label, callArguments);
            return null;
        }
        return emitter.call(label, callArguments);
    }

    private record DispatchCase(AggregateType runtimeClass, Symbol method, String label) {
    }
}
