package view;

import view.components.ColouredButton;
import view.components.Heading;
import view.components.PieChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class BudgetMakerView {
    private JPanel mainPanel;
    public BudgetMakerView() {

        JLabel budgetTitleLabel = new Heading("Budget Maker").getHeading();
        budgetTitleLabel.setBounds(99, 43, 191, 43);


        JLabel allowanceLabel = new JLabel("Enter your monthly allowance or salary: ");
        allowanceLabel.setBounds(35, 117, 300, 18);
        allowanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField incomeTextField = new JTextField();
        incomeTextField.setBounds(32, 147, 320, 60);
        incomeTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        incomeTextField.setBackground(Color.decode("#D6DCE6"));

        ColouredButton createBudget = new ColouredButton("Create Budget", "#1A1A1A", "#FFFFFF");
        JButton createBudgetButton = createBudget.getButton();
        createBudgetButton.setBounds(32, 618, 320, 60);

        HashMap<String, Double> data = new HashMap<>();
        data.put("January", 200.0);
        data.put("February", 150.0);
        data.put("March", 180.0);


        JPanel budgetMaker = new JPanel();
        budgetMaker.add(budgetTitleLabel);
        budgetMaker.add(allowanceLabel);
        budgetMaker.add(incomeTextField);
        budgetMaker.add(createBudgetButton);
        budgetMaker.setLayout(null);
        budgetMaker.setBackground(Color.decode("#FFFFFF"));
        this.mainPanel = budgetMaker;

        createBudgetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create budget clicked");
                Double income = Double.parseDouble(incomeTextField.getText());
                System.out.println(income);
                categories categories = new categories(income);
                System.out.println(categories.generateBudget());
                PieChart pieChart = new PieChart("Monthly Budget", categories.generateBudget());
                ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                chartPanel.setBackground(Color.decode("#FFFFFF"));
                chartPanel.setBounds(35, 233, 320, 320);
                chartPanel.setVisible(true);
                mainPanel.add(chartPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        JFrame frame = new JFrame("Budget Maker");
        frame.setSize(390, 744);
        frame.setContentPane(budgetMaker);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}