package Class;

import java.util.Scanner;
import java.util.function.Supplier;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class App {
    public static void main(String[] args) throws LoginException {
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

        Shop.addClothes(new Clothes(1, "T-Shirt", "Nike", "M", 50.0, 10, "Cotton", "Casual", "Regular", "None"));
        Shop.addClothes(new Clothes(2, "Jeans", "Levis", "M", 100.0, 5, "Denim", "Casual", "Regular", "None"));
        Shop.addClothes(new Clothes(3, "Dress Shirt", "H&M", "M", 80.0, 7, "Cotton", "Formal", "Regular", "None"));
        Shop.addClothes(new Clothes(4, "Sweater", "Uniqlo", "M", 70.0, 3, "Wool", "Casual", "Regular", "None"));
        Shop.addClothes(new Clothes(5, "Shorts", "Adidas", "M", 40.0, 8, "Polyester", "Casual", "Regular", "None"));
        Shop.addClothes(new Clothes(6, "Skirt", "Zara", "F", 60.0, 6, "Cotton", "Casual", "Regular", "None"));
        Shop.addClothes(new Clothes(7, "Blouse", "Forever 21", "F", 45.0, 4, "Silk", "Casual", "Regular", "None"));
        Shop.addClothes(new Clothes(8, "Dress", "Zara", "F", 90.0, 2, "Polyester", "Formal", "Regular", "None"));
        Shop.addClothes(new Clothes(9, "Pants", "Patagonia", "F", 85.0, 12, "Cotton", "Casual", "Regular", "None"));
        Shop.addClothes(new Clothes(10, "Jacket", "North Face", "F", 120.0, 9, "Polyester", "Casual", "Regular", "None"));

        new ClothingSupplier("Nike", "123 Nike St", "0123456789");
        new ClothingSupplier("Levis", "456 Levis St", "0987654321");
        new ClothingSupplier("H&M", "789 H&M St", "0876543210");
        new ClothingSupplier("Uniqlo", "123 Uniqlo St", "0123456789");
        new ClothingSupplier("Adidas", "456 Adidas St", "0987654321");
        new ClothingSupplier("Zara", "789 Zara St", "0876543210");
        new ClothingSupplier("Forever 21", "123 Forever 21 St", "0123456789");
        new ClothingSupplier("Patagonia", "456 Patagonia St", "0987654321");
        new ClothingSupplier("North Face", "789 North Face St", "0876543210");

        Employee loggedInUser = Employee.login(scan);

        if (loggedInUser.isManager()) {
            int choice;
            do {
                terminal.clearTerminal();
                System.out.println("\n====== Manager Operator ======");
                System.out.println("1. View All Employees");
                System.out.println("2. Update Employee's Details");
                System.out.println("3. Search Employee");
                System.out.println("4. View All Clothes");
                System.out.println("5. Search clothes by name");
                System.out.println("6. Search clothes by brand");
                System.out.println("7. Search clothes by ID");
                System.out.println("8. Add Clothes");
                System.out.println("9. Remove Clothes");
                System.out.println("10. View All Suppliers");
                System.out.println("11. Add Supplier");
                System.out.println("12. Remove Supplier By Name");
                System.out.println("13. Remove Supplier By ID");
                System.out.println("==============================");
                System.out.println("0. Logout");
                System.out.println("==============================");
                System.out.print("=> Select an option: ");
                choice = terminal.getValidIntegerInput(scan);

                switch (choice) {
                    case 0:
                        System.out.println("\n==============================");
                        System.out.println("Logging out...");
                        System.out.println("==============================");
                        terminal.sleeping();
                        return;
                    case 1:
                        terminal.clearTerminal();
                        Manager.viewAllEmployees();
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 2:
                        terminal.clearTerminal();
                        System.out.print("========================================\nEnter Employee ID to Update: ");
                        int employeeId = terminal.getValidIntegerInput(scan);
                        System.out.print("========================================\nPress <Enter> to continue...");
                        scan.nextLine();
                        Manager.updateEmployee(employeeId);
                        break;
                    case 3:
                        terminal.clearTerminal();
                        System.out.print("Enter employee name: ");
                        String employeeName = scan.nextLine();
                        Employee foundEmployee = (Employee) Person.searchByName(Employee.employeeList, employeeName);
                        if (foundEmployee == null) {
                            System.out.println("==============================\nEmployee not found!\n==============================");
                        } else {
                            System.out.println(foundEmployee);
                        }
                        scan.nextLine();
                        break;
                    case 4:
                        terminal.clearTerminal();
                        Shop.displayClothes();
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 5:
                        terminal.clearTerminal();
                        System.out.print("Enter clothes name: ");
                        String clothesName = scan.nextLine();
                        Clothes foundClothes = Shop.searchByName(clothesName);
                        if (foundClothes == null) {
                            System.out.println("Clothes not found!");
                        } else {
                            System.out.println(foundClothes);
                        }
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 6:
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
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 7:
                        terminal.clearTerminal();
                        System.out.print("Enter clothes ID: ");
                        int clothesID = Integer.parseInt(scan.nextLine());
                        Clothes foundClothesID = Shop.searchById(clothesID);
                        if (foundClothesID == null) {
                            System.out.println("Clothes not found!");
                        } else {
                            System.out.println(foundClothesID);
                        }
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 8:
                        terminal.clearTerminal();
                        System.out.print("Enter clothes name: ");
                        String name = scan.nextLine();
                        System.out.print("Enter brand: ");
                        String brand = scan.nextLine();
                        System.out.print("Enter size: ");
                        String size = scan.nextLine();
                        System.out.print("Enter price: ");
                        double price = Double.parseDouble(scan.nextLine());
                        System.out.print("Enter stock: ");
                        int stock = Integer.parseInt(scan.nextLine());
                        System.out.print("Enter material: ");
                        String material = scan.nextLine();
                        System.out.print("Enter type: ");
                        String type = scan.nextLine();
                        System.out.print("Enter fit: ");
                        String fit = scan.nextLine();
                        System.out.print("Enter design: ");
                        String design = scan.nextLine();
                        Shop.addClothes(new Clothes(Shop.totalClothes() + 1, name, brand, size, price, stock, material, type, fit, design));
                        System.out.println("Clothes added successfully.");
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 9:
                        terminal.clearTerminal();
                        System.out.print("Enter clothes ID to remove: ");
                        int removeClothesID = Integer.parseInt(scan.nextLine());
                        Shop.removeClothesById(removeClothesID);
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 10:
                        terminal.clearTerminal();
                        ClothingSupplier.displaySuppliers();
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 11:
                        terminal.clearTerminal();
                        System.out.print("Enter supplier name: ");
                        String supplierName = scan.nextLine();
                        System.out.print("Enter supplier address: ");
                        String supplierAddress = scan.nextLine();
                        System.out.print("Enter supplier contact: ");
                        String supplierContact = scan.nextLine();
                        ClothingSupplier.addSupplier(new ClothingSupplier(supplierName, supplierAddress, supplierContact));
                        System.out.println("Supplier added successfully.");
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 12:
                        terminal.clearTerminal();
                        System.out.print("Enter supplier name to remove: ");
                        String removeSupplierName = scan.nextLine();
                        ClothingSupplier.removeSupplier(removeSupplierName);
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 13:
                        terminal.clearTerminal();
                        System.out.print("Enter supplier ID to remove: ");
                        int removeSupplierID = Integer.parseInt(scan.nextLine());
                        ClothingSupplier.removeSupplier(removeSupplierID);
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    default:
                        System.out.println("==============================\nInvalid choice, try again.\n==============================");
                        scan.nextLine();
                }
            } while (true);
        } else {
            do {
                terminal.clearTerminal();
                System.out.println("\n========= Welcome ============");
                System.out.println("1. Check Stock");
                System.out.println("2. Purchase");
                System.out.println("3. Search clothes by name");
                System.out.println("4. Search clothes by brand");
                System.out.println("5. Search clothes by ID");
                System.out.println("==============================");
                System.out.println("0. Logout");
                System.out.println("==============================");
                System.out.print("\n=> Select an option: ");
                int choice = Integer.valueOf(scan.nextLine());
                switch (choice) {
                    case 0:
                        terminal.clearTerminal();
                        System.out.println("\n==============================\nLogging out...\n==============================");
                        return;
                    case 1:
                        terminal.clearTerminal();
                        Shop.displayClothes();
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break;
                    case 2:
                        terminal.clearTerminal();
                        Staff.purchase(loggedInUser);
                        System.out.println("Press <Enter> to continue...");
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
                        System.out.println("Press <Enter> to continue...");
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
                        System.out.println("Press <Enter> to continue...");
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
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                        break; 
                    default:
                        System.out.println("==============================\nInvalid choice, try again.\n==============================");
                        System.out.println("Press <Enter> to continue...");
                        scan.nextLine();
                }
            } while (true);
        }
    }
}