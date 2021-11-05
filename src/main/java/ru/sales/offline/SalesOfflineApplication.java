package ru.sales.offline;

import lombok.extern.slf4j.Slf4j;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.gui.AuthDialog;
import ru.sales.offline.gui.main.MainApplicationForm;

@Slf4j
public class SalesOfflineApplication {

  public static final ApplicationContext applicationContext = new ApplicationContext();

  public static void main(String[] args) {
    log.info("Start application");
    new AuthDialog(applicationContext);
    new MainApplicationForm(applicationContext);
    log.info("Finish application");
  }
}
