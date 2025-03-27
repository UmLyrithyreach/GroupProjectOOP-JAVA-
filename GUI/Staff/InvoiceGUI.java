package Staff;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class InvoiceGUI extends JFrame {
    private List<String> cartItems;
    private String employeeName;
    private String paymentMethod;

    public InvoiceGUI(List<String> cartItems, String employeeName, String paymentMethod) {
        this.cartItems = cartItems;
        this.employeeName = employeeName;
        this.paymentMethod = paymentMethod;

        setTitle("Invoice");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));

        // Title
        JLabel title = new JLabel("Invoice", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(new Color(0, 123, 255));
        title.setPreferredSize(new Dimension(600, 60));
        panel.add(title, BorderLayout.NORTH);

        // Invoice Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(245, 245, 245));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Employee Name and Date
        JLabel employeeLabel = new JLabel("Employee: " + employeeName);
        employeeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        employeeLabel.setForeground(new Color(50, 50, 50));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = dateFormat.format(new Date());
        JLabel dateLabel = new JLabel("Date: " + currentDate);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateLabel.setForeground(new Color(50, 50, 50));

        detailsPanel.add(employeeLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(dateLabel);

        // Item Details Title
        JLabel itemDetailTitle = new JLabel("Item Detail");
        itemDetailTitle.setFont(new Font("Arial", Font.BOLD, 20));
        itemDetailTitle.setForeground(new Color(0, 123, 255));

        detailsPanel.add(Box.createVerticalStrut(20));
        detailsPanel.add(itemDetailTitle);

        // Create the table-like structure for item list
        String[] columnNames = {"Item", "Quantity", "Price per Unit"};
        Object[][] data = generateItemData();

        JTable itemTable = new JTable(data, columnNames);
        itemTable.setFont(new Font("Arial", Font.PLAIN, 16));
        itemTable.setForeground(new Color(50, 50, 50));
        itemTable.setRowHeight(30);
        itemTable.setEnabled(false); // Makes the table non-editable
        itemTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        itemTable.getTableHeader().setBackground(new Color(0, 123, 255));
        itemTable.setBackground(new Color(245, 245, 245));

        JScrollPane tableScrollPane = new JScrollPane(itemTable);
        tableScrollPane.setPreferredSize(new Dimension(550, 150));
        detailsPanel.add(tableScrollPane);

        // Calculate and display total amount
        double totalAmount = calculateTotalAmount();
        JLabel totalAmountLabel = new JLabel(String.format("Total Amount: $%.2f", totalAmount));
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalAmountLabel.setForeground(new Color(0, 123, 255));

        JLabel paymentMethodLabel = new JLabel("Payment Method: " + paymentMethod);
        paymentMethodLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        paymentMethodLabel.setForeground(new Color(50, 50, 50));

        detailsPanel.add(Box.createVerticalStrut(20));
        detailsPanel.add(totalAmountLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(paymentMethodLabel);

        // Add detailsPanel to the center of the frame
        panel.add(detailsPanel, BorderLayout.CENTER);

        // OK Button
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setPreferredSize(new Dimension(100, 40));
        okButton.setBackground(new Color(0, 123, 255));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setBorderPainted(false);
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.add(okButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private Object[][] generateItemData() {
        Object[][] data = new Object[cartItems.size()][3];
        for (int i = 0; i < cartItems.size(); i++) {
            String cartItem = cartItems.get(i);
            String[] parts = cartItem.split(" x");
            String itemName = parts[0];
            String quantity = "x" + parts[1];
            String pricePerUnit = getPricePerUnit(itemName);

            data[i][0] = itemName;       // Item
            data[i][1] = quantity;       // Quantity
            data[i][2] = pricePerUnit;   // Price per Unit
        }
        return data;
    }

    private String getPricePerUnit(String itemName) {
        switch (itemName) {
            case "T-Shirt":
                return "$10.00";
            case "Jeans":
                return "$25.00";
            case "Jacket":
                return "$35.00";
            case "Sweater":
                return "$20.00";
            case "Shorts":
                return "$15.00";
            default:
                return "$0.00"; // Default price for unknown items
        }
    }

    private double calculateTotalAmount() {
        double total = 0.0;
        for (String cartItem : cartItems) {
            String[] parts = cartItem.split(" x");
            String itemName = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            double pricePerUnit = Double.parseDouble(getPricePerUnit(itemName).replace("$", ""));
            total += quantity * pricePerUnit;
        }
        return total;
    }

    public void showInvoice() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<String> sampleCart = new ArrayList<>();
            sampleCart.add("T-Shirt x1");
            sampleCart.add("Sweater x2");
            new InvoiceGUI(sampleCart, "someth168", "KHQR").showInvoice();
        });
    }
}