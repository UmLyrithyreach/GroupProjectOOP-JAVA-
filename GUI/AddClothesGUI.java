package GUI;

import javax.swing.*;

import Class.DatabaseConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClothesGUI extends JFrame {
    private JTextField nameField, brandField, sizeField, priceField, stockField, styleField, supplierIdField;
    private JButton addButton, backButton;

    public AddClothesGUI() {
        setTitle("Add New Clothes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fields
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Brand:"));
        brandField = new JTextField();
        panel.add(brandField);

        panel.add(new JLabel("Size:"));
        sizeField = new JTextField();
        panel.add(sizeField);

        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        panel.add(stockField);

        panel.add(new JLabel("Style:"));
        styleField = new JTextField();
        panel.add(styleField);

        panel.add(new JLabel("Supplier ID:"));
        supplierIdField = new JTextField();
        panel.add(supplierIdField);

        // Buttons
        addButton = createButton("Add Clothes");
        backButton = createButton("Back");

        panel.add(addButton);
        panel.add(backButton);

        // Action Listeners
        addButton.addActionListener(e -> addClothes());
        backButton.addActionListener(e -> {
            dispose();
            new StaffGUI("Staff").setVisible(true); // Go back to the staff GUI
        });

        add(panel);
    }

    private void addClothes() {
        try {
            String name = nameField.getText();
            String brand = brandField.getText();
            String size = sizeField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            String style = styleField.getText();
            int supplierId = Integer.parseInt(supplierIdField.getText());

            String query = "INSERT INTO clothes (name, brand, size, price, stock, style, supplierId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, brand, size, price, stock, style, supplierId);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Clothes added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add clothes.");
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