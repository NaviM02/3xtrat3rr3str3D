package com.navi.ui.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class ProjectFileService {

    public Path createFile(Path directory, String name) throws IOException {
        validateName(name);

        Path file = directory.resolve(name).normalize();

        validateInside(directory, file);

        if (Files.exists(file)) {
            throw new IOException("Ya existe un archivo o carpeta con ese nombre.");
        }

        return Files.createFile(file);
    }

    public Path createDirectory(Path directory, String name) throws IOException {
        validateName(name);

        Path folder = directory.resolve(name).normalize();

        validateInside(directory, folder);

        if (Files.exists(folder)) {
            throw new IOException("Ya existe un archivo o carpeta con ese nombre.");
        }

        return Files.createDirectory(folder);
    }

    public void delete(Path path) throws IOException {
        if (!Files.exists(path)) {
            return;
        }

        if (Files.isDirectory(path)) {
            deleteDirectory(path);
            return;
        }

        Files.delete(path);
    }

    private void deleteDirectory(Path directory) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            Path[] paths = stream
                    .sorted(Comparator.reverseOrder())
                    .toArray(Path[]::new);

            for (Path path : paths) {
                Files.delete(path);
            }
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        if (name.contains("/") || name.contains("\\")) {
            throw new IllegalArgumentException(
                    "El nombre no puede contener separadores de ruta."
            );
        }

        if (name.equals(".") || name.equals("..")) {
            throw new IllegalArgumentException("Nombre inválido.");
        }
    }

    private void validateInside(Path parent, Path child) {
        Path normalizedParent = parent.toAbsolutePath().normalize();
        Path normalizedChild = child.toAbsolutePath().normalize();

        if (!normalizedChild.startsWith(normalizedParent)) {
            throw new IllegalArgumentException("Ruta inválida.");
        }
    }
}