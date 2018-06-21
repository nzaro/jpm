package com.jpm.test1;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nzarokostas
 */
public class WorkingDay {

  // Default working days
  private final static int WORKING_DAY_TYPE_0[] = new int[]{0, 1, 1, 1, 1, 1, 0};
  // AED/SAR working days
  private final static int WORKING_DAY_TYPE_1[] = new int[]{1, 1, 1, 1, 1, 0, 0};

  public static boolean isWorkingDay(String currency, int dayOfWeek) {
    int isWorkingday;
    if (currency == null) {
      throw new RuntimeException("Curreny cannot be null");
    } else if (currency.equalsIgnoreCase("AED")) {
      isWorkingday = WORKING_DAY_TYPE_1[dayOfWeek - 1];
    } else if (currency.equalsIgnoreCase("SAR")) {
      isWorkingday = WORKING_DAY_TYPE_1[dayOfWeek - 1];
    } else {
      isWorkingday = WORKING_DAY_TYPE_0[dayOfWeek - 1];
    }
    return isWorkingday == 1;
  }

  public static Date getNextWorkingDate(String currency, Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    while (!WorkingDay.isWorkingDay(currency, c.get(Calendar.DAY_OF_WEEK))) {
      c.add(Calendar.DAY_OF_WEEK, 1);
    }
    return c.getTime();
  }

}
