package GUI;

import javax.swing.*;

import Class.DatabaseConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployeeGUI extends JFrame {
    private JTextField idField, nameField, usernameField, ageField, phoneField, emailField, addressField, salaryField, startDateField, roleField, passwordField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JCheckBox isManagerCheckBox;
    private JButton updateButton, backButton;

    public UpdateEmployeeGUI() {
        setTitle("Update Employee Details");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(14, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fields
        panel.add(new JLabel("Employee ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        // Gender Radio Buttons
        panel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        panel.add(genderPanel);

        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Address:"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        panel.add(salaryField);

        panel.add(new JLabel("Start Date:"));
        startDateField = new JTextField();
        panel.add(startDateField);

        panel.add(new JLabel("Role:"));
        roleField = new JTextField();
        panel.add(roleField);

        panel.add(new JLabel("Password:"));
        passwordField = new JTextField();
        panel.add(passwordField);

        panel.add(new JLabel("Is Manager:"));
        isManagerCheckBox = new JCheckBox();
        panel.add(isManagerCheckBox);

        // Buttons
        updateButton = createButton("Update Employee");
        backButton = createButton("Back");

        panel.add(updateButton);
        panel.add(backButton);

        // Action Listeners
        updateButton.addActionListener(e -> updateEmployee());
        backButton.addActionListener(e -> {
            dispose();
            new ManagerGUI("Manager").setVisible(true);
        });

        add(panel);
    }

    private void updateEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String username = usernameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = maleRadioButton.isSelected() ? "Male" : "Female"; // Get selected gender
            String phone = phoneField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            String startDate = startDateField.getText();
            String role = roleField.getText();
            String password = passwordField.getText();
            boolean isManager = isManagerCheckBox.isSelected();

            String query = "UPDATE employees SET name = ?, username = ?, age = ?, gender = ?, phoneNumber = ?, email = ?, address = ?, salary = ?, startDate = ?, role = ?, password = ?, isManager = ? WHERE id = ?";
            int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, username, age, gender, phone, email, address, salary, startDate, role, password, isManager ? 1 : 0, id);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update employee.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}