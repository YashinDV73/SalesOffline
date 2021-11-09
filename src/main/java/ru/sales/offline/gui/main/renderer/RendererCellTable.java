package ru.sales.offline.gui.main.renderer;

import ru.sales.offline.dto.receipt.types.ComboType;
import ru.sales.offline.gui.GuiUtils;
import ru.sales.offline.gui.main.MainTableModel;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class RendererCellTable extends DefaultTableCellRenderer implements TableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    if (value instanceof ComboType) {
      setText(((ComboType) value).value().getValue());
    }
    if (value instanceof Double) {
      setText(GuiUtils.FORMATTER_CURRENCY.format(value));
    }
    if (value instanceof String) {
      setText(String.valueOf(value));
    }
    if (value instanceof Integer) {
      setText(String.valueOf(value));
    }
    switch (column) {
      case MainTableModel.COLUMN_NAME:
      case MainTableModel.COLUMN_QTY:
      case MainTableModel.COLUMN_COST:
        if (isSelected) {
          setBackground(Color.lightGray);
          setOpaque(true);
        } else {
          setBackground(Color.WHITE);
          setOpaque(false);
        }
    }
    switch (column) {
      case MainTableModel.COLUMN_SERIAL_NUMBER:
      case MainTableModel.COLUMN_QTY:
      case MainTableModel.COLUMN_NDS:
      case MainTableModel.COLUMN_OBJECT_CALC:
      case MainTableModel.COLUMN_METHOD_CALC:
        setHorizontalAlignment(JLabel.CENTER);
        break;
      case MainTableModel.COLUMN_COST:
      case MainTableModel.COLUMN_SUM:
        setHorizontalAlignment(JLabel.RIGHT);
        break;
    }
    return this;
  }
}
