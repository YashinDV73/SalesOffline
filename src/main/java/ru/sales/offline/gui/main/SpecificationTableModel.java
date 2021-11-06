package ru.sales.offline.gui.main;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.gui.model.TableColumn;
import ru.sales.offline.gui.model.TableModel;

import javax.swing.table.DefaultTableModel;
import java.util.Comparator;

@Slf4j
public class SpecificationTableModel extends DefaultTableModel {

  @Getter private final TableModel model = new MainTableModel();

  private ApplicationContext applicationContext;

  public SpecificationTableModel(ApplicationContext applicationContext) {

    this.applicationContext = applicationContext;
    insertRow(
        0,
        model
            .getTableColumns()
            .stream()
            .sorted(Comparator.comparingInt(TableColumn::getId))
            .map(TableColumn::getDefaultValue)
            .toArray());
  }

  //    @Override
  //    public Object getValueAt(int rowIndex, int columnIndex) {
  //        return model.getTableColumns().stream()
  //                .filter(tableColumn -> tableColumn.getId()==columnIndex)
  //                .map(TableColumn::getDefaultValue);
  //    }

  @Override
  public String getColumnName(int column) {
    return model
        .getTableColumns()
        .stream()
        .filter(tableColumn -> tableColumn.getId() == column)
        .map(TableColumn::getName)
        .findFirst()
        .orElse("---");
  }

  @Override
  public Class<?> getColumnClass(int column) {
    switch (column) {
      case 3:
      case 7:
        return Double.class;
      default:
        return Object.class;
    }
  }

  @Override
  public void setValueAt(Object aValue, int row, int column) {
    super.setValueAt(aValue, row, column);
    log.info("setValueAt: {}, {}, {}", aValue, row, column);
  }

  @Override
  public int getColumnCount() {
    return model.getColumnSize();
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return model
        .getTableColumns()
        .stream()
        .filter(tableColumn -> tableColumn.getId() == column)
        .map(TableColumn::isEditable)
        .findFirst()
        .orElse(false);
  }
}
