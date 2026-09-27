package com.navi.backend.semantic.loading;

import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;

// carga y parsea los modulos importados
// la implementacion real depende de la infra de archivos, por eso es interfaz
public interface ModuleLoader {

    // carga un .y y devuelve su AST
    ProgramY loadY(String importPath);

    // carga un .z y devuelve su AST
    ProgramZ loadZ(String importPath);
}
