package ru.sales.offline.gui.main;

import ru.sales.offline.dto.receipt.types.NdsType;

import javax.swing.table.DefaultTableModel;

public class SpecificationTableModel extends DefaultTableModel {

    private static final int COLUMN_SERIAL_NUMBER = 0;
    private static final int COLUMN_NAME = 0;
    private static final int COLUMN_QTY = 0;
    private static final int COLUMN_COST = 0;
    private static final int COLUMN_NDS = 0;
    private static final int COLUMN_SERIAL_NUMBER = 0;

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 1: return Boolean.class;
            case 2: return String.class;
            default: return Object.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }


    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);
    }
}
