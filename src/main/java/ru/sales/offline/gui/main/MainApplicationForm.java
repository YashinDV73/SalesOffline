package ru.sales.offline.gui.main;

import ru.sales.offline.context.ApplicationContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MainApplicationForm extends JDialog {
  // Модель столбцов таблицы
  private TableColumnModel columnModel;
  // Данные для таблиц
  private Object[][] array =
      new String[][] {{"1", "", "1", "0.0", "20", "Товар", "Полный расчёт", "0.0"}};
  // Заголовки столбцов
  private Object[] columnsHeader =
      new String[] {"п.п.", "Наименование", "Количество", "Цена", "НДС", "ППР", "ПСР", "Сумма"};
  private ApplicationContext applicationContext;

  public MainApplicationForm(ApplicationContext applicationContext) {
    super((Dialog) null, "Sales offline", true);
    this.applicationContext = applicationContext;
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    // Создание таблицы
    final JTable table1 = new JTable(array, columnsHeader);
    // Получаем стандартную модель
    columnModel = table1.getColumnModel();
    // Определение минимального и максимального размеров столбцов
    Enumeration<TableColumn> e = columnModel.getColumns();
    while (e.hasMoreElements()) {
      TableColumn column = (TableColumn) e.nextElement();
      column.setMinWidth(50);
      column.setMaxWidth(200);
    }
    // Размещение таблиц в панели с блочным расположением
    Box contents = new Box(BoxLayout.Y_AXIS);
    contents.add(new JScrollPane(table1));

    // Кнопка добавления колонки в модель TableColumnModel
    JButton add = new JButton("Добавить колонку");
    // Слушатель обработки события
    add.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            // Добавление столбца к модели TableColumnModel
            TableColumn сolumn = new TableColumn(3, 50);
            сolumn.setHeaderValue("<html><b>Цена</b></html>");
            columnModel.addColumn(сolumn);
          }
        });
    // Кнопка перемещения колонки
    JButton move = new JButton("Переместить колонку");
    // Слушатель обработки события
    move.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            // Индекс первой колоки
            int first = table1.getSelectedColumn();
            // Индекс второй колонки
            int last = (first == columnModel.getColumnCount()) ? first + 1 : 0;
            // Перемещение столбцов
            columnModel.moveColumn(first, last);
          }
        });
    // Панель кнопок
    JPanel pnlButtons = new JPanel();
    pnlButtons.add(add);
    pnlButtons.add(move);
    // Statusbar
    JPanel statusBar = new JPanel();
    JLabel lblEmployee = new JLabel(applicationContext.getEmployeeName());
    statusBar.add(lblEmployee);
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

    // Вывод окна на экран
    getContentPane().add(contents);
    getContentPane().add(pnlButtons, BorderLayout.SOUTH);
    getContentPane().add(statusBar, BorderLayout.SOUTH);
    setSize(480, 300);
    setVisible(true);
  }
}
