package Class;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends Person {
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    int id;
    private double salary;
    private String startDate;
    private String role;
    private String password;
    private boolean isAdmin;
    private String username;

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