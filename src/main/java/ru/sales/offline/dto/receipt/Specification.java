package ru.sales.offline.dto.receipt;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class Specification {

  List<Position> positionList;
}
