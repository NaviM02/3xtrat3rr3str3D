package com.navi.ui.c3d;

import com.navi.ui.utils.NumeroLinea;

import javax.swing.*;
import java.awt.*;

public class C3DPanel extends JPanel {

    private static final Color BACKGROUND = new Color(25, 25, 25);
    private static final Color TEXT = new Color(224, 224, 224);
    private static final Color MIKU = new Color(57, 197, 187);

    private final JTextArea area;

    public C3DPanel() {
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        area.setBackground(BACKGROUND);
        area.setForeground(TEXT);
        area.setCaretColor(MIKU);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        area.setTabSize(4);
        area.setLineWrap(false);
        area.setMargin(new Insets(6, 8, 6, 8));

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setRowHeaderView(new NumeroLinea(area));
        scrollPane.getViewport().setBackground(BACKGROUND);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void setCode(String code) {
        area.setText(code == null ? "" : code);
        area.setCaretPosition(0);
    }

    public void clear() {
        area.setText("");
    }
}
