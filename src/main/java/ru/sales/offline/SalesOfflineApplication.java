package ru.sales.offline;

import lombok.extern.slf4j.Slf4j;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.dto.receipt.Header;
import ru.sales.offline.dto.receipt.Specification;
import ru.sales.offline.gui.AuthDialog;
import ru.sales.offline.gui.main.MainApplicationForm;

import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class SalesOfflineApplication {

  public static final ApplicationContext applicationContext = new ApplicationContext();

  public static void main(String[] args) {
    log.info("Start application");
    new AuthDialog(applicationContext);
    init();
    new MainApplicationForm(applicationContext);
    log.info("Finish application");
  }

  private static void init() {
    applicationContext.setReceipt(new Header(new Date(), new Specification(new ArrayList<>())));
  }
}
