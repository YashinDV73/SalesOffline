package ru.sales.offline.gui.main;

import lombok.Getter;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.gui.model.TableColumn;
import ru.sales.offline.gui.model.TableModel;

import javax.swing.table.DefaultTableModel;

public class SpecificationTableModel extends DefaultTableModel {

  @Getter private final TableModel model = new MainTableModel();

  private ApplicationContext applicationContext;

  public SpecificationTableModel(ApplicationContext applicationContext) {

    this.applicationContext = applicationContext;
  }

  @Override
  public Class<?> getColumnClass(int column) {
    switch (column) {
      case 1:
        return Boolean.class;
      case 2:
        return String.class;
      default:
        return Object.class;
    }
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return null;
  }

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
