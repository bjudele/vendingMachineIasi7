package com.sda;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Main {

  public static void main(String[] args) {

    VendingMachine vendingMachine = new VendingMachine();
    vendingMachine.loadProducts();
    vendingMachine.loadCoins();
    vendingMachine.start();
  }
}
