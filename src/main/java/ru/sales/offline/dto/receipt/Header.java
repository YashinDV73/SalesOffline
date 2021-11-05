package ru.sales.offline.dto.receipt;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@AllArgsConstructor
public class Header {

  Date dateTime;
  Specification specification;
}
