package Class;

import java.util.Scanner;

public class Manager extends Staff {

    public Manager(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String password, boolean isAdmin, String username) {
        super(id, name, gender, age, phone, email, address, salary, startDate, role, password, isAdmin, username);
    }

    // Update Employee
    public static void updateEmployee(int employeeId) {
        Scanner scan = new Scanner(System.in);
        Terminal terminal = new TerminalSystem();
        for (Employee employee : Employee.employeeList) {
            if (employee.getEmployeeID() == employeeId) {
                terminal.clearTerminal();
                System.out.println("Choose a field to update:");
                System.out.println("1. Role");
                System.out.println("2. Salary");
                System.out.println("3. Address");
                System.out.println("4. Phone");
                System.out.println("\n0. Cancel\n");
                System.out.print("=> Select an option: ");
                int choice = scan.nextInt();
                scan.nextLine();
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        terminal.clearTerminal();
                        System.out.print("Enter new role: ");
                        String newRole = scan.nextLine();
                        employee.setRole(newRole);
                        System.out.println("Role updated successfully.");
                        scan.nextLine();
                        break;
                    case 2:
                        terminal.clearTerminal();
                        System.out.print("Enter new salary: ");
                        double newSalary = scan.nextDouble();
                        scan.nextLine();
                        employee.setSalary(newSalary);
                        System.out.println("Salary updated successfully.");
                        break;
                    case 3:
                        terminal.clearTerminal();
                        System.out.print("Enter new address: ");
                        String newAddress = scan.nextLine();
                        employee.setAddress(newAddress);
                        System.out.println("Address updated successfully.");
                        break;
                    case 4:
                        terminal.clearTerminal();
                        System.out.print("Enter new phone: ");
                        String newPhone = scan.nextLine();
                        employee.setPhoneNumber(newPhone);
                        System.out.println("Phone number updated successfully.");
                        break;
                    default:
                        terminal.clearTerminal();
                        System.out.println("Invalid choice, try again.");
                        scan.nextLine();
                }
                scan.close();
                return;
            }
        }
        System.out.println("Employee not found.");
        scan.close();
    }

    // View all employees (Admin Only)
    public static void viewAllEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee.toString() + "\n");
        }
        System.out.println("Total Employees: " + employeeList.size() + "\n");
    }
}
