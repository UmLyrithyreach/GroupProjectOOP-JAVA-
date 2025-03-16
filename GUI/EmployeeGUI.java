package GUI;

import javax.swing.*;

import Class.DatabaseConnection;
import Class.Employee;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


class EmployeeGUI extends JFrame {
    private JTextArea displayArea;
    private JTextField nameField, ageField, genderField, phoneField, emailField, addressField,
                       idField, salaryField, startDateField, roleField, passwordField, isManagerField, usernameField;

    public EmployeeGUI() {
        setTitle("Employee Operations");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Label and text field
        JPanel inputPanel = new JPanel(new GridLayout(14, 2));

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
        
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        
        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        inputPanel.add(salaryField);
        
        inputPanel.add(new JLabel("Start Date:"));
        startDateField = new JTextField();
        inputPanel.add(startDateField);
        
        inputPanel.add(new JLabel("Role:"));
        roleField = new JTextField();
        inputPanel.add(roleField);
        
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JTextField();
        inputPanel.add(passwordField);
        
        inputPanel.add(new JLabel("Manager Status:"));
        isManagerField = new JTextField();
        inputPanel.add(isManagerField);
        
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));

        JButton searchEmployeeByIdBtn = new JButton("Search Employee By ID");
        JButton searchEmployeeByNameBtn = new JButton("Search Employee By Name");
        JButton addNewEmployeeBtn = new JButton("Add New Employee");
        JButton updateEmployeeBtn = new JButton("Update Employee's Info");
        JButton viewAllEmployeesBtn = new JButton("View All Employees");

        buttonPanel.add(searchEmployeeByIdBtn);
        buttonPanel.add(searchEmployeeByNameBtn);
        buttonPanel.add(addNewEmployeeBtn);
        buttonPanel.add(updateEmployeeBtn);
        buttonPanel.add(viewAllEmployeesBtn);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        searchEmployeeByIdBtn.addActionListener(e -> searchEmployeeByID());
        searchEmployeeByNameBtn.addActionListener(e -> searchEmployeeByName());
        addNewEmployeeBtn.addActionListener(e -> addNewEmployee());
        updateEmployeeBtn.addActionListener(e -> updateEmployee());
        viewAllEmployeesBtn.addActionListener(e -> viewAllEmployees());

        add(panel);
    }

    private void viewAllEmployees() {
        // String query to select all cashiers
        String query = "SELECT * FROM employees";

        // Execute the query
        try (ResultSet rs = DatabaseConnection.executeQuery(query)) {

            if (!rs.next()) {
                displayArea.setText("No Employees Found!");
                return;
            }

            // Fetch data
            StringBuilder result = new StringBuilder();
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
                
            } while (rs.next());

            displayArea.setText(result.toString());

        } catch (SQLException e) {
            displayArea.setText("Error while displaying all cashiers");
            e.printStackTrace();
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

    private void addNewEmployee() {
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String gender = genderField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String address = addressField.getText().trim();
        String salary = salaryField.getText().trim();
        String startDate = startDateField.getText().trim();
        String role = roleField.getText().trim();
        String password = passwordField.getText().trim();
        String isManager = isManagerField.getText().trim();
        String username = usernameField.getText().trim();

        if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || phone.isEmpty() ||
            email.isEmpty() || address.isEmpty() || salary.isEmpty() || startDate.isEmpty() ||
            role.isEmpty()) {
                
            displayArea.setText("Please input all the field except ID. Password, Manager Status, Username is optional.");   
            return; 
        }

        // String query to add new employee
        String query = "INSERT INTO employees (name, age, gender, phoneNumber, email, address, salary, startDate, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, age, gender, phone, email, address, salary, startDate, role);

        if (rowsAffected > 0) {
            displayArea.setText("Employee added successfully.");
        } else {
            displayArea.setText("Failed to add employee.");
        }
    }

    private void updateEmployee() {
        
    }
}