package Class;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TerminalSystem implements Terminal {
    // For clearing terminal
    public void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // For generating random loading
    public void sleeping() {
        int sleepTime = ThreadLocalRandom.current().nextInt(2000, 3001);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Loaded");
        }
    }

    public int getValidIntegerInput(Scanner scan) {
        int flag = 0;
        while (true) {
            if (flag == 1) {
                System.out.print("=> Select an option: ");
            }
            String input = scan.nextLine();
            try {
                return Integer.parseInt(input); // Try parsing input
            } catch (NumberFormatException e) {
                flag = 1;

                System.out.println("\n==============================\nInvalid input! Please enter a number.\n==============================");
            }
        }
    }

}
