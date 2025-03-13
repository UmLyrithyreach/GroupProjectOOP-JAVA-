package Class;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;

public class GeneralEmployee extends Person {
    public static ArrayList<GeneralEmployee> employeeList = new ArrayList<>();
    int id;
    double salary;
    String startDate;
    String role;
    
    // Constructor
    public GeneralEmployee(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role) {
        super(name, age, gender, phone, email, address);
        this.id = id;
        this.salary = salary;
        this.startDate = startDate;
        this.role = role;
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
                System.out.println("Employee with id '" + employeeID + "' not found!");
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