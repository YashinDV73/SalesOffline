package ru.sales.offline.context;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sales.offline.dto.receipt.Header;

import javax.swing.*;

@Data
@NoArgsConstructor
public class ApplicationContext {

  String EmployeeName;
  Header receipt;
  JLabel labelSum = new JLabel("0.00");
}
