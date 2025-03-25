package GUI.Staff;

import GUI.LoginGUI;
import java.awt.*;
import javax.swing.*;

public class StaffGUI extends JFrame {

    public StaffGUI() {
        setTitle("Store Clerk Interface");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with card layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Title Label
        JLabel title = new JLabel("Store Clerk Operations");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0, 123, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        // Buttons for store clerk operations
        JButton checkoutBtn = createRoundedButton("Checkout");
        JButton stockCheckBtn = createRoundedButton("Check Stock");
        JButton searchItemBtn = createRoundedButton("Search Item");
        JButton returnItemBtn = createRoundedButton("Process Returns");
        JButton logoutBtn = createRoundedButton("Logout");

        // Layout buttons
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(checkoutBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(stockCheckBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(searchItemBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(returnItemBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(logoutBtn, gbc);

        // Button actions
        checkoutBtn.addActionListener(e -> openCheckout());
        stockCheckBtn.addActionListener(e -> openStockCheck());
        searchItemBtn.addActionListener(e -> openItemSearch());
        returnItemBtn.addActionListener(e -> openReturns());
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                LoginGUI loginScreen = new LoginGUI();
                loginScreen.setVisible(true);
                this.dispose();
            }
        });

        add(panel);
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
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 60));
        return button;
    }

    private void openCheckout() {
        // For processing customer purchases
        // Should include:
        // - Item scanning/selection
        // - Quantity selection
        // - Price calculation
        // - Payment processing
        // - Receipt generation
    }

    private void openStockCheck() {
        // For checking item availability
        // Should include:
        // - Size availability
        // - Color options
        // - Location in store
        // - Quantity in stock
    }

    private void openItemSearch() {
        // For finding specific items
        // Should include:
        // - Search by category
        // - Search by size
        // - Search by color
        // - Search by brand
    }

    private void openReturns() {
        // For processing customer returns
        // Should include:
        // - Return reason
        // - Item condition check
        // - Refund processing
        // - Stock update
    }
}