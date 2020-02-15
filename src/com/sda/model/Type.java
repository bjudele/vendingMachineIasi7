package com.sda.model;

public enum Type {
  COLA(101, "Cola", 4, "500ml"),
  FANTA(102, "Fanta", 5, "550ml"),
  ALUNE(103, "Nuts", 1, "150g"),
  CIOCOLATA(104, "Milk", 6, "60g");

  private int code;
  private String name;
  private int price;
  private String size;

  Type(int code, String name, int price, String size) {
    this.code = code;
    this.name = name;
    this.price = price;
    this.size = size;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public String getSize() {
    return size;
  }
}
