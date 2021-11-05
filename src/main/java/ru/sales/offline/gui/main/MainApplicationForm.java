package ru.sales.offline.gui.main;

import net.miginfocom.swing.MigLayout;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.gui.GuiUtils;
import ru.sales.offline.gui.main.panel.MainInfoPanel;
import ru.sales.offline.gui.main.panel.MainTopPanel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class MainApplicationForm extends JDialog {

  protected static final ColorUIResource COLOR_UI_RESOURCE =
      new ColorUIResource(Color.decode("#BACCFF"));

  // Данные для таблиц
  private ApplicationContext applicationContext;

  public MainApplicationForm(ApplicationContext applicationContext) {
    super((Dialog) null, "Sales offline", true);
    this.applicationContext = applicationContext;
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);
    setBackground(COLOR_UI_RESOURCE);
    getContentPane().setBackground(COLOR_UI_RESOURCE);

    // При выходе из диалогового окна работа заканчивается
    addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent we) {
            dispose();
            System.exit(0);
          }
        });

    JPanel panel = new JPanel(new MigLayout("debug, fillx", "[grow]"));
    panel.add(new MainTopPanel(), "wrap");

    JScrollPane scrollPane = new JScrollPane(new SpecificationTable(applicationContext));
    scrollPane.setPreferredSize(new Dimension(1000, 400));
    scrollPane.setBackground(COLOR_UI_RESOURCE);
    panel.add(scrollPane, "wrap");

    // Statusbar
    JPanel statusBar = new JPanel();
    JLabel lblEmployee = new JLabel("Кассир: " + applicationContext.getEmployeeName());
    statusBar.add(lblEmployee);

    // Вывод окна на экран
    panel.add(new MainInfoPanel(), "right, wrap");
    panel.add(statusBar);
    setContentPane(panel);
    setSize(1024, 768);
    GuiUtils.changeFont(this, new Font("Arial", 0, 18));
    setVisible(true);
  }
}
