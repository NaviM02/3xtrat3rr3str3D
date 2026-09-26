package com.navi.ui.editor;

import com.navi.ui.utils.NumeroLinea;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.nio.file.Path;

public class EditorPanel extends JPanel {

    private static final Color EDITOR_BACKGROUND = new Color(25, 25, 25);
    private static final Color TEXT = new Color(224, 224, 224);
    private static final Color MIKU = new Color(57, 197, 187);

    @Getter
    private final JTextPane editor;

    private final JLabel fileNameLabel;
    private final JLabel extensionLabel;

    private Path currentFile;

    public EditorPanel() {
        setLayout(new BorderLayout());

        editor = new JTextPane();

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

    public String getText() {
        return editor.getText();
    }

    public void setText(String text) {
        editor.setText(text);
        editor.setCaretPosition(0);
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
        setCurrentFile(null);
    }

    public void addCaretListener(CaretListener listener) {
        editor.addCaretListener(listener);
    }
}