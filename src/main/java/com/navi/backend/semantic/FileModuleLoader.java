package com.navi.backend.semantic;

import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.y.visitors.ProgramYVisitor;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.ast.z.visitors.ProgramZVisitor;
import com.navi.backend.lexer_parser.lat.PigLatinLexer;
import com.navi.backend.lexer_parser.lat.PigLatinParser;
import com.navi.backend.lexer_parser.y.IndentationTokenSource;
import com.navi.backend.lexer_parser.y.YLexer;
import com.navi.backend.lexer_parser.y.YParser;
import com.navi.backend.lexer_parser.z.ZLexer;
import com.navi.backend.lexer_parser.z.ZParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Carga los módulos importados desde el sistema de archivos, resolviendo rutas
 * relativas al directorio del archivo .pig de entrada.
 *
 * <p>Ejemplo: {@code import utils.utils.y} con el .pig en {@code prueba/} resuelve
 * a {@code prueba/utils/utils.y} (los '.' del path se mapean a '/').</p>
 */
public class FileModuleLoader implements ModuleLoader {

    private final Path baseDir;

    public FileModuleLoader(Path baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public ProgramY loadY(String importPath) {
        return parseY(read(resolve(importPath, "y")));
    }

    @Override
    public ProgramZ loadZ(String importPath) {
        return parseZ(read(resolve(importPath, "z")));
    }

    private Path resolve(String importPath, String ext) {
        String modulePath = new ImportPath(importPath).getModulePath();
        return baseDir.resolve(modulePath.replace('.', '/') + "." + ext).normalize();
    }

    private String read(Path file) {
        try {
            return Files.readString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SemanticError("No se pudo cargar el módulo: " + file);
        }
    }

    public static Program parseLat(String source) {
        PigLatinLexer lexer = new PigLatinLexer(CharStreams.fromString(source));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PigLatinParser parser = new PigLatinParser(tokens);
        CollectingErrorListener errors = new CollectingErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errors);
        PigLatinParser.ProgramContext tree = parser.program();
        if (errors.hasErrors()) throw new SemanticError(String.join("\n", errors.getErrors()));
        return (Program) new com.navi.backend.ast.lat.visitors.ProgramVisitor().visit(tree);
    }

    public static ProgramY parseY(String source) {
        YLexer lexer = new YLexer(CharStreams.fromString(source));
        IndentationTokenSource indentation = new IndentationTokenSource(lexer);
        CommonTokenStream tokens = new CommonTokenStream(indentation);
        YParser parser = new YParser(tokens);
        CollectingErrorListener errors = new CollectingErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errors);
        YParser.ProgramContext tree = parser.program();
        if (errors.hasErrors()) throw new SemanticError(String.join("\n", errors.getErrors()));
        return (ProgramY) new ProgramYVisitor().visit(tree); // todo: change
    }

    public static ProgramZ parseZ(String source) {
        ZLexer lexer = new ZLexer(CharStreams.fromString(source));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ZParser parser = new ZParser(tokens);
        CollectingErrorListener errors = new CollectingErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errors);
        ZParser.ProgramContext tree = parser.program();
        if (errors.hasErrors()) throw new SemanticError(String.join("\n", errors.getErrors()));
        return (ProgramZ) new ProgramZVisitor().visit(tree);
    }
}
