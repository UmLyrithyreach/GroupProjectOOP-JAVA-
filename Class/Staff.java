package Class;

import java.util.Scanner;

public class Staff extends Employee {
    public Staff(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String password, boolean isAdmin, String username) {
        super(id, name, gender, age, phone, email, address, salary, startDate, role, password, isAdmin, username);
    }

    // Staff
    public static void feature(Scanner scan) {
        Terminal terminal = new TerminalSystem();
        int choice;
        do {
            terminal.clearTerminal();
            System.out.println("\n====== Welcome ======");
            System.out.println("1. Check Clothing Items");
            System.out.println("2. Generate Receipt");
            System.out.println("3. Take Order");
            System.out.println("\n0. Logout\n");
            System.out.print("\n=> Select an option: ");
            choice = scan.nextInt();

            switch (choice) {
                case 0:
                    terminal.clearTerminal();
                    System.out.println("Logging out...");
                    return;
                case 1:
                    terminal.clearTerminal();
                    System.out.println("Displaying Available Clothing Items...");
                    break;
                case 2:
                    System.out.println("Generating Receipt...");
                    break;
                case 3:
                    System.out.println("Taking customer order...");
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (true);
    }
}
