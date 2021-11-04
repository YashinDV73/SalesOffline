package ru.sales.offline.gui.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TableColumn {
  int id;
  String name;
  int size;
  boolean editable;
}
