package view;

import app.EmailSender;
import entity.User;
import interface_adapter.budgetcompare.BudgetCompareController;
import interface_adapter.budgetcompare.BudgetComparePresenter;
import interface_adapter.budgetcompare.BudgetCompareViewModel;
import interface_adapter.budgettracker.BudgetTrackerController;
import interface_adapter.budgettracker.BudgetTrackerState;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import interface_adapter.user.MongoUserRepository;
import org.jfree.chart.ChartPanel;
import use_case.budgetcompare.BudgetCompareInteractor;
import use_case.budgetcompare.BudgetCompareOutputBoundary;
import view.components.ColouredButton;
import view.components.Heading;
import view.components.LogCard;
import view.components.PieChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class BudgetTrackerView extends JPanel implements ActionListener, PropertyChangeListener {
    private final BudgetTrackerViewModel budgetTrackerViewModel;
    private final BudgetTrackerController budgetTrackerController;
    private double income;
    private final MongoUserRepository userRepository = new MongoUserRepository();
    private User currentUser;
    private Integer distance = 0;

    public BudgetTrackerView(BudgetTrackerViewModel viewModel, BudgetTrackerController controller) {
        this.budgetTrackerController = controller;
        this.budgetTrackerViewModel = viewModel;
        // will change the next line to the name stored when logging in
        this.currentUser = userRepository.getUserByLastName("K");
        this.income = currentUser.getIncome();

        JLabel titleLabel = new Heading("Budget Tracker", 30).getHeading();
        titleLabel.setBounds(35, 23, 230, 43);

        ColouredButton createNew = new ColouredButton("Create New", "#1A1A1A", "#FFFFFF",
                18);
        JButton createNewButton = createNew.getButton();
        createNewButton.setBounds(35, 480, 150, 60);

        ColouredButton add = new ColouredButton("Add", "#1A1A1A", "#FFFFFF", 18);
        JButton addButton = add.getButton();
        addButton.setBounds(224, 480, 133, 60);

        ColouredButton spendingAnalysis = new ColouredButton("Spending Analysis", "#1A1A1A",
                "#FFFFFF", 10);
        JButton spendingAnalysisButton = spendingAnalysis.getButton();
        spendingAnalysisButton.setBounds(647, 35, 130, 30);

        this.add(titleLabel);
        this.add(createNewButton);
        this.add(addButton);

        if (currentUser.getBudgetTracker().size() > 0) {
            budgettracker.add(spendingAnalysisButton);
        }
        budgettracker.setLayout(null);
        budgettracker.setBackground(Color.decode("#FFFFFF"));

        HashMap<String, Double> categorySpending = new HashMap<>();
        createNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create new clicked");

                System.out.println(income);
                categorySpending.put("UNSPENT INCOME", income);

                budgetTrackerController.createBudgetTracker(income, categorySpending, 0.0,
                        "NONE", currentUser);
                final BudgetTrackerState currentState = budgetTrackerViewModel.getState();
                PieChart pieChart = new PieChart("Budget Tracker", currentState.getAlreadySpentCategories());
                ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                chartPanel.setBackground(Color.decode("#FFFFFF"));
                chartPanel.setBounds(400, 80, 380, 380);
                chartPanel.setVisible(true);
                mainPanel.add(spendingAnalysisButton);
                mainPanel.add(chartPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        spendingAnalysisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("spending analysis clicked");
                BudgetCompareViewModel compareViewModel = new BudgetCompareViewModel();
                BudgetCompareOutputBoundary compareOutputBoundary = new BudgetComparePresenter(compareViewModel);
                BudgetCompareInteractor compareInteractor = new BudgetCompareInteractor(compareOutputBoundary);
                BudgetCompareController compareController = new BudgetCompareController(compareInteractor);
                new BudgetCompareView(compareViewModel, compareController);
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("add clicked");
                JFrame addPopUp = new JFrame();
                addPopUp.setSize(380, 470);
                JLabel addTitleLabel = new Heading("Update Tracker", 30).getHeading();
                addTitleLabel.setBounds(30, 30, 220, 35);

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
                addButton.setBounds(30, 353, 320, 60);

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

                addButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("add clicked");
                        Double amountSpent = Double.parseDouble(amountSpentTextField.getText());
                        String categorySpentOn = categorySpentTextField.getText();

                        LogCard logCard = new LogCard(categorySpentOn, amountSpentTextField.getText());
                        logCard.setBounds(35,95+50*distance,320, 45);
                        distance += 1;

                        budgetTrackerController.createBudgetTracker(income, currentUser.getBudgetTracker(), amountSpent,
                                categorySpentOn, currentUser);
                        final BudgetTrackerState currentState = budgetTrackerViewModel.getState();
                        currentUser = userRepository.getUserByLastName("K");
                        System.out.println(currentState.getAlreadySpentCategories());
                        PieChart pieChart = new PieChart("Budget Tracker",
                                currentState.getAlreadySpentCategories());
                        ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                        chartPanel.setBackground(Color.decode("#FFFFFF"));
                        chartPanel.setBounds(400, 80, 380, 380);
                        chartPanel.setVisible(true);
                        budgettracker.add(logCard);
                        budgettracker.add(chartPanel);
                        budgettracker.setComponentZOrder(chartPanel, 0);

                        if (currentState.isSpent_more_than_income()) {
                            String userEmail = "amishmamtani@gmail.com";
                            String userFirstName = "Amish";
//                            String userEmail = currentUser.getEmail();
//                            String userFirstName = currentUser.getFirstName();
                            new EmailSender().sendEmail(userEmail,
                                    "Your wallet’s waving a little red flag \uD83D\uDEA9",
                                    "<html>Hi "+ userFirstName+ "!<br>"+System.getenv("OVER_BUDGET_EMAIL"));
                            JLabel warningLabel = new JLabel("Warning: you have spent more than your income.");
                            warningLabel.setBounds(420,490, 331,19);
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
        frame.setSize(830, 600);
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