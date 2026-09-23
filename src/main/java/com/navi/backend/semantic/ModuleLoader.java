package com.navi.backend.semantic;

import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;

/**
 * Carga y parsea los módulos importados. La implementación real (mapeo de path a
 * archivo + lexer/parser correspondiente) depende de la infraestructura de
 * archivos de la app; se deja como interfaz para desacoplarla del orquestador.
 */
public interface ModuleLoader {

    /** Carga un archivo .y y devuelve su AST (ProgramY). */
    ProgramY loadY(String importPath);

    /** Carga un archivo .z y devuelve su AST (ProgramZ). */
    ProgramZ loadZ(String importPath);
}
