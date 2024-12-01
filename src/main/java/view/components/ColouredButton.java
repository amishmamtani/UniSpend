package view.components;

import javax.swing.*;
import java.awt.*;

/**
 * A class for creating and managing a custom-colored button with specific text, background color, text color, and font size.
 */
public class ColouredButton {

    /** The JButton instance representing the customized button */
    private JButton button;

    /**
     * Constructs a ColouredButton instance with the specified properties.
     *
     * @param text       The text to display on the button.
     * @param btnColour  The background color of the button, in hexadecimal format (e.g., "#FFFFFF").
     * @param txtColour  The text color of the button, in hexadecimal format (e.g., "#000000").
     * @param size       The font size of the button text.
     */
    public ColouredButton(String text, String btnColour, String txtColour, Integer size) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode(btnColour)); // Set button background color
        button.setForeground(Color.decode(txtColour)); // Set button text color
        button.setFont(new Font("Arial", Font.BOLD, size)); // Set button font
        button.setOpaque(true); // Ensure the button's background is visible
        button.setContentAreaFilled(true); // Fill the content area with the background color
        button.setBorderPainted(false); // Remove button border
        button.setFocusPainted(false); // Remove focus painting on the button
        this.button = button;
    }

    /**
     * Retrieves the JButton instance representing the customized button.
     *
     * @return The JButton instance.
     */
    public JButton getButton() {
        return button;
    }
}
