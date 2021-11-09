package ru.sales.offline.bussines;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sales.offline.SalesOfflineApplication;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.dto.receipt.types.ReceiptCalculationType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public final class PreparationReceiptData {

  private static final Logger logger = LoggerFactory.getLogger(SalesOfflineApplication.class);
  private ApplicationContext applicationContext;

  public PreparationReceiptData(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;

    fillingData();
  }

  private void fillingData() {
    JComboBox<ReceiptCalculationType> box = applicationContext.getRendererComboBoxReceiptType();
    applicationContext.getReceipt().setType(box.getItemAt(box.getSelectedIndex()));
  }

  public boolean validate() {

    List<String> message = new ArrayList<>();

    if (applicationContext
        .getReceipt()
        .getSpecification()
        .getPositionList()
        .stream()
        .anyMatch(position -> StringUtils.isBlank(position.getName()))) {
      message.add("Необходимо указать названия товаров для всех позиций!");
    }

    if (applicationContext
        .getReceipt()
        .getSpecification()
        .getPositionList()
        .stream()
        .anyMatch(position -> position.getQty() <= 0)) {
      message.add("Необходимо указать количество больше 0!");
    }

    if (!applicationContext.getFiscalDevice().isOpened()) {
      message.add(
          "Ошибка фискального принтера: "
              + applicationContext.getFiscalDevice().errorDescription());
    }

    if (CollectionUtils.isNotEmpty(message)) {
      JOptionPane.showMessageDialog(
          applicationContext.getParent(),
          String.join("\r\n", message),
          "SalesOffline messages",
          JOptionPane.ERROR_MESSAGE);
      logger.warn("Подготовка к печати чека завершилась ошибкой: {}", message);
      return false;
    }

    return true;
  }
}
