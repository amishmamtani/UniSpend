package view.components;

import javax.swing.*;
import java.awt.*;

/**
 * A class for creating a log card panel that displays a category and an associated amount.
 */
public class LogCard extends JPanel {

    /**
     * Constructs a LogCard instance with the specified category and amount.
     *
     * @param category The category to display on the log card.
     * @param amount   The amount associated with the category.
     */
    public LogCard(String category, String amount) {
        // Create a label for the category text
        JLabel logCategoryText = new JLabel(category);
        logCategoryText.setBounds(20, 13, 200, 20);

        // Create a label for the amount text
        JLabel logAmountText = new JLabel("$ " + amount);
        logAmountText.setForeground(Color.decode("#FFFFFF")); // Set text color for the amount label

        // Create a panel to contain the amount label
        JPanel amountPanel = new JPanel();
        amountPanel.setBounds(240, 0, 80, 45);
        amountPanel.add(logAmountText); // Add the amount label to the panel
        amountPanel.setLayout(new GridBagLayout()); // Center the amount label within the panel
        amountPanel.setBackground(Color.decode("#394861")); // Set the background color of the panel

        // Configure the log card panel
        this.setBackground(Color.decode("#D6DCE6")); // Set the background color of the log card
        this.add(logCategoryText); // Add the category label to the log card
        this.add(amountPanel); // Add the amount panel to the log card
        this.setLayout(null); // Use absolute positioning for layout
    }
}
