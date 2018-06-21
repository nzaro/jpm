package com.jpm.test1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nzarokostas
 */
public class Main {

  public static void main(String[] args) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    List<Instruction> instructions = new ArrayList<>();
    Report report = new Report();
    try {
      instructions.add(new Instruction("foo", "B", 0.50, "SGP", sdf.parse("2016-01-01"), sdf.parse("2016-01-02"), 200, 100.25));
      instructions.add(new Instruction("bar", "S", 0.22, "AED", sdf.parse("2016-01-05"), sdf.parse("2016-01-07"), 450, 150.50));
      report.process(instructions);
    } catch (ParseException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
