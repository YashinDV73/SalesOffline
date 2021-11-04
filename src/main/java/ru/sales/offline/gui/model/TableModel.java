package ru.sales.offline.gui.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableModel {
    protected final List<TableColumn> tableColumns = new ArrayList<TableColumn>();
}
