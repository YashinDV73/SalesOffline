package ru.sales.offline.dto.receipt.types;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** 1214 признак способа расчета */
@AllArgsConstructor
public enum MethodCalculationType {
  PREPAYMENT_100(new Pair<Integer, String>(1, "Предоплата 100%")),
  PREPAYMENT(new Pair<Integer, String>(2, "Предоплата")),
  ADVANCE_PAYMENT(new Pair<Integer, String>(3, "Аванс")),
  FULL_SETTLEMENT(new Pair<Integer, String>(4, "Полный расчет"));

  @Getter private final Pair<Integer, String> value;
}
