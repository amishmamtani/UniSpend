package view.components;

import javax.swing.*;
import java.awt.*;

public class Heading {
    private JLabel heading;
    public Heading(String text) {
        JLabel heading = new JLabel(text);
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        this.heading = heading;
    }
    public JLabel getHeading() {
        return heading;
    }
}
