package ru.sales.offline.gui.main.panel;

import lombok.var;
import net.miginfocom.swing.MigLayout;
import ru.sales.offline.bussines.PreparationReceiptData;
import ru.sales.offline.bussines.ReceiptTransaction;
import ru.sales.offline.bussines.fiscal.PrintAndSaveReceipt;
import ru.sales.offline.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

public class MainInfoPanel extends JPanel {

  private ApplicationContext applicationContext;

  public MainInfoPanel(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;

    // debug,
    setLayout(new MigLayout("fillx", "[grow, right]"));

    JPanel sumInfoPanel = new JPanel();
    sumInfoPanel.add(new JLabel("Сумма к оплате наличными: "));
    sumInfoPanel.add(applicationContext.getLabelSum());
    add(sumInfoPanel, "wrap");

    JPanel buttonPanel = new JPanel();
    JButton addPosition = new JButton("Добавить позицию");
    addPosition.setAlignmentX(Component.RIGHT_ALIGNMENT);

    JButton delPosition = new JButton("Удалить позицию");
    delPosition.setAlignmentX(Component.RIGHT_ALIGNMENT);

    JButton clearSpecification = new JButton("Очистить спецификацию");
    clearSpecification.setAlignmentX(Component.RIGHT_ALIGNMENT);

    JButton printReceipt = new JButton("Оформить чек");
    printReceipt.setAlignmentX(Component.RIGHT_ALIGNMENT);

    buttonPanel.add(addPosition);
    buttonPanel.add(delPosition);
    buttonPanel.add(sep());
    buttonPanel.add(clearSpecification);
    buttonPanel.add(printReceipt);

    add(buttonPanel);

    addPosition.addActionListener(e -> addNewPositionAction(e.getSource()));

    clearSpecification.addActionListener(e -> clearSpecificationAction(e.getSource()));

    printReceipt.addActionListener(e -> printReceiptAndSave(applicationContext));
  }

  private void printReceiptAndSave(ApplicationContext applicationContext) {
    PrintAndSaveReceipt printAndSaveReceipt = new PrintAndSaveReceipt(applicationContext);
    PreparationReceiptData preparationReceiptData = new PreparationReceiptData(applicationContext);
    if (preparationReceiptData.validate()) {
      if (printAndSaveReceipt.action()) {
        new ReceiptTransaction(applicationContext).saveReceipt();
        applicationContext.getMainTable().clearSpecification();
      }
    }
  }

  private void clearSpecificationAction(Object source) {
    applicationContext.getMainTable().clearSpecification();
  }

  private void addNewPositionAction(Object e) {
    applicationContext.getMainTable().addNewPosition();
  }

  private JPanel sep() {
    var j = new JPanel();
    j.setSize(new Dimension(50, 20));
    return j;
  }
}
