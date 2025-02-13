package Class;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Employee(1, "Bro Eng", "Male", 35, "0123456789", "BroEng@shop.com", 
                     "123 Admin St", 5000, "01/01/2020", "Administrator", "admin123", true);

        new Employee(2, "PapaN", "Male", 28, "0987654321", "PapaN@shop.com", 
                     "456 Worker St", 1200, "02/02/2021", "Sales Assistant", "password123", false);

        new Employee(3, "Kimju", "Female", 25, "0876543210", "jane@shop.com", 
                     "789 Cashier St", 1500, "03/03/2022", "Cashier", "123", false);

        System.out.println("====== Clothing Shop Management System ======");
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Employee loggedInUser = Employee.login(email, password);

        if (loggedInUser == null) {
            System.out.println("Exiting...");
            sc.close();
            return;
        }

        if (loggedInUser.isAdmin()) {
            adminMenu(sc);
        } else {
            staffMenu(sc);
        }

        sc.close();
    }

    // Admin
    public static void adminMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n====== Admin Menu ======");
            System.out.println("1. View All Staff");
            System.out.println("2. Update Staff Details");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    Employee.viewAllEmployees();
                    break;
                case 2:
                    System.out.print("Enter Employee ID to Update: ");
                    int empID = sc.nextInt();
                    sc.nextLine();
                    updateEmployee(sc, empID);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (true);
    }

    // Update Employee 
    public static void updateEmployee(Scanner sc, int empID) {
        for (Employee emp : Employee.employeeList) {
            if (emp.getEmployeeID() == empID) {
                System.out.println("Choose a field to update:");
                System.out.println("1. Role");
                System.out.println("2. Salary");
                System.out.println("3. Address");
                System.out.println("4. Phone");
                System.out.println("5. Cancel");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter new role: ");
                        String newRole = sc.nextLine();
                        emp.setRole(newRole);
                        System.out.println("Role updated successfully.");
                        break;
                    case 2:
                        System.out.print("Enter new salary: ");
                        double newSalary = sc.nextDouble();
                        sc.nextLine();
                        emp.setSalary(newSalary);
                        System.out.println("Salary updated successfully.");
                        break;
                    case 3:
                        System.out.print("Enter new address: ");
                        String newAddress = sc.nextLine();
                        emp.setAddress(newAddress);
                        System.out.println("Address updated successfully.");
                        break;
                    case 4:
                        System.out.print("Enter new phone: ");
                        String newPhone = sc.nextLine();
                        emp.setPhone(newPhone);
                        System.out.println("Phone number updated successfully.");
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    // Staff
    public static void staffMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n====== Staff Menu ======");
            System.out.println("1. Check Clothing Items");
            System.out.println("2. Generate Receipt");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Displaying Available Clothing Items...");
                    break;
                case 2:
                    System.out.println("Generating Receipt...");
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (true);
    }
}


