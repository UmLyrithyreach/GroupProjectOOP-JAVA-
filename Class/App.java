package Class;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        new Employee(1, "Bro Eng", "Male", 35, "0123456789", "BroEng@shop.com", 
                     "123 Admin St", 5000, "01/01/2020", "Administrator", "admin123", true);

        new Employee(2, "PapaN", "Male", 28, "0987654321", "PapaN@shop.com", 
                     "456 Worker St", 1200, "02/02/2021", "Sales Assistant", "password123", false);

        new Employee(3, "Kimju", "Female", 25, "0876543210", "jane@shop.com", 
                     "789 Cashier St", 1500, "03/03/2022", "Cashier", "123", false);
        
        new Employee(4, "Diddy", "Male", 40, "0876543210", "Diddy@shop.com", 
                     "789 Cashier St", 420, "03/03/2022", "Miner", "123", false);

        System.out.println("====== Clothing Shop Management System ======");
        System.out.print("Enter Email: ");
        String email = scan.nextLine();
        System.out.print("Enter Password: ");
        String password = scan.nextLine();

        Employee loggedInUser = Employee.login(email, password);

        if (loggedInUser == null) {
            System.out.println("Exiting...");
            scan.close();
            return;
        }

        if (loggedInUser.isManager()) {
            managerFeature(scan);
        } else {
            staffFeature(scan);
        }

        scan.close();
    }

    // Manager Feature
    public static void managerFeature(Scanner scan) {
        int choice;
        do {
            System.out.println("\n====== Manager Operator ======");
            System.out.println("1. View All Staff");
            System.out.println("2. Update Staff Details");
            System.out.println("\n0. Logout");
            System.out.print("=> Select an option: ");
            choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 1:
                    Employee.viewAllEmployees();
                    System.out.println("< Press enter to continue >");
                    scan.nextLine();
                    break;
                case 2:
                    System.out.print("Enter Employee ID to Update: ");
                    int empID = scan.nextInt();
                    scan.nextLine();
                    updateEmployee(scan, empID);
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
    public static void updateEmployee(Scanner scan, int empID) {
        for (Employee emp : Employee.employeeList) {
            if (emp.getEmployeeID() == empID) {
                System.out.println("Choose a field to update:");
                System.out.println("1. Role");
                System.out.println("2. Salary");
                System.out.println("3. Address");
                System.out.println("4. Phone");
                System.out.println("5. Cancel");
                System.out.print("Enter choice: ");
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter new role: ");
                        String newRole = scan.nextLine();
                        emp.setRole(newRole);
                        System.out.println("Role updated successfully.");
                        break;
                    case 2:
                        System.out.print("Enter new salary: ");
                        double newSalary = scan.nextDouble();
                        scan.nextLine();
                        emp.setSalary(newSalary);
                        System.out.println("Salary updated successfully.");
                        break;
                    case 3:
                        System.out.print("Enter new address: ");
                        String newAddress = scan.nextLine();
                        emp.setAddress(newAddress);
                        System.out.println("Address updated successfully.");
                        break;
                    case 4:
                        System.out.print("Enter new phone: ");
                        String newPhone = scan.nextLine();
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
    public static void staffFeature(Scanner scan) {
        int choice;
        do {
            System.out.println("\n====== Welcome ======");
            System.out.println("1. Check Clothing Items");
            System.out.println("2. Generate Receipt");
            System.out.println("0. Logout");
            System.out.print("\n=> Select an option: ");
            choice = scan.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Logging out...");
                    return;
                case 1:
                    System.out.println("Displaying Available Clothing Items...");
                    break;
                case 2:
                    System.out.println("Generating Receipt...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (true);
    }
}