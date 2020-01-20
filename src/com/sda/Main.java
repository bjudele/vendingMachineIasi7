package com.sda;

public class Main {

  public static void main(String[] args) {
    VendingMachine vendingMachine = new VendingMachine();
    vendingMachine.loadProducts();
    vendingMachine.start();
  }
}
