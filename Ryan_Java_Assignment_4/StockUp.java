import objects.*;
import utility.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StockUp {
  private static Scanner input = new Scanner(System.in);
  private static Ink ink = new Ink();
  private static Validator validator = new Validator();
  private static Market market = new Market();
  private static Portfolio portfolio;

  private static int min = 1; // used for menu selections
  private static int max = 8; // we need a way to set that based on menu items!!
  private static boolean isDone = false;
  private static boolean goBack = false;

  public static void main(String[] args) {

    ink.printWelcome();

    	// sets the starting balance for our portfolio
      System.out.println("How much money are you starting with?");
      double deposit = input.nextDouble();
      portfolio = new Portfolio(deposit, ink);

      seedStocks(); // seed out stocks with some pretend stocks
      seedMarket(); // creates our test Market stocks

      while (!isDone) {
        int choice = validator.selValidation(ink, input, min, max);

        switch (choice) {
					case 1: // print portfolio
          ink.printPortfolio(portfolio.getStocks(), portfolio.getNetworth(), portfolio.getBalance());
						printPortfolio(portfolio); // prints networth inside portfolio
						break;
          case 2: // Print Market and Buy Stocks
            while (!goBack) {
              ink.printMarket(market.getStocks());
              int idx = input.nextInt();
              input.nextLine(); // Consume newline character
              Stock stock = market.getStock(idx - 1);
              ink.printStock(stock);
              int qty = input.nextInt();
              input.nextLine(); // Consume newline character
              boolean isSuccess = market.buyStocks(stock, qty, portfolio.getBalance());
              if (isSuccess) {
                double purchaseAmount = stock.getPrice() * qty;
                portfolio.buyStock(stock, qty, purchaseAmount);
                goBack = true;
                System.out.printf("%s purchased successfully!\n", stock.getSymbol());
              }
            }
            goBack = false; // Reset goBack to false
            break;          
					case 3: // add funds
						double amount = validator.fundValidation(ink, input, portfolio.getBalance());
						portfolio.addFunds(amount);
						// print the new balance
						System.out.printf("New balance: $%.2f\n", portfolio.getBalance());
						break;
					case 4: // sell stocks
						sellStocks();
						break;
					case 5: // convert stocks
						portfolio.convertStocks(input); // Pass Scanner object
						break;
            case 6: // withdraw funds
            double amountToWithdraw = validator.withdrawValidation(ink, input, portfolio.getBalance());
            System.out.println("Enter amount to withdraw:");
            portfolio.withdraw(amountToWithdraw);
            break;
					case 7: // exit
						isDone = !isDone;
						break;
				} // switch
			} // while	
		} // main()

		public static void printPortfolio(Portfolio portfolio) {
			portfolio.sortStocksByValue(); // sorts stocks by total value before print
      ArrayList<Stock> stocks = portfolio.getStocks();
			double netWorth = portfolio.calculateNetWorth();
			double balance = portfolio.getBalance();

			ink.printPortfolio(stocks, netWorth, balance);
		}

    public static void seedStocks() {
    // the purpose is to create some TEST stocks!
    	Stock stock = new Stock("Microsoft", "MSFT", 415.00, 100);
    	portfolio.addStock(stock);
    	stock = new Stock("Uber", "UBER", 66.00, 50);
    	portfolio.addStock(stock);
    	stock = new Stock("Nvidia", "NVDA", 900.00, 90);
    	portfolio.addStock(stock);
  	} // seedStocks()

    public static void seedMarket() {
      ArrayList<Stock> stocks = new ArrayList<>();
      // the purpose is to create some TEST stocks for the Market
      Stock stock = new Stock("Adobe", "ADBE", 482.00, 0);
      stocks.add(stock);
      stock = new Stock("Netflix", "NFLX", 610.00, 0);
      stocks.add(stock);
      stock = new Stock("Apple", "AAPL", 183.00, 0);
      stocks.add(stock);
      stock = new Stock("Disney", "DIS", 105.00, 0);
      stocks.add(stock);
      stock = new Stock("Microsoft", "MSFT", 450.00, 0);
      stocks.add(stock);
      stock = new Stock("Uber", "UBER", 120.00, 0);
      stocks.add(stock);
      stock = new Stock("Nvidia", "NVDA", 900.00, 0);
      stocks.add(stock);
      market.setStocks(stocks);
    } // seedMarket()

    public static void sellStocks() {
      ink.printSellMenu();
      String symbol = input.next();
      if (symbol.equals("0")) {
      	return; // go back to main menu
      }

        Stock stockToSell = null;
        for (Stock stock : portfolio.getStocks()) {
          if (stock.getSymbol().equals(symbol)) {
          	stockToSell = stock;
              break;
        }
      }
      if (stockToSell != null) {
        System.out.println("Enter the quantity to sell:");
        int quantity = input.nextInt();
        portfolio.sellStocks(stockToSell, quantity);
      } else {
        System.out.println("Stock not found in Portfolio.");
    	}
  	} // sell stock
	} // class