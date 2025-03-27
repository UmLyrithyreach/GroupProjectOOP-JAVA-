package Staff;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class PaymentGUI extends JFrame {
    private JComboBox<String> paymentDropdown;
    private JButton generateReceiptButton, backButton;
    private List<String> cartItems;
    private String employeeName;

    public PaymentGUI(List<String> cartItems, String employeeName) {
        this.cartItems = cartItems;
        this.employeeName = employeeName;

        setTitle("Payment");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel title = new JLabel("Select Payment Method");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 123, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        // Choose Payment Method (Label aligned left)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Choose Payment:"), gbc);

        String[] payments = {"Cash", "Card", "ABA", "KHQR"};
        paymentDropdown = new JComboBox<>(payments);
        paymentDropdown.setFont(new Font("Arial", Font.PLAIN, 16));
        paymentDropdown.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(paymentDropdown, gbc);

        // Generate Receipt Button
        generateReceiptButton = new JButton("Generate Invoice");
        generateReceiptButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateReceiptButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(generateReceiptButton, gbc);

        // Back Button (Bottom-Right)
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        panel.add(backButton, gbc);

        // Action Listeners
        generateReceiptButton.addActionListener(e -> showInvoice());
        backButton.addActionListener(e -> goBack());

        add(panel);
    }

    private void showInvoice() {
        String paymentMethod = (String) paymentDropdown.getSelectedItem();
        InvoiceGUI invoiceGUI = new InvoiceGUI(cartItems, employeeName, paymentMethod);
        invoiceGUI.showInvoice();
        dispose();
    }

    private void goBack() {
        WalkInPurchaseGUI walkInPurchaseGUI = new WalkInPurchaseGUI(employeeName);
        walkInPurchaseGUI.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentGUI(new ArrayList<>(), "someth168").setVisible(true));
    }
}