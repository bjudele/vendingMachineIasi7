package com.sda.service;

import com.sda.model.Coin;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PaymentService {

  private final IOService ioService;
  private final Map<Coin, Integer> coinStock;

  public PaymentService(IOService ioService, Map<Coin, Integer> coinStock) {
    this.ioService = ioService;
    this.coinStock = coinStock;
  }

  public boolean processPaymentOf(int amountToBePaid) {
    ioService.displayMessage("Please insert a total value of " + amountToBePaid);
    ioService.displayMessage("Type: ");
    for (Coin each : Coin.values()) {
      ioService
          .displayMessage(each.getCode() + " for " + each.getValue() + " " + each.getCurrency());
    }

    int insertedCoinsValue = 0;
    while (insertedCoinsValue < amountToBePaid) {
      int userInputCoinCode = ioService.readUserInput();
      Coin userCoin = Coin.fromCode(userInputCoinCode);
      insertedCoinsValue += userCoin.getValue();

      if (insertedCoinsValue < amountToBePaid) {
        ioService.displayMessage(
            "Inserted value: " + insertedCoinsValue + ". Remaining value " + (amountToBePaid
                - insertedCoinsValue) + ". Please add more coins:");
      }
    }

    if (insertedCoinsValue == amountToBePaid) {
      return true;
    } else {
      //trebuie sa dau rest
      int changeToBeReturned = insertedCoinsValue - amountToBePaid;
      ioService.displayMessage("Need to give back " + changeToBeReturned);

      List<Coin> listWithCoins = new ArrayList<>();
      while (changeToBeReturned > 0) {
        Optional<Coin> biggestCoinLessOrEqualToChange = getNextCoin(changeToBeReturned);
        if (biggestCoinLessOrEqualToChange.isPresent()) {
          Coin coinToBeReleased = biggestCoinLessOrEqualToChange.get();
          changeToBeReturned -= coinToBeReleased.getValue();
          listWithCoins.add(coinToBeReleased);
        } else {
          ioService.displayMessage("No change available. Reverting transaction");
          return false;
        }
      }
      for (Coin each : listWithCoins) {
        ioService
            .displayMessage("Giving a coin of " + each.getValue() + each.getCurrency() + " back.");
      }
    }

    //daca codul userului e coresp monedei de 5 bani,

    return true;

  }

  private Optional<Coin> getNextCoin(int value) {
    for (Map.Entry<Coin, Integer> each : coinStock.entrySet()) {
      Coin coinToBeChecked = each.getKey();
      if (coinToBeChecked.getValue() <= value && each.getValue() > 0) {
        coinStock.put(each.getKey(), each.getValue() - 1);
        return Optional.of(coinToBeChecked);
      }
    }
    return Optional.empty();
  }
}
