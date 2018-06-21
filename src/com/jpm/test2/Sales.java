package com.jpm.test2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nzarokostas
 */
public class Sales {

  private int numOfSales;
  private double totalAmount;
  private double totalAdjustedAmount;
  private final List<Sale> sales;
  private final List<Adjustment> adjustments;

  public Sales() {
    this.numOfSales = 0;
    this.totalAmount = 0.0;
    this.totalAdjustedAmount = 0.0;
    this.sales = new ArrayList<>();
    this.adjustments = new ArrayList<>();
  }

  public void addSale(Sale sale) {
    sales.add(sale);
    numOfSales += sale.getOccurences();
    totalAmount += sale.getAmount();
    totalAdjustedAmount += sale.getAdjustedAmount();
  }

  public void addAdjustment(Adjustment adjustment) {
    adjustments.add(adjustment);
    totalAdjustedAmount = 0;
    for (Sale s : sales) {
      totalAdjustedAmount += s.adjustAmount(adjustment);
    }
  }

  //-----------------------------------------------------------------------------
  // Getters
  //-----------------------------------------------------------------------------
  public int getNumOfSales() {
    return numOfSales;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public double getTotalAdjustedAmount() {
    return totalAdjustedAmount;
  }

  public List<Sale> getSales() {
    return sales;
  }

  public List<Adjustment> getAdjustments() {
    return adjustments;
  }

}
