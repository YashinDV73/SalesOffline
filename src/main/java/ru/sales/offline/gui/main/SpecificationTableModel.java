package ru.sales.offline.gui.main;

import ru.sales.offline.dto.receipt.types.NdsType;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

public class SpecificationTableModel extends DefaultTableModel {

    private static final int COLUMN_SERIAL_NUMBER = 0;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_QTY = 2;
    private static final int COLUMN_COST = 3;
    private static final int COLUMN_NDS = 4;
    private static final int COLUMN_OBJECT_CALC = 5;
    private static final int COLUMN_METHOD_CALC = 6;
    private static final int COLUMN_SUM = 7;
    private static final int COLUMN_COUNT = 8;

    private static final Map<Integer, Integer> COLUMN_SIZE ;

    static {
        COLUMN_SIZE = new HashMap<Integer, Integer>(COLUMN_COUNT);
        COLUMN_SIZE.put(COLUMN_SERIAL_NUMBER, 100);
        COLUMN_SIZE.put(COLUMN_NAME, 300);
        COLUMN_SIZE.put(COLUMN_QTY, 100);
        COLUMN_SIZE.put(COLUMN_COST, 150);
        COLUMN_SIZE.put(COLUMN_NDS, 150);
        COLUMN_SIZE.put(COLUMN_OBJECT_CALC, 200);
        COLUMN_SIZE.put(COLUMN_METHOD_CALC, 200);
        COLUMN_SIZE.put(COLUMN_SUM, 200);
    }

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
