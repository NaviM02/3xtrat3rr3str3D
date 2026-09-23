package com.navi;

import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;
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
 * Arnés de prueba del análisis semántico. Uso:
 * <pre>java -cp ... com.navi.Main &lt;archivo.pig|.y|.z&gt;</pre>
 *
 * Para {@code .pig} resuelve los imports de forma relativa al directorio del archivo.
 */
public class Main {

    public static void main(String[] args) {
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            // Cambia esto por la ruta exacta de donde está tu archivo .pig en tu proyecto
            filePath = "testfiles/prueba/main.pig";
        }

        // El resto del código continúa exactamente igual, pero usando 'filePath'
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

        try {
            switch (ext) {
                case "pig" -> {
                    Program program = FileModuleLoader.parseLat(source);
                    analyzer.analyze(program);
                }
                case "y" -> {
                    ProgramY program = FileModuleLoader.parseY(source);
                    analyzer.analyze(program);
                }
                case "z" -> {
                    ProgramZ program = FileModuleLoader.parseZ(source);
                    analyzer.analyze(program);
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
    }
}
