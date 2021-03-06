package ru.sales.offline.gui.main;

import net.miginfocom.swing.MigLayout;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.gui.GuiUtils;
import ru.sales.offline.gui.main.panel.MainInfoPanel;
import ru.sales.offline.gui.main.panel.MainTopPanel;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.ColorUIResource;

public class MainApplicationForm extends JDialog {

  protected static final ColorUIResource COLOR_UI_RESOURCE =
      new ColorUIResource(Color.decode("#BACCFF"));

  public MainApplicationForm(ApplicationContext applicationContext) {
    super((Dialog) null, "Sales offline", true);
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

    // debug, fillx
    JPanel panel = new JPanel(new MigLayout("fillx", "[grow]"));
    panel.add(new MainTopPanel(applicationContext.getRendererComboBoxReceiptType()), "wrap");

    SpecificationTable table = new SpecificationTable(applicationContext);
    applicationContext.setMainTable(table);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(1000, 400));
    scrollPane.setBackground(COLOR_UI_RESOURCE);
    panel.add(scrollPane, "wrap");

    // Statusbar
    JPanel statusBar = new JPanel();
    JLabel lblEmployee = new JLabel("Кассир: " + applicationContext.getEmployeeName());
    statusBar.add(lblEmployee);

    // Вывод окна на экран
    panel.add(new MainInfoPanel(applicationContext), "right, wrap");
    panel.add(statusBar);
    setContentPane(panel);
    setSize(1024, 768);
    GuiUtils.changeFont(this, new Font("Arial", 0, 18));
    applicationContext.setParent(this);
    centerScreenOn();
    pack();
    setVisible(true);
  }

  private void centerScreenOn() {
    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    final Dimension screenSize = toolkit.getScreenSize();
    final int x = (screenSize.width - getWidth()) / 2;
    final int y = (screenSize.height - getHeight()) / 2;
    setLocation(x, y);
  }
}
