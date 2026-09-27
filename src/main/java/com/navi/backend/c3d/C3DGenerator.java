package com.navi.backend.c3d;

import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.c3d.lat.LatC3DVisitor;
import com.navi.backend.c3d.y.YC3DVisitor;
import com.navi.backend.c3d.z.ZC3DVisitor;
import com.navi.backend.semantic.SemanticContext;

import java.util.List;

// orquestador de C3D: recorre los AST (primero los modulos importados)
// y emite cuartetas con un emitter compartido
public class C3DGenerator {

    private final SemanticContext context;
    private final C3DEmitter emitter = new C3DEmitter();

    public C3DGenerator(SemanticContext context) {
        this.context = context;
    }

    // entrada Lat: primero los modulos importados y luego Lat (funciones + main)
    public String generate(Program lat) {
        for (Object ast : context.getLoadedAsts().values()) {
            if (ast instanceof ProgramY y) new YC3DVisitor(context, emitter).generate(y);
            else if (ast instanceof ProgramZ z) new ZC3DVisitor(context, emitter).generate(z);
        }
        new LatC3DVisitor(context, emitter).generate(lat);
        return emitter.render();
    }

    public String generate(ProgramY y) {
        new YC3DVisitor(context, emitter).generate(y);
        return emitter.render();
    }

    public String generate(ProgramZ z) {
        new ZC3DVisitor(context, emitter).generate(z);
        return emitter.render();
    }

    // cuartetas finales (listas para traducir a C)
    public List<Quad> quads() {
        return emitter.finalQuads();
    }

    // celdas del area global (fijan el inicio del stack en C)
    public int globalSize() {
        return emitter.globalSize();
    }
}
