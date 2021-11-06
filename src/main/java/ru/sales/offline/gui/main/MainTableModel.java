package ru.sales.offline.gui.main;

import ru.sales.offline.dto.receipt.types.MethodCalculationType;
import ru.sales.offline.dto.receipt.types.NdsType;
import ru.sales.offline.dto.receipt.types.ObjectCalculationType;
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

  private static final List<TableColumn> tableColumns = new ArrayList<>();

  static {
    tableColumns.add(
        TableColumn.<String>builder()
            .id(COLUMN_SERIAL_NUMBER)
            .name("№")
            .size(100)
            .editable(false)
            .defaultValue("1")
            .aClass(String.class)
            .build());
    tableColumns.add(
        TableColumn.<String>builder()
            .id(COLUMN_NAME)
            .name("Название")
            .size(500)
            .editable(true)
            .defaultValue("")
            .aClass(String.class)
            .build());
    tableColumns.add(
        TableColumn.<Integer>builder()
            .id(COLUMN_QTY)
            .name("Кол-во")
            .size(100)
            .editable(true)
            .defaultValue(1)
            .aClass(Integer.class)
            .build());
    tableColumns.add(
        TableColumn.<Double>builder()
            .id(COLUMN_COST)
            .name("Цена")
            .size(150)
            .editable(true)
            .defaultValue(0d)
            .aClass(Double.class)
            .build());
    tableColumns.add(
        TableColumn.<NdsType>builder()
            .id(COLUMN_NDS)
            .name("НДС")
            .size(150)
            .editable(true)
            .defaultValue(NdsType.NDS_20)
            .aClass(NdsType.class)
            .columnData(NdsType.values())
            .build());
    tableColumns.add(
        TableColumn.<ObjectCalculationType>builder()
            .id(COLUMN_OBJECT_CALC)
            .name("Предмет расчёта")
            .size(200)
            .editable(true)
            .defaultValue(ObjectCalculationType.GOODS)
            .aClass(ObjectCalculationType.class)
            .columnData(ObjectCalculationType.values())
            .build());
    tableColumns.add(
        TableColumn.<MethodCalculationType>builder()
            .id(COLUMN_METHOD_CALC)
            .name("Способ расчёта")
            .size(200)
            .editable(true)
            .defaultValue(MethodCalculationType.FULL_SETTLEMENT)
            .aClass(MethodCalculationType.class)
            .columnData(MethodCalculationType.values())
            .build());
    tableColumns.add(
        TableColumn.<Double>builder()
            .id(COLUMN_SUM)
            .name("Сумма")
            .size(200)
            .editable(false)
            .defaultValue(0d)
            .aClass(Double.class)
            .build());
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
