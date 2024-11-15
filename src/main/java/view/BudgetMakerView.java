package view;

import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetState;
import interface_adapter.budget.BudgetViewModel;
import use_case.budget.BudgetOutputBoundary;
import use_case.budget.BudgetOutputData;
import view.components.ColouredButton;
import view.components.Heading;
import view.components.PieChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;


public class BudgetMakerView extends JPanel implements ActionListener, PropertyChangeListener{

    private final BudgetViewModel BudgetviewModel;
    private final BudgetController budgetController;
    private final HashMap<String, Double> percentageCategories;
    public BudgetMakerView(BudgetViewModel budgetViewModel, BudgetController controller) {

        this.budgetController = controller;
        this.BudgetviewModel = budgetViewModel;
        this.percentageCategories = new HashMap<>();
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


        JPanel budgetMaker = new JPanel();
        budgetMaker.add(budgetTitleLabel);
        budgetMaker.add(allowanceLabel);
        budgetMaker.add(incomeTextField);
        budgetMaker.add(createBudgetButton);
        budgetMaker.setLayout(null);
        budgetMaker.setBackground(Color.decode("#FFFFFF"));

        createBudgetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create budget clicked");
                Double income = Double.parseDouble(incomeTextField.getText());
                System.out.println(income);
                Map<String, Boolean> selectedCategories = Map.of(
                        "Housing", true,
                        "Food", true,
                        "Transportation", true,
                        "Utilities", true,
                        "Entertainment", true,
                        "Healthcare", true,
                        "Savings", true,
                        "Investments", true);
//                BudgetOutputBoundary BPC = new BudgetOutputBoundary() {
//                    @Override
//                    public void presentBudget(BudgetOutputData outputData) {
//                        percentageCategories.put("Housing", outputData.getCategoryAllocations().get("Housing"));
//                        percentageCategories.put("Food", outputData.getCategoryAllocations().get("Food"));
//                        percentageCategories.put("Transportation", outputData.getCategoryAllocations().get("Transportation"));
//                        percentageCategories.put("Utilities", outputData.getCategoryAllocations().get("Utilities"));
//                        percentageCategories.put("Entertainment", outputData.getCategoryAllocations().get("Entertainment"));
//                        percentageCategories.put("Healthcare", outputData.getCategoryAllocations().get("Healthcare"));
//                        percentageCategories.put("Savings", outputData.getCategoryAllocations().get("Savings"));
//                        percentageCategories.put("Investments", outputData.getCategoryAllocations().get("Investments"));
//                    }
//                };
                budgetController.createBudget(income, selectedCategories);
                final BudgetState currentState = budgetViewModel.getState();
                PieChart pieChart = new PieChart("Monthly Budget", currentState.getCategoryAllocations());
                ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                chartPanel.setBackground(Color.decode("#FFFFFF"));
                chartPanel.setBounds(35, 233, 320, 320);
                chartPanel.setVisible(true);
                budgetMaker.add(chartPanel);
                budgetMaker.revalidate();
                budgetMaker.repaint();
            }
        });

        JFrame frame = new JFrame("Budget Maker");
        frame.setSize(390, 744);
        frame.setResizable(false);
        frame.setContentPane(budgetMaker);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}