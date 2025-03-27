package GUI;

import Class.DatabaseConnection;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClothesGUI extends JFrame {
    private JFrame frame;
    private JTextField nameField, brandField, sizeField, styleField, priceField, supplierIdField;

    public ClothesGUI() {
        frame = new JFrame("Clothes Management");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("Poppins", Font.PLAIN, 20));

        String[] column = { "ID", "Name", "Brand", "Size", "Price", "Style", "Supplier ID" };
        DefaultTableModel model = new DefaultTableModel(column, 0);
        JTable table = new JTable(model);
        // Set Table Row's height
        table.setRowHeight(30);
        // Set width of each columns
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);

        loadClothesData(model);

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

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addButton = createRoundedButton("Add New Clothes");
        addButton.addActionListener(e -> showAddClothesDialog(model));
        buttonPanel.add(addButton);

        JButton editButton = createRoundedButton("Edit Selected");
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                showEditClothesDialog(model, selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a clothes item to edit.");
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = createRoundedButton("Delete Selected");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                deleteClothes(model, selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a clothes item to delete.");
            }
        });
        buttonPanel.add(deleteButton);

        JButton refreshButton = createRoundedButton("Refresh Data");
        refreshButton.addActionListener(e -> {
            model.setRowCount(0);
            loadClothesData(model);
        });
        buttonPanel.add(refreshButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void loadClothesData(DefaultTableModel model) {
        String query = "SELECT id, name, brand, size, price, style, supplierId FROM clothes";
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query);
             ResultSet rs = stmt.executeQuery()) {

            boolean hasData = rs.next();
            if (!hasData) {
                JOptionPane.showMessageDialog(frame, "No clothes found in database. You can add new items.");
            } else {
                do {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String brand = rs.getString("brand");
                    String size = rs.getString("size");
                    BigDecimal price = rs.getBigDecimal("price");
                    String style = rs.getString("style");
                    int supplierId = rs.getInt("supplierId");

                    model.addRow(new Object[] { id, name, brand, size, price, style, supplierId });
                } while (rs.next());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading clothes data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAddClothesDialog(DefaultTableModel model) {
        JDialog dialog = new JDialog(frame, "Add New Clothes", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(createStyledLabel("Name:"));
        nameField = createStyledTextField();
        formPanel.add(nameField);

        formPanel.add(createStyledLabel("Brand:"));
        brandField = createStyledTextField();
        formPanel.add(brandField);

        formPanel.add(createStyledLabel("Size:"));
        sizeField = createStyledTextField();
        formPanel.add(sizeField);

        formPanel.add(createStyledLabel("Style:"));
        styleField = createStyledTextField();
        formPanel.add(styleField);

        formPanel.add(createStyledLabel("Price:"));
        priceField = createStyledTextField();
        formPanel.add(priceField);

        formPanel.add(createStyledLabel("Supplier ID:"));
        supplierIdField = createStyledTextField();
        formPanel.add(supplierIdField);

        dialog.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = createRoundedButton("Save");
        saveButton.addActionListener(e -> {
            if (addClothesToDatabase(model)) {
                dialog.dispose();
            }
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = createRoundedButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private boolean addClothesToDatabase(DefaultTableModel model) {
        String name = nameField.getText().trim();
        String brand = brandField.getText().trim();
        String size = sizeField.getText().trim();
        String style = styleField.getText().trim();
        String priceStr = priceField.getText().trim();
        String supplierIdStr = supplierIdField.getText().trim();

        // Validate input
        if (name.isEmpty() || brand.isEmpty() || size.isEmpty() || style.isEmpty() ||
                priceStr.isEmpty() || supplierIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required.");
            return false;
        }

        // Validate numeric fields
        double price;
        int supplierId;
        try {
            price = Double.parseDouble(priceStr);
            supplierId = Integer.parseInt(supplierIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Price and Supplier ID must be valid numbers.");
            return false;
        }

        // Insert into database
        String query = "INSERT INTO clothes (name, brand, size, style, price, supplierId) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, brand, size, style, price, supplierId);

        if (rowsAffected > 0) {
            // Get the newly inserted ID
            try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(
                    "SELECT id FROM clothes WHERE name = ? AND brand = ? AND size = ? ORDER BY id DESC LIMIT 1",
                    name, brand, size);
                 ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    int id = rs.getInt("id");
                    model.addRow(new Object[] { id, name, brand, size, price, style, supplierId });
                    JOptionPane.showMessageDialog(frame, "Clothes added successfully!");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        JOptionPane.showMessageDialog(frame, "Failed to add clothes to database.");
        return false;
    }

    private void showEditClothesDialog(DefaultTableModel model, int selectedRow) {
        JDialog dialog = new JDialog(frame, "Edit Clothes", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        int clothesId = (int) model.getValueAt(selectedRow, 0);
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(createStyledLabel("Name:"));
        nameField = createStyledTextField();
        nameField.setText(model.getValueAt(selectedRow, 1).toString());
        formPanel.add(nameField);

        formPanel.add(createStyledLabel("Brand:"));
        brandField = createStyledTextField();
        brandField.setText(model.getValueAt(selectedRow, 2).toString());
        formPanel.add(brandField);

        formPanel.add(createStyledLabel("Size:"));
        sizeField = createStyledTextField();
        sizeField.setText(model.getValueAt(selectedRow, 3).toString());
        formPanel.add(sizeField);

        formPanel.add(createStyledLabel("Style:"));
        styleField = createStyledTextField();
        styleField.setText(model.getValueAt(selectedRow, 5).toString());
        formPanel.add(styleField);

        formPanel.add(createStyledLabel("Price:"));
        priceField = createStyledTextField();
        priceField.setText(model.getValueAt(selectedRow, 4).toString());
        formPanel.add(priceField);

        formPanel.add(createStyledLabel("Supplier ID:"));
        supplierIdField = createStyledTextField();
        supplierIdField.setText(model.getValueAt(selectedRow, 6).toString());
        formPanel.add(supplierIdField);

        dialog.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = createRoundedButton("Update");
        saveButton.addActionListener(e -> {
            if (updateClothesInDatabase(model, selectedRow, clothesId)) {
                dialog.dispose();
            }
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = createRoundedButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private boolean updateClothesInDatabase(DefaultTableModel model, int selectedRow, int clothesId) {
        String name = nameField.getText().trim();
        String brand = brandField.getText().trim();
        String size = sizeField.getText().trim();
        String style = styleField.getText().trim();
        String priceStr = priceField.getText().trim();
        String supplierIdStr = supplierIdField.getText().trim();

        // Validate input
        if (name.isEmpty() || brand.isEmpty() || size.isEmpty() || style.isEmpty() ||
                priceStr.isEmpty() || supplierIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required.");
            return false;
        }

        // Validate numeric fields
        double price;
        int supplierId;
        try {
            price = Double.parseDouble(priceStr);
            supplierId = Integer.parseInt(supplierIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Price and Supplier ID must be valid numbers.");
            return false;
        }

        // Update database
        String query = "UPDATE clothes SET name = ?, brand = ?, size = ?, style = ?, price = ?, supplierId = ? WHERE id = ?";
        int rowsAffected = DatabaseConnection.executePreparedUpdate(
                query, name, brand, size, style, price, supplierId, clothesId);

        if (rowsAffected > 0) {
            // Update the table model
            model.setValueAt(name, selectedRow, 1);
            model.setValueAt(brand, selectedRow, 2);
            model.setValueAt(size, selectedRow, 3);
            model.setValueAt(price, selectedRow, 4);
            model.setValueAt(style, selectedRow, 5);
            model.setValueAt(supplierId, selectedRow, 6);
            
            JOptionPane.showMessageDialog(frame, "Clothes updated successfully!");
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Failed to update clothes.");
            return false;
        }
    }

    private void deleteClothes(DefaultTableModel model, int selectedRow) {
        int clothesId = (int) model.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
                frame, 
                "Are you sure you want to delete this clothes item?", 
                "Confirm Deletion", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM clothes WHERE id = ?";
            int rowsAffected = DatabaseConnection.executePreparedUpdate(query, clothesId);
            
            if (rowsAffected > 0) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "Clothes deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to delete clothes.");
            }
        }
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
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
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
}