package com.navi.ui;

import com.navi.backend.ast.lat.global.Program;
import com.navi.backend.ast.y.global.ProgramY;
import com.navi.backend.ast.z.global.ProgramZ;
import com.navi.backend.c3d.C3DGenerator;
import com.navi.backend.c3d.CGenerator;
import com.navi.backend.semantic.*;
import com.navi.ui.console.ConsolePanel;
import com.navi.ui.editor.EditorPanel;
import com.navi.ui.project.ProjectExplorerPanel;
import com.navi.ui.symbols.SymbolTablePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CompilerWindow extends JFrame {

    private static final Color MIKU = new Color(57, 197, 187);
    private static final Color MIKU_LIGHT = new Color(161, 244, 240);
    private static final Color MIKU_BORDER = new Color(39, 111, 107);
    private static final Color MIKU_HOVER = new Color(47, 156, 149);
    private static final Color BACKGROUND = new Color(28, 28, 28);
    private static final Color ERROR = new Color(255, 82, 82);

    private EditorPanel editorPanel;
    private ConsolePanel consolePanel;
    private ProjectExplorerPanel projectExplorerPanel;

    // Por ahora no se muestra.
    private SymbolTablePanel symbolTablePanel;

    private JLabel lineLabel;
    private JLabel columnLabel;
    private JLabel fileLabel;
    private JLabel statusLabel;

    private JButton compileButton;

    private Path currentFile;

    public CompilerWindow() {
        setTitle("C3D Compiler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(1100, 700));
        setSize(1400, 850);
        setLocationRelativeTo(null);

        initComponents();
        initStyles();
        initListeners();
    }

    private void initComponents() {
        editorPanel = new EditorPanel();
        consolePanel = new ConsolePanel();
        projectExplorerPanel = new ProjectExplorerPanel();
        symbolTablePanel = new SymbolTablePanel();

        compileButton = new JButton("Compilar");

        lineLabel = new JLabel("Línea: 1");
        columnLabel = new JLabel("Columna: 1");
        fileLabel = new JLabel("Sin archivo");
        statusLabel = new JLabel("Listo");

        setJMenuBar(createMenuBar());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(8, 8, 8, 8));

        mainPanel.add(createToolbar(), BorderLayout.NORTH);
        mainPanel.add(createMainContent(), BorderLayout.CENTER);
        mainPanel.add(createStatusBar(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private Component createMainContent() {

        JSplitPane editorConsoleSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editorPanel, consolePanel);

        editorConsoleSplit.setResizeWeight(0.75);
        editorConsoleSplit.setDividerSize(8);

        JSplitPane projectSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectExplorerPanel, editorConsoleSplit);

        projectSplit.setResizeWeight(0.18);
        projectSplit.setDividerLocation(240);
        projectSplit.setDividerSize(8);

        return projectSplit;
    }

    private JPanel createToolbar() {
        JPanel toolbar = new JPanel(new BorderLayout());

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));

        left.add(compileButton);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> saveCurrentFile());
        styleButton(saveButton);

        left.add(saveButton);

        JButton clearButton = new JButton("Limpiar consola");
        clearButton.addActionListener(e -> consolePanel.clear());
        styleButton(clearButton);

        left.add(clearButton);

        toolbar.add(left, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 4));

        right.add(statusLabel);

        toolbar.add(right, BorderLayout.EAST);

        return toolbar;
    }

    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());

        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 8, 2, 8));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));

        left.add(lineLabel);
        left.add(columnLabel);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));

        right.add(fileLabel);
        right.add(new JLabel("UTF-8"));

        statusBar.add(left, BorderLayout.WEST);
        statusBar.add(right, BorderLayout.EAST);

        return statusBar;
    }

    private void initListeners() {
        compileButton.addActionListener(e -> compile());

        editorPanel.addCaretListener(e -> updateCaretPosition());

        projectExplorerPanel.setFileOpenListener(this::openFile);
    }

    private void updateCaretPosition() {
        try {
            JTextPane editor = editorPanel.getEditor();

            int position = editor.getCaretPosition();

            javax.swing.text.Element root = editor.getDocument().getDefaultRootElement();

            int line = root.getElementIndex(position);

            int start = root.getElement(line).getStartOffset();

            int column = position - start;

            lineLabel.setText("Línea: " + (line + 1));

            columnLabel.setText("Columna: " + (column + 1));

        } catch (Exception ignored) {
        }
    }

    // =========================================================
    // PROYECTO
    // =========================================================

    private void openProject() {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setDialogTitle("Abrir proyecto");

        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path project = chooser.getSelectedFile().toPath().toAbsolutePath().normalize();

        projectExplorerPanel.openProject(project);

        setTitle("C3D Compiler - " + project.getFileName());

        consolePanel.appendSuccessLine("Proyecto abierto: " + project);

        setSuccessStatus("Proyecto abierto");
    }

    // =========================================================
    // ARCHIVOS
    // =========================================================

    private void openFile(Path file) {
        try {
            String source = Files.readString(file, StandardCharsets.UTF_8);

            currentFile = file.toAbsolutePath().normalize();

            editorPanel.setCurrentFile(currentFile);
            editorPanel.setText(source);

            fileLabel.setText(currentFile.getFileName().toString());

            setSuccessStatus("Archivo abierto");

            consolePanel.appendLine("Archivo abierto: " + currentFile);

        } catch (IOException e) {
            showError("No se pudo abrir el archivo.\n" + e.getMessage());
        }
    }

    private boolean saveCurrentFile() {
        if (currentFile == null) {
            showError("No hay ningún archivo seleccionado.");

            return false;
        }

        try {
            Files.writeString(currentFile, editorPanel.getText(), StandardCharsets.UTF_8);

            fileLabel.setText(currentFile.getFileName().toString());

            setSuccessStatus("Archivo guardado");

            return true;

        } catch (IOException e) {
            showError("No se pudo guardar el archivo.\n" + e.getMessage());

            return false;
        }
    }

    // =========================================================
    // COMPILACIÓN
    // =========================================================

    private void compile() {
        if (currentFile == null) {
            showError("Selecciona un archivo del proyecto antes de compilar.");

            return;
        }

        /*
         * Muy importante:
         *
         * El editor NO es directamente la entrada del compilador.
         *
         * Primero guardamos el archivo y luego compilamos
         * utilizando su ubicación real.
         */
        if (!saveCurrentFile()) {
            return;
        }

        consolePanel.clear();

        consolePanel.appendLine("Compilando: " + currentFile);

        setStatus("Compilando...");

        try {
            compileFile(currentFile);

        } catch (SemanticError e) {
            consolePanel.appendErrorLine("Error semántico: " + e.getMessage());

            setErrorStatus("Error semántico");

        } catch (Exception e) {
            consolePanel.appendErrorLine("Error inesperado: " + e.getMessage());

            setErrorStatus("Error");

            e.printStackTrace();
        }
    }

    private void compileFile(Path file) throws Exception {

        String source = Files.readString(file, StandardCharsets.UTF_8);

        String name = file.getFileName().toString();

        String ext = getExtension(name);

        SemanticContext ctx = new SemanticContext();

        /*
         * Los imports se resuelven respecto al directorio
         * donde se encuentra el archivo actual.
         */
        ModuleLoader loader = new FileModuleLoader(file.getParent());

        SemanticAnalyzer analyzer = new SemanticAnalyzer(ctx, loader);

        C3DGenerator c3d = new C3DGenerator(ctx);

        String code = null;

        switch (ext) {

            case "pig" -> {
                Program program = FileModuleLoader.parseLat(source);

                analyzer.analyze(program);

                if (!ctx.getErrors().hasErrors()) {
                    code = c3d.generate(program);
                }
            }

            case "y" -> {
                ProgramY program = FileModuleLoader.parseY(source);

                analyzer.analyze(program);

                if (!ctx.getErrors().hasErrors()) {
                    code = c3d.generate(program);
                }
            }

            case "z" -> {
                ProgramZ program = FileModuleLoader.parseZ(source);

                analyzer.analyze(program);

                if (!ctx.getErrors().hasErrors()) {
                    code = c3d.generate(program);
                }
            }

            default -> {
                consolePanel.appendErrorLine("Extensión no soportada: ." + ext);

                setErrorStatus("Extensión no soportada");

                return;
            }
        }

        // =====================================================
        // REPORTE SEMÁNTICO
        // =====================================================

        String report = SemanticReporter.report(ctx);

        if (report != null && !report.isBlank()) {
            consolePanel.appendLine("");
            consolePanel.append(report);

            if (!report.endsWith("\n")) {
                consolePanel.append("\n");
            }
        }

        if (ctx.getErrors().hasErrors()) {
            consolePanel.appendErrorLine("\nLa compilación contiene errores.");

            setErrorStatus("Errores de compilación");

            return;
        }

        // Más adelante:
        //
        // symbolTablePanel.setContext(ctx);
        //
        // o
        //
        // symbolTablePanel.setSymbolTable(...);

        consolePanel.appendSuccessLine("\nAnálisis semántico completado.");

        if (code != null) {
            consolePanel.appendSuccessLine("Código de tres direcciones generado.");

            emitC(name, c3d);
        }

        consolePanel.appendSuccessLine("\nCOMPILACIÓN EXITOSA");

        setSuccessStatus("Compilación exitosa");
    }

    // =========================================================
    // C
    // =========================================================

    private void emitC(String fileName, C3DGenerator c3d) {

        String base = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;

        try {
            String cSource = new CGenerator(c3d.quads()).generate();

            Path outDir;

            /*
             * Es mejor que output quede dentro del proyecto.
             */
            if (projectExplorerPanel.getProjectRoot() != null) {
                outDir = projectExplorerPanel.getProjectRoot().resolve("output");
            } else {
                outDir = Path.of("output");
            }

            cleanOutput(outDir);

            Files.createDirectories(outDir);

            Path cFile = outDir.resolve(base + ".c");

            Files.writeString(cFile, cSource, StandardCharsets.UTF_8);

            consolePanel.appendLine("Archivo C: " + cFile.toAbsolutePath());

            Path exe = outDir.resolve(base);

            Process gcc = new ProcessBuilder("gcc", "-O0", "-o", exe.toString(), cFile.toString()).redirectErrorStream(true).start();

            String gccOut = new String(gcc.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            int status = gcc.waitFor();

            if (status == 0) {
                consolePanel.appendSuccessLine("Compilado con gcc: " + exe.toAbsolutePath());

            } else {
                consolePanel.appendErrorLine("gcc terminó con código " + status);

                consolePanel.appendError(gccOut);
            }

            projectExplorerPanel.refresh();

        } catch (Exception e) {
            consolePanel.appendErrorLine("No se pudo generar/compilar C: " + e.getMessage());
        }
    }

    private void cleanOutput(Path outDir) {
        if (!Files.isDirectory(outDir)) {
            return;
        }

        try (var entries = Files.list(outDir)) {

            entries.filter(Files::isRegularFile).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException ignored) {
                }
            });

        } catch (IOException ignored) {
        }
    }

    // =========================================================
    // MENÚ
    // =========================================================

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem openProject = new JMenuItem("Abrir proyecto...");
        JMenuItem save = new JMenuItem("Guardar");
        JMenuItem exit = new JMenuItem("Salir");
        openProject.addActionListener(e -> openProject());
        save.addActionListener(e -> saveCurrentFile());
        exit.addActionListener(e -> System.exit(0));

        fileMenu.add(openProject);
        fileMenu.addSeparator();
        fileMenu.add(save);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        JMenu compileMenu = new JMenu("Compilar");
        JMenuItem compile = new JMenuItem("Compilar archivo actual");
        compile.addActionListener(e -> compile());
        compileMenu.add(compile);
        menuBar.add(fileMenu);
        menuBar.add(compileMenu);

        return menuBar;
    }

    // =========================================================
    // UTILIDADES
    // =========================================================

    private String getExtension(String name) {
        int dot = name.lastIndexOf('.');
        if (dot < 0) return "";
        return name.substring(dot + 1).toLowerCase();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        setErrorStatus("Error");
    }

    private void setStatus(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(MIKU_LIGHT);
    }

    private void setErrorStatus(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(ERROR);
    }

    private void setSuccessStatus(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(MIKU);
    }

    // =========================================================
    // ESTILOS
    // =========================================================

    private void initStyles() {
        getContentPane().setBackground(BACKGROUND);

        styleButton(compileButton);

        styleLabel(lineLabel);
        styleLabel(columnLabel);
        styleLabel(fileLabel);
        styleLabel(statusLabel);

        styleMenuBar(getJMenuBar());
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.setForeground(MIKU_LIGHT);

        button.setBackground(new Color(40, 56, 56));

        button.setFocusPainted(false);

        button.setBorder(BorderFactory.createLineBorder(MIKU_BORDER, 2));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color normal = button.getBackground();

        button.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(MIKU_HOVER);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(normal);
            }
        });
    }

    private void styleLabel(JLabel label) {
        label.setForeground(MIKU);
        label.setFont(new Font("Arial", Font.BOLD, 13));
    }

    private void styleMenuBar(JMenuBar menuBar) {
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            menu.setForeground(MIKU);
            menu.setFont(new Font("Arial", Font.BOLD, 14));
            styleMenu(menu);
        }
    }

    private void styleMenu(JMenu menu) {
        for (Component component : menu.getMenuComponents()) {

            if (component instanceof JMenuItem item) {
                item.setForeground(MIKU);
                item.setBackground(new Color(40, 56, 56));
                item.setFont(new Font("Arial", Font.BOLD, 14));
                item.setOpaque(true);
            }

            if (component instanceof JMenu submenu) {
                styleMenu(submenu);
            }
        }
    }
}