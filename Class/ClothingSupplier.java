package Class;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ClothingSupplier extends Person {
    int id;
    public static ArrayList<ClothingSupplier> supplierList = new ArrayList<>();

    // Constructor
    public ClothingSupplier(String name, String phoneNumber, String address) {
        super(name, phoneNumber, address);
    }

    public static ClothingSupplier searchSupplier(String name) {
        for (ClothingSupplier supplier: supplierList) {
            if (supplier.name.equalsIgnoreCase(name)) {
                return supplier;
            }
        }
        return null;
    }

    public static ClothingSupplier searchSupplier(int id) {
        for (ClothingSupplier supplier: supplierList) {
            if (supplier.id == id) {
                return supplier;
            }
        }
        return null;
    }

    public static void removeSupplier(String name) {
        ClothingSupplier supplier = searchSupplier(name);
        if (supplier == null) {
            System.out.println("Supplier not found.");
            return;
        } else {
            supplierList.remove(supplier);
            System.out.println("Supplier removed successfully.");
        }
    }

    public static void removeSupplier(int id) {
        ClothingSupplier supplier = searchSupplier(id);
        if (supplier == null) {
            System.out.println("Supplier not found.");
            return;
        } else {
            supplierList.remove(supplier);
            System.out.println("Supplier removed successfully.");
        }
    }

    public static void displaySuppliers() {
        for (ClothingSupplier supplier: supplierList) {
            System.out.println(supplier);
        }
    }

    // Save supplier data to database
    public void saveToDatabase() {
        String query = "INSERT INTO suppliers (name, phone_number, address) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, this.name);
            stmt.setString(2, this.phoneNumber);
            stmt.setString(3, this.address);
            stmt.executeUpdate();

            // Retrieve the auto-generated ID
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1); // Get the auto-generated ID
            }

            System.out.println("Supplier added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding supplier: " + e.getMessage());
        }
    }

    public static void addSupplier() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter supplier name: ");
        String supplierName = scan.nextLine();
        System.out.print("Enter supplier address: ");
        String supplierAddress = scan.nextLine();
        System.out.print("Enter supplier contact: ");
        String supplierContact = scan.nextLine();

        ClothingSupplier supplier = new ClothingSupplier(supplierName, supplierContact, supplierAddress);
        supplier.saveToDatabase();
        
        System.out.println("Supplier added successfully.");
        scan.close();
    }

    // Getter and Setter for id
    public int getId() {
        return this.id;
    }

    // Override Method
    @Override
    public String toString() {
        String result = "ID: " + this.id + "\n";
        result += super.toString();
        
        return result;
    }
}
