package ru.sales.offline.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.text.NumberFormat;

public final class GuiUtils {
  public static final NumberFormat FORMATTER_CURRENCY = NumberFormat.getCurrencyInstance();
  public static void changeFont(Component component, Font font) {
    component.setFont(font);
    if (component instanceof Container) {
      for (Component child : ((Container) component).getComponents()) {
        changeFont(child, font);
      }
    }
  }
}
