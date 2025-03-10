package Class;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Employee extends Person implements Terminal {
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    int id;
    private double salary;
    private String startDate;
    private String role;
    private String password;
    private boolean isAdmin;
    private String username;
    private int phone;
    
    // Constructor
    public Employee(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String password, boolean isAdmin, String username) {
        super(name, age, gender, phone, email, address);
        this.id = id;
        this.salary = salary;
        this.startDate = startDate;
        this.role = role;
        this.password = password;
        this.isAdmin = isAdmin;
        this.username = username;
        employeeList.add(this);
    }

    // Save employee data to a file
    public static void saveEmployeesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Employee employee : employeeList) {
                writer.write(employee.id + "," + employee.name + "," + employee.gender + "," + employee.age + "," + employee.phone + "," + employee.email + "," + employee.address + "," + employee.salary + "," + employee.startDate + "," + employee.role + "," + employee.password + "," + employee.isAdmin + "," + employee.username);
            writer.newLine();
            }
            System.out.println("Employee data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    public static void loadEmployeesFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }
    
        try (Scanner fileReader = new Scanner(file)) {  // Use File, not String
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");
                if (data.length != 13) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String gender = data[2];
                    int age = Integer.parseInt(data[3]);
                    String phone = data[4];
                    String email = data[5];
                    String address = data[6];
                    double salary = Double.parseDouble(data[7]);
                    String startDate = data[8];
                    String role = data[9];
                    String password = data[10];
                    boolean isAdmin = Boolean.parseBoolean(data[11]);
                    String username = data[12];
    
                    new Employee(id, name, gender, age, phone, email, address, salary, startDate, role, password, isAdmin, username);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            System.out.println("Employee data loaded from " + filename);
        } catch (FileNotFoundException e) {  // Correct exception type
            System.out.println("Error loading employee data: " + e.getMessage());
        }
    }

    // Login method
    public static Employee login(Scanner scan) {
        Terminal terminal = new TerminalSystem();
        int tryCounter = 0;
        int max_Attempts = 3;

        while (tryCounter < max_Attempts) {
            System.out.println("====== Clothing Shop Management System ======");
            System.out.print("Enter username: ");
            String username = scan.nextLine();
            System.out.print("Enter Password: ");
            String password = scan.nextLine();

            for (Employee employee: employeeList) {
                if (employee.username.equals(username) && employee.password.equals(password)) {
                    terminal.clearTerminal();
                    System.out.println("=============================================");
                    System.out.println("Login Successful! Welcome, " + employee.name);
                    System.out.println("=============================================");
                    terminal.sleeping();
                    return employee;
                }
            }

            System.out.println("========================================\nLogin Failed! Invalid username or password.\n========================================");
            System.out.println("Please wait to re-attempt...");
            tryCounter++;
            terminal.sleeping();
            terminal.clearTerminal();
        }
        System.out.println("========================================\nMaximum login attempts reached. Exiting...\n========================================");
        terminal.sleeping();
        return null;
    }

    public static Employee searchByID(int id) {
        for (Employee employee: employeeList) {
            if (employee.id == id) {
                return employee;
            }
        }
        return null;
    }

    
    // Setter Methods
    public boolean isManager() { return this.isAdmin; }

    public int getEmployeeID() { return this.id; }

    public void setRole(String newRole) { this.role = newRole; }

    public void setSalary(double newSalary) { this.salary = newSalary; }

    public void setStartDate(String newStartDate) { this.startDate = newStartDate; }

    public void setPassword(String newPassword) { this.password = newPassword; }
    public String getPassword() { return this.password; }

    public void setIsAdmin(boolean newIsAdmin) { this.isAdmin = newIsAdmin; }

    public void setUsername(String newUsername) { this.username = newUsername; }
    

    @Override
    public String toString() {
        return("==============================\n" +
                "ID: " + this.id +
                "\n==============================" +
                super.toString() +
                "\nSalary: " + this.salary +
                "\nStart Date: " + this.startDate +
                "\nRole: " + this.role +
                "\nUsername: " + this.username +
                "\nAdmin Status: " + this.isAdmin +
                "\n=============================="
        );
    }
}