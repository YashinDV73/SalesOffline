package ru.sales.offline.dto.receipt.types;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ReceiptCalculationType {
  INCOME(new Pair<>(1, "приход")),
  INCOME_RETURN(new Pair<>(2, "возврат прихода")),
  SPENDING(new Pair<>(3, "расход")),
  SPENDING_RETURN(new Pair<>(4, "возврат расхода"));
  @Getter private final Pair<Integer, String> value;
}
