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

    @Getter
    private Path projectRoot;
    @Setter
    private Consumer<Path> fileOpenListener;

    public ProjectExplorerPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(230, 0));

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

    private void installListeners() {
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() != 2) {
                    return;
                }

                TreePath selection = tree.getPathForLocation(e.getX(), e.getY());

                if (selection == null) {
                    return;
                }

                DefaultMutableTreeNode node =
                        (DefaultMutableTreeNode) selection.getLastPathComponent();

                Object value = node.getUserObject();

                if (!(value instanceof FileNode fileNode)) {
                    return;
                }

                Path path = fileNode.path();

                if (Files.isRegularFile(path) && fileOpenListener != null) {
                    fileOpenListener.accept(path);
                }
            }
        });
    }

    public void openProject(Path root) {
        projectRoot = root.toAbsolutePath().normalize();

        DefaultMutableTreeNode rootNode = createNode(projectRoot);

        tree.setModel(new DefaultTreeModel(rootNode));
        tree.setRootVisible(true);

        expandRoot();
    }

    private DefaultMutableTreeNode createNode(Path path) {
        DefaultMutableTreeNode node =
                new DefaultMutableTreeNode(new FileNode(path));

        if (!Files.isDirectory(path)) {
            return node;
        }

        try {
            List<Path> children = new ArrayList<>();

            try (var stream = Files.list(path)) {
                stream
                        .filter(this::shouldShow)
                        .sorted(
                                Comparator
                                        .comparing((Path p) -> !Files.isDirectory(p))
                                        .thenComparing(
                                                p -> p.getFileName().toString().toLowerCase()
                                        )
                        )
                        .forEach(children::add);
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

            return !name.equals(".git")
                    && !name.equals(".idea")
                    && !name.equals("target")
                    && !name.equals("output");
        }

        String name = path.getFileName().toString().toLowerCase();

        return name.endsWith(".pig")
                || name.endsWith(".y")
                || name.endsWith(".z");
    }

    private void expandRoot() {
        if (tree.getRowCount() > 0) {
            tree.expandRow(0);
        }
    }

    public void refresh() {
        if (projectRoot != null) {
            openProject(projectRoot);
        }
    }

    private record FileNode(Path path) {

        @Override
        public String toString() {
            Path fileName = path.getFileName();

            if (fileName == null) {
                return path.toString();
            }

            return fileName.toString();
        }
    }
}