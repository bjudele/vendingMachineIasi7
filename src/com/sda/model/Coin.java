package com.sda.model;

public enum Coin  {
  UNU(1001, 1, "ron"),
  CINCI(1002, 5, "ron"),
  ZECE(1003, 10, "ron"),
  CINCIZECI(1004, 50, "ron");

  private int code;
  private Integer value;
  private String currency;

  Coin(int code, int value, String currency) {
    this.code = code;
    this.value = value;
    this.currency = currency;
  }

  public static Coin fromCode(int userInputCoinCode) {
    for (Coin each : values()) {
      if (each.getCode() == userInputCoinCode) {
        return each;
      }
    }
    throw new RuntimeException("no such coin!");
  }

  public static Coin lowerOrEqualTo(int value) {
    for (Coin each : values()) {
      if (each.getValue() == value) {
        return each;
      }
    }
    throw new RuntimeException("no such coin!");
  }

  public int getCode() {
    return code;
  }

  public Integer getValue() {
    return value;
  }

  public String getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return "Coin{" +
        "code=" + code +
        ", value=" + value +
        ", currency='" + currency + '\'' +
        '}';
  }

}
