package Class;

import java.util.ArrayList;
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
        System.out.println("==== Purchase Terminal ====");
        ArrayList<Clothes> purchasedClothes = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        String addMore = "Y";
    
        while (!addMore.equalsIgnoreCase("N")) {
            System.out.print("Enter Clothe's Id: ");
            int clothesID = Integer.valueOf(scan.nextLine());
            Clothes selectedClothes = null;
    
            // Find the clothes item by ID
            for (Clothes clothes : Shop.clothesList) {
                if (clothes.getID() == clothesID) {
                    selectedClothes = clothes;
                    break; // Stop searching once found
                }
            }
    
            if (selectedClothes != null) {
                System.out.print("Clothes quantity: ");
                int itemQuantity = Integer.valueOf(scan.nextLine());
                purchasedClothes.add(selectedClothes);
                quantity.add(itemQuantity);
                System.out.println("Clothes added successfully.");
            } else {
                System.out.println("Clothes not found!");
            }
    
            System.out.print("Add another clothes? (Y/N): ");
            addMore = scan.nextLine();
        }
    
        System.out.print("Payment Method: ");
        String payment = scan.nextLine();
    
        Order order = new Order(employee, purchasedClothes, quantity, LocalDate.now(), payment);
        System.out.println(order.toString());
        // Do NOT close the scanner here if it's used elsewhere in the program.
    }
    
}