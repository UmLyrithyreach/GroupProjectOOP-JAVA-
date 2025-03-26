package GUI;

import java.awt.*;
import javax.swing.*;

public class AppGUI extends JFrame {

    public AppGUI() {
        setTitle("Clothes Store Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with card layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Title Label
        JLabel title = new JLabel("Manager Operator");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0, 123, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        // Buttons with rounded design
        JButton employeeBtn = createRoundedButton("Employee Operations");
        JButton clothesBtn = createRoundedButton("Clothes Operations");
        JButton supplierBtn = createRoundedButton("Supplier Operations");
        JButton logoutBtn = createRoundedButton("Logout");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(employeeBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(clothesBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(supplierBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(logoutBtn, gbc);

        employeeBtn.addActionListener(e -> openEmployeeOperations());
        clothesBtn.addActionListener(e -> openClothesOperations());
        supplierBtn.addActionListener(e -> openSupplierOperations());
        logoutBtn.addActionListener(e -> System.exit(0));

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