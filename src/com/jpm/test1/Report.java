package com.jpm.test1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nzarokostas
 */
public class Report {

  // total amount per working settlement date
  private final Map<Date, Double> totalAmountIncomingPerDay;
  private final Map<Date, Double> totalAmountOutgoingPerDay;
  // total amount per entity
  private final Map<String, Double> totalAmountIncomingPerEntity;
  private final Map<String, Double> totalAmountOutgoingPerEntity;
  // range of dates
  private Date minDate;
  private Date maxDate;

  public Report() {
    this.totalAmountIncomingPerDay = new HashMap<>();
    this.totalAmountOutgoingPerDay = new HashMap<>();
    this.totalAmountIncomingPerEntity = new HashMap<>();
    this.totalAmountOutgoingPerEntity = new HashMap<>();
  }

  public void process(List<Instruction> instructions) {
    if (instructions == null || instructions.isEmpty()) {
      return;
    }

    // clear counters
    totalAmountIncomingPerDay.clear();
    totalAmountOutgoingPerDay.clear();
    totalAmountIncomingPerEntity.clear();
    totalAmountOutgoingPerEntity.clear();
    minDate = null;
    maxDate = null;

    // calculate sums
    for (Instruction instruction : instructions) {
      Date date = instruction.getWorkingSettlementDate();
      if (minDate == null || minDate.after(date)) {
        minDate = date;
      }
      if (maxDate == null || maxDate.before(date)) {
        maxDate = date;
      }

      if (instruction.getBuySell().equals("B")) {
        // Outgoing total per day
        Double currentTotalAmountPerDay = totalAmountOutgoingPerDay.get(date);
        if (currentTotalAmountPerDay == null) {
          currentTotalAmountPerDay = 0.0;
        }
        currentTotalAmountPerDay += instruction.getAmountOfTrade();
        totalAmountOutgoingPerDay.put(date, currentTotalAmountPerDay);

        // Outgoing total per entity
        Double currentTotalAmountPerEntity = totalAmountOutgoingPerEntity.get(instruction.getEntity());
        if (currentTotalAmountPerEntity == null) {
          currentTotalAmountPerEntity = 0.0;
        }
        currentTotalAmountPerEntity += instruction.getAmountOfTrade();
        totalAmountOutgoingPerEntity.put(instruction.getEntity(), currentTotalAmountPerEntity);
      } else {
        // Incoming total per day
        Double currentTotalAmountPerDay = totalAmountIncomingPerDay.get(date);
        if (currentTotalAmountPerDay == null) {
          currentTotalAmountPerDay = 0.0;
        }
        currentTotalAmountPerDay += instruction.getAmountOfTrade();
        totalAmountIncomingPerDay.put(date, currentTotalAmountPerDay);

        // Incoming total per entity
        Double currentTotalAmountPerEntity = totalAmountIncomingPerEntity.get(instruction.getEntity());
        if (currentTotalAmountPerEntity == null) {
          currentTotalAmountPerEntity = 0.0;
        }
        currentTotalAmountPerEntity += instruction.getAmountOfTrade();
        totalAmountIncomingPerEntity.put(instruction.getEntity(), currentTotalAmountPerEntity);
      }
    }

    generateAmountOutput();
    generateRankingOutput();
  }

  //----------------------------------------------------------------------------
  // Helpers
  //----------------------------------------------------------------------------
  private void generateAmountOutput() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String rowDelimiter = String.format("|%s|\n", String.join("", Collections.nCopies(34, "-")));

    StringBuilder sb = new StringBuilder();
    sb.append(rowDelimiter);
    sb.append(String.format("| %-10s | %s | %s |\n", "Date", "Incoming", "Outgoing"));
    sb.append(rowDelimiter);

    Calendar c = Calendar.getInstance();
    c.setTime(minDate);
    while (c.getTime().getTime() <= maxDate.getTime()) {
      Date date = c.getTime();
      Double incoming = totalAmountIncomingPerDay.get(date);
      if (incoming == null) {
        incoming = 0.0;
      }
      Double outgoing = totalAmountOutgoingPerDay.get(date);
      if (outgoing == null) {
        outgoing = 0.0;
      }
      sb.append(String.format("| %-10s | %8.2f | %8.2f |\n", sdf.format(date), incoming, outgoing));
      c.add(Calendar.DAY_OF_WEEK, 1);
    }
    sb.append(rowDelimiter);
    System.out.println(sb);
  }

  private void generateRankingOutput() {
    String rowDelimiter = String.format("|%s|\n", String.join("", Collections.nCopies(29, "-")));

    List<Map.Entry<String, Double>> incomingList = new ArrayList<>(totalAmountIncomingPerEntity.entrySet());
    List<Map.Entry<String, Double>> outgoingList = new ArrayList<>(totalAmountOutgoingPerEntity.entrySet());

    Collections.sort(incomingList, new RankingComparator());
    Collections.sort(outgoingList, new RankingComparator());

    StringBuilder sb = new StringBuilder();
    sb.append(rowDelimiter);
    sb.append(String.format("| %s | %s | %s |\n", "Entity", "Ranking", "Incoming"));
    sb.append(rowDelimiter);
    for (int i = 0; i < incomingList.size(); i++) {
      Map.Entry<String, Double> entry = incomingList.get(i);
      sb.append(String.format("| %-6s | %-7d | %8.2f |\n", entry.getKey(), i, entry.getValue()));
    }
    sb.append(rowDelimiter);
    System.out.println(sb);

    sb = new StringBuilder();
    sb.append(rowDelimiter);
    sb.append(String.format("| %s | %s | %s |\n", "Entity", "Ranking", "Outgoing"));
    sb.append(rowDelimiter);
    for (int i = 0; i < outgoingList.size(); i++) {
      Map.Entry<String, Double> entry = outgoingList.get(i);
      sb.append(String.format("| %-6s | %-7d | %8.2f |\n", entry.getKey(), i, entry.getValue()));
    }
    sb.append(rowDelimiter);
    System.out.println(sb);
  }

  //----------------------------------------------------------------------------
  // Comparator
  //----------------------------------------------------------------------------
  private class RankingComparator implements Comparator<Map.Entry<String, Double>> {

    @Override
    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
      return o1.getValue().compareTo(o2.getValue());
    }
  }

}
