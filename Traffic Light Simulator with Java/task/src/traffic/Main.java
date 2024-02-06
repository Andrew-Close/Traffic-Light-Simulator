package traffic;

import java.util.Scanner;

public class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int numOfRoads;
    int interval;




    // Need to create a new function which only checks if it's an integer, cause right now you can only select 0-3 for the roads and interval.




    System.out.println("Welcome! You have just entered the traffic management system.");
    System.out.print("Input the number of roads: ");
    numOfRoads = InputChecker.getValidInteger();
    System.out.print("Input the interval: ");
    interval = InputChecker.getValidInteger();
    selectionLoop();
  }

  public static void selectionLoop() {
    loop:
    while (true) {
      System.out.println("Menu:");
      System.out.println("1. Add");
      System.out.println("2. Delete");
      System.out.println("3. System");
      System.out.println("0. Quit");
      switch (scanner.nextInt()) {
        case 0 -> {
          System.out.println("Goodbye!");
          break loop;
        }
        case 1 -> System.out.println("Road added");
        case 2 -> System.out.println("Road deleted");
        case 3 -> System.out.println("System opened");
      }
    }
  }
}
