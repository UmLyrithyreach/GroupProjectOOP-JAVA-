package Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

public class Staff extends Employee {
    public Staff(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String password, boolean isAdmin, String username) {
        super(id, name, gender, age, phone, email, address, salary, startDate, role, password, isAdmin, username);
    }

    public static void purchase(Employee employee) {
        Scanner scan = new Scanner(System.in);
        System.out.println("===== Walk in Purchasing =====");
        ArrayList<Clothes> purchasedClothes = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        String addMore = "Y";

        while (!addMore.equals("N")) {
            Clothes selectedClothes = null;
            int itemQuantity = 0;
            boolean validItem = false;

            // Loop for Clothes ID until valid
            while (!validItem) {
                try {
                    System.out.print("Enter Clothe's Id: ");
                    int clothesID = Integer.parseInt(scan.nextLine().trim());
                    if (clothesID <= 0) {
                        throw new PurchaseException("Clothes ID must be a positive number.\n==============================");
                    }

                    // Find the clothes item by ID
                    for (Clothes clothes : Shop.clothesList) {
                        if (clothes.getID() == clothesID) {
                            selectedClothes = clothes;
                            break;
                        }
                    }
                    if (selectedClothes == null) {
                        throw new PurchaseException("Clothes with ID " + clothesID + " not found!\n==============================");
                    }
                    validItem = true; // ID is valid, exit this loop

                } catch (NumberFormatException e) {
                    System.out.println("==============================");
                    System.out.println("Error: Please enter a valid number for ID.\n==============================");
                } catch (PurchaseException e) {
                    System.out.println("==============================");
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Loop for Quantity until valid
            while (true) {
                try {
                    System.out.print("Clothes quantity: ");
                    itemQuantity = Integer.parseInt(scan.nextLine().trim());
                    if (itemQuantity <= 0) {
                        throw new PurchaseException("Quantity must be a positive number.\n==============================");
                    }
                    break; // Quantity is valid, exit this loop

                } catch (NumberFormatException e) {
                    System.out.println("==============================");
                    System.out.println("Error: Please enter a valid number for quantity.\n==============================");
                } catch (PurchaseException e) {
                    System.out.println("==============================");
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Add the valid item and quantity
            purchasedClothes.add(selectedClothes);
            quantity.add(itemQuantity);
            System.out.println("==============================");
            System.out.println("Clothes added successfully.");

            // Y/N prompt after successful addition
            int attempts = 0;
            final int MAX_ATTEMPTS = 3;
            while (true) {
                System.out.println("==============================");
                System.out.print("Add another clothes? (Y/N): ");
                addMore = scan.nextLine().trim();
                if (addMore.equals("Y") || addMore.equals("N")) {
                    break;
                } else {
                    attempts++;
                    System.out.println("==============================");
                    System.out.println("Error: Invalid input. Please enter exactly 'Y' or 'N' (uppercase only).");
                    if (attempts >= MAX_ATTEMPTS) {
                        System.out.println("Too many invalid attempts. Defaulting to 'N' to proceed.");
                        addMore = "N";
                        break;
                    }
                }
            }
        }

        // Payment method handling
        String payment = "";
        ArrayList<String> validPayments = new ArrayList<>(Arrays.asList("Cash", "Card", "ABA", "KHQR"));
        while (true) {
            try {
                System.out.println("==============================");
                System.out.println("Payment Options: \n\t\tCash, \n\t\tCard, \n\t\tABA, \n\t\tKHQR");
                System.out.print("Payment Method: ");
                payment = scan.nextLine().trim();
                if (payment.isEmpty()) {
                    throw new PurchaseException("Payment method cannot be empty. Purchase could not be completed.");
                }
                if (!validPayments.contains(payment)) {
                    throw new PurchaseException("Invalid payment method. Please choose from: Cash, Card, ABA, KHQR.");
                }
                break;

            } catch (PurchaseException e) {
                System.out.println("==============================");
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Create order once payment is valid
        Order order = new Order(employee, purchasedClothes, quantity, LocalDate.now(), payment);
        System.out.println(order.toString());
        // Do NOT close the scanner here if it's used elsewhere in the program.
    }
}