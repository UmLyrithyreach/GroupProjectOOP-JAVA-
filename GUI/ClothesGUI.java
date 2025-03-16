package GUI;

import javax.swing.*;

import Class.DatabaseConnection;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

class ClothesGUI extends JFrame {
    private JTextArea displayArea;
    private JTextField idField, nameField, brandField, sizeField, styleField, priceField, stockField, supplierIdField;

    public ClothesGUI() {
        setTitle("Clothes Operations");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Label and Text Field
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Brand:"));
        brandField = new JTextField();
        inputPanel.add(brandField);

        inputPanel.add(new JLabel("Size:"));
        sizeField = new JTextField();
        inputPanel.add(sizeField);

        inputPanel.add(new JLabel("Style:"));
        styleField = new JTextField();
        inputPanel.add(styleField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        inputPanel.add(stockField);

        inputPanel.add(new JLabel("Supplier ID:"));
        supplierIdField = new JTextField();
        inputPanel.add(supplierIdField);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));

        JButton addClothesBtn = new JButton("Add New Clothes");
        JButton displayClothesBtn = new JButton("Display Clothes");
        JButton searchByName = new JButton("Search Clothes By Name");
        JButton searchByBrand = new JButton("Search Clothes By Brand");
        JButton searchById = new JButton("Search Clothes By ID");
        JButton removeById = new JButton("Remove Clothes By ID");

        buttonPanel.add(addClothesBtn);
        buttonPanel.add(displayClothesBtn);
        buttonPanel.add(searchByName);
        buttonPanel.add(searchByBrand);
        buttonPanel.add(searchById);
        buttonPanel.add(removeById);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        addClothesBtn.addActionListener(e -> addClothes());
        displayClothesBtn.addActionListener(e -> displayClothes());
        searchByName.addActionListener(e -> searchByName());
        searchByBrand.addActionListener(e -> searchByBrand());
        searchById.addActionListener(e -> searchById());
        removeById.addActionListener(e -> removeClothesById());

        add(panel);
    }

    private void addClothes() {
        String name = nameField.getText().trim();
        String brand = brandField.getText().trim();
        String size = sizeField.getText().trim();
        String style = styleField.getText().trim();
        String price = priceField.getText().trim();
        String stock = stockField.getText().trim();
        String supplier = supplierIdField.getText().trim();

        if (name.isEmpty() || brand.isEmpty() || size.isEmpty() || style.isEmpty() ||
            price.isEmpty() || stock.isEmpty() || supplier.isEmpty()) {

            displayArea.setText("All fields must be filled except ID field.");
            return;
        }

        // String query to insert the clothes into the database
        String query = "INSERT INTO clothes (name, brand, size, price, stock, style, supplierId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, brand, size, price, stock, style, supplier);

        if (rowsAffected > 0) {
            displayArea.setText("Clothes added successfully.");
        } else {
            displayArea.setText("Failed to add clothes.");
        }
    }

    private void displayClothes() {
        // String query to get all clothes
        String query = "SELECT * FROM clothes";

        // Execute the query
        try (ResultSet rs = DatabaseConnection.executeQuery(query)) {
            if (rs == null || !rs.next()) {
                displayArea.setText("No clothes found!");
                return;
            }

            StringBuilder result = new StringBuilder();
            do {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String size = rs.getString("size");
                BigDecimal price = rs.getBigDecimal("price");
                int stock = rs.getInt("stock");
                String style = rs.getString("style");
                int supplierId = rs.getInt("supplierId");

                // Build String result
                result.append("ID: ").append(id)
                      .append("Name: ").append(name)
                      .append("Brand: ").append(brand)
                      .append("Size: ").append(size)
                      .append("Price: ").append(price)
                      .append("Stock: ").append(stock)
                      .append("Style: ").append(style)
                      .append("Supplier ID: ").append(supplierId);

            } while (rs.next());

            // Print clothes details
            displayArea.setText(result.toString());

        } catch (SQLException e) {
            displayArea.setText("Error while displaying clothes.");
            e.printStackTrace();
        }
    }

    private void searchByName() {

    }

    private void searchByBrand() {

    }

    private void searchById() {

    }

    private void removeClothesById() {
        
    }
}