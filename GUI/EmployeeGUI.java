package GUI;

import javax.swing.*;
import java.awt.*;


class EmployeeGUI extends JFrame {
    private JTextArea displayArea;

    public EmployeeGUI() {
        setTitle("Employee Operations");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Employee Operations Page", SwingConstants.CENTER));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);


    }

    private void searchEmployeeByID() {

    }

    private void searchEmployeeByName() {

    }

    private void addNewEmployee() {

    }

    private void purchase() {
        
    }
}