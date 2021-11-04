package ru.sales.offline.gui.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class TableColumn {
    private String name;
    private int size;
    private boolean editable;
}
