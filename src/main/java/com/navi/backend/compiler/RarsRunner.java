package com.navi.backend.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/** Una sesión RARS sin ventana, con entrada y salida conectadas a la aplicación. */
public class RarsRunner implements AutoCloseable {

    private final ExecutorService inputExecutor = Executors.newSingleThreadExecutor(task -> {
        Thread thread = new Thread(task, "rars-input");
        thread.setDaemon(true);
        return thread;
    });
    private Process process;
    private boolean stopped;

    public synchronized void start(Path assemblyFile) throws IOException {
        if (stopped) throw new IOException("La ejecución fue detenida.");
        if (process != null) throw new IllegalStateException("La sesión ya fue iniciada.");
        Path jar = locateJar();
        String executable = System.getProperty("os.name").startsWith("Windows") ? "java.exe" : "java";
        Path java = Path.of(System.getProperty("java.home"), "bin", executable);
        process = new ProcessBuilder(java.toString(), "-Dfile.encoding=UTF-8", "-Djava.awt.headless=true",
                "-jar", jar.toString(), "nc", "me", "ae1", "se2", assemblyFile.toAbsolutePath().toString())
                .redirectErrorStream(true)
                .start();
    }

    // Lee fragmentos, no líneas: los prompts sin salto de línea también deben aparecer.
    public int readOutput(Consumer<String> output) throws IOException, InterruptedException {
        Process running;
        synchronized (this) {
            if (process == null) throw new IllegalStateException("La sesión no ha sido iniciada.");
            running = process;
        }
        try (InputStreamReader reader = new InputStreamReader(running.getInputStream(), StandardCharsets.UTF_8)) {
            char[] buffer = new char[2048];
            int count;
            while ((count = reader.read(buffer)) != -1) {
                output.accept(new String(buffer, 0, count));
            }
        }
        return running.waitFor();
    }

    public CompletableFuture<Void> sendLine(String line) {
        return CompletableFuture.runAsync(() -> {
            Process running;
            synchronized (this) {
                running = process;
                if (stopped || running == null || !running.isAlive()) {
                    throw new CompletionException(new IOException("El programa ya no está en ejecución."));
                }
            }
            try {
                running.getOutputStream().write((line + "\n").getBytes(StandardCharsets.UTF_8));
                running.getOutputStream().flush();
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        }, inputExecutor);
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    @Override
    public synchronized void close() {
        stopped = true;
        if (process != null && process.isAlive()) process.destroyForcibly();
        inputExecutor.shutdownNow();
    }

    private static Path locateJar() throws IOException {
        Path embeddedJar = extractEmbeddedJar();
        if (embeddedJar != null) return embeddedJar;

        Path workingDirectory = Path.of("").toAbsolutePath();
        Path jar = workingDirectory.resolve("lib/rars1_6.jar");
        if (Files.isRegularFile(jar)) return jar;
        // También permite iniciar la UI desde otro directorio (classes o jar de la aplicación).
        try {
            Path location = Path.of(RarsRunner.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            for (Path directory = location.getParent(); directory != null; directory = directory.getParent()) {
                jar = directory.resolve("lib/rars1_6.jar");
                if (Files.isRegularFile(jar)) return jar;
            }
        } catch (Exception e) {
            throw new IOException("No se pudo localizar lib/rars1_6.jar.", e);
        }
        throw new IOException("No se encontró lib/rars1_6.jar junto a la aplicación o en " + workingDirectory);
    }

    private static Path extractEmbeddedJar() throws IOException {
        try (InputStream resource = RarsRunner.class.getResourceAsStream("/embedded/rars1_6.jar")) {
            if (resource == null) return null;

            Path extracted = Files.createTempFile("3xtrat3rr3str3D-rars-", ".jar");
            try {
                Files.copy(resource, extracted, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                extracted.toFile().deleteOnExit();
                return extracted;
            } catch (IOException e) {
                Files.deleteIfExists(extracted);
                throw e;
            }
        }
    }
}
