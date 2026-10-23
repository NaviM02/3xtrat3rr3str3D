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

    private final boolean successful;

    private final String message;

    public CompilationResult(
            Path sourceFile, SemanticContext semanticContext, String c3d,
            Path riscVFile, boolean successful, String message
    ) {
        this.sourceFile = sourceFile;
        this.semanticContext = semanticContext;
        this.c3d = c3d;
        this.riscVFile = riscVFile;
        this.successful = successful;
        this.message = message;
    }
}
