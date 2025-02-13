package Class;

import java.util.ArrayList;

public class Employee {
    public static ArrayList<Employee> employeeList = new ArrayList<>();

    private int employeeID;
    String name; // changeed by someth
    private String gender;
    private int age;
    private String phone;
    private String email;
    private String address;
    private double salary;
    private String startDate;
    private String role;
    private String password;
    private boolean isAdmin;

    // Constructor
    public Employee(int id, String name, String gender, int age, String phone, String email,String address, double salary, String startDate, String role, String password, boolean isAdmin) {
        this.employeeID = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.startDate = startDate;
        this.role = role;
        this.password = password;
        this.isAdmin = isAdmin;
        employeeList.add(this);
    }

    // Login method
    public static Employee login(String email, String password) {
        for (Employee emp : employeeList) {
            if (emp.email.equals(email) && emp.password.equals(password)) {
                System.out.println("Login Successful! Welcome, " + emp.name);
                return emp;
            }
        }
        System.out.println("Login Failed! Invalid email or password.");
        return null;
    }

    // Display Employee Details
    public void displayInfo() {
        System.out.println("\nEmployee ID: " + employeeID);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("Salary: $" + salary);
        System.out.println("Start Date: " + startDate);
        System.out.println("Role: " + role);
    }

    // View all employees (Admin Only)
    public static void viewAllEmployees() {
        for (Employee emp : employeeList) {
            emp.displayInfo();
            System.out.println("----------------------");
        }
    }

    // Getter and Setter Methods
    public int getEmployeeID() { return employeeID; }
    public boolean isAdmin() { return isAdmin; }
    public void setRole(String newRole) { this.role = newRole; }

    public void setPhone(String newPhone) {

        throw new UnsupportedOperationException("Unimplemented method 'setPhone'");
    }

    public void setAddress(String newAddress) {
        throw new UnsupportedOperationException("Unimplemented method 'setAddress'");
    }

    public void setSalary(double newSalary) {
        throw new UnsupportedOperationException("Unimplemented method 'setSalary'");
    }
}



