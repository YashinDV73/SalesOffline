package ru.sales.offline.basic.gui.table;

import javax.swing.table.AbstractTableModel;

public class DefaultTableModel extends AbstractTableModel {
  @Override
  public int getRowCount() {
    return 0;
  }

  @Override
  public int getColumnCount() {
    return 0;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return null;
  }
}
