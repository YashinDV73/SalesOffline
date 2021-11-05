package ru.sales.offline.gui.main;

import ru.sales.offline.context.ApplicationContext;

import javax.swing.JTable;
import java.awt.*;

public class SpecificationTable<T> extends JTable {

  public SpecificationTable(ApplicationContext applicationContext) {
    final SpecificationTableModel model = new SpecificationTableModel(applicationContext);

    setModel(model);
    getTableHeader().setReorderingAllowed(false);

    model
        .getModel()
        .getTableColumns()
        .forEach(
            tableColumn ->
                getColumnModel()
                    .getColumn(tableColumn.getId())
                    .setPreferredWidth(tableColumn.getSize()));

    setPreferredSize(new Dimension(1000, 370));

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
