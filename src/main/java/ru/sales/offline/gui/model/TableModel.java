package ru.sales.offline.gui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public abstract class TableModel {
  private List<TableColumn> tableColumns;
  private int columnSize;
}
