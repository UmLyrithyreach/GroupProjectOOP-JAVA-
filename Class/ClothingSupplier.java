package Class;

import java.sql.SQLException;
import java.util.Scanner;

import java.sql.ResultSet;

public class ClothingSupplier extends Person {
    int id;

    // Constructor
    public ClothingSupplier(String name, String phoneNumber, String address) {
        super(name, phoneNumber, address);
    }

    public static void searchSupplierByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter supplier name: ");
        String name = scanner.nextLine();

        // String query to get the supplier by name
        String query = "SELECT * FROM suppliers WHERE name = ?";

        // Execute the query
        ResultSet rs = DatabaseConnection.executePreparedQuery(query, name);

        try {
            if (rs == null || !rs.next()) {
                System.out.println("Supplier with name '" + name + "' not found!");
            }
            
            int id = rs.getInt("id");
            String contact = rs.getString("contact");
            String address = rs.getString("address");

            // Print the supplier details
            System.out.println(toString(id, name, address, contact));

        } catch (SQLException e) {
            System.out.println("Error while searching for supplier.");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
        }
    }

    public static void searchSupplierByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter supplier's ID: ");
        int id = Integer.valueOf(scanner.nextLine());

        // String query to get the supplier by ID.
        String query = "SELECT * FROM supplier WHERE id = ?";

        // Execute the query
        ResultSet rs = DatabaseConnection.executePreparedQuery(query, id);

        // Execute the query using PreparedStatement
        try {
            if (rs == null || !rs.next()) {
                System.out.println("Supplier not found!");
            }
            
            String name = rs.getString("name");
            String contact = rs.getString("contact");
            String address = rs.getString("address");

            System.out.println(toString(id, name, address, contact));

        } catch (SQLException e) {
            System.out.println("Error while searching for supplier.");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
        }
    }

    public static void removeSupplierByName() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter supplier name to remove: ");
        String supplierName = scan.nextLine();

        // String query to remove supplier by name
        String query = "DELETE FROM suppliers WHERE name = ?";

        // Execute the query using PreparedStatement
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, supplierName);
        
        if (rowsAffected > 0) {
            System.out.println("Supplier with name '" + supplierName + "' has been removed successfully.");
        } else {
            System.out.println("No supplier found with name '" + supplierName +"'.");
        }
    }

    public static void removeSupplier(int id) {
        // String query to remove supplier by id
        String query = "DELETE FROM supplier WHERE id = ?";

        // Execute the query using PreparedStatement
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, id);

        if (rowsAffected > 0) {
            System.out.println("Supplier with id '" + id + "' has been removed successfully.");
        } else {
            System.out.println("No supplier found with id '" + id + "'.");
        }
    }

    public static void displayAllSuppliers() {
        // String query to get all suppliers from the database
        String query = "SELECT * FROM suppliers";

        // Execute the query
        ResultSet rs = DatabaseConnection.executeQuery(query);

        // Process the result set
        try {
            if (rs == null || !rs.next()) {
                System.out.println("No suppliers found.");
                return;
            }

            do {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String contact = rs.getString("contact");

                System.out.println(toString(id, name, address, contact));
            } while (rs.next());

        } catch (SQLException e) {
            System.out.println("Error while displaying suppliers.");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
        }
    }

    public static void addSupplierToDatabase() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter supplier name: ");
        String supplierName = scan.nextLine();
        System.out.print("Enter supplier address: ");
        String supplierAddress = scan.nextLine();
        System.out.print("Enter supplier contact: ");
        String supplierContact = scan.nextLine();
        
        // String query to insert the supplier into the database
        String query = "INSERT INTO suppliers (name, address, contact) VALUES (?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, supplierName, supplierAddress, supplierContact);

        if (rowsAffected > 0) {
            System.out.println("Supplier added successfully.");
        } else {
            System.out.println("Failed to add supplier.");
        }
    }

    // Getter and Setter for id
    public int getId() {
        return this.id;
    }

    // Override Method
    public static String toString(int id, String name, String address, String contact) {
        return "ID: " + id + 
            "\nName: " + name + 
            "\nAddress: " + address + 
            "\nContact: " + contact;
    }
}
