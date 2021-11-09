package ru.sales.offline.dto.receipt.types;

import javafx.util.Pair;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NdsType implements ComboType {
  WITHOUT(new Pair<>(6, "Без НДС")),
  NDS_ZERO(new Pair<>(5, "НДС 0%")),
  NDS_10(new Pair<>(2, "НДС 10%")),
  NDS_20(new Pair<>(7, "НДС 20%"));

  private Pair<Integer, String> value;

  @Override
  public Pair<Integer, String> value() {
    return value;
  }
}
