package traffic;

import java.util.Scanner;

public class InputGetter {
    private enum Choices {
        ZERO(0),ONE(1), TWO(2), THREE(3);
        private final int choice;

        Choices(int choice) {
            this.choice = choice;
        }
    }

    private final static Scanner scanner = new Scanner(System.in);

    public static int getValidPositiveIntegerLoop() {
        while (true) {
            String input = scanner.nextLine();
            int value;
            try {
                value = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Incorrect input. Please try again.");
                continue;
            }
            if (value > 0) {
                return value;
            } else {
                System.out.println("Incorrect input. Please try again.");
            }
        }
    }

    public static int getValidMenuOption() {
        String input = scanner.nextLine();
        int value;
        try {
            value = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Incorrect option. Please try again.");
            return -1;
        }
        for (Choices choice : Choices.values()) {
            if (choice.choice == value) {
                return value;
            }
        }
        System.out.println("Incorrect option. Please try again.");
        return -1;
    }
}
