package view;

import interface_adapter.budgettracker.BudgetTrackerController;
import interface_adapter.budgettracker.BudgetTrackerState;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import org.jfree.chart.ChartPanel;
import view.components.ColouredButton;
import view.components.Heading;
import view.components.PieChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class BudgetTrackerView extends JPanel implements ActionListener, PropertyChangeListener {
    private final BudgetTrackerViewModel budgetTrackerViewModel;
    private final BudgetTrackerController budgetTrackerController;
//    private HashMap<String, Double> alreadySpentCategories;
    private double income;

    public BudgetTrackerView(BudgetTrackerViewModel viewModel, BudgetTrackerController controller) {
        this.budgetTrackerController = controller;
        this.budgetTrackerViewModel = viewModel;
//        this.alreadySpentCategories = new HashMap<>();

        JLabel titleLabel = new Heading("Budget Tracker", 30).getHeading();
        titleLabel.setBounds(99, 43, 191, 43);

        ColouredButton createNew = new ColouredButton("Create New", "#1A1A1A", "#FFFFFF", 18);
        JButton createNewButton = createNew.getButton();
        createNewButton.setBounds(35, 629, 133, 60);

        ColouredButton add = new ColouredButton("Add", "#1A1A1A", "#FFFFFF", 18);
        JButton addButton = add.getButton();
        addButton.setBounds(224, 629, 133, 60);

        JPanel budgettracker = new JPanel();
        budgettracker.add(titleLabel);
        budgettracker.add(createNewButton);
        budgettracker.add(addButton);
        budgettracker.setLayout(null);
        budgettracker.setBackground(Color.decode("#FFFFFF"));

        HashMap<String, Double> categorySpending1 = new HashMap<>();
        createNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create new clicked");
                JFrame createNewPopUp = new JFrame();
                createNewPopUp.setSize(400, 470);
                JLabel createNewTitleLabel = new Heading("New Budget Tracker", 30).getHeading();
                createNewTitleLabel.setBounds(30, 30, 245, 35);

                JLabel salaryLabel = new JLabel("Enter your salary or allowance: ");
                salaryLabel.setBounds(30, 79, 209, 19);
                salaryLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                JTextField salaryTextField = new JTextField();
                salaryTextField.setBounds(30, 112, 320, 60);
                salaryTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                salaryTextField.setBackground(Color.decode("#D6DCE6"));

                ColouredButton createNew = new ColouredButton("Create New", "#1A1A1A", "#FFFFFF", 18);
                JButton createNewButton = createNew.getButton();
                createNewButton.setBounds(30, 353, 340, 60);

                JPanel createNewPanel = new JPanel();
                createNewPanel.add(createNewTitleLabel);
                createNewPanel.add(salaryLabel);
                createNewPanel.add(salaryTextField);
                createNewPanel.add(createNewButton);
                createNewPanel.setLayout(null);
                createNewPanel.setBackground(Color.decode("#FFFFFF"));
                createNewPanel.setVisible(true);
                createNewPopUp.add(createNewPanel);
                createNewPopUp.setVisible(true);


                createNewButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("create new clicked");
                        income = Double.parseDouble(salaryTextField.getText());
                        System.out.println(income);
                        categorySpending1.put("UNSPENT INCOME", income);

                        budgetTrackerController.createBudgetTracker(income, categorySpending1, 0.0, "none");
                        final BudgetTrackerState currentState = budgetTrackerViewModel.getState();
                        PieChart pieChart = new PieChart("Budget Tracker", currentState.getAlreadySpentCategories());
                        ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                        chartPanel.setBackground(Color.decode("#FFFFFF"));
                        chartPanel.setBounds(34, 119, 321, 371);
                        chartPanel.setVisible(true);
                        createNewPanel.setVisible(false);
                        budgettracker.add(chartPanel);
                        budgettracker.revalidate();
                        budgettracker.repaint();
                        createNewPopUp.dispose();
                    }
                });
            }
        });

//        HashMap<String, Double> categorySpending = new HashMap<>(categorySpending1);
//        System.out.println(categorySpending);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("add clicked");
                JFrame addPopUp = new JFrame();
                addPopUp.setSize(400, 470);
                JLabel addTitleLabel = new Heading("Update Tracker", 30).getHeading();
                addTitleLabel.setBounds(30, 30, 186, 35);

                JLabel amountSpentLabel = new JLabel("Enter your spending: ");
                amountSpentLabel.setBounds(30, 82, 141, 19);
                amountSpentLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                JTextField amountSpentTextField = new JTextField();
                amountSpentTextField.setBounds(30, 118, 320, 60);
                amountSpentTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                amountSpentTextField.setBackground(Color.decode("#D6DCE6"));

                JLabel categorySpentLabel = new JLabel("Enter which category you spent this in: ");
                categorySpentLabel.setBounds(30, 207, 264, 19);
                categorySpentLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                JTextField categorySpentTextField = new JTextField();
                categorySpentTextField.setBounds(30, 244, 320, 60);
                categorySpentTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                categorySpentTextField.setBackground(Color.decode("#D6DCE6"));

                ColouredButton add = new ColouredButton("Add", "#1A1A1A", "#FFFFFF", 18);
                JButton addButton = add.getButton();
                addButton.setBounds(30, 353, 340, 60);

                JPanel addPanel = new JPanel();
                addPanel.add(addTitleLabel);
                addPanel.add(amountSpentLabel);
                addPanel.add(amountSpentTextField);
                addPanel.add(categorySpentLabel);
                addPanel.add(categorySpentTextField);
                addPanel.add(addButton);
                addPanel.setLayout(null);
                addPanel.setBackground(Color.decode("#FFFFFF"));
                addPanel.setVisible(true);
                addPopUp.add(addPanel);
                addPopUp.setVisible(true);

                System.out.println(categorySpending1);
                addButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("add clicked");
                        Double amountSpent = Double.parseDouble(amountSpentTextField.getText());
                        String categorySpentOn = categorySpentTextField.getText();
                        System.out.println(categorySpending1);
                        budgetTrackerController.createBudgetTracker(income, categorySpending1, amountSpent, categorySpentOn);
                        final BudgetTrackerState currentState = budgetTrackerViewModel.getState();
                        HashMap<String, Double> categorySpending1 = new HashMap<>(currentState.getAlreadySpentCategories());
                        System.out.println(currentState.getAlreadySpentCategories());
                        PieChart pieChart = new PieChart("Budget Tracker", currentState.getAlreadySpentCategories());
                        ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                        chartPanel.setBackground(Color.decode("#FFFFFF"));
                        chartPanel.setBounds(34, 119, 321, 371);
                        chartPanel.setVisible(true);
                        budgettracker.add(chartPanel);

                        if (currentState.isSpent_more_than_income()) {
                            JLabel warningLabel = new JLabel("Warning: you have spent more than your income.");
                            warningLabel.setBounds(36,490, 331,19);
                            warningLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                            warningLabel.setForeground(Color.decode("#FF0000"));
                            budgettracker.add(warningLabel);
                        }

                        budgettracker.revalidate();
                        budgettracker.repaint();
                        addPopUp.dispose();
                    }
                });
            }
        });

        JFrame frame = new JFrame("Budget Tracker");
        frame.setSize(390, 744);
        frame.setResizable(false);
        frame.setContentPane(budgettracker);
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