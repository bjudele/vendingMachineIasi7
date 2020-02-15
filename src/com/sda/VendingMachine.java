package com.sda;

import com.sda.model.Coin;
import com.sda.model.Product;
import com.sda.model.Type;
import com.sda.service.CoinComparator;
import com.sda.service.IOService;
import com.sda.service.PaymentService;
import com.sda.service.ProductFactory;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachine {

  private Map<Integer, List<Product>> productStock;
  private Map<Coin, Integer> coinStock;

  private final ProductFactory productFactory;
  private final IOService ioService;
  private final PaymentService paymentService;

  public VendingMachine() {
    coinStock = new TreeMap<>(new CoinComparator());
    productStock = new TreeMap<>();
    ioService = new IOService();
    productFactory = new ProductFactory();
    paymentService = new PaymentService(ioService, coinStock);
  }

  public void loadProducts() {

    for (Type eachProdType : Type.values()) {
      List<Product> products = productFactory.create(10, eachProdType);
      productStock.put(eachProdType.getCode(), products);
    }
  }

  public void start() {
    while (true) {
      displayMenu();
      int userInputCode = ioService.readUserInput();
      if (isCodeToShutdown(userInputCode)) {
        break;
      }
      processUserInput(userInputCode);
    }
  }

  private boolean isCodeToShutdown(int userInputCode) {
    return userInputCode == 1234;
  }

  private void processUserInput(int userInputCode) {

    if (!isValid(userInputCode)) {
      ioService
          .displayMessage("Sorry, your code does not exist. Do you know how to read, correct?");
      return;
    }
    //aici codul este 100% valid
    // oare produsul o fi pe stoc?
    if (!isInStock(userInputCode)) {
      ioService.displayMessage("Sorry, product is not available!");
      return;
    }
    //aici stiu sigur ca am produsul pe stoc

    Product selectedProduct = getProduct(userInputCode);
    ioService.displayMessage("You have selected " + selectedProduct.getName());
    boolean paymentSuccessful = paymentService.processPaymentOf(selectedProduct.getPrice());
    if (paymentSuccessful) {
      releaseProduct(userInputCode);
    } else {
      ioService.displayMessage("Something went wrong. Please retry holding your fingers crossed!");
    }
  }

  private Product getProduct(int userInputCode) {
    List<Product> products = productStock.get(userInputCode);
    return products.get(0);
  }

  private void releaseProduct(int userInputCode) {
    List<Product> products = productStock.get(userInputCode);
    products.remove(0);
    ioService.displayMessage("Enjoy your product! B-bye now!");
  }

  private boolean isInStock(int userInputCode) {
    List<Product> productsHavingUserInputCode =
        productStock.get(userInputCode);
    int numberOfProductsAvailable = productsHavingUserInputCode.size();
    return numberOfProductsAvailable > 0;
    // exactly the same with the following:
//    return productStock.get(userInputCode).size() > 0;
  }

  private boolean isValid(int userInputCode) {
    return productStock.containsKey(userInputCode);
  }

  private void displayMenu() {
    ioService.displayMessage("\nWelcome, stranger! Choose your poison:");
    ioService.displayMessage("Code  Name  Quantity  Price");
    for (Map.Entry<Integer, List<Product>> each : productStock.entrySet()) {
      if (each.getValue().size() > 0) {
        // daca size-ul listei este mai mare ca 0, e clar ca am macar un produs
        // ceea ce inseamna ca, sigur sigur am un produs pe indexul 0.
        Product productToDisplay = each.getValue().get(0);
        ioService.displayMessage(productToDisplay.toString());
      }
    }
    ioService.displayMessage("Type the product code that you want to buy.");
    ioService.displayMessage("Type pin to shutdown the machine.");
  }

  public void loadCoins() {

    coinStock.put(Coin.CINCI, 34);
    coinStock.put(Coin.CINCIZECI, 24);
    coinStock.put(Coin.UNU, 2);
    coinStock.put(Coin.ZECE, 16);
  }
}
