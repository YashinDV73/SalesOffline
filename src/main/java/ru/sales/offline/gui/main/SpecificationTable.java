package ru.sales.offline.gui.main;

import lombok.extern.slf4j.Slf4j;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.gui.main.renderer.RendererCellTable;
import ru.sales.offline.gui.main.renderer.RendererComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

@Slf4j
public class SpecificationTable extends JTable {

  public SpecificationTable(ApplicationContext applicationContext) {
    final SpecificationTableModel model = new SpecificationTableModel(applicationContext);

    setModel(model);
    getTableHeader().setReorderingAllowed(false);
    setCellSelectionEnabled(true);
    model
        .getModel()
        .getTableColumns()
        .forEach(
            tableColumn ->
                getColumnModel()
                    .getColumn(tableColumn.getId())
                    .setPreferredWidth(tableColumn.getSize()));

    model
        .getModel()
        .getTableColumns()
        .stream()
        .filter(tableColumn -> Objects.nonNull(tableColumn.getColumnData()))
        .forEach(
            tableColumn -> {
              getColumnModel()
                  .getColumn(tableColumn.getId())
                  .setCellEditor(
                      new DefaultCellEditor(new RendererComboBox<>(tableColumn.getColumnData())));
              log.info("Data column {}: {}", tableColumn.getId(), tableColumn.getColumnData());
            });

    model
        .getModel()
        .getTableColumns()
        .forEach(
            tableColumn -> {
              getColumnModel()
                  .getColumn(tableColumn.getId())
                  .setCellRenderer(new RendererCellTable());
              log.info(
                  "Data column {}: {}", tableColumn.getId(), tableColumn.getAClass().getName());
            });

    setCellEditor(new SelectAllCellEditor(new JTextField()));

    setRowHeight(30);

    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    setPreferredSize(new Dimension(1000, 370));
  }

  public void addNewPosition() {
    ((SpecificationTableModel) getModel()).addPosition();
  }

  public void clearSpecification() {
    ((SpecificationTableModel) getModel()).clearSpecification();
  }

  public static class SelectAllCellEditor extends DefaultCellEditor {
    public SelectAllCellEditor(final JTextField textField) {
      super(textField);
      textField.addFocusListener(
          new FocusAdapter() {
            public void focusGained(final FocusEvent e) {
              textField.selectAll();
            }
          });
    }
  }
}
