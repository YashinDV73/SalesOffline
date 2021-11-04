package ru.sales.offline.gui.main;

public class SpecificationTable extends SpecificationTableModel  {

    private static final Object[][] array =
            new String[][] {{"1", "", "1", "0.0", "20", "Товар", "Полный расчёт", "0.0"}};
    // Заголовки столбцов
    private static final Object[] columnsHeader =
            new String[] {"п.п.", "Наименование", "Количество", "Цена", "НДС", "ППР", "ПСР", "Сумма"};


    public SpecificationTable() {
        setColumnIdentifiers(columnsHeader);

    }
}
