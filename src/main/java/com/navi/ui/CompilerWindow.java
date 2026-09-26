package com.navi.ui;

import com.navi.backend.compiler.CompilationResult;
import com.navi.backend.compiler.CompilerService;
import com.navi.backend.highlight.HighlightService;
import com.navi.backend.highlight.HighlightSpan;
import com.navi.backend.semantic.SemanticContext;
import com.navi.ui.console.ConsolePanel;
import com.navi.ui.editor.EditorPanel;
import com.navi.ui.project.ProjectExplorerPanel;
import com.navi.ui.symbols.SymbolTablePanel;
import com.navi.ui.symbols.TypeTablePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CompilerWindow extends JFrame {

    private static final Color MIKU = new Color(57, 197, 187);
    private static final Color MIKU_LIGHT = new Color(161, 244, 240);
    private static final Color MIKU_BORDER = new Color(39, 111, 107);
    private static final Color MIKU_HOVER = new Color(47, 156, 149);
    private static final Color BACKGROUND = new Color(28, 28, 28);
    private static final Color ERROR = new Color(255, 82, 82);

    private static final int HIGHLIGHT_DELAY_MS = 200;

    private EditorPanel editorPanel;
    private JTabbedPane editorTabs;
    private ConsolePanel consolePanel;
    private ProjectExplorerPanel projectExplorerPanel;

    private final CompilerService compilerService;
    private final HighlightService highlightService = new HighlightService();
    private SymbolTablePanel symbolTablePanel;
    private TypeTablePanel typeTablePanel;
    private JTabbedPane bottomTabs;

    private Timer highlightTimer;
    private int highlightGeneration;

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

        compilerService = new CompilerService();

        initComponents();
        initStyles();
        initListeners();
    }

    private void initComponents() {
        editorTabs = new JTabbedPane();
        editorTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        consolePanel = new ConsolePanel();
        projectExplorerPanel = new ProjectExplorerPanel();
        symbolTablePanel = new SymbolTablePanel();
        typeTablePanel = new TypeTablePanel();

        bottomTabs = new JTabbedPane();
        bottomTabs.addTab("Consola", consolePanel);
        bottomTabs.addTab("Tabla de símbolos", symbolTablePanel);
        bottomTabs.addTab("Tabla de tipos", typeTablePanel);

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
        JSplitPane editorResultsSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editorTabs, bottomTabs);
        editorResultsSplit.setResizeWeight(0.72);
        editorResultsSplit.setDividerSize(8);

        JSplitPane projectSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectExplorerPanel, editorResultsSplit);
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

        highlightTimer = new Timer(HIGHLIGHT_DELAY_MS, e -> requestHighlight());

        highlightTimer.setRepeats(false);

        editorTabs.addChangeListener(e -> onEditorTabChanged());

        installEditorTabMouseHandling();

        projectExplorerPanel.setFileOpenListener(this::openFile);

        projectExplorerPanel.setFileDeletedListener(this::handleDeletedPath);
    }

    // =========================================================
    // PESTAÑAS DE EDITOR
    // =========================================================

    private void onEditorTabChanged() {
        Component selected = editorTabs.getSelectedComponent();

        if (selected instanceof EditorPanel editor) {
            editorPanel = editor;
            currentFile = editor.getCurrentFile();
        } else {
            editorPanel = null;
            currentFile = null;
        }

        updateFileLabel();
        updateCaretPosition();

        if (editorPanel != null) {
            requestHighlight();
        }
    }

    private EditorPanel createEditor(Path path, String source) {
        EditorPanel editor = new EditorPanel();

        editor.setCurrentFile(path);
        editor.setText(source);

        editor.addCaretListener(e -> {
            if (editor == editorPanel) {
                updateCaretPosition();
            }
        });

        editor.addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                onEditorChanged(editor);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onEditorChanged(editor);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Cambios de atributos (el propio resaltado), no del texto.
            }
        });

        return editor;
    }

    private void onEditorChanged(EditorPanel editor) {
        if (editor != editorPanel) return;

        scheduleHighlight();
    }

    private EditorPanel findEditor(Path path) {
        for (int i = 0; i < editorTabs.getTabCount(); i++) {
            if (editorTabs.getComponentAt(i) instanceof EditorPanel editor && path.equals(editor.getCurrentFile())) {
                return editor;
            }
        }

        return null;
    }

    private void closeEditor(EditorPanel editor) {
        int index = editorTabs.indexOfComponent(editor);

        if (index >= 0) {
            editorTabs.remove(index);
        }
    }

    private void closeOthers(EditorPanel keep) {
        List<EditorPanel> others = new ArrayList<>();

        for (int i = 0; i < editorTabs.getTabCount(); i++) {
            if (editorTabs.getComponentAt(i) instanceof EditorPanel editor && editor != keep) {
                others.add(editor);
            }
        }

        for (EditorPanel editor : others) {
            closeEditor(editor);
        }

        editorTabs.setSelectedComponent(keep);
    }

    private void closeAllEditors() {
        editorTabs.removeAll();
    }

    private void installEditorTabMouseHandling() {
        editorTabs.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    int index = editorTabs.indexAtLocation(e.getX(), e.getY());

                    if (index >= 0 && editorTabs.getComponentAt(index) instanceof EditorPanel editor) {
                        closeEditor(editor);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                maybeShowEditorPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                maybeShowEditorPopup(e);
            }
        });
    }

    private void maybeShowEditorPopup(MouseEvent event) {
        if (!event.isPopupTrigger()) return;

        int index = editorTabs.indexAtLocation(event.getX(), event.getY());

        if (index < 0 || !(editorTabs.getComponentAt(index) instanceof EditorPanel editor)) return;

        JPopupMenu menu = new JPopupMenu();

        JMenuItem close = new JMenuItem("Cerrar");
        close.addActionListener(e -> closeEditor(editor));
        menu.add(close);

        JMenuItem closeOthers = new JMenuItem("Cerrar las demás");
        closeOthers.addActionListener(e -> closeOthers(editor));
        menu.add(closeOthers);

        JMenuItem closeAll = new JMenuItem("Cerrar todas");
        closeAll.addActionListener(e -> closeAllEditors());
        menu.add(closeAll);

        menu.show(editorTabs, event.getX(), event.getY());
    }

    private void updateFileLabel() {
        fileLabel.setText(currentFile == null ? "Sin archivo" : currentFile.getFileName().toString());
    }

    private void updateCaretPosition() {
        if (editorPanel == null) {
            lineLabel.setText("Línea: 1");
            columnLabel.setText("Columna: 1");

            return;
        }

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
    // RESALTADO
    // =========================================================

    private void scheduleHighlight() {
        if (editorPanel == null) return;

        highlightGeneration++;

        highlightTimer.restart();
    }

    private void requestHighlight() {
        EditorPanel editor = editorPanel;
        Path file = currentFile;

        if (editor == null || file == null) return;

        String extension = getExtension(file.getFileName().toString());

        if (highlightService.isSupported(extension)) {
            editor.clearHighlights();

            return;
        }

        String source = editor.getText();

        int generation = highlightGeneration;

        SwingWorker<List<HighlightSpan>, Void> worker = new SwingWorker<>() {

            @Override
            protected List<HighlightSpan> doInBackground() {
                return highlightService.highlight(source, extension);
            }

            @Override
            protected void done() {
                if (generation != highlightGeneration) return;

                try {
                    editor.applyHighlights(get());
                } catch (Exception ignored) {
                }
            }
        };

        worker.execute();
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
        Path path = file.toAbsolutePath().normalize();

        EditorPanel existing = findEditor(path);

        if (existing != null) {
            editorTabs.setSelectedComponent(existing);

            return;
        }

        try {
            String source = Files.readString(path, StandardCharsets.UTF_8);

            EditorPanel editor = createEditor(path, source);

            editorTabs.addTab(path.getFileName().toString(), editor);
            editorTabs.setSelectedComponent(editor);

            consolePanel.appendLine("Archivo abierto: " + path);

            setSuccessStatus("Archivo abierto");

        } catch (IOException e) {
            showError("No se pudo abrir el archivo.\n" + e.getMessage());
        }
    }

    private boolean saveCurrentFile() {
        if (editorPanel == null || currentFile == null) {
            showError("No hay ningún archivo seleccionado.");

            return false;
        }

        try {
            Files.writeString(currentFile, editorPanel.getText(), StandardCharsets.UTF_8);

            updateFileLabel();

            setSuccessStatus("Archivo guardado");

            return true;

        } catch (IOException e) {
            showError("No se pudo guardar el archivo.\n" + e.getMessage());

            return false;
        }
    }

    private void handleDeletedPath(Path deletedPath) {
        List<EditorPanel> affected = new ArrayList<>();

        for (int i = 0; i < editorTabs.getTabCount(); i++) {
            if (editorTabs.getComponentAt(i) instanceof EditorPanel editor) {
                Path file = editor.getCurrentFile();

                if (file != null && file.startsWith(deletedPath)) {
                    affected.add(editor);
                }
            }
        }

        if (affected.isEmpty()) return;

        for (EditorPanel editor : affected) {
            closeEditor(editor);
        }

        symbolTablePanel.clear();
        typeTablePanel.clear();

        setStatus("Archivo eliminado");
        consolePanel.appendLine("Se cerró el archivo porque fue eliminado.");
    }

    // =========================================================
    // COMPILACIÓN
    // =========================================================

    private void compile() {
        if (editorPanel == null || currentFile == null) {
            showError("Selecciona un archivo del proyecto antes de compilar.");
            return;
        }

        if (!saveCurrentFile()) return;

        consolePanel.clear();
        consolePanel.appendLine("Compilando: " + currentFile);

        setStatus("Compilando...");

        try {
            CompilationResult result = compilerService.compile(currentFile);

            if (!result.isSuccessful()) {
                consolePanel.appendErrorLine("La compilación contiene errores.");

                String errors = formatErrors(result.getSemanticContext());

                if (!errors.isBlank()) {
                    consolePanel.append(errors);
                }
                setErrorStatus("Errores de compilación");

                return;
            }

            symbolTablePanel.setSymbolTable(result.getSemanticContext().getSymbolTable());
            typeTablePanel.setTypeTable(result.getSemanticContext().getTypeTable());
            bottomTabs.setSelectedComponent(consolePanel);

            consolePanel.appendSuccessLine("Análisis semántico completado.");
            consolePanel.appendSuccessLine("Código de tres direcciones generado.");
            consolePanel.appendLine("Archivo C: " + result.getCFile());
            consolePanel.appendSuccessLine("Compilado con gcc: " + result.getExecutable());
            consolePanel.appendSuccessLine("\nCOMPILACIÓN EXITOSA");

            setSuccessStatus("Compilación exitosa");
            projectExplorerPanel.refresh();

        } catch (Exception e) {
            consolePanel.appendErrorLine("Error durante la compilación: " + e.getMessage());
            setErrorStatus("Error");
            e.printStackTrace();
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
        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
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

    private String formatErrors(SemanticContext context) {
        List<String> errors = context.getErrors().getErrors();

        if (errors.isEmpty()) return "";

        StringBuilder builder = new StringBuilder();

        for (String error : errors) {
            builder.append(error).append("\n");
        }

        return builder.toString();
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