package GUI;

import javax.swing.*;


class EmployeeGUI extends JFrame {
    public EmployeeGUI() {
        setTitle("Employee Operations");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Employee Operations Page", SwingConstants.CENTER));
    }
}