package ru.sales.offline.gui.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TableColumn<T> {
  int id;
  String name;
  int size;
  boolean editable;
  T defaultValue;
  Class<T> aClass;
  T[] columnData;
}
