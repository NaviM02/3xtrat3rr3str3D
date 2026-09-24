package com.navi;

import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.c3d.C3DGenerator;
import com.navi.backend.semantic.FileModuleLoader;
import com.navi.backend.semantic.ModuleLoader;
import com.navi.backend.semantic.SemanticAnalyzer;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.SemanticError;
import com.navi.backend.semantic.SemanticReporter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Arnés de prueba: valida semántica y genera C3D. Uso:
 * <pre>java -cp ... com.navi.Main &lt;archivo.pig|.y|.z&gt;</pre>
 *
 * Para {@code .pig} resuelve los imports de forma relativa al directorio del archivo.
 */
public class Main {

    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "testfiles/prueba/main.pig";

        Path file = Path.of(filePath).toAbsolutePath();
        String source;
        try {
            source = Files.readString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("No se pudo leer el archivo: " + file);
            System.exit(1);
            return;
        }

        String name = file.getFileName().toString();
        String ext = name.contains(".") ? name.substring(name.lastIndexOf('.') + 1) : "";

        SemanticContext ctx = new SemanticContext();
        ModuleLoader loader = new FileModuleLoader(file.getParent());
        SemanticAnalyzer analyzer = new SemanticAnalyzer(ctx, loader);
        C3DGenerator c3d = new C3DGenerator(ctx);

        String code = null;
        try {
            switch (ext) {
                case "pig" -> {
                    Program program = FileModuleLoader.parseLat(source);
                    analyzer.analyze(program);
                    if (!ctx.getErrors().hasErrors()) code = c3d.generate(program);
                }
                case "y" -> {
                    ProgramY program = FileModuleLoader.parseY(source);
                    analyzer.analyze(program);
                    if (!ctx.getErrors().hasErrors()) code = c3d.generate(program);
                }
                case "z" -> {
                    ProgramZ program = FileModuleLoader.parseZ(source);
                    analyzer.analyze(program);
                    if (!ctx.getErrors().hasErrors()) code = c3d.generate(program);
                }
                default -> {
                    System.err.println("Extensión no soportada: ." + ext + " (use .pig, .y o .z)");
                    System.exit(1);
                }
            }
        } catch (SemanticError e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Archivo analizado: " + file);
        System.out.println(SemanticReporter.report(ctx));
        if (code != null) {
            System.out.println("==================== CÓDIGO DE TRES DIRECCIONES ====================");
            System.out.print(code);
        }
    }
}
