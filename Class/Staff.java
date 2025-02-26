package Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

public class Staff extends Employee {
    public Staff(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String password, boolean isAdmin, String username) {
        super(id, name, gender, age, phone, email, address, salary, startDate, role, password, isAdmin, username);
    }

    public static void displayClothes() {
        System.out.println("Pending");
    }

    public static void purchase(Employee employee) {
        Scanner scan = new Scanner(System.in);
        System.out.println("===== Walk in Purchasing =====");
        ArrayList<Clothes> purchasedClothes = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        String addMore = "Y";

        // Valid Y/N options
        ArrayList<String> validYes = new ArrayList<>(Arrays.asList("Y", "y", "Yes", "YES", "yes"));
        ArrayList<String> validNo = new ArrayList<>(Arrays.asList("N", "n", "No", "NO", "no"));

        //changed by someth
        while (!addMore.equalsIgnoreCase("N")) {
            try {
                System.out.print("Enter Clothe's Id: ");
                int clothesID = Integer.parseInt(scan.nextLine().trim()); // Use parseInt for better exception handling

                // check if clothID is not smaller than 0
                if(clothesID <= 0) {
                    throw new PurchaseException("Clothes ID must be a positive number.");
                }

                Clothes selectedClothes = null;

                // Find the clothes item by ID
                for (Clothes clothes : Shop.clothesList) {
                    if (clothes.getID() == clothesID) {
                        selectedClothes = clothes;
                        break; // Stop searching once found
                    }
                }

                if (selectedClothes == null) {
                    throw new PurchaseException("Clothes with ID " + clothesID + " not found!");
                }

                System.out.print("Clothes quantity: ");
                int itemQuantity = Integer.parseInt(scan.nextLine().trim());

                // Validate quantity
                if (itemQuantity <= 0) {
                    throw new PurchaseException("Quantity must be a positive number.");
                }

                purchasedClothes.add(selectedClothes);
                quantity.add(itemQuantity);
                System.out.println("==============================");
                System.out.println("Clothes added successfully.");
            } catch (NumberFormatException e) {
                System.out.println("==============================");
                System.out.println("Error: Please enter a valid number for ID or quantity.");
            } catch (PurchaseException e) {
                System.out.println("==============================");
                System.out.println("Error: " + e.getMessage());
            }

            while (true) {
                System.out.println("==============================");
                System.out.print("Add another clothes? (Y/N): ");
                addMore = scan.nextLine().trim();
                if (validYes.contains(addMore) || validNo.contains(addMore)) {
                    break; // Valid input, exit the loop
                } else {
                    System.out.println("==============================");
                    System.out.println("Error: Please enter \n\t\t'Y', 'y', 'Yes', 'YES', 'yes' for Yes, \n\t\tor \n\t\t'N', 'n', 'No', 'NO', 'no' for No.");
                }
            }
        }

        // payment method handling
        String payment = "";
        ArrayList<String> validPayments = new ArrayList<>(Arrays.asList("Cash", "Card", "ABA", "KHQR Code Scan to Pay"));
        while (true) {
            try {
                System.out.println("==============================");
                System.out.println("Payment Options: \n\t\tCash, \n\t\tCard, \n\t\tABA, \n\t\tKHQR Code Scan to Pay");
                System.out.print("Payment Method: ");
                payment = scan.nextLine().trim();

                if (payment.isEmpty()) {
                    throw new PurchaseException("Payment method cannot be empty. Purchase could not be completed.");
                }

                if (!validPayments.contains(payment)) {
                    throw new PurchaseException("Invalid payment method. Please choose from: Cash, Card, ABA, KHQR Code Scan to Pay.");
                }

                // If we reach here, payment is valid
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