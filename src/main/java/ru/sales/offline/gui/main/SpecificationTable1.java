package ru.sales.offline.gui.main;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

public class SpecificationTable1 extends JTable {

  public SpecificationTable1() {

    super(new SpecificationTable());

    // Слушатель событий модели столбцов таблицы
    columnModel.addColumnModelListener(
        new TableColumnModelListener() {

          public void columnAdded(TableColumnModelEvent arg0) {
            System.out.println("TableColumnModelListener.columnAdded()");
          }

          public void columnMarginChanged(ChangeEvent arg0) {
            System.out.println("TableColumnModelListener.columnMarginChanged()");
          }

          public void columnMoved(TableColumnModelEvent arg0) {
            System.out.println("TableColumnModelListener.columnMoved()");
          }

          public void columnRemoved(TableColumnModelEvent arg0) {}

          public void columnSelectionChanged(ListSelectionEvent arg0) {
            System.out.println("TableColumnModelListener.columnSelectionChanged()");
          }
        });
  }


}