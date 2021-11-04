package ru.sales.offline;

import lombok.extern.slf4j.Slf4j;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.gui.AuthDialog;
import ru.sales.offline.gui.main.MainApplicationForm;

@Slf4j
public class SalesOfflineApplication {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ApplicationContext();
    log.info("Start application");
    new AuthDialog(applicationContext);

    new MainApplicationForm(applicationContext);
    log.info("Finish application");
  }
}
