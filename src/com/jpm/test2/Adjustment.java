package com.jpm.test2;

/**
 *
 * @author nzarokostas
 */
public class Adjustment {

  private String operationType;
  private Double operationValue;

  public Adjustment(String operationType, Double operationValue) {
    this.operationType = operationType;
    this.operationValue = operationValue;
  }

  @Override
  public String toString() {
    return operationType + operationValue;
  }

  //----------------------------------------------------------------------------
  // Getter/Setter
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

}
