package com.jpm.test2;

/**
 *
 * @author nzarokostas
 */
public class AmountAdjustment {

  private String operationType;
  private Double operationValue;
  private Double originalAmount;
  private Double adjustedAmount;

  public AmountAdjustment(Adjustment adjustment) {
    this.operationType = adjustment.getOperationType();
    this.operationValue = adjustment.getOperationValue();
    this.originalAmount = 0.0;
    this.adjustedAmount = 0.0;
  }

  @Override
  public String toString() {
    return operationType + operationValue;
  }

  //----------------------------------------------------------------------------
  // Getters/Setters
  //----------------------------------------------------------------------------
  public String getOperationType() {
    return operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  public Double getOperationValue() {
    return operationValue;
  }

  public void setOperationValue(Double operationValue) {
    this.operationValue = operationValue;
  }

  public Double getOriginalAmount() {
    return originalAmount;
  }

  public void setOriginalAmount(Double originalAmount) {
    this.originalAmount = originalAmount;
  }

  public Double getAdjustedAmount() {
    return adjustedAmount;
  }

  public void setAdjustedAmount(Double adjustedAmount) {
    this.adjustedAmount = adjustedAmount;
  }

}
