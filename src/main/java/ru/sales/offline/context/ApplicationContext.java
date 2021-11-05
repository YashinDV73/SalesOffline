package ru.sales.offline.context;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sales.offline.dto.receipt.Header;

@Data
@NoArgsConstructor
public class ApplicationContext {

  String EmployeeName;
  Header receipt;
}
