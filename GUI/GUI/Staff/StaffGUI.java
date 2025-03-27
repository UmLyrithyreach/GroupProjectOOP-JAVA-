package GUI.Staff;

import java.awt.*;
import javax.swing.*;

public class StaffGUI extends JFrame {
    private String employeeName; // Field to store the employee name

    // Updated constructor to accept employeeName
    public StaffGUI(String employeeName) {
        this.employeeName = employeeName; // Store the employee name

        setTitle("Store Clerk Interface");
        setSize(600, 600);
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
        JButton purchaseBtn = createRoundedButton("Walk-in Purchase", new Color(0, 123, 255));
        JButton stockCheckBtn = createRoundedButton("Check Stock", new Color(0, 123, 255));
        JButton searchItemBtn = createRoundedButton("Search Item", new Color(0, 123, 255));
        JButton returnItemBtn = createRoundedButton("Generate Invoices", new Color(0, 123, 255));
        JButton logoutBtn = createRoundedButton("Logout", Color.RED);

        // Layout buttons
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(purchaseBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(stockCheckBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(searchItemBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(returnItemBtn, gbc);

        // Move logout button to the bottom right
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST; // Align bottom-right
        gbc.weighty = 1; // Push it to the bottom
        panel.add(logoutBtn, gbc);

        // Button actions
        purchaseBtn.addActionListener(e -> {
            WalkInPurchaseGUI walkInPurchaseGUI = new WalkInPurchaseGUI(employeeName); // Pass the employee name
            walkInPurchaseGUI.setVisible(true);
        });
        stockCheckBtn.addActionListener(e -> openStockCheck());
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

    private JButton createRoundedButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
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

    private void openStockCheck() {
        try {
            // Create a new CheckStock instance to show the stock check window
            CheckStock stockChecker = new CheckStock();
            
            // You could also use the static method if you modified the CheckStock class to include it
            // CheckStock.openStockCheckWindow();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error opening stock check window: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StaffGUI("someth168").setVisible(true));
    }
}