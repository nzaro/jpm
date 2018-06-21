package com.jpm.test2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nzarokostas
 */
public class MessageHandler {

  private int msgCounter;
  private boolean active;
  private final Map<String, Sales> salesPerProduct;

  public MessageHandler() {
    this.msgCounter = 0;
    this.active = true;
    this.salesPerProduct = new HashMap<>();
  }

  public void onMessage(Message msg) {
    msgCounter++;
    if (!active) {
      return;
    }

    Sale sale = new Sale("" + msgCounter, msg.getUnitPrice(), msg.getOccurences());
    Sales sales = salesPerProduct.get(msg.getProductType());
    if (sales == null) {
      sales = new Sales();
      salesPerProduct.put(msg.getProductType(), sales);
    }
    sales.addSale(sale);

    if (msg.getMessageType().equals("3")) {
      Adjustment adjustment = new Adjustment(msg.getOperationType(), msg.getOperationAmount());
      sales.addAdjustment(adjustment);
    }

    if (msgCounter % 10 == 0) {
      reportSales();
    }
    if (msgCounter == 50) {
      active = false;
      reportAdjustments();
    }
  }

  //----------------------------------------------------------------------------
  // Helpers
  //----------------------------------------------------------------------------
  private void reportSales() {
    String rowDelimiter = String.format("|%s|\n", String.join("", Collections.nCopies(33, "-")));

    StringBuilder sb = new StringBuilder();
    sb.append(rowDelimiter);
    sb.append(String.format("| %-12s | %s | %-8s |\n", "ProductType", "Sales", "Value"));
    sb.append(rowDelimiter);
    for (String productType : salesPerProduct.keySet()) {
      Sales sales = salesPerProduct.get(productType);
      sb.append(String.format("| %-12s | %05d | %8.2f |\n", productType, sales.getNumOfSales(), sales.getTotalAdjustedAmount()));
    }
    sb.append(rowDelimiter);
    System.out.println(sb);
  }

  private void reportAdjustments() {
    String rowDelimiter = String.format("|%s|\n", String.join("", Collections.nCopies(78, "-")));

    StringBuilder sb = new StringBuilder();
    sb.append(rowDelimiter);
    sb.append(String.format("| %-12s | %-12s | %-12s | %-12s | %-12s |\n", "ProductType", "Sale", "Adjustment", "OriginalAmount", "AdjustedAmount"));
    sb.append(rowDelimiter);
    for (String productType : salesPerProduct.keySet()) {
      Sales sales = salesPerProduct.get(productType);
      for (Sale s : sales.getSales()) {
        for (AmountAdjustment adj : s.getAdjustments()) {
          sb.append(String.format("| %-12s | %-12s | %-12s | %-14.2f | %-14.2f |\n", productType, s.toString(), adj.toString(), adj.getOriginalAmount(), adj.getAdjustedAmount()));
        }
      }
    }
    sb.append(rowDelimiter);
    System.out.println(sb);
  }

}
