package com.navi.ui.console;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.function.Consumer;

public class ConsolePanel extends JPanel {

    private static final Color CONSOLE_BACKGROUND = new Color(20, 20, 20);
    private static final Color MIKU_LIGHT = new Color(161, 244, 240);
    private static final Color MIKU = new Color(57, 197, 187);

    private static final Color ERROR = new Color(255, 82, 82);
    private static final Color ERROR_LIGHT = new Color(255, 140, 140);

    private final JTextPane textPane;
    private final StyledDocument document;
    private final JTextField inputField;
    private final JButton sendButton;
    private Consumer<String> inputListener;

    private final Style normalStyle;
    private final Style successStyle;
    private final Style errorStyle;
    private final Style errorHeaderStyle;

    public ConsolePanel() {
        setLayout(new BorderLayout());

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(CONSOLE_BACKGROUND);
        textPane.setCaretColor(MIKU);
        textPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        document = textPane.getStyledDocument();

        normalStyle = textPane.addStyle("normal", null);
        StyleConstants.setForeground(normalStyle, MIKU_LIGHT);
        StyleConstants.setFontFamily(normalStyle, Font.MONOSPACED);
        StyleConstants.setFontSize(normalStyle, 13);

        successStyle = textPane.addStyle("success", null);
        StyleConstants.setForeground(successStyle, MIKU);
        StyleConstants.setBold(successStyle, true);
        StyleConstants.setFontFamily(successStyle, Font.MONOSPACED);
        StyleConstants.setFontSize(successStyle, 13);

        errorStyle = textPane.addStyle("error", null);
        StyleConstants.setForeground(errorStyle, ERROR_LIGHT);
        StyleConstants.setFontFamily(errorStyle, Font.MONOSPACED);
        StyleConstants.setFontSize(errorStyle, 13);

        errorHeaderStyle = textPane.addStyle("errorHeader", null);
        StyleConstants.setForeground(errorHeaderStyle, ERROR);
        StyleConstants.setBold(errorHeaderStyle, true);
        StyleConstants.setFontFamily(errorHeaderStyle, Font.MONOSPACED);
        StyleConstants.setFontSize(errorHeaderStyle, 13);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(8, 0));
        inputPanel.setBackground(CONSOLE_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(39, 111, 107)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        JLabel prompt = new JLabel(">");
        prompt.setForeground(MIKU);
        inputPanel.add(prompt, BorderLayout.WEST);

        inputField = new JTextField();
        inputField.setBackground(CONSOLE_BACKGROUND);
        inputField.setForeground(MIKU_LIGHT);
        inputField.setCaretColor(MIKU);
        inputField.setFont(textPane.getFont());
        inputField.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        inputField.setToolTipText("Escribe un valor y presiona Enter para enviarlo al programa");
        inputField.addActionListener(e -> submitInput());
        inputPanel.add(inputField, BorderLayout.CENTER);

        sendButton = new JButton("Enviar");
        sendButton.addActionListener(e -> submitInput());
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);
        setInputEnabled(false);
    }

    public void setInputListener(Consumer<String> listener) {
        inputListener = listener;
    }

    public void setInputEnabled(boolean enabled) {
        inputField.setEnabled(enabled);
        sendButton.setEnabled(enabled);
        if (enabled) inputField.requestFocusInWindow();
        else inputField.setText("");
    }

    private void submitInput() {
        if (!inputField.isEnabled() || inputListener == null) return;
        String line = inputField.getText();
        inputField.setText("");
        appendLine(line);
        inputListener.accept(line);
    }

    public void append(String text) {
        append(text, normalStyle);
    }

    public void appendLine(String text) {
        append(text + "\n", normalStyle);
    }

    public void appendSuccess(String text) {
        append(text, successStyle);
    }

    public void appendSuccessLine(String text) {
        append(text + "\n", successStyle);
    }

    public void appendError(String text) {
        append(text, errorStyle);
    }

    public void appendErrorLine(String text) {
        append(text + "\n", errorStyle);
    }

    public void appendErrorHeader(String text) {
        append(text, errorHeaderStyle);
    }

    private void append(String text, Style style) {
        try {
            document.insertString(document.getLength(), text, style);
            textPane.setCaretPosition(document.getLength());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        textPane.setText("");
    }
}
