package view.components;

import javax.swing.*;
import java.awt.*;

/**
 * A class for creating and managing a heading (label) with custom text and font size.
 */
public class Heading {

    /** The JLabel instance representing the heading */
    private JLabel heading;

    /**
     * Constructs a Heading instance with the specified text and font size.
     *
     * @param text The text to display as the heading.
     * @param size The font size of the heading text.
     */
    public Heading(String text, Integer size) {
        JLabel heading = new JLabel(text);
        heading.setFont(new Font("Arial", Font.BOLD, size)); // Set the font and size for the heading
        this.heading = heading;
    }

    /**
     * Retrieves the JLabel instance representing the heading.
     *
     * @return The JLabel instance.
     */
    public JLabel getHeading() {
        return heading;
    }
}
