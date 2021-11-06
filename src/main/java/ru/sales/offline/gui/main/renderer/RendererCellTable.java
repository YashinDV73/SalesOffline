package ru.sales.offline.gui.main.renderer;

import ru.sales.offline.dto.receipt.types.ComboType;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.NumberFormat;

public class RendererCellTable extends JLabel implements TableCellRenderer {

  private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    if (value instanceof ComboType) {
      setText(((ComboType) value).value().getValue());
    }
    if (value instanceof Double) {
      setText(FORMATTER.format(value));
    }
    if (value instanceof String) {
      setText(String.valueOf(value));
    }
    if (value instanceof Integer) {
      setText(String.valueOf(value));
    }
    switch (column) {
      case 0:
      case 2:
      case 4:
      case 5:
      case 6:
        setHorizontalAlignment(JLabel.CENTER);
        break;
      case 3:
      case 7:
        setHorizontalAlignment(JLabel.RIGHT);
        break;
    }
    return this;
  }
}
