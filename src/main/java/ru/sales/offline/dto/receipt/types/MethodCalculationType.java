package ru.sales.offline.dto.receipt.types;

import javafx.util.Pair;
import lombok.AllArgsConstructor;

/** 1214 признак способа расчета */
@AllArgsConstructor
public enum MethodCalculationType implements ComboType {
  PREPAYMENT_100(new Pair<>(1, "Предоплата 100%")),
  PREPAYMENT(new Pair<>(2, "Предоплата")),
  ADVANCE_PAYMENT(new Pair<>(3, "Аванс")),
  FULL_SETTLEMENT(new Pair<>(4, "Полный расчет"));

  private Pair<Integer, String> value;

  @Override
  public Pair<Integer, String> value() {
    return value;
  }
}
