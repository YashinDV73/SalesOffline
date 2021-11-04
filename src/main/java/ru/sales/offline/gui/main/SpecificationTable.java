package ru.sales.offline.gui.main;

import javax.swing.JTable;

public class SpecificationTable extends JTable {

  private static final SpecificationTableModel model = new SpecificationTableModel();

  public SpecificationTable() {
    setModel(model);

    model
        .getModel()
        .getTableColumns()
        .forEach(
            tableColumn ->
                getColumnModel()
                    .getColumn(tableColumn.getId())
                    .setPreferredWidth(tableColumn.getSize()));

    //    // Слушатель событий модели столбцов таблицы
    //    columnModel.addColumnModelListener(
    //        new TableColumnModelListener() {
    //
    //          public void columnAdded(TableColumnModelEvent arg0) {
    //            System.out.println("TableColumnModelListener.columnAdded()");
    //          }
    //
    //          public void columnMarginChanged(ChangeEvent arg0) {
    //            System.out.println("TableColumnModelListener.columnMarginChanged()");
    //          }
    //
    //          public void columnMoved(TableColumnModelEvent arg0) {
    //            System.out.println("TableColumnModelListener.columnMoved()");
    //          }
    //
    //          public void columnRemoved(TableColumnModelEvent arg0) {}
    //
    //          public void columnSelectionChanged(ListSelectionEvent arg0) {
    //            System.out.println("TableColumnModelListener.columnSelectionChanged()");
    //          }
    //        });
  }
}
