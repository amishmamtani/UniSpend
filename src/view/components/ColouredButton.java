package view.components;

import javax.swing.*;
import java.awt.*;

public class ColouredButton {
    private JButton button;
    public ColouredButton(String text, String btnColour, String txtColour){
        JButton button = new JButton(text);
        button.setBackground(Color.decode(btnColour));
        button.setForeground(Color.decode(txtColour));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        this.button = button;
    }
    public JButton getButton() {
        return button;
    }
}
