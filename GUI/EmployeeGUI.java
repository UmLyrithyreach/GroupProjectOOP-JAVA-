package GUI;

import Class.DatabaseConnection;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


class EmployeeGUI {
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField nameField, ageField, genderField, phoneField, emailField, addressField,
                       salaryField, startDateField, roleField;

    public EmployeeGUI() {
        frame = new JFrame("Employee's Operations");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("Poppins", Font.PLAIN, 20));

        String [] column = {"id", "name", "age", "gender", "phoneNumber", "email", "address", "salary", "startDate", "role", "password", "isManager", "username"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        JTable table = new JTable(model);
        // Set Table Row's height
        table.setRowHeight(30);
        // Set Width of Each Columns
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(25);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(140);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(60);
        table.getColumnModel().getColumn(8).setPreferredWidth(60);
        table.getColumnModel().getColumn(9).setPreferredWidth(150);
        table.getColumnModel().getColumn(10).setPreferredWidth(90);
        table.getColumnModel().getColumn(11).setPreferredWidth(10);
        table.getColumnModel().getColumn(12).setPreferredWidth(120);

        String query = "SELECT id, name, age, gender, phoneNumber, email, address, salary, startDate, role, password, isManager, username FROM employees";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query);
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (!rs.next()) {
                displayArea.setText("No Employees Found!");
                return;
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

                model.addRow(new Object[]{id, name, age, gender, phoneNumber, email, address, salary, startDate, role, password, isManager, username});
                      
            } while (rs.next());
            
        } catch (SQLException e) {
            displayArea.setText("Error while displaying all cashiers");
            e.printStackTrace();
        }

        JLabel exit = new JLabel("Home");
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exit.setFont(new Font("Poppins", Font.PLAIN, 18));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        JPanel topPanel = new JPanel();
        topPanel.add(exit);
        frame.add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4, 6));
        JButton removeEmployeeBtn = new JButton("Remove Employee");
        removeEmployeeBtn.addActionListener(e -> removeEmployee(table, model));
        bottomPanel.add(removeEmployeeBtn);

        JButton updateNameBtn = new JButton("Update Name");
        updateNameBtn.addActionListener(e -> updateName(table, model));
        bottomPanel.add(updateNameBtn);

        JButton updateAgeBtn = new JButton("Update Age");
        updateAgeBtn.addActionListener(e -> updateAge(table, model));
        bottomPanel.add(updateAgeBtn);

        JButton updateGenderBtn = new JButton("Update Gender");
        updateGenderBtn.addActionListener(e -> updateGender(table, model));
        bottomPanel.add(updateGenderBtn);
        
        JButton updatePhoneNumberBtn = new JButton("Update Phone Number");
        updatePhoneNumberBtn.addActionListener(e -> updatePhoneNumber(table, model));
        bottomPanel.add(updatePhoneNumberBtn);
        
        JButton updateEmailBtn = new JButton("Update Email");
        updateEmailBtn.addActionListener(e -> updateEmail(table, model));
        bottomPanel.add(updateEmailBtn);
        
        JButton updateAddressBtn = new JButton("Update Gender");
        updateAddressBtn.addActionListener(e -> updateAddress(table, model));
        bottomPanel.add(updateAddressBtn);
        
        JButton updateStartDateBtn = new JButton("Update Start-Date");
        updateStartDateBtn.addActionListener(e -> updateStartDate(table, model));
        bottomPanel.add(updateStartDateBtn);
        
        JButton updateRole = new JButton("Update Role");
        updateRole.addActionListener(e -> updateRole(table, model));
        bottomPanel.add(updateRole);

        JButton updateUsername = new JButton("Update Username");
        updateUsername.addActionListener(e -> updateUsername(table, model));
        bottomPanel.add(updateUsername);

        JButton updatePassword = new JButton("Update Password");
        updatePassword.addActionListener(e -> updatePassword(table, model));
        bottomPanel.add(updatePassword);
        
        JButton updateManager = new JButton("Update Manager Status");
        updateManager.addActionListener(e -> updateManager(table, model));
        bottomPanel.add(updateManager);

        JButton addNewEmployeeBtn = new JButton("Add New Employee");
        addNewEmployeeBtn.addActionListener(e -> addNewEmployee(table, model));
        bottomPanel.add(addNewEmployeeBtn);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    private void updateName(JTable table, DefaultTableModel model) {
        String columnName = "name";
        int columnNumber = 1;
        updateString(table, model, columnName, columnNumber);
    }

    private void updateAge(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String inputAge = JOptionPane.showInputDialog("Enter new age: ");
            if (inputAge != null && !inputAge.isEmpty()) {
                int age = Integer.parseInt(inputAge);
                String query = "UPDATE employees SET age = ? WHERE id = ?";

                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, age, id);
                if (rowsAffected > 0) {
                    model.setValueAt(age, selectedRow, 2);
                    JOptionPane.showMessageDialog(null, "Age updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating new age!");
                }
            }
        }
    }

    private void updateGender(JTable table, DefaultTableModel model) {
        String columnName = "gender";
        int columnNumber = 3;
        updateString(table, model, columnName, columnNumber);
    }
    
    private void updatePhoneNumber(JTable table, DefaultTableModel model) {
        String columnName = "phoneNumber";
        int columnNumber = 4;
        updateString(table, model, columnName, columnNumber);
    }
    
    private void updateEmail(JTable table, DefaultTableModel model) {
        String columnName = "email";
        int columnNumber = 5;
        updateString(table, model, columnName, columnNumber);
    }
    
    private void updateAddress(JTable table, DefaultTableModel model) {
        String columnName = "address";
        int columnNumber = 6;
        updateString(table, model, columnName, columnNumber);
    }
    
    private void updateSalary(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String newSalary = JOptionPane.showInputDialog("Enter new email: ");
            if (newSalary != null && !newSalary.isEmpty()) {
                Double salary = Double.parseDouble(newSalary);
                String query = "UPDATE employees SET salary = ? WHERE id = ?";

                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, salary, id);
                if (rowsAffected > 0) {
                    model.setValueAt(salary, selectedRow, 1);
                    JOptionPane.showMessageDialog(null, "Salary updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating new salary!");
                }
            }
        }
    }
    
    private void updateStartDate(JTable table, DefaultTableModel model) {
        String columnName = "startDate";
        int columnNumber = 8;
        updateString(table, model, columnName, columnNumber);
    }
    
    private void updateRole(JTable table, DefaultTableModel model) {
        String columnName = "role";
        int columnNumber = 9;
        updateString(table, model, columnName, columnNumber);
    }
    
    private void updateUsername(JTable table, DefaultTableModel model) {
        String columnName = "username";
        int columnNumber = 12;
        updateString(table, model, columnName, columnNumber);
    }

    private void updatePassword(JTable table, DefaultTableModel model) {
        String columnName = "password";
        int columnNumber = 10;
        updateString(table, model, columnName, columnNumber);
    }

    private void updateString(JTable table, DefaultTableModel model, String columnName, int columnNumber) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String newAddress = JOptionPane.showInputDialog("Enter new " + columnName + ": ");
            if (newAddress != null && !newAddress.isEmpty()) {
                String query = "UPDATE employees SET " + columnName + " = ? WHERE id = ?";

                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, newAddress, id);
                if (rowsAffected > 0) {
                    model.setValueAt(newAddress, selectedRow, columnNumber);
                    JOptionPane.showMessageDialog(null,  columnName + " updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating new " + columnName + "!");
                }
            }
        }
    }

    private void updateManager(JTable table, DefaultTableModel model) {
        int selecedRow = table.getSelectedRow();
    }

    private void removeEmployee(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int employeeId = (int) model.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_NO_OPTION) {
                String query = "DELETE FROM employees Where id = ?";
                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, employeeId);
                if (rowsAffected > 0) {
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Employees deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete employee");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to remove.");
        }
    }

    private void searchEmployeeByID() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            displayArea.setText("ID is required");
            return;
        }

        // String query to search for employee by ID
        String query = "SELECT * FROM employees WHERE id = ?";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, id);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs == null || !rs.next()) {
                System.out.println("Employee with id '" + id + "' not found!");
                return;
            } 

            // Fetch data if found
            StringBuilder result = new StringBuilder();

            String name = rs.getString("name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String phoneNumber = rs.getString("phoneNumber");
            String email = rs.getString("email");
            String address = rs.getString("address");
            BigDecimal salary = rs.getBigDecimal("salary");
            String startDate = rs.getString("startDate");
            String role = rs.getString("role");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int isManager = rs.getInt("id");

            // Build result to display
            result.append("ID: ").append(id)
            .append("Name: ").append(name)
            .append("Age: ").append(age)
            .append("Gender: ").append(gender)
            .append("Phone Number: ").append(phoneNumber)
            .append("Email: ").append(email)
            .append("Address: ").append(address)
            .append("Salary: ").append(salary)
            .append("Start Date: ").append(startDate)
            .append("Role: ").append(role)
            .append("Username: ").append(username)
            .append("Password: ").append(password)
            .append("Manager Status: ").append((isManager == 1)  ? "True" : "False" );
            
            displayArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchEmployeeByName() {
        String name = nameField.getText().trim();

        // String query to search for employees by name
        String query = "SELECT * FROM employees WHERE name LIKE ?";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, "%" + name + "%");
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs == null || !rs.next()) {
                displayArea.setText("Employee with '" + name + "' not found!");
                return;
            } 

            StringBuilder result = new StringBuilder();

            do {

                // Fetch data if found
                int id = rs.getInt("id");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String address = rs.getString("address");
                BigDecimal salary = rs.getBigDecimal("salary");
                String startDate = rs.getString("startDate");
                String role = rs.getString("role");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int isManager = rs.getInt("id");

                // Build result to display
                result.append("ID: ").append(id)
                .append("Name: ").append(name)
                .append("Age: ").append(age)
                .append("Gender: ").append(gender)
                .append("Phone Number: ").append(phoneNumber)
                .append("Email: ").append(email)
                .append("Address: ").append(address)
                .append("Salary: ").append(salary)
                .append("Start Date: ").append(startDate)
                .append("Role: ").append(role)
                .append("Username: ").append(username)
                .append("Password: ").append(password)
                .append("Manager Status: ").append((isManager == 1)  ? "True" : "False" );
                
            } while(rs.next());

            displayArea.setText(result.toString());
                
        } catch (SQLException e) {
            displayArea.setText("Error: " + e.getMessage());
        }
    }

    private void addNewEmployee(JTable table, DefaultTableModel model) {

        JFrame frame = new JFrame("Employee Operations");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);

        frame.setFont(new Font("Poppins", Font.PLAIN, 20));

        // Label and text field
        JPanel inputPanel = new JPanel(new GridLayout(11, 2));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);
        
        inputPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        inputPanel.add(genderField);

        inputPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);
        
        inputPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        inputPanel.add(addressField);
        
        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        inputPanel.add(salaryField);
        
        inputPanel.add(new JLabel("Start Date:"));
        startDateField = new JTextField();
        inputPanel.add(startDateField);
        
        inputPanel.add(new JLabel("Role:"));
        roleField = new JTextField();
        inputPanel.add(roleField);

        frame.add(inputPanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        JButton addEmployeeBtn = new JButton("Add");
        addEmployeeBtn.addActionListener(e -> addEmployee(table, model));
        centerPanel.add(addEmployeeBtn);

        frame.add(centerPanel, BorderLayout.SOUTH);

        JLabel exit = new JLabel("Back");
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exit.setFont(new Font("Poppins", Font.PLAIN, 18));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        JPanel topPanel = new JPanel();
        topPanel.add(exit);
        frame.add(topPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private void addEmployee(JTable table, DefaultTableModel model) {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        double salary = Double.parseDouble(salaryField.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(startDateField.getText(), formatter);
        Date sqlDate = Date.valueOf(date);

        String role = roleField.getText();

        if (name.isEmpty() || gender.isEmpty() || phone.isEmpty() ||
            email.isEmpty() || address.isEmpty() || role.isEmpty()) {
                
            JOptionPane.showMessageDialog(null, "Please input all the field except ID. Password, Manager Status, Username is optional.");   
            return; 
        }

        // String query to add new employee
        String query = "INSERT INTO employees (name, age, gender, phoneNumber, email, address, salary, startDate, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, age, gender, phone, email, address, salary, sqlDate, role);
        if (rowsAffected > 0) {
            model.addRow(new Object[]{4, name, age, gender, phone, email, address, salary, date, role, "", 0, ""});
            JOptionPane.showMessageDialog(null, "Employee added successfully.");
            nameField.setText("");
            ageField.setText("");
            genderField.setText("");
            phoneField.setText("");
            emailField.setText("");
            addressField.setText("");
            salaryField.setText("");
            startDateField.setText("");
            roleField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add employee.");
        }
    }
}