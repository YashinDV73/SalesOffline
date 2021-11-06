package ru.sales.offline.gui.main.panel;

import lombok.var;
import net.miginfocom.swing.MigLayout;
import ru.sales.offline.gui.main.SpecificationTable;

import javax.swing.*;
import java.awt.*;

public class MainInfoPanel extends JPanel {

  private SpecificationTable table;

  public MainInfoPanel(JLabel jLabelSum, SpecificationTable table) {
    this.table = table;

    setLayout(new MigLayout("debug, fillx", "[grow, right]"));

    JPanel sumInfoPanel = new JPanel();
    sumInfoPanel.add(new JLabel("Сумма к оплате наличными: "));
    sumInfoPanel.add(jLabelSum);
    add(sumInfoPanel, "wrap");

    JPanel buttonPanel = new JPanel();
    JButton addPosition = new JButton("Добавить позицию");
    addPosition.setAlignmentX(Component.RIGHT_ALIGNMENT);
    addPosition.addActionListener(e -> {});

    JButton delPosition = new JButton("Удалить позицию");
    delPosition.setAlignmentX(Component.RIGHT_ALIGNMENT);
    delPosition.addActionListener(e -> {});

    JButton clearSpecification = new JButton("Очистить спецификацию");
    clearSpecification.setAlignmentX(Component.RIGHT_ALIGNMENT);
    clearSpecification.addActionListener(e -> {});

    JButton move = new JButton("Оформить чек");
    move.setAlignmentX(Component.RIGHT_ALIGNMENT);
    move.addActionListener(e -> {});

    buttonPanel.add(addPosition);
    buttonPanel.add(delPosition);
    buttonPanel.add(sep());
    buttonPanel.add(clearSpecification);
    buttonPanel.add(move);

    add(buttonPanel);

    addPosition.addActionListener(
        e -> {
          addNewPosition(e.getSource());
        });
  }

  private void addNewPosition(Object e) {
    table.addNewPosition();
  }

  private JPanel sep() {
    var j = new JPanel();
    j.setSize(new Dimension(50, 20));
    return j;
  }
}
