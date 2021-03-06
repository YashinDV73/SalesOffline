package ru.sales.offline.gui.main;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sales.offline.SalesOfflineApplication;
import ru.sales.offline.context.ApplicationContext;
import ru.sales.offline.dto.receipt.Position;
import ru.sales.offline.dto.receipt.Specification;
import ru.sales.offline.dto.receipt.types.MethodCalculationType;
import ru.sales.offline.dto.receipt.types.NdsType;
import ru.sales.offline.dto.receipt.types.ObjectCalculationType;
import ru.sales.offline.gui.GuiUtils;
import ru.sales.offline.gui.model.TableColumn;
import ru.sales.offline.gui.model.TableModel;

import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

public class SpecificationTableModel extends DefaultTableModel {

  private static final Logger logger = LoggerFactory.getLogger(SalesOfflineApplication.class);
  @Getter private final TableModel model = new MainTableModel();

  private ApplicationContext applicationContext;
  private Specification specification;

  public SpecificationTableModel(ApplicationContext applicationContext) {

    this.applicationContext = applicationContext;
    specification = this.applicationContext.getReceipt().getSpecification();

    addPosition();

    addTableModelListener(
        e -> {
          logger.info("addTableModelListener: {}", e.getSource());
          recalcSum();
        });
  }

  private void recalcSum() {
    BigDecimal sum = new BigDecimal(0);
    for (int i = 0; i < getRowCount(); i++) {
      sum = sum.add(BigDecimal.valueOf((Double) getValueAt(i, MainTableModel.COLUMN_SUM)));
    }
    applicationContext.getLabelSum().setText(GuiUtils.FORMATTER_CURRENCY.format(sum));
  }

  @Override
  public String getColumnName(int column) {
    return model
        .getTableColumns()
        .stream()
        .filter(tableColumn -> tableColumn.getId() == column)
        .map(TableColumn::getName)
        .findFirst()
        .orElse("---");
  }

  @Override
  public Class<?> getColumnClass(int column) {
    switch (column) {
      case MainTableModel.COLUMN_QTY:
        return Integer.class;
      case MainTableModel.COLUMN_COST:
      case MainTableModel.COLUMN_SUM:
        return Double.class;
      default:
        return Object.class;
    }
  }

  @Override
  @SneakyThrows
  public void setValueAt(Object aValue, int row, int column) {
    if (aValue == null) {
      super.setValueAt(aValue, row, column);
      logger.error("?????????????? ???????????????? ?? ?????????????? NULL! ????????????: {} ??????????????: {}", row, column);
    }
    switch (column) {
      case MainTableModel.COLUMN_NAME:
        updateSpecification(row).setName(String.valueOf(aValue));
        break;
      case MainTableModel.COLUMN_QTY:
        updateSpecification(row).setQty((Integer) aValue);
        setValueAt(
            (Integer) aValue * (Double) getValueAt(row, MainTableModel.COLUMN_COST),
            row,
            MainTableModel.COLUMN_SUM);
        break;
      case MainTableModel.COLUMN_COST:
        updateSpecification(row).setCost((Double) aValue);
        setValueAt(
            (Double) aValue * (Integer) getValueAt(row, MainTableModel.COLUMN_QTY),
            row,
            MainTableModel.COLUMN_SUM);
        break;
      case MainTableModel.COLUMN_NDS:
        updateSpecification(row).setNds((NdsType) aValue);
        break;
      case MainTableModel.COLUMN_OBJECT_CALC:
        updateSpecification(row).setSignObjectCalculation((ObjectCalculationType) aValue);
        break;
      case MainTableModel.COLUMN_METHOD_CALC:
        updateSpecification(row).setSignMethodCalculation((MethodCalculationType) aValue);
        break;
    }
    super.setValueAt(aValue, row, column);
    logger.info("setValueAt: {}, {}, {}", Arrays.asList(aValue, row, column).toArray());
    logger.info("specification: {}", new ObjectMapper().writeValueAsString(specification));
  }

  private Position updateSpecification(int row) {
    return specification
        .getPositionList()
        .stream()
        .filter(position -> position.getId().equals(row))
        .findFirst()
        .orElse(addPositionToSpecification());
  }

  private Position addPositionToSpecification() {
    return null;
  }

  @Override
  public int getColumnCount() {
    return model.getColumnSize();
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return model
        .getTableColumns()
        .stream()
        .filter(tableColumn -> tableColumn.getId() == column)
        .map(TableColumn::isEditable)
        .findFirst()
        .orElse(false);
  }

  public void addPosition() {

    var array =
        model
            .getTableColumns()
            .stream()
            .sorted(Comparator.comparingInt(TableColumn::getId))
            .map(TableColumn::getDefaultValue)
            .toArray();

    int rowCount = getRowCount();

    array[0] = rowCount + 1;

    specification
        .getPositionList()
        .add(
            new Position(
                rowCount,
                "",
                1,
                0d,
                NdsType.NDS_20,
                ObjectCalculationType.GOODS,
                MethodCalculationType.FULL_SETTLEMENT));

    insertRow(rowCount, array);
  }

  public void clearSpecification() {
    specification.getPositionList().clear();
    setRowCount(0);
    addPosition();
  }
}
