package ru.sales.offline.dto.receipt.types;

import javafx.util.Pair;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReceiptCalculationType implements ComboType {
  INCOME(new Pair<>(1, "приход")),
  INCOME_RETURN(new Pair<>(2, "возврат прихода")),
  SPENDING(new Pair<>(3, "расход")),
  SPENDING_RETURN(new Pair<>(4, "возврат расхода"));

  private Pair<Integer, String> value;

  @Override
  public Pair<Integer, String> value() {
    return value;
  }
}
