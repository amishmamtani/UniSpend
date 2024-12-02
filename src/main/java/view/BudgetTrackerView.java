package view;

import app.EmailSender;
import entity.User;
import interface_adapter.budgetcompare.BudgetCompareController;
import interface_adapter.budgetcompare.BudgetComparePresenter;
import interface_adapter.budgetcompare.BudgetCompareViewModel;
import interface_adapter.budgettracker.BudgetTrackerController;
import interface_adapter.budgettracker.BudgetTrackerState;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import interface_adapter.chatbot.ChatBotState;
import interface_adapter.user.MongoUserRepository;
import io.github.cdimascio.dotenv.Dotenv;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

/**
 * A class representing the Budget Tracker view, allowing users to track their spending,
 * create a budget, and analyze their expenses.
 */
public class BudgetTrackerView extends JPanel implements ActionListener, PropertyChangeListener {

    /** The name of the view */
    private final String viewName = "budget tracker";

    /** The view model for managing the state of the Budget Tracker view */
    private final BudgetTrackerViewModel budgetTrackerViewModel;

    /** The controller for handling user actions in the Budget Tracker view */
    private final BudgetTrackerController budgetTrackerController;

    /** The user's income */
    private double income;

    /** Repository for managing user data */
    private final MongoUserRepository userRepository = new MongoUserRepository();

    /** The current user whose budget data is being displayed */
    private User currentUser;

    /** A variable to track the vertical distance for dynamic layout changes */
    private Integer distance = 0;

    /** The main panel containing the components of the Budget Tracker view */
    private final JPanel mainPanel;

    /**
     * Constructs a BudgetTrackerView instance with the specified view model and controller.
     *
     * @param viewModel The view model for managing the state of the Budget Tracker view.
     * @param controller The controller for handling user actions in the Budget Tracker view.
     */
    public BudgetTrackerView(BudgetTrackerViewModel viewModel, BudgetTrackerController controller) {
        this.budgetTrackerController = controller;
        this.budgetTrackerViewModel = viewModel;

        // Create the back button and configure its appearance
        ImageIcon backIcon = new ImageIcon("src/main/resources/back.png");
        JLabel backButton = new JLabel(backIcon);
        backButton.setBounds(28, 29, backIcon.getIconWidth(), backIcon.getIconHeight());

        // Create and configure the title label for the Budget Tracker view
        JLabel titleLabel = new Heading("Budget Tracker", 28).getHeading();
        titleLabel.setBounds(100, 23, 230, 43);

        // Create and configure the buttons for the Budget Tracker view
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

        // Add components to the main panel
        this.add(titleLabel);
        this.add(createNewButton);
        this.add(addButton);
        this.add(backButton);
        if (!(this.currentUser == null)) {
            if (currentUser.getBudgetTracker().size() > 0) {
                this.add(spendingAnalysisButton);
            }
        }
        this.setLayout(null);
        this.setBackground(Color.decode("#FFFFFF"));
        mainPanel = this;

        // Add listener for the back button
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                budgetTrackerController.switchBack();
            }
        });

        // Create and manage category spending
        HashMap<String, Double> categorySpending = new HashMap<>();

        // Add listener for the "Create New" button
        createNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create new clicked");
                System.out.println(viewModel.getState().getEmailId());
                currentUser = userRepository.getUserByEmail(viewModel.getState().getEmailId());
                income = currentUser.getIncome();
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
                mainPanel.setComponentZOrder(chartPanel, 0);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        // Add listener for the "Spending Analysis" button
        spendingAnalysisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("spending analysis clicked");
                BudgetCompareViewModel compareViewModel = new BudgetCompareViewModel();
                BudgetCompareOutputBoundary compareOutputBoundary = new BudgetComparePresenter(compareViewModel);
                BudgetCompareInteractor compareInteractor = new BudgetCompareInteractor(compareOutputBoundary);
                BudgetCompareController compareController = new BudgetCompareController(compareInteractor);
                new BudgetCompareView(compareViewModel, compareController, currentUser);
            }
        });

        // Add listener for the "Add" button
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
                        currentUser = userRepository.getUserByEmail(viewModel.getState().getEmailId());
                        income = currentUser.getIncome();

                        LogCard logCard = new LogCard(categorySpentOn, amountSpentTextField.getText());
                        logCard.setBounds(35, 95 + 50 * distance, 320, 45);
                        distance += 1;
                        budgetTrackerController.createBudgetTracker(income, currentUser.getBudgetTracker(), amountSpent,
                                categorySpentOn, currentUser);
                        final BudgetTrackerState currentState = budgetTrackerViewModel.getState();
                        System.out.println(currentState.getAlreadySpentCategories());
                        PieChart pieChart = new PieChart("Budget Tracker",
                                currentState.getAlreadySpentCategories());
                        ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                        chartPanel.setBackground(Color.decode("#FFFFFF"));
                        chartPanel.setBounds(400, 80, 380, 380);
                        chartPanel.setVisible(true);
                        mainPanel.add(spendingAnalysisButton);
                        mainPanel.add(logCard);
                        mainPanel.add(chartPanel);
                        mainPanel.setComponentZOrder(chartPanel, 0);

                        if (currentState.isSpent_more_than_income()) {
                            String userEmail = currentUser.getEmail();
                            String userFirstName = currentUser.getFirstName();
                            Dotenv dotenv = Dotenv.load();
                            try {
                                new EmailSender().sendEmail(userEmail,
                                        "Your wallet’s waving a little red flag \uD83D\uDEA9",
                                        "<html>Hi "+ userFirstName+ "!<br>"+dotenv.get(
                                                "OVER_BUDGET_EMAIL"));
                            } catch (EmailSender.InvalidEmailException ex) {
                                System.err.println(ex.getMessage());
                            }
                            JLabel warningLabel = new JLabel("Warning: you have spent more than your income.");
                            warningLabel.setBounds(420, 490, 331, 19);
                            warningLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                            warningLabel.setForeground(Color.decode("#FF0000"));
                            mainPanel.add(warningLabel);
                        }

                        mainPanel.revalidate();
                        mainPanel.repaint();
                        addPopUp.dispose();
                    }
                });
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty method for implementing ActionListener interface
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BudgetTrackerState state = (BudgetTrackerState) evt.getNewValue();
    }

    /**
     * Retrieves the view name for the Budget Tracker view.
     *
     * @return The name of the view.
     */
    public String getViewName() {
        return viewName;
    }
}
