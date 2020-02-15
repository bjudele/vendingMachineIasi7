package com.sda.service;

import com.sda.model.Coin;
import java.util.Comparator;

public class CoinComparator implements Comparator<Coin> {

  @Override
  public int compare(Coin o1, Coin o2) {
    return o2.getValue().compareTo(o1.getValue());
  }
}
