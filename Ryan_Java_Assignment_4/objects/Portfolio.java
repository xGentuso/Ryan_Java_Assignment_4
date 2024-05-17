package objects;

import utility.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Portfolio {

  private Calendar openDate = Calendar.getInstance();
  private Calendar closeDate = Calendar.getInstance();
  private double balance;
  private double networth;

  private Ink ink;
  private ArrayList<Stock> stocks = new ArrayList<>();

  public Portfolio(double deposit, Ink ink) {
    this.balance = deposit;
    this.networth = 0.0;
    this.ink = ink;
  }

  public String getOpenDate() {
    return this.openDate.toString();
  }

  public String getCloseDate() {
    return this.closeDate.toString();
  }

  public double getBalance() {
    return this.balance;
  }

  public double getNetworth() {
    return this.networth;
  }

  public ArrayList<Stock> getStocks() {
    return this.stocks;
  }

  public Ink getInk() {
    return ink;
  }

  public void setCloseDate() {
    this.closeDate = Calendar.getInstance();
  }

  public void addFunds(double amount) {
    if (amount > 0)
      this.balance += amount;
  }

  public void setInk(Ink ink) {
    this.ink = ink;
  }

  public void addStock(Stock stock) {
    stocks.add(stock);
  }

  public void buyStock(Stock stock, int qty, double purchaseAmount) {
    this.balance -= purchaseAmount;
    stock.setQty(qty);
    stocks.add(stock);
  }

  public double calculateNetWorth() {
    double netWorth = balance;
    for (Stock stock : stocks) {
      int qty = stock.getQty();
      double price = stock.getPrice();
      netWorth += qty * price;
    }
    return netWorth;
  }

  public void sellStocks(Stock stock, int quantity) {
    boolean stockFound = false;

    for (Stock portfolioStock : stocks) {
      if (portfolioStock.equals(stock)) {
        stockFound = true;
        double totalValue = stock.getPrice() * quantity;
        balance += totalValue;
        portfolioStock.sell(quantity);

        if (portfolioStock.getQty() == 0) {
          stocks.remove(portfolioStock);
        }

        ink.printSellSuccessful(stock, quantity, totalValue);
        break;
      }
    }

    if (!stockFound) {
      ink.printSellFailure();
    }
  }

  

  public void convertStocks(Scanner input) {
    // print a menu listing the stocks in the portfolio
    System.out.println("Select the stock you want to convert FROM:");
    for (int i = 0; i < stocks.size(); i++) {
      System.out.printf("(%d) %s\n", i + 1, stocks.get(i).getName());
    }
  
    // get the index of the stock to convert FROM
    int fromIndex = input.nextInt();
    input.nextLine(); // Consume newline character
  
    // validate the input
    if (fromIndex < 1 || fromIndex > stocks.size()) {
      System.out.println("Invalid option. Please choose a stock from the list.");
      return;
    }
  
    // get the stock object from the portfolio
    Stock fromStock = stocks.get(fromIndex - 1);
  
    // print a menu again for the stocks to convert TO
    System.out.println("Select the stock you want to convert TO:");
    for (int i = 0; i < stocks.size(); i++) {
      if (i != fromIndex - 1) {
        System.out.printf("(%d) %s\n", i + 1, stocks.get(i).getName());
      }
    }
  
    // get the index of the stock to convert TO
    int toIndex = input.nextInt();
    input.nextLine(); // Consume newline character
  
    // validate the input
    if (toIndex < 1 || toIndex > stocks.size() || toIndex == fromIndex) {
      System.out.println("Invalid option. Please choose a different stock from the list.");
      return;
    }
  
    // get the stock object to convert TO
    Stock toStock = stocks.get(toIndex - 1);
  
    // get the quantity to convert
    System.out.println("Enter the quantity to convert:");
    int quantity = input.nextInt();
    input.nextLine(); // consume newline character
  
    // perform the conversion
    if (fromStock.getQty() >= quantity) {
      fromStock.setQty(fromStock.getQty() - quantity);
      toStock.setQty(toStock.getQty() + quantity);
      System.out.printf("Converted %d shares of %s to %s successfully.\n", quantity, fromStock.getName(), toStock.getName());
    } else {
      System.out.println("Insufficient quantity of " + fromStock.getName() + " to convert.");
    }
  } // convert
  
  public void withdraw(double amount) {
    if (amount <= 0) {
      System.out.println("Withdrawal amount must be positive.");
      return;
    }

    if (balance >= amount) {
      balance -= amount;
      ink.printWithdrawSuccessful(amount, balance); // succesful print for ink class
      ink.printWithdrawFailure(); // fail print for ink class
    }
  } // withdraw

  public void sortStocksByValue() {
    Collections.sort(stocks, new Comparator<Stock>() {
      @Override
      public int compare(Stock s1, Stock s2) {
        double value1 = s1.getQty() * s1.getPrice();
        double value2 = s2.getQty() * s2.getPrice();
        return Double.compare(value2, value1); // Sort in descending order of value
      }
    });
  }
}// class
