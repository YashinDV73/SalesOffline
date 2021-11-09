package ru.sales.offline.dto.receipt;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.sales.offline.dto.receipt.types.ReceiptCalculationType;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Header {

  Date dateTime;
  Specification specification;
  ReceiptCalculationType type;
}
