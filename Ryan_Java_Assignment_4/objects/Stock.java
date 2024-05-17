package objects;



public class Stock {

  private String name;
  private String symbol;
  private double price;
  private int qty;

  public Stock(String name, String symbol,
    double price, int qty) {
    this.name = name;
    this.symbol = symbol;
    this.price = price;
    this.qty = qty;
  } // constructor

  //=================>>
  // GETTERS
  public String getName() {
    return this.name;
  }
  public String getSymbol() {
    return this.symbol;
  }
  public double getPrice() {
    return this.price;
  }
  public int getQty() {
    return this.qty;
  }
  //=================>>
  // SETTERS
  public void setName(String name) {
    this.name = name;
  }
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
  public void setPrice(double price) {
    this.price = price;
  }
  public void setQty(int qty) {
    this.qty = qty;
  }

  public void sell(int quantity) {
    if (quantity > 0 && quantity <= qty) {
      qty -= quantity;
      System.out.println(quantity + " stocks of " + name + " sold successfully.");
    } else {
      System.out.println("Insufficient quantity to sell.");
    }
  } // sell stocks

  public void convertTo(Stock targetStock) {
    // Check if there are enough stocks to convert
    if (this.qty > 0) {
      // reduce the quantity of this stock
      int quantityToConvert = Math.min(this.qty, targetStock.getMaxConversionQuantity());
      this.qty -= quantityToConvert;
      // increase the quantity of the target stock
      targetStock.qty += quantityToConvert;
      System.out.printf("%d stocks of %s converted to %s successfully.\n", quantityToConvert, this.name, targetStock.name);
    } else {
      System.out.println("Insufficient quantity to convert.");
    }
  }

// add a method to get the maximum quantity that can be converted
  public int getMaxConversionQuantity() {
    // return any condition for the maximum quantity to be converted,
    // such as a specific percentage of the current quantity
    // or a fixed maximum quantity
    return Integer.MAX_VALUE; // For demonstration purposes, returning maximum integer value
  }
  } // class
