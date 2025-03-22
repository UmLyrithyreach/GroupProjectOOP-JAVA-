package GUI;

import javax.swing.*;

import Class.Clothes;
import Class.Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffGUI extends JFrame {
    public StaffGUI(String name) {
        setTitle("Staff Operations");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Welcome Message
        JLabel welcomeLabel = new JLabel("Welcome, " + name + " (Staff)");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(welcomeLabel);

        // Buttons
        JButton viewClothesButton = createButton("View All Clothes");
        JButton searchClothesButton = createButton("Search Clothes");
        JButton addClothesButton = createButton("Add New Clothes");
        JButton purchaseButton = createButton("Make a Purchase");
        JButton logoutButton = createButton("Logout");

        panel.add(viewClothesButton);
        panel.add(searchClothesButton);
        panel.add(addClothesButton);
        panel.add(purchaseButton);
        panel.add(logoutButton);

        // Action Listeners
        viewClothesButton.addActionListener(e -> Clothes.displayClothes());
        searchClothesButton.addActionListener(e -> Clothes.searchByName());
        addClothesButton.addActionListener(e -> new AddClothesGUI().setVisible(true));
        purchaseButton.addActionListener(e -> Employee.purchase(new Employee(0)));
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