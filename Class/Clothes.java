package Class;

import java.util.Scanner;

import javax.xml.crypto.Data;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Clothes {
    int id;
    String name;
    String brand;
    String size;
    double price;
    int stock;
    String style;
    int supplierId;

    public Clothes(String name, String brand, String size, double price, int stock, String style, int supplierId) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.style = style;
        this.supplierId = supplierId;
        this.size = size;
    }

    // Getter Methods

    public int getID() { return this.id; }
    
    public String getName() { return name; }

    public int getStock() { return this.stock; }

    public double getPrice() { return this.price; }

    public String getStyle() { return this.style; }

    public int getSupplierId() { return this.supplierId; }

    public String getSizes() { return this.size; }

    public String getBrand() { return this.brand; }
    

    // Setter Methods
    public void increaseStock(int amount) {
        if (amount > 0) {
            this.stock += amount;
        }
    }

    public void decreaseStock(int amount) {
        if (amount > 0 && this.stock >= amount) {
            this.stock -= amount;
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    public boolean isAvailable() {
        return (this.stock > 0) ? true : false;
    }

    public static void addClothes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter clothes name: ");
        String name = scanner.nextLine();
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter size: ");
        String size = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter type: ");
        String style = scanner.nextLine();
        System.out.print("Enter supplier ID: ");
        int supplierId = Integer.parseInt(scanner.nextLine());

        // String query to insert the clothes into the database
        String query = "INSERT INTO clothes (name, brand, size, price, stock, style, supplierId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, brand, size, price, stock, style, supplierId);

        if (rowsAffected > 0) {
            System.out.println("Clothes added successfully.");
        } else {
            System.out.println("Failed to add clothes.");
        }
    }

    public static void displayClothes() {
        // String query to get all clothes
        String query = "SELECT * FROM clothes";

        // Execute the query
        try (ResultSet rs = DatabaseConnection.executeQuery(query)) {
            if (rs == null || !rs.next()) {
                System.out.println("No clothes found!");
                return;
            }

            do {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String size = rs.getString("size");
                BigDecimal price = rs.getBigDecimal("price");
                int stock = rs.getInt("stock");
                String style = rs.getString("style");
                int supplierId = rs.getInt("supplierId");

                System.out.println(toString(id, name, brand, style, price, stock, size, supplierId));
            } while (rs.next());

        } catch (SQLException e) {
            System.out.println("Error while displaying clothes.");
            e.printStackTrace();
        }
    }

    // Search by name
    public static void searchByName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter clothe name: ");
        String name = scanner.nextLine();

        // String query to search for the clothe's name
        String query = "SELECT * FROM clothes WHERE name = ?";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, name);
             ResultSet rs = stmt.executeQuery()) {
            if (rs == null || !rs.next()) {
                System.out.println("No clothes found!");
                return;
            }

            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            String size = rs.getString("size");
            BigDecimal price = rs.getBigDecimal("price");
            int stock = rs.getInt("stock");
            String style = rs.getString("style");
            int supplierId = rs.getInt("supplierId");

            System.out.println(toString(id, name, brand, style, price, stock, size, supplierId));
        } catch (SQLException e) {
            System.out.println("Error while searching for clothes");
        }
    }

    // Search by brand
    public static void searchByBrand() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();

        // String query to search for clothes brand
        String query = "SELECT * FROM clothes WHERE brand = ?";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, brand);
             ResultSet rs = stmt.executeQuery()) {

            if (rs == null || !rs.next()) {
                System.out.println("No brand found!");
                return;
            }

            do {
                // Fetch data
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String size = rs.getString("size");
                BigDecimal price = rs.getBigDecimal("price");
                int stock = rs.getInt("stock");
                String style = rs.getString("style");
                int supplierId = rs.getInt("supplierId");
    
                // Print clothes details
                System.out.println(toString(id, name, brand, style, price, stock, size, supplierId));
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println("Error while searching for clothes");
        }
    }

    // Search by ID
    public static void searchById() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter ID: ");
        int id = Integer.valueOf(scanner.nextLine());

        // String query to search for id
        String query = "SELECT * FROM clothes WHERE id = ?";

        // Execute the query
        ResultSet rs = DatabaseConnection.executePreparedQuery(query, id);

        // Process the result set
        try {
            
            if (rs == null || !rs.next()) {
                System.out.println("No ID found!");
                return;
            }

            String name = rs.getString("name");
            String size = rs.getString("size");
            BigDecimal price = rs.getBigDecimal("price");
            int stock = rs.getInt("stock");
            String style = rs.getString("style");
            int supplierId = rs.getInt("supplierId");
            String brand = rs.getString("brand");

            System.out.println(toString(id, name, brand, style, price, stock, size, supplierId));

        } catch (SQLException e) {
            System.out.println("Error while searching for clothes");
        } finally {
            DatabaseConnection.closeResultSet(rs);
        }
    }

    public static int totalClothes() {
        
    }

    public static int totalStock() {
        
    }

    public static void removeClothesById() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter ID: ");
        int id = Integer.valueOf(scanner.nextLine());

        // String query to search for id
        String query = "DELETE FROM clothes WHERE id = ?";

        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, id);

        if (rowsAffected > 0) {
            System.out.println("Clothes deleted successfully!");
        } else {
            System.out.println("Failed to delete clothes");
        }
    }

    public static String toString(int id, String name, String brand, String style, BigDecimal price, int stock, String size, int supplierId) {
        String info = "====================================\nID: " + id
                + "\nName: " + name
                + "\nBrand: " + brand
                + "\nStyle: " + style
                + "\nPrice: $" + price
                + "\nStock: " + stock
                + "\nSize: " + size
                + "\nSupplier ID: " + supplierId
                + "\n====================================\n";
        return info;
    }
}