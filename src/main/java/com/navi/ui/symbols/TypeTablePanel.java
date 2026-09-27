package com.navi.ui.symbols;

import com.navi.backend.semantic.model.AggregateType;
import com.navi.backend.semantic.model.Field;
import com.navi.backend.semantic.model.FunctionSignature;
import com.navi.backend.semantic.model.Symbol;
import com.navi.backend.semantic.model.Type;
import com.navi.backend.semantic.model.TypeTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TypeTablePanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;

    public TypeTablePanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Agregado", "Categoría", "Miembro", "Tipo", "Detalle"}, 0) {
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

    public void setTypeTable(TypeTable typeTable) {
        clear();

        if (typeTable == null) {
            return;
        }

        for (AggregateType type : typeTable.getTypes().values()) {
            String category = type.isClass() ? "class" : "struct";

            for (Field field : type.getFields()) {
                model.addRow(new Object[]{type.getName(), category, field.getName(), formatType(field.getType()), formatFieldDetail(field)});
            }

            for (Map.Entry<String, List<Symbol>> entry : type.getMemberScope().getSymbols().entrySet()) {
                for (Symbol member : entry.getValue()) {
                    model.addRow(new Object[]{type.getName(), category, member.getName(), formatType(member.getType()), formatMemberDetail(member)});
                }
            }

            if (type.getFields().isEmpty() && type.getMemberScope().getSymbols().isEmpty()) {
                model.addRow(new Object[]{type.getName(), category, "-", "-", "(sin miembros)"});
            }
        }
    }

    private String formatType(Type type) {
        if (type == null) {
            return "-";
        }

        return type.toString();
    }

    private String formatFieldDetail(Field field) {
        if (field.getArrayDims().isEmpty()) {
            return "campo";
        }

        StringBuilder detail = new StringBuilder("campo [");

        for (int i = 0; i < field.getArrayDims().size(); i++) {
            if (i > 0) {
                detail.append(", ");
            }
            detail.append(field.getArrayDims().get(i));
        }

        return detail.append("]").toString();
    }

    private String formatMemberDetail(Symbol member) {
        FunctionSignature signature = member.getSignature();

        if (signature == null) {
            return member.getKind().name().toLowerCase();
        }

        String returnType = signature.getReturnType() == null ? "-" : signature.getReturnType().toString();

        return member.getKind().name().toLowerCase() + signature.getParameters() + " -> " + returnType;
    }

    public void clear() {
        model.setRowCount(0);
    }
}
