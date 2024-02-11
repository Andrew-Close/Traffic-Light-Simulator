package traffic.main;

import traffic.queue.QueueThread;

import java.io.IOException;
import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);
  private static final QueueThread queueThread = new QueueThread();
  public static int numOfRoads;
  public static int interval;
  public static int state = 0;

  public static void main(String[] args) {
    System.out.println("Welcome! You have just entered the traffic management system.");
    System.out.print("Input the number of roads: ");
    numOfRoads = InputGetter.getValidPositiveIntegerLoop();
    queueThread.initQueue();
    System.out.print("Input the interval: ");
    interval = InputGetter.getValidPositiveIntegerLoop();
    try {
      var clearCommand = System.getProperty("os.name").contains("Windows")
              ? new ProcessBuilder("cmd", "/c", "cls")
              : new ProcessBuilder("clear");
      clearCommand.inheritIO().start().waitFor();
    }
    catch (IOException | InterruptedException ignored) {}
    queueThread.setDaemon(true);
    queueThread.start();
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
      int choice = InputGetter.getValidMenuOption();
      // This is if the inputted menu option is wrong. This is because it handles the loop and waiting for input here, not in InputGetter.java
      if (choice == -1) {
        scanner.nextLine();
        try {
          var clearCommand = System.getProperty("os.name").contains("Windows")
                  ? new ProcessBuilder("cmd", "/c", "cls")
                  : new ProcessBuilder("clear");
          clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException ignored) {}
      } else {
        switch (choice) {
          case 0: {
            System.out.println("Goodbye!");
            queueThread.terminate();
            break loop;
          }
          case 1:
            System.out.print("Input road name: ");
            queueThread.getQueue().enqueue(scanner.nextLine());
            state = 1;
            break;
          case 2:
            System.out.println(queueThread.getQueue().dequeue() + " deleted!");
            state = 2;
            break;
          case 3:
            System.out.println("System opened");
            queueThread.printSystemInformation();
            state = 3;
        }
        scanner.nextLine();
        state = 0;
        try {
          var clearCommand = System.getProperty("os.name").contains("Windows")
                  ? new ProcessBuilder("cmd", "/c", "cls")
                  : new ProcessBuilder("clear");
          clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException ignored) {}
      }
    }
  }
}
