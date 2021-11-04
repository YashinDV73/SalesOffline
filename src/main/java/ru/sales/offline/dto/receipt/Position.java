package ru.sales.offline.dto.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.sales.offline.dto.receipt.types.MethodCalculationType;
import ru.sales.offline.dto.receipt.types.NdsType;
import ru.sales.offline.dto.receipt.types.ObjectCalculationType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Position {

  private String name;
  private Integer qty;
  private BigDecimal cost;
  private NdsType nds;
  private ObjectCalculationType signObjectCalculation;
  private MethodCalculationType signMethodCalculation;
}
