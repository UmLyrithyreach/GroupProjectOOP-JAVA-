package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Class.DatabaseConnection;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClothesGUI extends JFrame {
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField nameField, brandField, sizeField, styleField, priceField, stockField, supplierIdField;

    public ClothesGUI() {
        frame = new JFrame("Clothes Operations");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("Poppins", Font.PLAIN, 20));

        String[] column = { "id", "name", "brand", "size", "price", "stock", "style", "suppliersId" };
        DefaultTableModel model = new DefaultTableModel(column, 0);
        JTable table = new JTable(model);
        // Set Table Row's height
        table.setRowHeight(30);
        // Set width of each columns
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(20);

        String query = "SELECT id, name, brand, size, price, stock, style, supplierId FROM clothes";
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query);
                ResultSet rs = stmt.executeQuery()) {

            if (!rs.next()) {
                displayArea.setText("No clothes found!");
                return;
            }

            do {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String size = rs.getString("size");
                BigDecimal price = rs.getBigDecimal("price");
                int stock = rs.getInt("stock");
                String style = rs.getString("style");
                int supplierId = rs.getInt("supplierId");

                model.addRow(new Object[] { id, name, brand, size, price, stock, style, supplierId });
            } while (rs.next());
        } catch (SQLException e) {
            displayArea.setText("Error while displaying clothes.");
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
        bottomPanel.setLayout(new GridLayout(2, 3));

        JButton addClothesBtn = createRoundedButton("Add New Clothes");
        addClothesBtn.addActionListener(e -> addClothes(table, model));
        bottomPanel.add(addClothesBtn);

        JButton updateNameBtn = createRoundedButton("Update Name");
        updateNameBtn.addActionListener(e -> updateName(table, model));
        bottomPanel.add(updateNameBtn);

        JButton updateBrandBtn = createRoundedButton("Update Brand");
        updateBrandBtn.addActionListener(e -> updateBrand(table, model));
        bottomPanel.add(updateBrandBtn);

        JButton updateSizeBtn = createRoundedButton("Update Size");
        updateSizeBtn.addActionListener(e -> updateSize(table, model));
        bottomPanel.add(updateSizeBtn);

        JButton updateStyleBtn = createRoundedButton("Update Style");
        updateStyleBtn.addActionListener(e -> updateStyle(table, model));
        bottomPanel.add(updateStyleBtn);

        JButton updateSupplierIdBtn = createRoundedButton("Update Supplier ID");
        updateSupplierIdBtn.addActionListener(e -> updateSupplierId(table, model));
        bottomPanel.add(updateSupplierIdBtn);

        JButton updateStockBtn = createRoundedButton("Update Stock");
        updateStockBtn.addActionListener(e -> updateStock(table, model));
        bottomPanel.add(updateStockBtn);

        JButton updatePriceBtn = createRoundedButton("Update Price");
        updatePriceBtn.addActionListener(e -> updatePrice(table, model));
        bottomPanel.add(updatePriceBtn);

        JButton removeClothesBtn = createRoundedButton("Remove Clothes");
        removeClothesBtn.addActionListener(e -> removeClothes(table, model));
        bottomPanel.add(removeClothesBtn);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void updateString(JTable table, DefaultTableModel model, String columnName, int columnNumber) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String newAddress = JOptionPane.showInputDialog("Enter new " + columnName + ": ");
            if (newAddress != null && !newAddress.isEmpty()) {
                String query = "UPDATE clothes SET " + columnName + " = ? WHERE id = ?";

                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, newAddress, id);
                if (rowsAffected > 0) {
                    model.setValueAt(newAddress, selectedRow, columnNumber);
                    JOptionPane.showMessageDialog(null, columnName + " updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating new " + columnName + "!");
                }
            }
        }
    }

    private void updateName(JTable table, DefaultTableModel model) {
        String columnName = "name";
        int columnNumber = 1;
        updateString(table, model, columnName, columnNumber);
    }

    private void updateBrand(JTable table, DefaultTableModel model) {
        String columnName = "brand";
        int columnNumber = 2;
        updateString(table, model, columnName, columnNumber);
    }

    private void updateSize(JTable table, DefaultTableModel model) {
        String columnName = "size";
        int columnNumber = 3;
        updateString(table, model, columnName, columnNumber);
    }

    private void updateStyle(JTable table, DefaultTableModel model) {
        String columnName = "style";
        int columnNumber = 4;
        updateString(table, model, columnName, columnNumber);
    }

    private void updateSupplierId(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String newSupplierId = JOptionPane.showInputDialog("Enter new Supplier ID: ");
            if (newSupplierId != null && !newSupplierId.isEmpty()) {
                String query = "UPDATE clothes SET supplierId = ? WHERE id = ?";
                int supplierId = Integer.parseInt(newSupplierId);
                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, supplierId, id);
                if (rowsAffected > 0) {
                    model.setValueAt(newSupplierId, selectedRow, 7);
                    JOptionPane.showMessageDialog(null, "Supplier ID updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating new Supplier ID!");
                }
            }
        }
    }

    private void updateStock(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String newStock = JOptionPane.showInputDialog("Enter new Stock: ");
            if (newStock != null && !newStock.isEmpty()) {
                String query = "UPDATE clothes SET stock = ? WHERE id = ?";
                int stock = Integer.parseInt(newStock);
                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, stock, id);
                if (rowsAffected > 0) {
                    model.setValueAt(newStock, selectedRow, 5);
                    JOptionPane.showMessageDialog(null, "Stock updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating new Stock!");
                }
            }
        }
    }

    private void updatePrice(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String newPrice = JOptionPane.showInputDialog("Enter new Price: ");
            if (newPrice != null && !newPrice.isEmpty()) {
                String query = "UPDATE clothes SET price = ? WHERE id = ?";
                Double price = Double.parseDouble(newPrice);

                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, price, id);
                if (rowsAffected > 0) {
                    model.setValueAt(price, selectedRow, 4);
                    JOptionPane.showMessageDialog(null, "Price updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating new Price!");
                }
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

    private void addClothes(JTable table, DefaultTableModel model) {

        JFrame frame = new JFrame("Add New Clothes");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);

        frame.setFont(new Font("Poppins", Font.PLAIN, 20));

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));

        inputPanel.add(createStyledLabel("Name:"));
        nameField = createStyledTextField();
        inputPanel.add(nameField);

        inputPanel.add(createStyledLabel("Brand:"));
        brandField = createStyledTextField();
        inputPanel.add(brandField);

        inputPanel.add(createStyledLabel("Size:"));
        sizeField = createStyledTextField();
        inputPanel.add(sizeField);

        inputPanel.add(createStyledLabel("Style:"));
        styleField = createStyledTextField();
        inputPanel.add(styleField);

        inputPanel.add(createStyledLabel("Price:"));
        priceField = createStyledTextField();
        inputPanel.add(priceField);

        inputPanel.add(createStyledLabel("Stock:"));
        stockField = createStyledTextField();
        inputPanel.add(stockField);

        inputPanel.add(createStyledLabel("Supplier ID:"));
        supplierIdField = createStyledTextField();
        inputPanel.add(supplierIdField);

        frame.add(inputPanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        JButton addClothesBtn = createRoundedButton("Add New Clothes");
        addClothesBtn.addActionListener(e -> addNewClothes(table, model));
        centerPanel.add(addClothesBtn);

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

    private void addNewClothes(JTable table, DefaultTableModel model) {
        String name = nameField.getText().trim();
        String brand = brandField.getText().trim();
        String size = sizeField.getText().trim();
        String style = styleField.getText().trim();
        String price = priceField.getText().trim();
        String stock = stockField.getText().trim();
        String supplier = supplierIdField.getText().trim();

        if (name.isEmpty() || brand.isEmpty() || size.isEmpty() || style.isEmpty() ||
                price.isEmpty() || stock.isEmpty() || supplier.isEmpty()) {

            JOptionPane.showMessageDialog(null, "All fields must be filled except ID field.");
            return;
        }

        // String query to insert the clothes into the database
        String query = "INSERT INTO clothes (name, brand, size, price, stock, style, supplierId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, brand, size, price, stock, style, supplier);

        if (rowsAffected > 0) {
            try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery("SELECT id FROM clothes WHERE name = ? AND brand = ? AND size = ? AND price = ? AND stock = ? AND style = ? AND supplierId = ?", name, brand, size, price, stock, style, supplier);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    model.addRow(new Object[] { id, name, brand, size, price, stock, style, supplier });
                    JOptionPane.showMessageDialog(null, "Clothes added successfully.");
                    nameField.setText("");
                    brandField.setText("");
                    sizeField.setText("");
                    styleField.setText("");
                    priceField.setText("");
                    stockField.setText("");
                    supplierIdField.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add clothes.");
        }
    }

    private void removeClothes(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this clothes?");
            if (confirm == JOptionPane.YES_NO_OPTION) {
                String query = "DELETE FROM clothes WHERE id = ?";
                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, id);
                if (rowsAffected > 0) {
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Clothes deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete clothes.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }
}