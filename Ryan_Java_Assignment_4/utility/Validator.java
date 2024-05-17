package utility;

import java.util.Scanner;

public class Validator {
  
  public Validator() {
    // do nothing
  }

  public int selValidation(Ink ink, Scanner input, int min, int max) {
    boolean valid = false;
    int choice = 0;
    while(!valid) {
      ink.printMenu();
      try {
        choice = input.nextInt(); // throw an exception
        if(choice >= min && choice <= max){
          valid = true;
        }
        else {
          System.out.println("Selection out of range!");
        }
      }
      catch (Exception e) {
        System.out.println("Please read the menu and make a valid selection!");
        valid = false; // just in case
      } 
      finally { // runs if there's an error or NOT!! always runs
        input.nextLine(); // clear the input
      }
    } // while()
    return choice;
  } // isValid()

  public double fundValidation(Ink ink, Scanner input, double balance) {
    double amount = 0;
    boolean valid = false;
    while(!valid) {
      ink.printAddFunds(balance);
      try {
        amount = input.nextDouble(); // throw an exception
        if(amount > 0){
          valid = true;
        }
        else {
          System.out.println("Enter a postive number!");
        }
      } 
      catch (Exception e) {
        System.out.println("Please read the directions!");
        valid = false; // just in case
      } 
      finally { // runs if there's an error or NOT!! always runs
        input.nextLine(); // clear the input
      }
    } // while()
    return amount;
  }


  public double withdrawValidation(Ink ink, Scanner input, double balance) {
    double amount;
    while (true) {
      System.out.println("Enter amount to withdraw:");
      if (input.hasNextDouble()) {
        amount = input.nextDouble();
        if (amount > 0 && amount <= balance) {
          break;
        } else {
          ink.printErrorMessage("Invalid amount. Please enter a positive amount up to your balance.");
        }
      } else {
        ink.printErrorMessage("Invalid input. Please enter a numeric value.");
        input.next(); // Clear the invalid input
      }
    }
    return amount;
  }
} // class