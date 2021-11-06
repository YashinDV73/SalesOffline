package ru.sales.offline.gui.main.combobox;

import ru.sales.offline.dto.receipt.types.ComboType;
import ru.sales.offline.dto.receipt.types.ReceiptCalculationType;

import javax.swing.*;
import java.awt.*;

public class MainComboBox<T> extends JComboBox<T> {

    public MainComboBox(T[] items) {
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
