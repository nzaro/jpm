package com.jpm.test1;

import java.util.Date;

/**
 *
 * @author nzarokostas
 */
public class Instruction {

  private String entity;
  private String buySell;
  private double agreedFx;
  private String currency;
  private Date instructionDate;
  private Date settlementDate;
  private int units;
  private double pricePerUnit;
  // derived fields
  private final double amountOfTrade;
  private final Date workingSettlementDate;

  public Instruction(String entity, String buySell, double agreedFx, String currency, Date instructionDate, Date settlementDate, int units, double pricePerUnit) {
    this.entity = entity;
    this.buySell = buySell;
    this.agreedFx = agreedFx;
    this.currency = currency;
    this.instructionDate = instructionDate;
    this.settlementDate = settlementDate;
    this.units = units;
    this.pricePerUnit = pricePerUnit;
    // derive fields
    this.amountOfTrade = pricePerUnit * units * agreedFx;
    this.workingSettlementDate = WorkingDay.getNextWorkingDate(currency, settlementDate);
  }

  //----------------------------------------------------------------------------
  // Getter/Setter
  //----------------------------------------------------------------------------
  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public String getBuySell() {
    return buySell;
  }

  public void setBuySell(String buySell) {
    this.buySell = buySell;
  }

  public double getAgreedFx() {
    return agreedFx;
  }

  public void setAgreedFx(double agreedFx) {
    this.agreedFx = agreedFx;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Date getInstructionDate() {
    return instructionDate;
  }

  public void setInstructionDate(Date instructionDate) {
    this.instructionDate = instructionDate;
  }

  public Date getSettlementDate() {
    return settlementDate;
  }

  public void setSettlementDate(Date settlementDate) {
    this.settlementDate = settlementDate;
  }

  public int getUnits() {
    return units;
  }

  public void setUnits(int units) {
    this.units = units;
  }

  public double getPricePerUnit() {
    return pricePerUnit;
  }

  public void setPricePerUnit(double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  public double getAmountOfTrade() {
    return amountOfTrade;
  }

  public Date getWorkingSettlementDate() {
    return workingSettlementDate;
  }

}
