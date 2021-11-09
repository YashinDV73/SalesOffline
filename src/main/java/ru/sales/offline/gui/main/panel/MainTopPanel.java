package ru.sales.offline.gui.main.panel;

import lombok.var;
import ru.sales.offline.dto.receipt.types.ReceiptCalculationType;
import ru.sales.offline.gui.main.renderer.RendererComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.util.Date;

public class MainTopPanel extends JPanel {

  public MainTopPanel(RendererComboBox<ReceiptCalculationType> rendererComboBoxReceiptType) {

    setLayout(new FlowLayout(FlowLayout.LEFT));

    add(new JLabel("Дата:"));
    add(new JLabel(DateFormat.getDateInstance().format(new Date())));

    add(sep());

    add(new JLabel("Тип чека"));

    add(rendererComboBoxReceiptType);
  }

  private JPanel sep() {
    var j = new JPanel();
    j.setSize(new Dimension(50, 20));
    return j;
  }
}
