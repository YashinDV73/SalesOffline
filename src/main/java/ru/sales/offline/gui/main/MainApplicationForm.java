package ru.sales.offline.gui.main;

import ru.sales.offline.context.ApplicationContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MainApplicationForm extends JDialog {

  // Данные для таблиц
  private ApplicationContext applicationContext;

  public MainApplicationForm(ApplicationContext applicationContext) {
    super((Dialog) null, "Sales offline", true);
    this.applicationContext = applicationContext;
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Размещение таблиц в панели с блочным расположением
    Box contents = new Box(BoxLayout.Y_AXIS);
    contents.add(new JScrollPane(new JTable(new SpecificationTable())));

    // При выходе из диалогового окна работа заканчивается
    addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent we) {
            dispose();
            System.exit(0);
          }
        });

    // Кнопка добавления колонки в модель TableColumnModel
    JButton add = new JButton("Добавить колонку");
    // Слушатель обработки события
    add.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {}
        });

    // Кнопка перемещения колонки
    JButton move = new JButton("Переместить колонку");
    // Слушатель обработки события
    move.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {}
        });

    // Панель кнопок
    JPanel pnlButtons = new JPanel();
    pnlButtons.add(add);
    pnlButtons.add(move);

    // Statusbar
    JPanel statusBar = new JPanel();
    JLabel lblEmployee = new JLabel(applicationContext.getEmployeeName());
    statusBar.add(lblEmployee);

    // Вывод окна на экран
    getContentPane().add(contents);
    getContentPane().add(pnlButtons, BorderLayout.SOUTH);
    getContentPane().add(statusBar, BorderLayout.SOUTH);
    setSize(480, 300);

    setVisible(true);
  }
}
