package Class;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.ResultSet;

public class Employee extends Person {
    int id;
    double salary;
    String startDate;
    String role;
    String password;
    int isManager;
    String username;
    
    // Constructor
    public Employee(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String username, String password, int isManager) {
        super(name, age, gender, phone, email, address);
        this.id = id;
        this.salary = salary;
        this.startDate = startDate;
        this.role = role;
        this.password = password;
        this.isManager = isManager;
        this.username = username;
    }

    // Login Constructor
    public Employee (int isManager) {
        this.isManager = isManager;
    }

    public static void searchEmployeeByID() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee ID: ");
        int employeeID = Integer.parseInt(scanner.nextLine());

        // String query to search for employee by ID
        String query = "SELECT * FROM employees WHERE id = ?";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, employeeID);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs == null || !rs.next()) {
                System.out.println("Employee with id '" + employeeID + "' not found!");
                return;
            } 

            // Fetch data if found
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String phoneNumber = rs.getString("phoneNumber");
            String email = rs.getString("email");
            String address = rs.getString("address");
            BigDecimal salary = rs.getBigDecimal("salary");
            String startDate = rs.getString("startDate");
            String role = rs.getString("role");
            
            System.out.println(toString(employeeID, name, age, gender, phoneNumber, email, address, salary, startDate, role));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchEmployeeByName() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name: ");
        String employeeName = scanner.nextLine();
        
        // String query to search for employees by name
        String query = "SELECT * FROM employees WHERE name = ?";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, employeeName);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs == null || !rs.next()) {
                System.out.println("Employee with name '" + employeeName + "' not found!");
                return;
            } 

            // Fetch data if found
            int id = rs.getInt("id");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String phoneNumber = rs.getString("phoneNumber");
            String email = rs.getString("email");
            String address = rs.getString("address");
            BigDecimal salary = rs.getBigDecimal("salary");
            String startDate = rs.getString("startDate");
            String role = rs.getString("role");
            
            System.out.println(toString(id, employeeName, age, gender, phoneNumber, email, address, salary, startDate, role));
                
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addNewEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== New Employee Detail =====");

        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Gender: ");
        String inputGender = scanner.nextLine();
        
        System.out.print("Age: ");
        int age = Integer.valueOf(scanner.nextLine());

        System.out.println("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Address: ");
        String address = scanner.nextLine();

        System.out.println("Salary");
        double salary = Double.valueOf(scanner.nextLine());

        System.out.println("Start Date: ");
        String startDate = scanner.nextLine();

        System.out.println("Role: ");
        String role = scanner.nextLine();

        // String query to add new employee
        String query = "INSERT INTO employees (name, age, gender, phoneNumber, email, address, salary, startDate, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, age, inputGender, phoneNumber, email, address, salary, startDate, role);

        if (rowsAffected > 0) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Failed to add employee.");
        }
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
                    } else if (itemQuantity > selectedClothes.getStock()) {
                        throw new PurchaseException("Quantity exceeds available stock (" + selectedClothes.getStock() + ").\n==============================");
                    }
                    selectedClothes.decreaseStock(itemQuantity);
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
        storeOrderSummary(order);
    }

    // Login method
    public static Employee login(Scanner scan) {
        Terminal terminal = new TerminalSystem();
        int tryCounter = 0;
        int max_Attempts = 3;

        // String query to verify employees
        String query = "SELECT * FROM employees WHERE username = ? AND password = ?";

        while (tryCounter < max_Attempts) {
            System.out.println("====== Clothing Shop Management System ======");
            System.out.print("Enter username: ");
            String username = scan.nextLine();
            System.out.print("Enter Password: ");
            String password = scan.nextLine();

            try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, username, password);
                 ResultSet rs = stmt.executeQuery()) {

                if (rs == null || !rs.next()) {
                    System.out.println("Invalid username or password");
                    System.out.println("Please wait to re-attempt...");
                    tryCounter++;
                    terminal.sleeping();
                    terminal.clearTerminal();
                    continue;
                }

                // Fetch data
                String name = rs.getString("name");
                int isManager = rs.getInt("isManager");

                terminal.clearTerminal();
                System.out.println("=============================================");
                System.out.println("Login Successful! Welcome, " + name);
                System.out.println("=============================================");
                Employee staff = new Employee(isManager);
                terminal.sleeping();
                return staff;


            } catch (SQLException e) {
                System.out.println("Error while verifying employee");
                e.printStackTrace();
            }

        }
        System.out.println("========================================\nMaximum login attempts reached. Exiting...\n========================================");
        terminal.sleeping();
        return null;
    }

    // Verify
    public int isManager() {
        return isManager;
    }

    // Method to store the order summary in a text file
    private static void storeOrderSummary(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("order_summary_" + order.orderId + ".txt", true))) {
            writer.write("--- Order Summary (ID: " + order.orderId + ") ---\n");
            writer.write(order.toString());
            writer.newLine(); // Add a newline for readability
            writer.newLine(); // Extra newline for separation between orders
            System.out.println("Order summary saved to 'all_order_summaries.txt'");
        } catch (IOException e) {
            System.out.println("Error saving order summary: " + e.getMessage());
        }
    }

    // Display Cashier Details
    public static String toString(int id, String name,int age, String gender, String phoneNumber, String email, String address, BigDecimal salary, String startDate, String role, String password, int isManager, String username) {
        String status = (isManager == 1) ? "Manager" : "Not Manager";
        return("==============================\n" +
                "ID: " + id +
                "\n==============================" +
                Person.toString(name, age, gender, phoneNumber, address, email) +
                "\nSalary: " + salary +
                "\nStart Date: " + startDate +
                "\nRole: " + role +
                "\nUsername: " + username +
                "\nPassword: " + password +
                "\nManager Status: " + status +
                "\n=============================="
        );
    }

    // Setter Methods

    public int getEmployeeID() { return this.id; }

    public void setRole(String newRole) { this.role = newRole; }

    public void setSalary(double newSalary) { this.salary = newSalary; }

    public void setStartDate(String newStartDate) { this.startDate = newStartDate; }
    

    public static String toString(int id, String name,int age, String gender, String phoneNumber, String email, String address, BigDecimal salary, String startDate, String role) {
        return("==============================\n" +
                "ID: " + id +
                "\n==============================" +
                Person.toString(name, age, gender, phoneNumber, address, email) +
                "\nSalary: " + salary +
                "\nStart Date: " + startDate +
                "\nRole: " + role +
                "\n=============================="
        );
    }
}