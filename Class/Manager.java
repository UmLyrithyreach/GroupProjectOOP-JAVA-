package Class;

import java.util.Scanner;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends Employee {

    public Manager(int id, String name, String gender, int age, String phone, String email, String address, 
                  double salary, String startDate, String role, String password, int isManager, String username) {
        super(id, name, gender, age, phone, email, address, salary, startDate, role, username, password, isManager);
    }

    // View all Cashiers
    public static void viewAllEmployees() {
        // String query to select all cashiers
        String query = "SELECT * FROM cashiers";

        // Execute the query
        try (ResultSet rs = DatabaseConnection.executeQuery(query)) {

            if (!rs.next()) {
                System.out.println("No Cashiers Found!");
            }

            // Fetch data
            do {

                int id = rs.getInt("id");
                String gender = rs.getString("gender");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String address = rs.getString("address");
                BigDecimal salary = rs.getBigDecimal("salary");
                String startDate = rs.getString("startDate");
                String role = rs.getString("role");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int isManager = rs.getInt("isManager");
                
                System.out.println(Employee.toString(id, name, age, gender, phoneNumber, email, address, salary, startDate, role, password, isManager, username));

            } while (rs.next());

        } catch (SQLException e) {
            System.out.println("Error while displaying all cashiers");
            e.printStackTrace();
        }
    }

    // Update Cashier
    public static void updateCashier() {
        Scanner scan = new Scanner(System.in);
        Terminal terminal = new TerminalSystem();

        System.out.print("========================================\nEnter Cashier ID to Update: ");
        int employeeId = terminal.getValidIntegerInput(scan);
        
        // String query to search for employee by ID
        String query = "SELECT * FROM employees WHERE id = ?";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, employeeId);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs == null || !rs.next()) {
                System.out.println("No employee found with ID '" + employeeId + "'.");
            }

            int choice;
            do {
                terminal.clearTerminal();
                System.out.println("========= Update Cashier Info =========");
                System.out.println("1. Name");
                System.out.println("2. Username");
                System.out.println("3. Age");
                System.out.println("4. Gender");
                System.out.println("5. Phone");
                System.out.println("6. Email");
                System.out.println("7. Address");
                System.out.println("8. Salary");
                System.out.println("9. Start Date");
                System.out.println("10. Role");
                System.out.println("11. Password");
                System.out.println("12. Manager Status");
                System.out.println("========================================\n0. Cancel\n========================================");
                System.out.print("=> Select an option: ");
                choice = terminal.getValidIntegerInput(scan);
                switch (choice) {
                    case 0:
                        terminal.clearTerminal();
                        System.out.println("========================================\nExiting...\n========================================");
                        break;
                    case 1:
                        terminal.clearTerminal();
                        System.out.print("Enter new name: ");
                        String newName = scan.nextLine();
                        // cashier.setName(newName);
                        System.out.println("Name updated successfully.");
                        scan.nextLine();
                        break;
                    case 2:
                        terminal.clearTerminal();
                        System.out.print("Enter new username: ");
                        String newUsername = scan.nextLine();
                        // cashier.setName(newUsername);
                        System.out.println("Username updated successfully.");
                        scan.nextLine();
                        break;
                    case 3:
                        terminal.clearTerminal();
                        System.out.println("Enter new age: ");
                        int newAge = scan.nextInt();
                        // cashier.setAge(newAge);
                        System.out.println("Age updated successfully.");
                        scan.nextLine();
                        break;
                    case 4:
                        terminal.clearTerminal();
                        System.out.print("Enter new gender: ");
                        String newGender = scan.nextLine();
                        // cashier.setGender(newGender);
                        System.out.println("Gender updated successfully.");
                        scan.nextLine();
                        break;
                    case 5: 
                        terminal.clearTerminal();
                        System.out.print("Enter new phone: ");
                        String newPhone = scan.nextLine();
                        // cashier.setPhoneNumber(newPhone);
                        System.out.println("Phone number updated successfully.");
                        scan.nextLine();
                        break;
                    case 6:
                        terminal.clearTerminal();
                        System.out.print("Enter new email: ");
                        String newEmail = scan.nextLine();
                        // cashier.setEmail(newEmail);
                        System.out.println("Email updated successfully.");
                        scan.nextLine();
                        break;
                    case 7:
                        terminal.clearTerminal();
                        System.out.print("Enter new address: ");
                        String newAddress = scan.nextLine();
                        // cashier.setAddress(newAddress);
                        System.out.println("Address updated successfully.");
                        break;
                    case 8:
                        terminal.clearTerminal();
                        System.out.print("Enter new salary: ");
                        double newSalary = scan.nextDouble();
                        // cashier.setSalary(newSalary);
                        System.out.println("Salary updated successfully.");
                        break;
                    case 9:
                        terminal.clearTerminal();
                        System.out.print("Enter new start date: ");
                        String newStartDate = scan.nextLine();
                        // cashier.setStartDate(newStartDate);
                        System.out.println("Start date updated successfully.");
                        scan.nextLine();
                        break;
                    case 10:
                        terminal.clearTerminal();
                        System.out.print("Enter new role: ");
                        String newRole = scan.nextLine();
                        // cashier.setRole(newRole);
                        System.out.println("Role updated successfully.");
                        scan.nextLine();
                        break;
                    case 11:
                        terminal.clearTerminal();
                        System.out.print("Enter new password: ");
                        String newPassword = scan.nextLine();
                        // cashier.setPassword(newPassword);
                        System.out.println("Password updated successfully.");
                        scan.nextLine();
                        break;
                    case 12:
                        terminal.clearTerminal();
                        System.out.print("Enter new admin status (true/false): ");
                        boolean newAdminStatus = scan.nextBoolean();
                        // cashier.setIsAdmin(newAdminStatus);
                        System.out.println("Admin status updated successfully.");
                        scan.nextLine();
                        break;
                    default:
                        terminal.clearTerminal();
                        System.out.println("========================================\nPress Enter to try again...\n========================================");
                        scan.nextLine(); // Wait for user to press Enter
                }
            } while(choice != 0);
            
        } catch (SQLException e) {
            System.out.println("Error while searching for employee");
            e.printStackTrace();
        }
                
    }
}