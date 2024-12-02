package view;

import view.components.ColouredButton;
import view.components.Heading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class representing the view for adding a new category with a percentage.
 * This view allows the user to input a category name and the corresponding percentage,
 * and provides a button to add the new category.
 */
public class AddCategoryView extends JPanel {

    /** Button to add a new category */
    private JButton addCategoryButton;

    /** Text field for entering the category name */
    private JTextField category;

    /** Text field for entering the percentage associated with the category */
    private JTextField percentage;

    /**
     * Constructs the AddCategoryView with the necessary components,
     * including labels, text fields, and a button.
     */
    public AddCategoryView() {
        // Create and configure the heading label
        JLabel addCategoryLabel = new Heading("Add Category", 25).getHeading();
        addCategoryLabel.setBounds(30, 30, 189, 33);

        // Create and configure the text field for entering the category name
        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(30, 71, 189, 33);
        JTextField newCategoryTextField = new JTextField();
        newCategoryTextField.setBounds(30, 111, 233, 60);
        newCategoryTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        newCategoryTextField.setBackground(Color.decode("#D6DCE6")); // Set background color
        category = newCategoryTextField;

        // Create and configure the text field for entering the percentage
        JLabel percentageLabel = new JLabel("%");
        percentageLabel.setBounds(270, 71, 40, 33);
        JTextField percentageTextField = new JTextField();
        percentageTextField.setBounds(270, 111, 99, 60);
        percentageTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        percentageTextField.setBackground(Color.decode("#D6DCE6")); // Set background color
        percentage = percentageTextField;

        // Create a coloured button for adding the category
        ColouredButton add = new ColouredButton("Add", "#1A1A1A", "#FFFFFF", 16);
        JButton addButton = add.getButton();
        addButton.setBounds(30, 200, 340, 60); // Set position and size of the button
        addCategoryButton = addButton;

        // Add all components to the panel
        this.add(addCategoryLabel);
        this.add(categoryLabel);
        this.add(percentageLabel);
        this.add(newCategoryTextField);
        this.add(percentageTextField);
        this.add(addCategoryButton);

        // Set the layout manager to null for absolute positioning
        this.setLayout(null);
    }

    /**
     * Retrieves the add category button.
     *
     * @return The JButton instance for adding the category.
     */
    public JButton getAddCategoryButton() {
        return addCategoryButton;
    }

    /**
     * Retrieves the text field for the category name.
     *
     * @return The JTextField for entering the category name.
     */
    public JTextField getCategory() {
        return category;
    }

    /**
     * Retrieves the text field for the percentage.
     *
     * @return The JTextField for entering the percentage.
     */
    public JTextField getPercentage() {
        return percentage;
    }
}
