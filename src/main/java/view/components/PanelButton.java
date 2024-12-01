package view.components;

import javax.swing.*;
import java.awt.*;

/**
 * A class for creating a panel styled as a button with an image background.
 */
public class PanelButton extends JPanel {

    /**
     * Constructs a PanelButton instance with the specified image name for the background.
     *
     * @param imageName The name of the image file to be used as the button background.
     */
    public PanelButton(String imageName) {
        // Set the layout and background color of the panel
        this.setLayout(null);
        this.setBackground(Color.decode("#FFFFFF")); // Set the panel's background color to white

        // Load the image as an icon
        ImageIcon buttonRectangle = new ImageIcon("src/main/resources/" + imageName);

        // Create a label to hold the image and position it within the panel
        JLabel buttonBackground = new JLabel(buttonRectangle);
        buttonBackground.setBounds(0, 0, buttonRectangle.getIconWidth(), buttonRectangle.getIconHeight());

        // Add the image label to the panel
        this.add(buttonBackground);
    }
}
