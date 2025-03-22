package GUI;

import javax.swing.*;

import Class.Clothes;
import Class.Employee;
import Class.Manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerGUI extends JFrame {
    public ManagerGUI(String name) {
        setTitle("Manager Operations");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Welcome Message
        JLabel welcomeLabel = new JLabel("Welcome, " + name + " (Manager)");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(welcomeLabel);

        // Buttons
        JButton viewEmployeesButton = createButton("View All Employees");
        JButton updateEmployeeButton = createButton("Update Employee Details");
        JButton viewClothesButton = createButton("View All Clothes");
        JButton addClothesButton = createButton("Add New Clothes");
        JButton purchaseButton = createButton("Make a Purchase");
        JButton logoutButton = createButton("Logout");

        panel.add(viewEmployeesButton);
        panel.add(updateEmployeeButton);
        panel.add(viewClothesButton);
        panel.add(addClothesButton);
        panel.add(purchaseButton);
        panel.add(logoutButton);

        // Action Listeners
        viewEmployeesButton.addActionListener(e -> Manager.viewAllEmployees());
        updateEmployeeButton.addActionListener(e -> new UpdateEmployeeGUI().setVisible(true));
        viewClothesButton.addActionListener(e -> Clothes.displayClothes());
        addClothesButton.addActionListener(e -> new AddClothesGUI().setVisible(true));
        purchaseButton.addActionListener(e -> Employee.purchase(new Employee(0))); // Act as cashier
        logoutButton.addActionListener(e -> {
            new LoginGUI().setVisible(true);
            dispose();
        });

        add(panel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}