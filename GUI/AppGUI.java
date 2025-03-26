package GUI;

import java.awt.*;
import javax.swing.*;

public class AppGUI extends JFrame {

    public AppGUI() {
        setTitle("Clothes Store Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout for positioning elements
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add padding around elements

        // Title Label
        JLabel title = new JLabel("Manager Operator");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0, 123, 255)); // Blue color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        // Create buttons with custom colors
        JButton employeeBtn = createRoundedButton("Employee Operations", new Color(0, 123, 255)); // Blue
        JButton clothesBtn = createRoundedButton("Clothes Operations", new Color(0, 123, 255));  // Blue
        JButton supplierBtn = createRoundedButton("Supplier Operations", new Color(0, 123, 255)); // Blue
        JButton logoutBtn = createRoundedButton("Logout", new Color(220, 53, 69)); // Red (Danger theme)

        // Add Employee Button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(employeeBtn, gbc);

        // Add Clothes Button
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(clothesBtn, gbc);

        // Add Supplier Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(supplierBtn, gbc);

        // Position Logout Button at the Bottom-Right
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTHEAST; // Align to bottom-right
        gbc.weighty = 1; // Push it to the bottom
        gbc.insets = new Insets(20, 20, 40, 40); // Extra spacing for better positioning
        panel.add(logoutBtn, gbc);

        // Button Actions
        employeeBtn.addActionListener(e -> openEmployeeOperations());
        clothesBtn.addActionListener(e -> openClothesOperations());
        supplierBtn.addActionListener(e -> openSupplierOperations());
        logoutBtn.addActionListener(e -> {
            dispose(); // Close the manager window
            LoginGUI login = new LoginGUI(); // Open the login screen
            login.setVisible(true);
        });
        add(panel);
    }

    // ✅ Modified createRoundedButton to accept a custom color
    private JButton createRoundedButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(color); // Use the custom color passed as an argument
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

    // Methods to open different sections
    private void openEmployeeOperations() {
        EmployeeGUI employeeGUI = new EmployeeGUI();
        employeeGUI.setVisible(true);
    }

    private void openClothesOperations() {
        ClothesGUI clothesGUI = new ClothesGUI();
        clothesGUI.setVisible(true);
    }

    private void openSupplierOperations() {
        SupplierGUI supplierGUI = new SupplierGUI();
        supplierGUI.setVisible(true);
    }
}
