package GUI;

import Class.DatabaseConnection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginGUI() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Username
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        // Password
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        // Status Label
        statusLabel = new JLabel("");
        panel.add(statusLabel);

        // Action Listener for Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Validate credentials against the database
                try {
                    String query = "SELECT * FROM employees WHERE username = ? AND password = ?";
                    PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query, username, password);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        int isManager = rs.getInt("isManager");
                        String name = rs.getString("name"); // Fetch the employee's name
                        statusLabel.setText("Login Successful!");

                        if (isManager == 1) {
                            new ManagerGUI(name).setVisible(true); // Pass the name to ManagerGUI
                        } else {
                            new StaffGUI(name).setVisible(true); // Pass the name to StaffGUI
                        }
                        dispose(); // Close the login window
                    } else {
                        statusLabel.setText("Invalid username or password");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    statusLabel.setText("Database error. Please try again.");
                }
            }
        });

        add(panel);
    }
}