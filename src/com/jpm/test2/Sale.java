package com.jpm.test2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nzarokostas
 */
public class Sale {

  private final String id;
  private final double unitPrice;
  private final int occurences;
  private final Double amount;
  private Double adjustedAmount;
  private final List<AmountAdjustment> adjustments;

  public Sale(String id, double unitPrice, int occurences) {
    this.id = id;
    this.unitPrice = unitPrice;
    this.occurences = occurences;
    this.amount = unitPrice * occurences;
    this.adjustedAmount = this.amount;
    this.adjustments = new ArrayList<>();
  }

  public Double adjustAmount(Adjustment adjustment) {
    AmountAdjustment amountAdjustment = new AmountAdjustment(adjustment);
    amountAdjustment.setOriginalAmount(adjustedAmount);
    if (adjustment.getOperationType().equals("+")) {
      adjustedAmount += adjustment.getOperationValue();
    } else if (adjustment.getOperationType().equals("-")) {
      adjustedAmount -= adjustment.getOperationValue();
    } else if (adjustment.getOperationType().equals("*")) {
      adjustedAmount *= adjustment.getOperationValue();
    }
    amountAdjustment.setAdjustedAmount(adjustedAmount);
    adjustments.add(amountAdjustment);
    return adjustedAmount;
  }

  @Override
  public String toString() {
    return id;
  }

  //----------------------------------------------------------------------------
  // Getters
  //----------------------------------------------------------------------------
  public String getId() {
    return id;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public int getOccurences() {
    return occurences;
  }

  public Double getAmount() {
    return amount;
  }

  public Double getAdjustedAmount() {
    return adjustedAmount;
  }

  public List<AmountAdjustment> getAdjustments() {
    return adjustments;
  }

}
