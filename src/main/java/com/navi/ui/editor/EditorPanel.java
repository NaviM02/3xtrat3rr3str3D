package com.navi.ui.editor;

import com.navi.backend.highlight.HighlightKind;
import com.navi.backend.highlight.HighlightSpan;
import com.navi.ui.utils.NumeroLinea;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EditorPanel extends JPanel {

    private static final Color EDITOR_BACKGROUND = new Color(25, 25, 25);
    private static final Color TEXT = new Color(224, 224, 224);
    private static final Color MIKU = new Color(57, 197, 187);

    private static final Color KEYWORD = new Color(197, 146, 234);
    private static final Color TYPE = new Color(78, 201, 176);
    private static final Color BOOLEAN = new Color(255, 203, 107);
    private static final Color NUMBER = new Color(181, 206, 168);
    private static final Color STRING = new Color(206, 145, 120);
    private static final Color CHAR = new Color(206, 145, 120);
    private static final Color COMMENT = new Color(106, 153, 85);
    private static final Color OPERATOR = new Color(212, 212, 212);
    private static final Color PUNCTUATION = new Color(42, 106, 239);

    private static final AttributeSet DEFAULT_STYLE = styleOf(TEXT, false);
    private static final Map<HighlightKind, AttributeSet> HIGHLIGHT_STYLES = buildStyles();

    @Getter
    private final JTextPane editor;

    private final JLabel fileNameLabel;
    private final JLabel extensionLabel;

    private final UndoManager undoManager = new UndoManager();

    private Path currentFile;

    public EditorPanel() {
        setLayout(new BorderLayout());

        editor = new JTextPane();

        installUndoRedo();
        installTabBehavior();

        JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setRowHeaderView(new NumeroLinea(editor));

        fileNameLabel = new JLabel("  Sin archivo");
        fileNameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        extensionLabel = new JLabel("  ");

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(4, 4, 4, 4));

        header.add(fileNameLabel, BorderLayout.WEST);
        header.add(extensionLabel, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        style();
    }

    private void style() {
        editor.setBackground(EDITOR_BACKGROUND);
        editor.setForeground(TEXT);
        editor.setCaretColor(MIKU);
        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
    }

    private void installUndoRedo() {
        editor.getDocument().addUndoableEditListener(event -> {
            UndoableEdit edit = event.getEdit();

            if (edit instanceof AbstractDocument.DefaultDocumentEvent documentEvent
                    && documentEvent.getType() == DocumentEvent.EventType.CHANGE) {
                return; // No registrar los cambios de atributos del resaltado.
            }

            undoManager.addEdit(edit);
        });

        editor.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "editor-undo");
        editor.getActionMap().put("editor-undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });

        editor.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "editor-redo");
        editor.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "editor-redo");
        editor.getActionMap().put("editor-redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            }
        });
    }

    private void installTabBehavior() {
        editor.setFocusTraversalKeysEnabled(false);

        editor.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "editor-insert-tab");
        editor.getActionMap().put("editor-insert-tab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.replaceSelection("    ");
            }
        });
    }

    private static Map<HighlightKind, AttributeSet> buildStyles() {
        Map<HighlightKind, AttributeSet> styles = new EnumMap<>(HighlightKind.class);

        styles.put(HighlightKind.KEYWORD, styleOf(KEYWORD, true));
        styles.put(HighlightKind.TYPE, styleOf(TYPE, true));
        styles.put(HighlightKind.BOOLEAN, styleOf(BOOLEAN, false));
        styles.put(HighlightKind.NUMBER, styleOf(NUMBER, false));
        styles.put(HighlightKind.STRING, styleOf(STRING, false));
        styles.put(HighlightKind.CHAR, styleOf(CHAR, false));
        styles.put(HighlightKind.COMMENT, styleOf(COMMENT, false));
        styles.put(HighlightKind.OPERATOR, styleOf(OPERATOR, false));
        styles.put(HighlightKind.PUNCTUATION, styleOf(PUNCTUATION, false));
        styles.put(HighlightKind.IDENTIFIER, styleOf(TEXT, false));

        return styles;
    }

    private static AttributeSet styleOf(Color color, boolean bold) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();

        StyleConstants.setForeground(attributes, color);

        if (bold) {
            StyleConstants.setBold(attributes, true);
        }

        return attributes;
    }

    /** Repinta el documento con los rangos indicados, limpiando los anteriores. */
    public void applyHighlights(List<HighlightSpan> spans) {
        StyledDocument document = editor.getStyledDocument();
        int length = document.getLength();

        if (length > 0) {
            document.setCharacterAttributes(0, length, DEFAULT_STYLE, true);
        }

        if (spans == null) return;

        for (HighlightSpan span : spans) {
            int start = span.start();
            int end = start + span.length();

            if (start < 0 || start >= end || end > length) continue;

            AttributeSet attributes = HIGHLIGHT_STYLES.get(span.kind());

            if (attributes == null) continue;

            document.setCharacterAttributes(start, span.length(), attributes, true);
        }
    }

    public void clearHighlights() {
        applyHighlights(List.of());
    }

    public void addDocumentListener(DocumentListener listener) {
        editor.getDocument().addDocumentListener(listener);
    }

    public String getText() {
        return editor.getText();
    }

    public void setText(String text) {
        editor.setText(text);
        editor.setCaretPosition(0);
        undoManager.discardAllEdits();
    }

    public void setCurrentFile(Path file) {
        this.currentFile = file;

        if (file == null) {
            fileNameLabel.setText("  Sin archivo");
            extensionLabel.setText("  ");
            return;
        }

        String name = file.getFileName().toString();

        fileNameLabel.setText("  " + name);

        int dot = name.lastIndexOf('.');
        String extension = dot >= 0 ? name.substring(dot) : "";

        extensionLabel.setText(extension + "  ");
    }

    public Path getCurrentFile() {
        return currentFile;
    }

    public void clear() {
        currentFile = null;
        editor.setText("");
        undoManager.discardAllEdits();
        setCurrentFile(null);
    }

    public void addCaretListener(CaretListener listener) {
        editor.addCaretListener(listener);
    }
}