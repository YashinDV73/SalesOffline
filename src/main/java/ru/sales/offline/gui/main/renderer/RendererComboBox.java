package ru.sales.offline.gui.main.renderer;

import ru.sales.offline.dto.receipt.types.ComboType;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Component;

public class RendererComboBox<T> extends JComboBox<T> {

  public RendererComboBox(T[] items) {
    super(items);

    setRenderer(
        new DefaultListCellRenderer() {
          @Override
          public Component getListCellRendererComponent(
              JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setText(((ComboType) value).value().getValue());
            return this;
          }
        });
  }
}
