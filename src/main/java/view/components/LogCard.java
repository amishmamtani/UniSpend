package view.components;

import javax.swing.*;
import java.awt.*;

public class LogCard extends JPanel {
    public LogCard(String category, String amount) {
        JLabel logCategoryText = new JLabel(category);
        JLabel logAmountText = new JLabel("$ "+ amount);
        logCategoryText.setBounds(20, 13, 50, 20);
        //logAmountText.setBounds(270, 13, 50, 20);
        logAmountText.setForeground(Color.decode("#FFFFFF"));
        JPanel amountPanel = new JPanel();
        amountPanel.setBounds(240, 0, 80, 45);
        amountPanel.add(logAmountText);
        amountPanel.setLayout(new GridBagLayout());
        amountPanel.setBackground(Color.decode("#394861"));
        this.setBackground(Color.decode("#D6DCE6"));
        this.add(logCategoryText);
        this.add(amountPanel);
        this.setLayout(null);
    }
}
