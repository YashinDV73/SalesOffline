package ru.sales.offline.bussines.fiscal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sales.offline.SalesOfflineApplication;
import ru.sales.offline.bussines.fiscal.driver.Atol77F;
import ru.sales.offline.context.ApplicationContext;

import static ru.sales.offline.SalesOfflineApplication.applicationContext;

public class PrintAndSaveReceipt {

  private static final Logger logger = LoggerFactory.getLogger(SalesOfflineApplication.class);
  private final Atol77F fiscalDevice;

  public PrintAndSaveReceipt(ApplicationContext applicationContext) {

    fiscalDevice = applicationContext.getFiscalDevice();
  }

  public boolean action() {
    if (fiscalDevice.isOpened()) {
      return fiscalDevice.printReceipt(
          applicationContext.getEmployeeName(), applicationContext.getReceipt());
    }
    return true;
  }
}
