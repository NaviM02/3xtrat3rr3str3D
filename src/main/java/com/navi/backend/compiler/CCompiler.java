package com.navi.backend.compiler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** Compila y enlaza el C generado usando GCC instalado en el sistema. */
public class CCompiler {

    public void compile(Path source, Path object, Path executable) throws IOException, InterruptedException {
        run(List.of("gcc", "-std=c11", "-c", source.toString(), "-o", object.toString()), "compilar C");
        run(List.of("gcc", object.toString(), "-lm", "-o", executable.toString()), "enlazar el ejecutable C");
    }

    private void run(List<String> command, String stage) throws IOException, InterruptedException {
        Path log = Files.createTempFile("3xtrat3rr3str3D-gcc-", ".log");
        Process process = null;
        try {
            try {
                process = new ProcessBuilder(command).redirectErrorStream(true)
                        .redirectOutput(log.toFile()).start();
            } catch (IOException e) {
                throw new IOException("No se pudo iniciar GCC. Comprueba que esté instalado y disponible en PATH.", e);
            }
            process.getOutputStream().close();
            if (!process.waitFor(60, TimeUnit.SECONDS)) {
                throw new IOException("GCC excedió el tiempo de espera al " + stage + ".");
            }
            if (process.exitValue() != 0) {
                String diagnostics = Files.readString(log, StandardCharsets.UTF_8).strip();
                throw new IOException("Error al " + stage + " (GCC, código " + process.exitValue() + "):\n" + diagnostics);
            }
        } finally {
            if (process != null && process.isAlive()) {
                process.destroyForcibly().waitFor();
            }
            Files.deleteIfExists(log);
        }
    }
}
