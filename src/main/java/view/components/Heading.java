package view.components;

import javax.swing.*;
import java.awt.*;

public class Heading {
    private JLabel heading;
    public Heading(String text, Integer size) {
        JLabel heading = new JLabel(text);
        heading.setFont(new Font("Arial", Font.BOLD, size));
        this.heading = heading;
    }
    public JLabel getHeading() {
        return heading;
    }
}
