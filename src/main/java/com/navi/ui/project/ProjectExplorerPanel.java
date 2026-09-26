package com.navi.ui.project;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class ProjectExplorerPanel extends JPanel {

    private final JTree tree;
    private final ProjectFileService fileService;

    @Getter
    private Path projectRoot;

    @Setter
    private Consumer<Path> fileOpenListener;
    @Setter
    private Consumer<Path> fileDeletedListener;

    public ProjectExplorerPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(240, 0));

        fileService = new ProjectFileService();

        JLabel title = new JLabel("PROYECTO");
        title.setFont(new Font("Arial", Font.BOLD, 13));
        title.setBorder(new EmptyBorder(6, 8, 6, 8));

        tree = new JTree(new DefaultMutableTreeNode("Sin proyecto"));

        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(tree), BorderLayout.CENTER);

        installListeners();
    }

    public void openProject(Path root) {
        projectRoot = root.toAbsolutePath().normalize();

        refresh();
    }

    public void refresh() {
        if (projectRoot == null) {
            return;
        }

        DefaultMutableTreeNode rootNode = createNode(projectRoot);

        tree.setModel(new DefaultTreeModel(rootNode));

        tree.expandRow(0);
    }

    private DefaultMutableTreeNode createNode(Path path) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new FileNode(path));

        if (!Files.isDirectory(path)) {
            return node;
        }

        try {
            List<Path> children = new ArrayList<>();

            try (var stream = Files.list(path)) {
                stream.filter(this::shouldShow).sorted(Comparator.comparing((Path p) -> !Files.isDirectory(p)).thenComparing(p -> p.getFileName().toString().toLowerCase())).forEach(children::add);
            }

            for (Path child : children) {
                node.add(createNode(child));
            }

        } catch (Exception ignored) {
        }

        return node;
    }

    private boolean shouldShow(Path path) {
        if (Files.isDirectory(path)) {
            String name = path.getFileName().toString();

            return !name.equals(".git") && !name.equals(".idea") && !name.equals("target") && !name.equals("output");
        }

        String name = path.getFileName().toString().toLowerCase();

        return name.endsWith(".pig") || name.endsWith(".y") || name.endsWith(".z");
    }

    private void installListeners() {
        tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {

                    openSelectedFile(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                handlePopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handlePopup(e);
            }
        });
    }

    private void openSelectedFile(MouseEvent event) {
        TreePath selection = tree.getPathForLocation(event.getX(), event.getY());

        if (selection == null) {
            return;
        }

        FileNode node = getFileNode(selection);

        if (node == null) {
            return;
        }

        Path path = node.path();

        if (Files.isRegularFile(path) && fileOpenListener != null) {

            fileOpenListener.accept(path);
        }
    }

    private void handlePopup(MouseEvent event) {
        if (!event.isPopupTrigger()) {
            return;
        }

        TreePath selection = tree.getPathForLocation(event.getX(), event.getY());

        if (selection == null) {
            return;
        }

        tree.setSelectionPath(selection);

        FileNode node = getFileNode(selection);

        if (node == null) {
            return;
        }

        createPopupMenu(node.path()).show(tree, event.getX(), event.getY());
    }

    private JPopupMenu createPopupMenu(Path selectedPath) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem newFile = new JMenuItem("Nuevo archivo");

        JMenuItem newFolder = new JMenuItem("Nueva carpeta");

        JMenuItem delete = new JMenuItem("Eliminar");

        newFile.addActionListener(e -> createFile(selectedPath));

        newFolder.addActionListener(e -> createFolder(selectedPath));

        delete.addActionListener(e -> deletePath(selectedPath));

        menu.add(newFile);
        menu.add(newFolder);
        menu.addSeparator();

        if (!selectedPath.equals(projectRoot)) {
            menu.add(delete);
        }

        return menu;
    }

    private void createFile(Path selectedPath) {
        Path directory = resolveDirectory(selectedPath);

        String name = JOptionPane.showInputDialog(this, "Nombre del archivo:", "Nuevo archivo", JOptionPane.PLAIN_MESSAGE);

        if (name == null) {
            return;
        }

        name = name.trim();

        if (name.isBlank()) {
            return;
        }

        if (!isSupportedSourceFile(name)) {
            showError("El archivo debe tener extensión .pig, .y o .z");
            return;
        }

        try {
            Path file = fileService.createFile(directory, name);

            refresh();

            if (fileOpenListener != null) {
                fileOpenListener.accept(file);
            }

        } catch (Exception e) {
            showError("No se pudo crear el archivo:\n" + e.getMessage());
        }
    }

    private void createFolder(Path selectedPath) {
        Path directory = resolveDirectory(selectedPath);

        String name = JOptionPane.showInputDialog(this, "Nombre de la carpeta:", "Nueva carpeta", JOptionPane.PLAIN_MESSAGE);

        if (name == null) {
            return;
        }

        name = name.trim();

        if (name.isBlank()) {
            return;
        }

        try {
            fileService.createDirectory(directory, name);

            refresh();

        } catch (Exception e) {
            showError("No se pudo crear la carpeta:\n" + e.getMessage());
        }
    }

    private void deletePath(Path path) {
        String type = Files.isDirectory(path) ? "la carpeta" : "el archivo";

        String extra = Files.isDirectory(path) ? "\n\nSe eliminará también todo su contenido." : "";

        int result = JOptionPane.showConfirmDialog(this, "¿Eliminar " + type + " \"" + path.getFileName() + "\"?" + extra, "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            fileService.delete(path);

            if (fileDeletedListener != null) {
                fileDeletedListener.accept(path);
            }

            refresh();

        } catch (Exception e) {
            showError("No se pudo eliminar:\n" + e.getMessage());
        }
    }

    private Path resolveDirectory(Path selectedPath) {
        if (Files.isDirectory(selectedPath)) {
            return selectedPath;
        }

        return selectedPath.getParent();
    }

    private boolean isSupportedSourceFile(String name) {
        String lower = name.toLowerCase();

        return lower.endsWith(".pig") || lower.endsWith(".y") || lower.endsWith(".z");
    }

    private FileNode getFileNode(TreePath path) {
        Object component = path.getLastPathComponent();

        if (!(component instanceof DefaultMutableTreeNode treeNode)) {
            return null;
        }

        Object value = treeNode.getUserObject();

        if (!(value instanceof FileNode fileNode)) {
            return null;
        }

        return fileNode;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private record FileNode(Path path) {

        @Override
        public String toString() {
            Path name = path.getFileName();

            return name != null ? name.toString() : path.toString();
        }
    }
}