package ru.sales.offline.bussines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sales.offline.SalesOfflineApplication;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.utilites.AppUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public final class ReceiptTransaction {

  private static final Logger logger = LoggerFactory.getLogger(SalesOfflineApplication.class);
  private static File file;
  private ApplicationContext applicationContext;

  public ReceiptTransaction(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
    file =
        new File(
            AppUtils.getPropertiesValue(
                applicationContext.getProperties(), "ReceiptTransaction.file"));
  }

  public void saveReceipt() {
    byte[] data = AppUtils.getObjectAsJson(applicationContext.getReceipt());

    if (data == null) {
      logger.warn("Не удалось сформировать запись в журнал транзакций.");
      return;
    }

    OutputStream stream = null;
    try {
      stream = new FileOutputStream(file, true);
      stream.write(data, 0, data.length);
      stream.write(System.lineSeparator().getBytes(), 0, 2);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    } finally {
      Optional.ofNullable(stream)
          .ifPresent(
              s -> {
                try {
                  s.close();
                } catch (IOException e) {
                  logger.error(e.getMessage(), e);
                }
              });
    }
  }
}
