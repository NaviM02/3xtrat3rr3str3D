package com.navi;

import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.c3d.C3DGenerator;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import com.navi.backend.semantic.SemanticAnalyzer;
import com.navi.backend.semantic.SemanticContext;
import com.navi.backend.semantic.errors.SemanticReporter;
import com.navi.backend.semantic.loading.FileModuleLoader;
import com.navi.backend.semantic.loading.ModuleLoader;
import com.navi.ui.CompilerWindow;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    /*public static void main(String[] args) throws Exception {
        Path sourceFile = Path.of(args.length == 0 ? "testfile/main.pig" : args[0])
                .toAbsolutePath().normalize();
        if (!Files.isRegularFile(sourceFile)) {
            throw new IllegalArgumentException("El archivo no existe: " + sourceFile);
        }

        String source = Files.readString(sourceFile, StandardCharsets.UTF_8);
        String fileName = sourceFile.getFileName().toString();
        int dot = fileName.lastIndexOf('.');
        String extension = dot < 0 ? "" : fileName.substring(dot + 1).toLowerCase();

        SemanticContext context = new SemanticContext();
        ModuleLoader loader = new FileModuleLoader(sourceFile.getParent());
        SemanticAnalyzer analyzer = new SemanticAnalyzer(context, loader);
        C3DGenerator c3d = new C3DGenerator(context);

        String code = switch (extension) {
            case "pig" -> {
                Program program = FileModuleLoader.parseLat(source);
                analyzer.analyze(program);
                yield context.getErrors().hasErrors() ? null : c3d.generate(program);
            }
            case "y" -> {
                ProgramY program = FileModuleLoader.parseY(source);
                analyzer.analyze(program);
                yield context.getErrors().hasErrors() ? null : c3d.generate(program);
            }
            case "z" -> {
                ProgramZ program = FileModuleLoader.parseZ(source);
                analyzer.analyze(program);
                yield context.getErrors().hasErrors() ? null : c3d.generate(program);
            }
            default -> throw new IllegalArgumentException("Extensión no soportada: ." + extension);
        };

        System.out.print(SemanticReporter.report(context));
        if (code == null) return;

        System.out.println("\n==================== C3D ====================");
        System.out.print(code);
    }*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FlatMaterialDarkerIJTheme.setup();
            } catch (Exception ignored) {
            }

            CompilerWindow window = new CompilerWindow();
            window.setVisible(true);
        });
    }
}
