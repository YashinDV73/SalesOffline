package ru.sales.offline.bussines.fiscal.driver;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atol.drivers10.fptr.Fptr;
import ru.atol.drivers10.fptr.IFptr;
import ru.sales.offline.SalesOfflineApplication;
import ru.sales.offline.dto.receipt.Header;

import java.util.function.Supplier;

public class Atol77F {

  private static final Logger logger = LoggerFactory.getLogger(SalesOfflineApplication.class);
  private static IFptr fptr;

  @SneakyThrows
  public Atol77F(String pathLibrary) {

    fptr = StringUtils.isNotBlank(pathLibrary) ? new Fptr(pathLibrary) : new Fptr();
    init();

    logger.info("The fiscal device is initialized.");

    if (fptr == null) {
      throw new Exception("Can`t load driver library from " + pathLibrary);
    }
  }

  private void init() {
    fptr.setSingleSetting(
        IFptr.LIBFPTR_SETTING_MODEL, String.valueOf(IFptr.LIBFPTR_MODEL_ATOL_AUTO));
    fptr.setSingleSetting(IFptr.LIBFPTR_SETTING_PORT, String.valueOf(IFptr.LIBFPTR_PORT_USB));
    fptr.setSingleSetting(IFptr.LIBFPTR_SETTING_COM_FILE, "COM5");
    fptr.setSingleSetting(
        IFptr.LIBFPTR_SETTING_BAUDRATE, String.valueOf(IFptr.LIBFPTR_PORT_BR_115200));
    fptr.applySingleSettings();
  }

  @SneakyThrows
  public boolean isOpened() {
    if (!fptr.isOpened()) {
      if (fptr.open() == 0) {
        fptr.beep();
      } else {
        logger.error("Фискальный принтер: {}", fptr.errorDescription());
        return false;
      }
    }
    return fptr.isOpened();
  }

  public boolean printReceipt(String cashier, Header receipt) {

    checkOpenShift(cashier);

    logger.info("Формирование чека {}", receipt.getType().value());

    cashierRegistered(cashier);

    fptr.setParam(IFptr.LIBFPTR_PARAM_RECEIPT_TYPE, receipt.getType().value().getKey());
    if (fptr.openReceipt() != 0) {
      logger.error("Ошибка во время печати чека: {}", fptr.errorDescription());
    }
    checkDocumentClosed(
        () -> {
          receipt
              .getSpecification()
              .getPositionList()
              .forEach(
                  position -> {
                    fptr.setParam(IFptr.LIBFPTR_PARAM_COMMODITY_NAME, position.getName());
                    fptr.setParam(IFptr.LIBFPTR_PARAM_PRICE, position.getCost());
                    fptr.setParam(IFptr.LIBFPTR_PARAM_QUANTITY, position.getQty());
                    fptr.setParam(IFptr.LIBFPTR_PARAM_TAX_TYPE, position.getNds().value().getKey());
                    fptr.registration();
                  });

          fptr.setParam(IFptr.LIBFPTR_PARAM_PAYMENT_TYPE, IFptr.LIBFPTR_PT_CASH);
          return fptr.closeReceipt() == 0;
        });
    return true;
  }

  private void cashierRegistered(String cashier) {
    fptr.setParam(1021, cashier);
    fptr.setParam(1203, ""); // TODO: INN???
    fptr.operatorLogin();
  }

  private void checkOpenShift(String cashier) {

    logger.info("Проверка состояния текущей смены");
    fptr.setParam(IFptr.LIBFPTR_PARAM_DATA_TYPE, IFptr.LIBFPTR_DT_SHIFT_STATE);
    fptr.queryData();

    long state = fptr.getParamInt(IFptr.LIBFPTR_PARAM_SHIFT_STATE);
    long number = fptr.getParamInt(IFptr.LIBFPTR_PARAM_SHIFT_NUMBER);

    if (state == IFptr.LIBFPTR_SS_EXPIRED) {
      logger.warn("Текущая смена {} истекла", number);

      cashierRegistered(cashier);

      if (checkDocumentClosed(
          () -> {
            logger.info("Попытка закрыть текущую смену.");
            fptr.setParam(IFptr.LIBFPTR_PARAM_REPORT_TYPE, IFptr.LIBFPTR_RT_CLOSE_SHIFT);
            return fptr.report() == 0;
          })) {
        if (checkDocumentClosed(() -> fptr.openShift() == 0)) {
          logger.info("Смена успешно октыта!");
        }
      }
    }
  }

  /**
   * ресурс https://integration.atol.ru/api/?java#proverit-zakrytie-dokumenta
   *
   * @param actionReport фискальная опреация
   * @return true - если все ОК
   */
  private boolean checkDocumentClosed(Supplier<Boolean> actionReport) {
    if (!actionReport.get()) {
      logger.error("Ошибка формирования документа: {}", fptr.errorDescription());
      return false;
    }

    if (fptr.checkDocumentClosed() < 0) {
      // Не удалось проверить состояние документа. Вывести пользователю текст ошибки, попросить
      // устранить неполадку и повторить запрос
      fptr.cancelReceipt();
      logger.error("Не удалось проверить состояние документа. {}", fptr.errorDescription());
      return false;
    }

    if (!fptr.getParamBool(IFptr.LIBFPTR_PARAM_DOCUMENT_CLOSED)) {
      // Документ не закрылся. Требуется его отменить (если это чек) и сформировать заново
      fptr.cancelReceipt();
      logger.error("Документ не закрылся, необходимо сформировать заново");
      return false;
    }

    if (!fptr.getParamBool(IFptr.LIBFPTR_PARAM_DOCUMENT_PRINTED)) {
      // Можно сразу вызвать метод допечатывания документа, он завершится с ошибкой, если это
      // невозможно
      if (fptr.continuePrint() < 0) {
        // Если не удалось допечатать документ - показать пользователю ошибку и попробовать еще раз.
        logger.error(
            String.format(
                "Не удалось напечатать документ (Ошибка %s). Устраните неполадку и повторите.",
                fptr.errorDescription()));
        fptr.cancelReceipt();
        return false;
      }
    }
    logger.info("Документ успешно сформировался");
    return true;
  }

  public String errorDescription() {
    return fptr.errorDescription();
  }
}
