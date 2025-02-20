package Class;

import java.util.ArrayList;
import java.util.Scanner;

public class Staff extends Employee {
    public Staff(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String password, boolean isAdmin, String username) {
        super(id, name, gender, age, phone, email, address, salary, startDate, role, password, isAdmin, username);
    }

    public static void displayClothes() {
        System.out.println("Pending");
    }

    public void purchase() {
        Scanner scan = new Scanner(System.in);
        System.out.println("==== Purchase Terminal ====");
        ArrayList<Clothes> purchasedClothes = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        String addMore = "Y";
        int verify = 0;
        while (!addMore.equalsIgnoreCase("N")) {
            System.out.print("Enter Clothe's Id: ");
            int clothesID = Integer.valueOf(scan.nextLine());
            for (Clothes clothes: Shop.clothesList) {
                if (clothes.getID() == clothesID) {
                    purchasedClothes.add(clothes);
                    verify = 1;
                }
                if (verify == 1) {
                    System.out.print("Clothes quantity: ");
                    int itemQuantity = Integer.valueOf(scan.nextLine());
                    quantity.add(itemQuantity);
                    System.out.println("Clothes added successfully");
                } else {
                    System.out.println("Clothes not found!");
                }
            }
            System.out.print("Add another clothes? (Y/N): ");
            addMore = scan.nextLine();
        }
        System.out.print("Payment Method: ");
        String payment = scan.nextLine();
        
        scan.close();
    }
}