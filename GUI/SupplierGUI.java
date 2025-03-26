package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Class.DatabaseConnection;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierGUI extends JFrame {
    private JFrame frame;
    private JTextField nameField, addressField, contactField;

    public SupplierGUI() {
        frame = new JFrame("Supplier Operations");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        
        frame.setLocationRelativeTo(null);
        frame.setFont(new Font("Poppins", Font.PLAIN, 20));

        String[] column = {"id", "name", "address", "contact"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        JTable table = new JTable(model);

        //Set Table Row's height
        table.setRowHeight(30);

        //Set Table Font
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);

        String query = "SELECT id, name, address, contact FROM suppliers";

        // Execute the query
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No suppliers found.");
                return;
            }

            // Fetch data from the result set
            do {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String contact = rs.getString("contact");

                model.addRow(new Object[]{id, name, address, contact});
            } while (rs.next());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while displaying suppliers.");
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

        JButton updateNameBtn = createRoundedButton("Update Name");
        updateNameBtn.addActionListener(e -> updateName(table, model));
        bottomPanel.add(updateNameBtn);

        JButton updateAddressBtn = createRoundedButton("Update Address");
        updateAddressBtn.addActionListener(e -> updateAddress(table, model));
        bottomPanel.add(updateAddressBtn);

        JButton updateContactBtn = createRoundedButton("Update Contact");
        updateContactBtn.addActionListener(e -> updateContact(table, model));
        bottomPanel.add(updateContactBtn);

        JButton addSupplierBtn = createRoundedButton("Add Supplier");
        addSupplierBtn.addActionListener(e -> addNewSupplier(table, model));
        bottomPanel.add(addSupplierBtn);

        JButton removeSupplierBtn = createRoundedButton("Remove Supplier");
        removeSupplierBtn.addActionListener(e -> removeSupplier(table, model));
        bottomPanel.add(removeSupplierBtn);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
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

    private void updateString(JTable table, DefaultTableModel model, String columnName, int columnNumber) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            String newAddress = JOptionPane.showInputDialog("Enter new " + columnName + ": ");
            if (newAddress != null && !newAddress.isEmpty()) {
                String query = "UPDATE suppliers SET " + columnName + " = ? WHERE id = ?";

                int rowsAffected = DatabaseConnection.executePreparedUpdate(query, newAddress, id);
                if (rowsAffected > 0) {
                    model.setValueAt(newAddress, selectedRow, columnNumber);
                    JOptionPane.showMessageDialog(null,  columnName + " updated successfully!");
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

    private void updateAddress(JTable table, DefaultTableModel model) {
        String columnName = "address";
        int columnNumber = 2;
        updateString(table, model, columnName, columnNumber);
    }

    private void updateContact(JTable table, DefaultTableModel model) {
        String columnName = "contact";
        int columnNumber = 3;
        updateString(table, model, columnName, columnNumber);
    }

    private void addNewSupplier(JTable table, DefaultTableModel model) {
        JFrame frame = new JFrame("Add New Supplier");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);

        frame.setFont(new Font("Poppins", Font.PLAIN, 20));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        inputPanel.add(createStyledLabel("Name:"));
        nameField = createStyledTextField();
        inputPanel.add(nameField);

        inputPanel.add(createStyledLabel("Address:"));
        addressField = createStyledTextField();
        inputPanel.add(addressField);

        inputPanel.add(createStyledLabel("Contact:"));
        contactField = createStyledTextField();
        inputPanel.add(contactField);

        frame.add(inputPanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();

        JButton addSupplierBtn = createRoundedButton("Add Supplier");
        addSupplierBtn.addActionListener(e -> addSupplier(table, model));
        centerPanel.add(addSupplierBtn);

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

    private void addSupplier(JTable table, DefaultTableModel model) {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String contact = contactField.getText().trim();

        if (name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled.");
            return;
        }

        // String query to insert the supplier into the database
        String query = "INSERT INTO suppliers (name, address, contact) VALUES (?, ?, ?)";

        // Execute the query using PreparedStatement with the user input as parameters
        int rowsAffected = DatabaseConnection.executePreparedUpdate(query, name, address, contact);
        if (rowsAffected > 0) {
            try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery("SELECT id FROM suppliers WHERE name = ? AND address = ? AND contact = ?", name, address, contact);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    model.addRow(new Object[]{id, name, address, contact});
                    JOptionPane.showMessageDialog(null, "Supplier added successfully.");
                    nameField.setText("");
                    addressField.setText("");
                    contactField.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add supplier. Please try again");
        }
    }

    private void removeSupplier(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this supplier?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            String query = "DELETE FROM suppliers WHERE id = ?";

            int rowsAffected = DatabaseConnection.executePreparedUpdate(query, id);
            if (rowsAffected > 0) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Supplier removed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error removing supplier!");
            }
        }
    }
}