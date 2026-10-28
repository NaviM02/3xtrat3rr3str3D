package com.navi.backend.compiler;

import com.navi.backend.semantic.SemanticContext;
import lombok.Getter;

import java.nio.file.Path;

@Getter
public class CompilationResult {

    private final Path sourceFile;
    private final SemanticContext semanticContext;

    private final String c3d;

    private final Path riscVFile;
    private final Path cFile;
    private final Path objectFile;
    private final Path executableFile;

    private final boolean successful;

    private final String message;

    public CompilationResult(
            Path sourceFile, SemanticContext semanticContext, String c3d,
            Path riscVFile, boolean successful, String message
    ) {
        this(sourceFile, semanticContext, c3d, riscVFile, null, successful, message);
    }

    public CompilationResult(
            Path sourceFile, SemanticContext semanticContext, String c3d,
            Path riscVFile, Path cFile, boolean successful, String message
    ) {
        this(sourceFile, semanticContext, c3d, riscVFile, cFile, null, null, successful, message);
    }

    public CompilationResult(
            Path sourceFile, SemanticContext semanticContext, String c3d,
            Path riscVFile, Path cFile, Path objectFile, Path executableFile,
            boolean successful, String message
    ) {
        this.sourceFile = sourceFile;
        this.semanticContext = semanticContext;
        this.c3d = c3d;
        this.riscVFile = riscVFile;
        this.cFile = cFile;
        this.objectFile = objectFile;
        this.executableFile = executableFile;
        this.successful = successful;
        this.message = message;
    }
}
