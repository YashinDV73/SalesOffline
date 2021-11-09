package ru.sales.offline.context;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sales.offline.bussines.fiscal.driver.Atol77F;
import ru.sales.offline.dto.receipt.Header;
import ru.sales.offline.dto.receipt.types.ReceiptCalculationType;
import ru.sales.offline.gui.main.SpecificationTable;
import ru.sales.offline.gui.main.renderer.RendererComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

@Data
@NoArgsConstructor
public class ApplicationContext {

  String EmployeeName;
  Header receipt;
  JLabel labelSum = new JLabel("0.00");
  SpecificationTable mainTable;
  RendererComboBox<ReceiptCalculationType> rendererComboBoxReceiptType;
  Component parent;
  Atol77F fiscalDevice;
  Properties properties;
}
