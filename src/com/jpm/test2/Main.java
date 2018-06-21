package com.jpm.test2;

/**
 *
 * @author nzarokostas
 */
public class Main {

  public static void main(String[] args) {
    MessageHandler handler = new MessageHandler();
    for (int i = 0; i < 10; i++) {
      Message msg = new Message("1", "productTypeA", 1);
      handler.onMessage(msg);
    }
    for (int i = 0; i < 10; i++) {
      Message msg = new Message("2", "productTypeA", 1, i + 1);
      handler.onMessage(msg);
    }
    for (int i = 0; i < 10; i++) {
      Message msg = new Message("3", "productTypeA", 1, i + 1, "+", 1.0);
      handler.onMessage(msg);
    }
    for (int i = 0; i < 10; i++) {
      Message msg = new Message("3", "productTypeA", 1, i + 1, "-", 1.0);
      handler.onMessage(msg);
    }
    for (int i = 0; i < 10; i++) {
      Message msg = new Message("3", "productTypeA", 1, i + 1, "*", 2.0);
      handler.onMessage(msg);
    }
  }

}
