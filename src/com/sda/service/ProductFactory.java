package com.sda.service;

import com.sda.model.Product;
import com.sda.model.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductFactory {

  public List<Product> create(int howMany, Type prodType) {

    List<Product> result = new ArrayList<>();
    for (int index = 0; index < howMany; index++) {
      result.add(new Product(prodType.getCode(), prodType.getName(),
          prodType.getPrice(), prodType.getSize()));
    }
    return result;
  }
}
