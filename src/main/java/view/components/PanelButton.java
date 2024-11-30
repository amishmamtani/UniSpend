package view.components;

import javax.swing.*;
import java.awt.*;

public class PanelButton extends JPanel {
    public PanelButton(String imageName) {
        this.setLayout(null);
        this.setBackground(Color.decode("#FFFFFF"));
        ImageIcon buttonRectangle = new ImageIcon("src/main/resources/" + imageName);
        JLabel buttonBackground = new JLabel(buttonRectangle);
        buttonBackground.setBounds(0, 0, buttonRectangle.getIconWidth(), buttonRectangle.getIconHeight());
        this.add(buttonBackground);
    }
}
