package Class;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.security.auth.login.LoginException;

public class App {
    public static void main(String[] args) throws LoginException {
        Scanner scan = new Scanner(System.in);
        Terminal terminal = new TerminalSystem();
        terminal.clearTerminal();

        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Database connected successfully.");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // new Employee(2, "PapaN", "Male", 28, "0987654321", "PapaN@shop.com", 
        //             "456 Worker St", 1200, "02/02/2021", "Sales Assistant", "password123", false , "papaN123");

        // new Employee(3, "Kimju", "Female", 25, "0876543210", "jane@shop.com", 
        //             "789 Cashier St", 1500, "03/03/2022", "Cashier", "123", false, "kimju123");
        
        // new Employee(4, "Diddy", "Male", 40, "0876543210", "Diddy@shop.com", 
        //             "789 Cashier St", 420, "03/03/2022", "Miner", "123", false, "diddy123");

        Shop.addClothes(new Clothes("T-Shirt", "Nike", "M", 50.00, 100, "Regular", 1));

        new ClothingSupplier("Nike", "123 Nike St", "0123456789");
        new ClothingSupplier("Levis", "456 Levis St", "0987654321");
        new ClothingSupplier("H&M", "789 H&M St", "0876543210");
        new ClothingSupplier("Uniqlo", "123 Uniqlo St", "0123456789");
        new ClothingSupplier("Adidas", "456 Adidas St", "0987654321");
        new ClothingSupplier("Zara", "789 Zara St", "0876543210");
        new ClothingSupplier("Forever 21", "123 Forever 21 St", "0123456789");
        new ClothingSupplier("Patagonia", "456 Patagonia St", "0987654321");
        new ClothingSupplier("North Face", "789 North Face St", "0876543210");

        //Handle Exception login null

        Cashier loggedInUser = Cashier.login(scan);

        if (loggedInUser.isManager()) {
            int choice;
            do {
                terminal.clearTerminal();
                System.out.println("\n====== Manager Operator ======");
                System.out.println("1. Employee Operations");
                System.out.println("2. Clothes Operations");
                System.out.println("3. Supplier Operations");
                System.out.println("==============================");
                System.out.println("0. Logout");
                System.out.println("==============================");
                System.out.print("=> Select an option: ");
                choice = terminal.getValidIntegerInput(scan);
                
                switch (choice) {
                    case 0:
                        terminal.clearTerminal();
                        break;
                    case 1:
                        int choice01;
                        do {
                            terminal.clearTerminal();
                            System.out.println("====== Employee Operations ======");
                            System.out.println("1. View All Employees");
                            System.out.println("2. Update Employee's Details");
                            System.out.println("3. Search Employee By Name");
                            System.out.println("4. Search Employee By ID");
                            System.out.println("=================================");
                            System.out.println("0. Back");
                            System.out.println("=================================");
                            System.out.print("=> Select an option: ");
                            choice01 = terminal.getValidIntegerInput(scan);
                            switch (choice01) {
                                case 0:
                                    terminal.clearTerminal();
                                    break;
                                case 1:
                                    terminal.clearTerminal();
                                    Manager.viewAllEmployees();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 2:
                                    terminal.clearTerminal();
                                    System.out.print("========================================\nEnter Employee ID to Update: ");
                                    int employeeId = terminal.getValidIntegerInput(scan);
                                    Manager.updateEmployee(employeeId);
                                    System.out.print("\n========================================");
                                    scan.nextLine();
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
                                case 4:
                                    terminal.clearTerminal();
                                    System.out.print("Enter employee ID: ");
                                    int employeeID = Integer.parseInt(scan.nextLine());
                                    Employee foundEmployeeID = Employee.searchByID(employeeID);
                                    if (foundEmployeeID == null) {
                                        System.out.println("Employee not found!");
                                    } else {
                                        System.out.println(foundEmployeeID);
                                    }
                                    scan.nextLine();
                                    break;
                                default:
                                    System.out.println("==============================\nInvalid choice, try again.\n==============================");
                                    scan.nextLine();
                            }
                        } while (choice01 != 0);
                        break;
                    case 2:
                        int choice02;
                        do {
                            terminal.clearTerminal();
                            System.out.println("===== Clothes Operations =====");
                            System.out.println("1. View All Clothes");
                            System.out.println("2. Search clothes by name");
                            System.out.println("3. Search clothes by brand");
                            System.out.println("4. Search clothes by ID");
                            System.out.println("5. Add Clothes");
                            System.out.println("6. Remove Clothes");
                            System.out.println("==============================");
                            System.out.println("0. Back");
                            System.out.println("==============================");
                            System.out.print("=> Select an option: ");
                            choice02 = terminal.getValidIntegerInput(scan);
                            switch (choice02) {
                                case 1:
                                    terminal.clearTerminal();
                                    Clothes.displayClothes();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 2:
                                    terminal.clearTerminal();
                                    Clothes.searchByName();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 3:
                                    terminal.clearTerminal();
                                    Clothes.searchByBrand();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 4:
                                //Exception handling for searching clothes by ID
                                    terminal.clearTerminal();
                                    System.out.print("Enter clothes ID: ");
                                    int clothesID = Integer.parseInt(scan.nextLine());
                                    Clothes foundClothesID = Clothes.searchById(clothesID);
                                    if (foundClothesID == null) {
                                        System.out.println("Clothes not found!");
                                    } else {
                                        System.out.println(foundClothesID);
                                    }
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 5:
                                //Exception handling for adding clothes for Supplier ID
                                    terminal.clearTerminal();
                                    Clothes.addClothes();
                                    System.out.println("Clothes added successfully.");
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 6:
                                    //Exception handling for removing clothes by ID
                                    terminal.clearTerminal();
                                    System.out.print("Enter clothes ID to remove: ");
                                    int removeClothesID = Integer.parseInt(scan.nextLine());
                                    Clothes.removeClothesById(removeClothesID);
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                default:
                                    System.out.println("==============================\nInvalid choice, try again.\n==============================");
                                    scan.nextLine();
                            }
                        } while (choice02 != 0);
                        break;
                    case 3:
                        int choice03;
                        do {
                            terminal.clearTerminal();
                            System.out.println("===== Supplier Operations =====");
                            System.out.println("1. View All Suppliers");
                            System.out.println("2. Add Supplier");
                            System.out.println("3. Remove Supplier By Name");
                            System.out.println("4. Remove Supplier By ID");
                            System.out.println("5. Search Supplier By Name");
                            System.out.println("6. Search Supplier By ID");
                            System.out.println("==============================");
                            System.out.println("0. Back");
                            System.out.println("==============================");
                            System.out.print("=> Select an option: ");
                            choice03 = terminal.getValidIntegerInput(scan);
                            switch (choice03) {
                                case 1:
                                    terminal.clearTerminal();
                                    ClothingSupplier.displayAllSuppliers();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 2:
                                    terminal.clearTerminal();
                                    ClothingSupplier.addSupplierToDatabase();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 3:
                                    terminal.clearTerminal();
                                    ClothingSupplier.removeSupplierByName();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 4:
                                    //Exception handling for removing supplier by ID
                                    terminal.clearTerminal();
                                    System.out.print("Enter supplier ID to remove: ");
                                    int removeSupplierID = Integer.parseInt(scan.nextLine());
                                    ClothingSupplier.removeSupplier(removeSupplierID);
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 5:
                                    terminal.clearTerminal();
                                    ClothingSupplier.searchSupplierByName();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                                case 6:
                                    terminal.clearTerminal();
                                    ClothingSupplier.searchSupplierByID();
                                    System.out.println("\nPress <Enter> to continue...");
                                    scan.nextLine();
                                    break;
                            }
                        } while (choice03 != 0);
                        break;
                    default:
                        System.out.println("==============================\nInvalid choice, try again.\n==============================");
                        scan.nextLine();
                }
            } while (choice != 0);
        } else {
            do {
                terminal.clearTerminal();
                System.out.println("\n========= Welcome ============");
                System.out.println("1. Check Stock");
                System.out.println("2. Walk in Purchase");
                System.out.println("3. Search clothes by name");
                System.out.println("4. Search clothes by brand");
                System.out.println("5. Search clothes by ID");
                System.out.println("6. Check all Order Summary");
                System.out.println("==============================");
                System.out.println("0. Logout");
                System.out.println("==============================");
                System.out.print("\n=> Select an option: ");
                int choice = terminal.getValidIntegerInput(scan);
                switch (choice) {
                    case 0:
                        terminal.clearTerminal();
                        System.out.println("\n==============================\nLogging out...\n==============================");
                        return;
                    case 1:
                        terminal.clearTerminal();
                        Shop.displayClothes();
                        System.out.println("\nPress <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 2:
                        terminal.clearTerminal();
                        Cashier.purchase(loggedInUser);
                        System.out.println("\nPress <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 3:
                        terminal.clearTerminal();
                        System.out.print("Enter clothes name: ");
                        String clothesName = scan.nextLine();
                        Clothes foundClothes = Shop.searchByName(clothesName);
                        if (foundClothes == null) {
                            System.out.println("Clothes not found!");
                        } else {
                            System.out.println(foundClothes);
                        }
                        System.out.println("\nPress <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 4:
                        terminal.clearTerminal();
                        System.out.print("Enter brand name: ");
                        String brandName = scan.nextLine();
                        ArrayList<Clothes> sameBrand = Shop.searchByBrand(brandName);
                        if (sameBrand.isEmpty()) {
                            System.out.println("No clothes found!");
                        } else {
                            for (Clothes clothes : sameBrand) {
                                System.out.println(clothes);
                            }
                        }
                        System.out.println("\nPress <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 5:
                        terminal.clearTerminal();
                        System.out.print("Enter clothes ID: ");
                        int clothesID = Integer.parseInt(scan.nextLine());
                        Clothes foundClothesID = Shop.searchById(clothesID);
                        if (foundClothesID == null) {
                            System.out.println("Clothes not found!");
                        } else {
                            System.out.println(foundClothesID);
                        }
                        System.out.println("\nPress <Enter> to continue...");
                        scan.nextLine();
                        break;
                    // case 6:
                    //     terminal.clearTerminal();
                    //     viewAllOrderSummaries();
                    //     System.out.println("\nPress <Enter> to continue...");
                    //     scan.nextLine();
                    //     break;
                    default:
                        System.out.println("==============================\nInvalid choice, try again.\n==============================");
                        System.out.println("\nPress <Enter> to continue...");
                        scan.nextLine();
                }
            } while (true);
        }
    }
}