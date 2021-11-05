package ru.sales.offline.bussines;

import ru.sales.offline.SalesOfflineApplication;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.dto.receipt.Header;

public class CreateNewReceipt {

  private ApplicationContext applicationContext = SalesOfflineApplication.applicationContext;

  public Header newReceipt() {

    Header header = applicationContext.getReceipt();
    header.getSpecification().getPositionList().clear();

    return applicationContext.getReceipt();
  }
}
