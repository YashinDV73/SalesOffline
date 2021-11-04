package ru.sales.offline.dto.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Header {

  private final Date dateTime;
  private final Specification specification;
}
