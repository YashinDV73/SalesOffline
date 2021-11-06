package ru.sales.offline.dto.receipt.types;

import javafx.util.Pair;
import lombok.AllArgsConstructor;

/**
 * 1212 признак предмета расчета - признак предмета товара, работы, услуги, платежа, выплаты, иного
 * предмета расчета
 */
@AllArgsConstructor
public enum ObjectCalculationType implements ComboType {
  GOODS(new Pair<Integer, String>(1, "Товар")),
  SERVICE(new Pair<Integer, String>(4, "Услуга")),
  PAYMENT(new Pair<Integer, String>(10, "Платеж/Выплата"));

  private Pair<Integer, String> value;

  @Override
  public Pair<Integer, String> value() {
    return value;
  }
}
