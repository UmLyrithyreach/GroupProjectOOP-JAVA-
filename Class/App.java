package Class;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Terminal terminal = new TerminalSystem();
        terminal.clearTerminal();
        
        new Employee(1, "Bro Eng", "Male", 35, "0123456789", "BroEng@shop.com", 
                     "123 Admin St", 5000, "01/01/2020", "Administrator", "admin123", true, "broEng123");

        new Employee(2, "PapaN", "Male", 28, "0987654321", "PapaN@shop.com", 
                     "456 Worker St", 1200, "02/02/2021", "Sales Assistant", "password123", false , "papaN123");

        new Employee(3, "Kimju", "Female", 25, "0876543210", "jane@shop.com", 
                     "789 Cashier St", 1500, "03/03/2022", "Cashier", "123", false, "kimju123");
        
        new Employee(4, "Diddy", "Male", 40, "0876543210", "Diddy@shop.com", 
                     "789 Cashier St", 420, "03/03/2022", "Miner", "123", false, "diddy123");

        Employee loggedInUser = Employee.login(scan);

        if (loggedInUser.isManager()) {
            int choice;
            do {
                terminal.clearTerminal();
                System.out.println("\n====== Manager Operator ======");
                System.out.println("1. View All Staff");
                System.out.println("2. Update Staff Details");
                System.out.println("3. Search Employee");
                System.out.println("\n0. Logout\n");
                System.out.print("=> Select an option: ");
                choice = scan.nextInt();
                scan.nextLine(); 

                switch (choice) {
                    case 0:
                        System.out.println("Logging out...");
                        terminal.sleeping();
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
                        Manager.updateEmployee(employeeId);
                        break;
                    case 3:
                        terminal.clearTerminal();
                        System.out.print("Enter employee name: ");
                        String employeeName = scan.nextLine();
                        Employee foundEmployee = (Employee) Person.searchByName(Employee.employeeList, employeeName);
                        if (foundEmployee == null) {
                            System.out.println("Employee not found!");
                        } else {
                            System.out.println(foundEmployee);
                        }
                        scan.nextLine();
                        break;
                    default:
                        System.out.println("Invalid choice, try again.");
                        scan.nextLine();
                }
            } while (true);
        } else {
            int choice;
            do {
                terminal.clearTerminal();
                System.out.println("\n====== Welcome ======");
                System.out.println("1. Check Clothing Items");
                System.out.println("2. Generate Receipt");
                System.out.println("\n0. Logout\n");
                System.out.print("\n=> Select an option: ");
                choice = scan.nextInt();
    
                switch (choice) {
                    case 0:
                        terminal.clearTerminal();
                        System.out.println("Logging out...");
                        return;
                    case 1:
                        terminal.clearTerminal();
                        System.out.println("Displaying Available Clothing Items...");
                        scan.nextLine();
                        break;
                    case 2:
                        System.out.println("Generating Receipt...");
                        scan.nextLine();
                        break;
                    default:
                        System.out.println("Invalid choice, try again.");
                        scan.nextLine();
                }
            } while (true);
        }
    }
}