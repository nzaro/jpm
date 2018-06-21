package com.jpm.test2;

/**
 *
 * @author nzarokostas
 */
public class Message {

  private String messageType;
  private String productType;
  private Double unitPrice;
  private int occurences;
  // adjustment operation
  private String operationType;
  private Double operationAmount;

  public Message(String messageType, String productType, double unitPrice) {
    this.messageType = messageType;
    this.productType = productType;
    this.unitPrice = unitPrice;
    this.occurences = 1;
    this.operationType = null;
    this.operationAmount = null;
  }

  public Message(String messageType, String productType, double unitPrice, int occurences) {
    this.messageType = messageType;
    this.productType = productType;
    this.unitPrice = unitPrice;
    this.occurences = occurences;
    this.operationType = null;
    this.operationAmount = null;
  }

  public Message(String messageType, String productType, double unitPrice, int occurences, String operationType, double operationAmount) {
    this.messageType = messageType;
    this.productType = productType;
    this.unitPrice = unitPrice;
    this.occurences = occurences;
    this.operationType = operationType;
    this.operationAmount = operationAmount;
  }

  //----------------------------------------------------------------------------
  // Getter/Setter
  //----------------------------------------------------------------------------
  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public int getOccurences() {
    return occurences;
  }

  public void setOccurences(int occurences) {
    this.occurences = occurences;
  }

  public String getOperationType() {
    return operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  public double getOperationAmount() {
    return operationAmount;
  }

  public void setOperationAmount(double operationAmount) {
    this.operationAmount = operationAmount;
  }

}
