package com.navi.ui.symbols;

import com.navi.backend.semantic.Scope;
import com.navi.backend.semantic.Symbol;
import com.navi.backend.semantic.SymbolTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SymbolTablePanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;

    public SymbolTablePanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Nombre", "Kind", "Tipo", "Ámbito", "Pos. memoria", "Línea", "Columna"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getTableHeader().setReorderingAllowed(false);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        clear();

        if (symbolTable == null) {
            return;
        }

        Scope globalScope = symbolTable.getGlobalScope();

        loadScope(globalScope, "GLOBAL");
    }

    private void loadScope(Scope scope, String scopeName) {
        for (List<Symbol> overloads : scope.getSymbols().values()) {
            for (Symbol symbol : overloads) {
                model.addRow(new Object[]{symbol.getName(), symbol.getKind(), formatType(symbol), scopeName, formatPosition(symbol), symbol.getLine(), symbol.getColumn()});
            }
        }

        List<Scope> children = scope.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Scope child = children.get(i);
            String childScopeName = scopeName + " / " + child.getKind() + " " + (i + 1);
            loadScope(child, childScopeName);
        }
    }

    private String formatType(Symbol symbol) {
        if (symbol.getType() == null) {
            return "-";
        }

        return symbol.getType().toString();
    }

    private String formatPosition(Symbol symbol) {
        if (symbol.getPosMemory() < 0) {
            return "-";
        }

        return String.valueOf(symbol.getPosMemory());
    }

    public void clear() {
        model.setRowCount(0);
    }
}