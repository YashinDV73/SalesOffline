package ru.sales.offline.dto.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Specification {

  private final List<Position> positionList;
}
