package Staff;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class WalkInPurchaseGUI extends JFrame {
    private DefaultListModel<String> cartModel;
    private JList<String> cartList;
    private JComboBox<String> itemDropdown;
    private JTextField quantityField;
    private JButton addButton, toPaymentButton, backButton;
    private JPanel panel;
    private String employeeName;

    public WalkInPurchaseGUI(String employeeName) {
        this.employeeName = employeeName;

        setTitle("Walk-in Purchase");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel title = new JLabel("Add Cloth to Cart");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 123, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        // Select Item (Dropdown label aligned left)
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Select Item:"), gbc);

        String[] items = {"T-Shirt", "Jeans", "Jacket", "Sweater", "Shorts"};
        itemDropdown = new JComboBox<>(items);
        itemDropdown.setFont(new Font("Arial", Font.PLAIN, 16));
        itemDropdown.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(itemDropdown, gbc);

        // Quantity Field (Aligned properly)
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Quantity:"), gbc);

        quantityField = new JTextField("1", 5);
        quantityField.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityField.setPreferredSize(new Dimension(50, 30));
        gbc.gridx = 1;
        panel.add(quantityField, gbc);

        // Add to Cart Button
        addButton = new JButton("Add to Cart");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        // Cart Display (Fixed Scroll Pane Layout)
        cartModel = new DefaultListModel<>();
        cartList = new JList<>(cartModel);
        cartList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(cartList);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        // To Payment Button (Initially Disabled)
        toPaymentButton = new JButton("To Payment");
        toPaymentButton.setFont(new Font("Arial", Font.BOLD, 16));
        toPaymentButton.setPreferredSize(new Dimension(200, 40));
        toPaymentButton.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(toPaymentButton, gbc);

        // Back Button (Bottom-Right)
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        panel.add(backButton, gbc);

        // Action Listeners
        addButton.addActionListener(e -> addItemToCart());
        toPaymentButton.addActionListener(e -> openPaymentGUI());
        backButton.addActionListener(e -> dispose());

        add(panel);
    }

    private void addItemToCart() {
        String selectedItem = (String) itemDropdown.getSelectedItem();
        String quantity = quantityField.getText().trim();

        // Ensure valid quantity
        if (!quantity.matches("\\d+") || Integer.parseInt(quantity) <= 0) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cartModel.addElement(selectedItem + " x" + quantity);

        // Refresh UI properly
        cartList.revalidate();
        cartList.repaint();
        panel.revalidate();
        panel.repaint();

        // Enable "To Payment" button if cart is not empty
        toPaymentButton.setEnabled(true);
    }

    private void openPaymentGUI() {
        if (cartModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one item to the cart before proceeding to payment.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convert DefaultListModel to List<String>
        List<String> cartItemsList = new ArrayList<>();
        for (int i = 0; i < cartModel.size(); i++) {
            cartItemsList.add(cartModel.get(i));
        }

        // Pass the cart items and employee name to PaymentGUI
        PaymentGUI paymentGUI = new PaymentGUI(cartItemsList, employeeName);
        paymentGUI.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WalkInPurchaseGUI("someth168").setVisible(true));
    }
}