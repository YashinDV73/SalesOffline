package ru.sales.offline.gui.main;

import lombok.extern.slf4j.Slf4j;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.dto.receipt.types.ComboType;
import ru.sales.offline.dto.receipt.types.NdsType;
import ru.sales.offline.gui.main.combobox.MainComboBox;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Objects;

@Slf4j
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

        model
                .getModel()
                .getTableColumns()
                .stream()
                .filter(tableColumn -> Objects.nonNull(tableColumn.getColumnData()))
                .forEach(tableColumn -> {
                    getColumnModel()
                            .getColumn(tableColumn.getId())
                            .setCellEditor(new DefaultCellEditor(new MainComboBox<>(tableColumn.getColumnData())));
                    log.info("Data column {}: {}", tableColumn.getId(), tableColumn.getColumnData());
                });

        model
                .getModel()
                .getTableColumns()
                .stream()
                .filter(tableColumn -> Objects.nonNull(tableColumn.getColumnData()))
                .forEach(tableColumn ->
                        setDefaultRenderer(tableColumn.getAClass(), (table, value, isSelected, hasFocus, row, column) -> getColumnModel()
                        .getColumn(tableColumn.getId())
                        .setCellRenderer(new DefaultCellEditor(((ComboType)value).value().getValue()))));

        setRowHeight(30);

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
