package view;

import view.components.ColouredButton;
import view.components.Heading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryView extends JPanel {
    private JButton addCategoryButton;
    private JTextField category;
    private JTextField percentage;

    public AddCategoryView() {
        JLabel addCategoryLabel = new Heading("Add Category", 25).getHeading();
        addCategoryLabel.setBounds(30, 30, 189, 33);

        JTextField newCategoryTextField = new JTextField("Category");
        newCategoryTextField.setBounds(30, 81, 233, 60);
        newCategoryTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        newCategoryTextField.setBackground(Color.decode("#D6DCE6"));
        category = newCategoryTextField;

        JTextField percentageTextField = new JTextField("%");
        percentageTextField.setBounds(270, 81, 99, 60);
        percentageTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        percentageTextField.setBackground(Color.decode("#D6DCE6"));
        percentage = percentageTextField;

        ColouredButton add = new ColouredButton("Add", "#1A1A1A",
                "#FFFFFF", 16);
        JButton addButton = add.getButton();
        addButton.setBounds(30, 158, 340, 60);
        addCategoryButton = addButton;

        this.add(addCategoryLabel);
        this.add(newCategoryTextField);
        this.add(percentageTextField);
        this.add(addCategoryButton);
        this.setLayout(null);
    }

    public JButton getAddCategoryButton() {
        return addCategoryButton;
    }

    public JTextField getCategory() {
        return category;
    }

    public JTextField getPercentage() {
        return percentage;
    }
}
