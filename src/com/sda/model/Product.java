package com.sda.model;

public class Product {

  private int code;
  private String name;
  private int price;
  private String size;

  public Product(int code, String name, int price, String size) {
    this.code = code;
    this.name = name;
    this.price = price;
    this.size = size;
  }

  @Override
  public String toString() {
    return code + "   " + name + "  " + size + "   " + price+"ron";
  }

  public int getPrice() {
    return price;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getSize() {
    return size;
  }
}
