package ru.sales.offline.gui.main;

import ru.sales.offline.gui.model.TableColumn;
import ru.sales.offline.gui.model.TableModel;

import java.util.ArrayList;
import java.util.List;

public final class MainTableModel extends TableModel {

  private static final int COLUMN_SERIAL_NUMBER = 0;
  private static final int COLUMN_NAME = 1;
  private static final int COLUMN_QTY = 2;
  private static final int COLUMN_COST = 3;
  private static final int COLUMN_NDS = 4;
  private static final int COLUMN_OBJECT_CALC = 5;
  private static final int COLUMN_METHOD_CALC = 6;
  private static final int COLUMN_SUM = 7;

  private static final List<TableColumn> tableColumns = new ArrayList<TableColumn>();

  static {
    tableColumns.add(
        TableColumn.builder().id(COLUMN_SERIAL_NUMBER).name("№").size(100).editable(false).build());
    tableColumns.add(
        TableColumn.builder().id(COLUMN_NAME).name("Название").size(300).editable(true).build());
    tableColumns.add(
        TableColumn.builder().id(COLUMN_QTY).name("Кол-во").size(100).editable(true).build());
    tableColumns.add(
        TableColumn.builder().id(COLUMN_COST).name("Цена").size(150).editable(true).build());
    tableColumns.add(
        TableColumn.builder().id(COLUMN_NDS).name("НДС").size(150).editable(false).build());
    tableColumns.add(
        TableColumn.builder()
            .id(COLUMN_OBJECT_CALC)
            .name("Предмет расчёта")
            .size(200)
            .editable(false)
            .build());
    tableColumns.add(
        TableColumn.builder()
            .id(COLUMN_METHOD_CALC)
            .name("Способ расчёта")
            .size(200)
            .editable(false)
            .build());
    tableColumns.add(
        TableColumn.builder().id(COLUMN_SUM).name("Сумма").size(200).editable(false).build());
  }

  @Override
  public List<TableColumn> getTableColumns() {
    return tableColumns;
  }

  @Override
  public int getColumnSize() {
    return tableColumns.size();
  }
}
