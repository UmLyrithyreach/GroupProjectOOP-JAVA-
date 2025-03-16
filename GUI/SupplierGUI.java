package GUI;

import javax.swing.*;

import Class.DatabaseConnection;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class SupplierGUI extends JFrame {
    private JTextArea displayArea;
    private JTextField nameField, addressField, contactField;

    public SupplierGUI() {
        setTitle("Supplier Operations");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        
        // Label and text field
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        inputPanel.add(addressField);

        inputPanel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        inputPanel.add(contactField);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));

        JButton viewSuppliersBtn = new JButton("View All Suppliers");
        JButton addSupplierBtn = new JButton("Add Supplier");
        JButton searchSupplierBtn = new JButton("Search Supplier By Name");
        JButton removeSupplierIdBtn = new JButton("Remove Supplier By ID");
        JButton removeSupplierBtn = new JButton("Remove Supplier");
        JButton searchSupplierIdBtn = new JButton("Search Supplier By ID");

        buttonPanel.add(viewSuppliersBtn);
        buttonPanel.add(addSupplierBtn);
        buttonPanel.add(searchSupplierBtn);
        buttonPanel.add(removeSupplierBtn);
        buttonPanel.add(searchSupplierIdBtn);
        buttonPanel.add(removeSupplierIdBtn);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        viewSuppliersBtn.addActionListener(e -> viewAllSuppliers());
        addSupplierBtn.addActionListener(e -> addSupplier());
        searchSupplierBtn.addActionListener(e -> searchSupplierByName());
        searchSupplierIdBtn.addActionListener(e -> searchSupplierById());
        removeSupplierBtn.addActionListener(e -> removeSupplierByName());
        removeSupplierIdBtn.addActionListener(e -> removeSupplierById());

        add(panel);
    }

    private void viewAllSuppliers() {
        // String query to get all suppliers from the database
        String query = "SELECT * FROM suppliers";

        // Process the result set
        try (ResultSet rs = DatabaseConnection.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("No suppliers found.");
                return;
            }

            StringBuilder result = new StringBuilder();

            do {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String contact = rs.getString("contact");

                result.append("ID: ").append(id)
                      .append("\nName: ").append(name)
                      .append("\nAddress: ").append(address)
                      .append("\nContact: ").append(contact)
                      .append("\n\n");

            } while (rs.next());

            displayArea.setText(result.toString());

        } catch (SQLException e) {
            System.out.println("Error while displaying suppliers.");
            e.printStackTrace();
        }
    }

    private void addSupplier() {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String contact = contactField.getText().trim();

        if (name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            displayArea.setText("All fields must be filled.");
            return;
        }

        // String query to insert the supplier into the database
        String query = "INSERT INTO suppliers (name, address, contact) VALUES (?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, address, contact);

        if (rowsAffected > 0) {
            displayArea.setText("Supplier added successfully.");
            nameField.setText("");
            addressField.setText("");
            contactField.setText("");
        } else {
            displayArea.setText("Failed to add supplier. Please try again");
        }
    }

    private void searchSupplierByName() {
        String supplier = nameField.getText().trim();
        if (supplier.isEmpty()) {
            displayArea.setText("Searching for supplier: " + supplier);
            nameField.setText("");
            return;
        }

        // String query to get the supplier name like
        String query = "SELECT * FROM suppliers WHERE name LIKE ?";

        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, "%" + supplier + "%");
             ResultSet rs = stmt.executeQuery()) {
            
            if (!rs.next() || rs == null) {
                displayArea.setText("Supplier not found.");
                return;
            }

            StringBuilder result = new StringBuilder();
            do {
                // Fetch Data
                int id = rs.getInt("id");
                String contact = rs.getString("contact");
                String address = rs.getString("address");

                result.append("ID: ").append(id)
                      .append("Contact: ").append(contact)
                      .append("Address: ").append(address)
                      .append("\n\n");

            } while (rs.next());
            // Print the supplier details
            displayArea.setText(result.toString());

        } catch (SQLException e) {
            System.out.println("Error while searching for supplier");
            e.printStackTrace();
        }
        
    }

    private void removeSupplierByName() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            displayArea.setText("Enter supplier name to remove.");
            nameField.setText("");
            return;
        }

        String query = "DELETE FROM suppliers WHERE name = ?";

        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name);

        if (rowsAffected > 0) {
            displayArea.setText("Supplier '" + name + "' removed successfully!");
            nameField.setText("");
        } else {
            displayArea.setText("Supplier not found or could not be removed.");
        }
    }

    private void removeSupplierById() {
        
    }

    private void searchSupplierById() {

    }
}