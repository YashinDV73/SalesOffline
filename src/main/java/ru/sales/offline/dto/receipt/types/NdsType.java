package ru.sales.offline.dto.receipt.types;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum NdsType {
  WITHOUT(new Pair<Integer, String>(0, "Без НДС")),
  NDS_ZERO(new Pair<Integer, String>(0, "НДС 0%")),
  NDS_10(new Pair<Integer, String>(10, "НДС 10%")),
  NDS_20(new Pair<Integer, String>(20, "НДС 20%"));

  @Getter private final Pair<Integer, String> value;
}