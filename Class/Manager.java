package Class;

import java.util.Scanner;

public class Manager extends Staff {

    public Manager(int id, String name, String gender, int age, String phone, String email, String address, double salary, String startDate, String role, String password, boolean isAdmin, String username) {
        super(id, name, gender, age, phone, email, address, salary, startDate, role, password, isAdmin, username);
    }

    // Manager Feature
    public static void mfeature(Scanner scan) {
        Terminal terminal = new TerminalSystem();
        int choice;
        do {
            terminal.clearTerminal();
            System.out.println("\n====== Manager Operator ======");
            System.out.println("1. View All Staff");
            System.out.println("2. Update Staff Details");
            System.out.println("\n0. Logout\n");
            System.out.print("=> Select an option: ");
            choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 0:
                    System.out.println("Logging out...");
                    return;
                case 1:
                    terminal.clearTerminal();
                    Manager.viewAllEmployees();
                    System.out.println("< Press enter to continue >");
                    scan.nextLine();
                    break;
                case 2:
                    terminal.clearTerminal();
                    System.out.print("Enter Employee ID to Update: ");
                    int employeeId = scan.nextInt();
                    scan.nextLine();
                    updateEmployee(scan, employeeId);
                    break;
                case 3:
                    terminal.clearTerminal();
                    System.out.print("Enter employee name: ");
                    String employeeName = scan.nextLine();
                    Employee foundEmployee = (Employee) Person.searchByName(employeeList, employeeName);
                    if (foundEmployee == null) {
                        System.out.println("Employee not found!");
                    } else {
                        System.out.println(foundEmployee);
                    }
                    scan.nextLine();
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (true);
    }

    // Update Employee 
    public static void updateEmployee(Scanner scan, int employeeId) {
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
                        employee.setPhone(newPhone);
                        System.out.println("Phone number updated successfully.");
                        break;
                    default:
                        terminal.clearTerminal();
                        System.out.println("Invalid choice, try again.");
                        scan.nextLine();
                }
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // View all employees (Admin Only)
    public static void viewAllEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee.toString());
            System.out.println("==================");
        }
        System.out.println("Total Employees: " + employeeList.size());
    }
}
