package com.sda.service;

import java.util.Scanner;

public class IOService {

  private Scanner scanner;

  public IOService() {
    this.scanner = new Scanner(System.in);
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }

  public int readUserInput(){
     return scanner.nextInt();
  }
}
