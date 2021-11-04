package ru.sales.offline.dto.receipt.types;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 1212 признак предмета расчета - признак предмета товара, работы, услуги, платежа, выплаты, иного
 * предмета расчета
 */
@AllArgsConstructor
public enum ObjectCalculationType {
  GOODS(new Pair<Integer, String>(1, "Товар")),
  SERVICE(new Pair<Integer, String>(4, "Услуга")),
  PAYMENT(new Pair<Integer, String>(10, "Платеж/Выплата"));

  @Getter private final Pair<Integer, String> value;
}
