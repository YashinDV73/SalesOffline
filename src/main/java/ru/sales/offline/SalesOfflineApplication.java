package ru.sales.offline;

import lombok.SneakyThrows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import ru.sales.offline.bussines.fiscal.driver.Atol77F;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.dto.receipt.Header;
import ru.sales.offline.dto.receipt.Specification;
import ru.sales.offline.dto.receipt.types.ReceiptCalculationType;
import ru.sales.offline.gui.AuthDialog;
import ru.sales.offline.gui.main.MainApplicationForm;
import ru.sales.offline.gui.main.renderer.RendererComboBox;
import ru.sales.offline.utilites.AppUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class SalesOfflineApplication {

  private static final Logger logger = LoggerFactory.getLogger(SalesOfflineApplication.class);
  public static final ApplicationContext applicationContext = new ApplicationContext();

  public static void main(String[] args) {
    System.out.println("args = " + logger.getName());
    System.out.println("args = " + Thread.currentThread().getContextClassLoader().getResource(""));
    logger.info("Start application");
    new AuthDialog(applicationContext);
    try {
      init();
      new MainApplicationForm(applicationContext);
    } catch (Exception e) {
      logger.error("Application failed started.", e);
      System.exit(0);
    }
    logger.info("Finish application");
  }

  @SneakyThrows
  private static void init() {
    Properties appProps = new Properties();
    appProps.load(
        new ClasspathResourceLoader().getResourceStream("resources/application.properties"));

    applicationContext.setRendererComboBoxReceiptType(
        new RendererComboBox<>(ReceiptCalculationType.values()));
    applicationContext.setReceipt(
        new Header()
            .setDateTime(new Date())
            .setSpecification(new Specification(new ArrayList<>()))
            .setType(ReceiptCalculationType.INCOME));
    applicationContext.setProperties(appProps);
    applicationContext.setFiscalDevice(
        new Atol77F(AppUtils.getPropertiesValue(appProps, "Atol77F.driver")));
  }
}
