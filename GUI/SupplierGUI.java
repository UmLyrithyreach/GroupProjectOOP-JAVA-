package GUI;

import javax.swing.*;

import Class.DatabaseConnection;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierGUI extends JFrame {
    private JTextArea displayArea;
    private JTextField nameField, addressField, contactField;

    public SupplierGUI() {
        setTitle("Supplier Operations");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with card layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(240, 240, 240));

        inputPanel.add(createStyledLabel("Name:"));
        nameField = createStyledTextField();
        inputPanel.add(nameField);

        inputPanel.add(createStyledLabel("Address:"));
        addressField = createStyledTextField();
        inputPanel.add(addressField);

        inputPanel.add(createStyledLabel("Contact:"));
        contactField = createStyledTextField();
        inputPanel.add(contactField);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton viewSuppliersBtn = createRoundedButton("View All Suppliers");
        JButton addSupplierBtn = createRoundedButton("Add Supplier");
        JButton searchSupplierBtn = createRoundedButton("Search By Name");
        JButton removeSupplierBtn = createRoundedButton("Remove By Name");
        JButton searchSupplierIdBtn = createRoundedButton("Search By ID");
        JButton removeSupplierIdBtn = createRoundedButton("Remove By ID");

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
        removeSupplierBtn.addActionListener(e -> removeSupplierByName());
        searchSupplierIdBtn.addActionListener(e -> searchSupplierById());
        removeSupplierIdBtn.addActionListener(e -> removeSupplierById());

        add(panel);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0, 123, 255));
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 123, 255), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textField;
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 123, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.BOLD, 14));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
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