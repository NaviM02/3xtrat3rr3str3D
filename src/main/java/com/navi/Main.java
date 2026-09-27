package com.navi;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import com.navi.ui.CompilerWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FlatMaterialDarkerIJTheme.setup();
            } catch (Exception ignored) {
            }

            CompilerWindow window = new CompilerWindow();
            window.setVisible(true);
        });
    }
}
