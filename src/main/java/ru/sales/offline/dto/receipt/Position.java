package ru.sales.offline.dto.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import ru.sales.offline.dto.receipt.types.MethodCalculationType;
import ru.sales.offline.dto.receipt.types.NdsType;
import ru.sales.offline.dto.receipt.types.ObjectCalculationType;

@Data
@AllArgsConstructor
public class Position {
  @NonNull private Integer id;
  @NonNull private String name;
  @NonNull private Integer qty;
  @NonNull private Double cost;
  private NdsType nds;
  private ObjectCalculationType signObjectCalculation;
  private MethodCalculationType signMethodCalculation;
}
