package com.navi.backend.semantic;

import com.navi.backend.ast.lat.global.ImportDeclaration;
import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.semantic.lat.LatDeclarationVisitor;
import com.navi.backend.semantic.lat.LatSemanticVisitor;
import com.navi.backend.semantic.y.YDeclarationVisitor;
import com.navi.backend.semantic.y.YSemanticVisitor;
import com.navi.backend.semantic.z.ZDeclarationVisitor;
import com.navi.backend.semantic.z.ZSemanticVisitor;

/**
 * Orquestador de la semántica. Entrada: el {@link Program} de Lat (único que
 * importa). Estrategia simple (imports son {@code import *}, profundidad máxima 2):
 *
 * <pre>
 *   1) imports primero, en orden, eager y self-contenidos (Y/Z no importan):
 *        parsear -> pasada de declaraciones -> pasada semántica
 *   2) declarar globales de Lat (signatures, para recursión/llamadas a imports)
 *   3) checar/annotar cuerpos de Lat
 * </pre>
 *
 * Y y Z no tienen imports, por lo que no hay dependencias transitivas ni ciclos
 * reales; {@code loadedModules} es solo una guarda de re-carga.
 */
public class SemanticAnalyzer {

    private final SemanticContext context;
    private final ModuleLoader loader;

    public SemanticAnalyzer(SemanticContext context, ModuleLoader loader) {
        this.context = context;
        this.loader = loader;
    }

    public void analyze(Program program) {
        if (program.getImports() != null) {
            for (ImportDeclaration imp : program.getImports()) {
                loadImport(imp);
            }
        }

        new LatDeclarationVisitor(context).build(program);
        new LatSemanticVisitor(context).build(program);
    }

    /** Análisis standalone de un módulo Y (para pruebas). */
    public void analyze(ProgramY program) {
        new YDeclarationVisitor(context).build(program);
        new YSemanticVisitor(context).build(program);
    }

    /** Análisis standalone de un módulo Z (para pruebas). */
    public void analyze(ProgramZ program) {
        new ZDeclarationVisitor(context).build(program);
        new ZSemanticVisitor(context).build(program);
    }

    private void loadImport(ImportDeclaration imp) {
        ImportPath path = new ImportPath(imp.getPath());
        if (!path.isValid()) {
            context.getErrors().report(imp.getLine(), imp.getColumn(),
                    "Extensión de import no válida (debe terminar en .y o .z): " + imp.getPath());
            return;
        }

        if (context.isLoaded(imp.getPath())) {
            return;
        }
        context.markLoaded(imp.getPath());

        if (path.isY()) {
            ProgramY y = loader.loadY(imp.getPath());
            context.recordAst(imp.getPath(), y);
            new YDeclarationVisitor(context).build(y);
            new YSemanticVisitor(context).build(y);
        } else {
            ProgramZ z = loader.loadZ(imp.getPath());
            context.recordAst(imp.getPath(), z);
            new ZDeclarationVisitor(context).build(z);
            new ZSemanticVisitor(context).build(z);
        }
    }
}
