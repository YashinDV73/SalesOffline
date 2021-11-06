package ru.sales.offline.gui.main.panel;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ru.sales.offline.dto.receipt.types.ReceiptCalculationType;
import ru.sales.offline.gui.main.renderer.RendererComboBox;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

@Slf4j
public class MainTopPanel extends JPanel {

  public MainTopPanel() {

    setLayout(new FlowLayout(FlowLayout.LEFT));

    add(new JLabel("Дата:"));
    add(new JLabel(DateFormat.getDateInstance().format(new Date())));

    add(sep());

    add(new JLabel("Тип чека"));

    //    JComboBox<ReceiptCalculationType> typeJComboBox =
    //        new JComboBox<>(ReceiptCalculationType.values());
    //    typeJComboBox.setRenderer(
    //        new DefaultListCellRenderer() {
    //          @Override
    //          public Component getListCellRendererComponent(
    //              JList<?> list, Object value, int index, boolean isSelected, boolean
    // cellHasFocus) {
    //            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    //            setText(((ReceiptCalculationType) value).value().getValue());
    //            return this;
    //          }
    //        });
    add(new RendererComboBox<>(ReceiptCalculationType.values()));
  }

  private JPanel sep() {
    var j = new JPanel();
    j.setSize(new Dimension(50, 20));
    return j;
  }
}
