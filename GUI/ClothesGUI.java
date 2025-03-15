package GUI;

import javax.swing.*;

class ClothesGUI extends JFrame {
    public ClothesGUI() {
        setTitle("Clothes Operations");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Clothes Operations Page", SwingConstants.CENTER));
    }
}