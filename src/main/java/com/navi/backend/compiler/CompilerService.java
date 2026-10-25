package com.navi.backend.compiler;

import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.c3d.C3DGenerator;
import com.navi.backend.c3d.riscv.RiscVGenerator;
import com.navi.backend.semantic.loading.FileModuleLoader;
import com.navi.backend.semantic.loading.ModuleLoader;
import com.navi.backend.semantic.SemanticAnalyzer;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.errors.SemanticReporter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CompilerService {

    public CompilationResult compile(Path sourceFile) throws Exception {

        Path file = sourceFile.toAbsolutePath().normalize();
        validateFile(file);
        String source = Files.readString(file, StandardCharsets.UTF_8);
        String fileName = file.getFileName().toString();
        String extension = getExtension(fileName);
        SemanticContext context = new SemanticContext();
        ModuleLoader loader = new FileModuleLoader(file.getParent());
        SemanticAnalyzer analyzer = new SemanticAnalyzer(context, loader);

        C3DGenerator c3d = new C3DGenerator(context);

        String code = compileSource(extension, source, analyzer, context, c3d);

        if (context.getErrors().hasErrors()) {
            return new CompilationResult(file, context, null, null, false, SemanticReporter.report(context));
        }

        Path riscVFile = generateRiscV(file, c3d, context);

        return new CompilationResult(file, context, code, riscVFile, true, SemanticReporter.report(context));
    }

    private String compileSource(String extension, String source, SemanticAnalyzer analyzer, SemanticContext context, C3DGenerator c3d) {
        return switch (extension) {
            case "pig" -> {
                Program program = FileModuleLoader.parseLat(source);
                analyzer.analyze(program);
                if (context.getErrors().hasErrors()) yield null;
                yield c3d.generate(program);
            }
            case "y" -> {
                ProgramY program = FileModuleLoader.parseY(source);
                analyzer.analyze(program);
                if (context.getErrors().hasErrors()) yield null;
                yield c3d.generate(program);
            }
            case "z" -> {
                ProgramZ program = FileModuleLoader.parseZ(source);
                analyzer.analyze(program);
                if (context.getErrors().hasErrors()) yield null;
                yield c3d.generate(program);
            }
            default -> throw new IllegalArgumentException("Extensión no soportada: ." + extension + " (use .pig, .y o .z)");
        };
    }

    private Path generateRiscV(Path sourceFile, C3DGenerator c3d, SemanticContext context) throws Exception {
        String base = getBaseName(sourceFile.getFileName().toString());
        String asm = new RiscVGenerator(c3d.quads(), c3d.globalSize(), context).generate();
        Path outputDirectory = outputDirectoryFor(sourceFile);
        cleanOutput(outputDirectory);

        Files.createDirectories(outputDirectory);
        Path asmFile = outputDirectory.resolve(base + ".s");
        Files.writeString(asmFile, asm, StandardCharsets.UTF_8);

        return asmFile.toAbsolutePath();
    }

    // carpeta output/ junto al fuente; si no tiene padre, output/ relativo
    private Path outputDirectoryFor(Path sourceFile) {
        Path parent = sourceFile.getParent();

        if (parent == null) {
            return Path.of("output");
        }

        return parent.resolve("output");
    }

    private void validateFile(Path file) {
        if (!Files.exists(file)) throw new IllegalArgumentException("El archivo no existe: " + file);
        if (!Files.isRegularFile(file)) throw new IllegalArgumentException("La ruta no corresponde a un archivo: " + file);
    }

    private String getExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        if (dot < 0) return "";
        return fileName.substring(dot + 1).toLowerCase();
    }

    private String getBaseName(String fileName) {
        int dot = fileName.lastIndexOf('.');
        if (dot < 0) return fileName;
        return fileName.substring(0, dot);
    }

    private void cleanOutput(Path outputDirectory) {
        if (!Files.isDirectory(outputDirectory)) return;
        try (var entries = Files.list(outputDirectory)) {
            entries.filter(Files::isRegularFile).forEach(p -> {
                try {
                    Files.deleteIfExists(p);
                } catch (IOException ignored) {
                    // best-effort
                }
            });
        } catch (IOException ignored) {
            // best-effort
        }
    }

}
