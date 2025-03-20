package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Class.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Username Field
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        // Password Field
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Username and password are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if the user is a manager or staff
                String query = "SELECT isManager FROM employees WHERE username = ? AND password = ?";
                try {
                    ResultSet rs = DatabaseConnection.executePreparedQuery(query, username, password);
                    if (rs != null && rs.next()) {
                        int isManager = rs.getInt("isManager");
                        if (isManager == 1) {
                            // Open Manager GUI
                            AppGUI app = new AppGUI();
                            app.setVisible(true);
                            dispose(); // Close the login window
                        } else {
                            // Open Staff GUI (you can create a separate GUI for staff if needed)
                            JOptionPane.showMessageDialog(LoginGUI.this, "Welcome Staff!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        // Show error message for invalid username/password
                        JOptionPane.showMessageDialog(LoginGUI.this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginGUI.this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(loginButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI login = new LoginGUI();
            login.setVisible(true);
        });
    }
}
