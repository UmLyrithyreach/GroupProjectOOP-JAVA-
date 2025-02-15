package Class;

import java.util.ArrayList;

public class Employee extends Person {
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    int id;
    private double salary;
    private String startDate;
    private String role;
    private String password;
    private boolean isAdmin;

    // Constructor
    public Employee(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String password, boolean isAdmin) {
        super(name, age, gender, phone, email, address);
        this.id = id;
        this.salary = salary;
        this.startDate = startDate;
        this.role = role;
        this.password = password;
        this.isAdmin = isAdmin;
        employeeList.add(this);
    }

    // Login method
    public static Employee login(String email, String password) {
        for (Employee employee : employeeList) {
            if (employee.email.equals(email) && employee.password.equals(password)) {
                System.out.println("Login Successful! Welcome, " + employee.name);
                return employee;
            }
        }
        System.out.println("Login Failed! Invalid email or password.");
        return null;
    }

    // View all employees (Admin Only)
    public static void viewAllEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee.toString());
            System.out.println("==================");
        }
    }

    // Getter and Setter Methods
    public int getEmployeeID() { return id; }
    public boolean isManager() { return isAdmin; }
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

    @Override
    public String toString() {
        return super.toString() +
                "\nSalary: " + this.salary +
                "\nStart Date: " + this.startDate +
                "\nRole: " + this.role; 
    }
}



