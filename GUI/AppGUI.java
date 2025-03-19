package GUI;

import javax.swing.*;
import java.awt.*;

public class AppGUI extends JFrame {

    public AppGUI() {
        setTitle("Clothes Store Management");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel title = new JLabel("Manager Operator", SwingConstants.CENTER);
        panel.add(title);

        JButton employeeBtn = new JButton("Employee Operations");
        JButton clothesBtn = new JButton("Clothes Operations");
        JButton supplierBtn = new JButton("Supplier Operations");
        JButton logoutBtn = new JButton("Logout");

        panel.add(employeeBtn);
        panel.add(clothesBtn);
        panel.add(supplierBtn);
        panel.add(logoutBtn);

        employeeBtn.addActionListener(e -> openEmployeeOperations());
        clothesBtn.addActionListener(e -> openClothesOperations());
        supplierBtn.addActionListener(e -> openSupplierOperations());
        logoutBtn.addActionListener(e -> System.exit(0));

        add(panel);
    }

    private void openEmployeeOperations() {
        EmployeeGUI employeeGUI = new EmployeeGUI();
        employeeGUI.setVisible(false);
    }

    private void openClothesOperations() {
        ClothesGUI clothesGUI = new ClothesGUI();
        clothesGUI.setVisible(true);
    }

    private void openSupplierOperations() {
        SupplierGUI supplierGUI = new SupplierGUI();
        supplierGUI.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppGUI app = new AppGUI();
            app.setVisible(true);
        });
    }
}
