package Class;

import java.util.ArrayList;
import java.util.Scanner;

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

    public static GeneralEmployee searchByID(int id) {
        for (GeneralEmployee employee: employeeList) {
            if (employee.id == id) {
                return employee;
            }
        }
        return null;
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
    

    @Override
    public String toString() {
        return("==============================\n" +
                "ID: " + this.id +
                "\n==============================" +
                super.toString() +
                "\nSalary: " + this.salary +
                "\nStart Date: " + this.startDate +
                "\nRole: " + this.role +
                "\n=============================="
        );
    }
}