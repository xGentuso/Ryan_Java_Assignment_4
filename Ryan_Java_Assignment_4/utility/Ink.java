package utility;
import java.util.ArrayList;
import objects.Stock;

public class Ink {

  // ANSI color codes
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_RED = "\u001B[31m";
  

  public Ink() {
    // do nothing
  }

  public void printWelcome() {
    System.out.println(ANSI_GREEN + "*** Welcome to StockUP beta ***" + ANSI_RESET + "\n");
  }

  public void printGoodday() {
    System.out.println("*** Richer Every Day with stockUP ***\n");
  }

  public void printStock(Stock stock) {
    System.out.printf(ANSI_GREEN + "Name: %s\nSymbol: %s\nPrice: $%.2f" + ANSI_RESET,
      stock.getName(), stock.getSymbol(), stock.getPrice());
    System.out.println("\nHow many units of this stock would you like?? ");
  }

  public void printPortfolio(ArrayList<Stock> stocks, double networth, double balance) {
    for (int i = 0; i < stocks.size(); i++) {
      System.out.printf("(%d) Name: %s Symbol: %s Price: $%.2f Qty: %d\n",
        i + 1,
        stocks.get(i).getName(),
        stocks.get(i).getSymbol(),
        stocks.get(i).getPrice(),
        stocks.get(i).getQty());
    }
    System.out.printf("Networth: $%.2f\n", networth);
    System.out.printf("Balance: $%.2f\n", balance);
  }

  public void printMarket(ArrayList<Stock> stocks) {
    for (int i = 0; i < stocks.size(); i++) {
      System.out.printf("(%d) Name: %s Symbol: %s Price: $%.2f\n",
        i + 1,
        stocks.get(i).getName(),
        stocks.get(i).getSymbol(),
        stocks.get(i).getPrice());
    }
    System.out.println("Which stock would you like to buy?: ");
  }

  public void printMenu() {
    System.out.println(ANSI_GREEN + "(1) Show Portfolio" + ANSI_RESET);
    System.out.println(ANSI_GREEN + "(2) Show Stocks" + ANSI_RESET);
    System.out.println(ANSI_GREEN + "(3) Add Funds" + ANSI_RESET);
    System.out.println(ANSI_GREEN + "(4) Sell Stocks" + ANSI_RESET);
    System.out.println(ANSI_GREEN + "(5) Convert Stocks" + ANSI_RESET);
    System.out.println(ANSI_GREEN + "(6) Withdraw Funds" + ANSI_RESET);
    System.out.println(ANSI_GREEN + "(7) Exit" + ANSI_RESET);
  }

  public void printStockDetail(Stock stock) {
    System.out.printf("Name: %s Symbol: %s Price: %d Qty: %d",
      stock.getName(), stock.getSymbol(),
      stock.getPrice(), stock.getQty());
  }

  public void printAddFunds(double balance) {
    System.out.printf("Current balance: $%.2f\nAmount to add?: ",
      balance);
  }

  public void printSellMenu() {
    System.out.println("=== Sell Stocks Menu ===");
    System.out.println("Chose a stock to sell:");
    System.out.println("Enter the symbol of the stock you want to sell:");
  }

  public void printSellSuccessful(Stock stock, int quantity, double totalValue) {
    System.out.printf("Sold %d share of %s for $%.2f\n", quantity, stock.getName(), totalValue);
  }

  public void printSellFailure() {
    System.out.println("Failed to sell stock. Stock not found in Portfolio.");
  }

  public void printInsufficientQuantity() {
    System.out.println("Failed to sell stock. Insufficient quantity");
  }

  public void printConversionMenu(ArrayList<Stock> stocks) {
    System.out.println("Conversion Menu:");
    int option = 1;
    for (Stock stock : stocks) {
      System.out.printf("%d. Convert %s\n", option++, stock.getName());
    }
    System.out.println("0. Back to Main Menu");
    System.out.println("Choose an option:");
  }

  public void printEnterQuantityToConvert(String fromStockName) {
    System.out.printf("Enter the quantity of %s to convert: ", fromStockName);
  }

  public void printWithdrawSuccessful(double amount, double newBalance) {
    System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f.\n", amount, newBalance);
  }

  public void printWithdrawFailure() {
    System.out.println("Insufficient funds for withdrawal.");
  }

  public void printErrorMessage(String message) {
    System.out.println(ANSI_RED + message + ANSI_RESET);
  }
}
