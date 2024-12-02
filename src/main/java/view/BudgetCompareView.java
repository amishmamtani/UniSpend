package view;

import entity.User;
import interface_adapter.budgetcompare.BudgetCompareState;
import interface_adapter.user.MongoUserRepository;
import org.jfree.chart.ChartPanel;
import use_case.login.LogInOutputData;
import view.components.BarChart;
import view.components.Heading;
import view.components.PieChart;

import use_case.budget.BudgetOutputData;
import use_case.budgettracker.BudgetTrackerOutputData;
import interface_adapter.budgetcompare.BudgetCompareController;
import interface_adapter.budgetcompare.BudgetComparePresenter;
import interface_adapter.budgetcompare.BudgetCompareViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for creating the Budget Compare view, which shows a comparison of advised and actual spending.
 */
public class BudgetCompareView {
    /** View model for managing the state of the Budget Compare view */
    private final BudgetCompareViewModel viewModel;

    /** Controller for handling user actions in the Budget Compare view */
    private final BudgetCompareController controller;

    /** The current user whose budget data is being displayed */
    private final User user;

    /**
     * Constructs a BudgetCompareView instance with the specified view model, controller, and user.
     *
     * @param viewModel The view model for managing the state of the Budget Compare view.
     * @param controller The controller for handling user actions in the Budget Compare view.
     * @param user The current user whose budget data is being displayed.
     */
    public BudgetCompareView(BudgetCompareViewModel viewModel, BudgetCompareController controller, User user) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.user = user;

        // Create the title label for the Budget Compare view
        JLabel titleLabel = new Heading("Spending Analysis", 30).getHeading();
        titleLabel.setBounds(90, 43, 230, 43);

        this.controller.createBudgetCompare(this.user);
        final BudgetCompareState currentState = this.viewModel.getState();
        HashMap<String, Double> finalAdvisedCategories = new HashMap<>();
        HashMap<String, Double> finalSpentCategories = new HashMap<>();

        for (String key: currentState.getAdvisedAllocations().keySet()) {
            finalAdvisedCategories.put(key.toUpperCase(), currentState.getAdvisedAllocations().get(key));
            if (currentState.getSpentAllocations().containsKey(key.toUpperCase())) {
                finalSpentCategories.put(key.toUpperCase(), currentState.getSpentAllocations().get(key.toUpperCase()));
            } else {
                finalSpentCategories.put(key.toUpperCase(), 0.0); // Add default if not found
            }
        }
        finalSpentCategories.put("SAVINGS", currentState.getSpentAllocations().get("UNSPENT INCOME"));

        BarChart barChart = new BarChart("A comparison of what you spent vs. what you were supposed to spend",
                finalAdvisedCategories, finalSpentCategories);
        ChartPanel chartPanel = new ChartPanel(barChart.getBarChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 600));
        chartPanel.setBackground(Color.decode("#FFFFFF"));
        chartPanel.setBounds(34, 120, 600, 400);
        chartPanel.setVisible(true);

        // Create a panel to hold the title label and chart panel
        JPanel comparePanel = new JPanel();
        comparePanel.add(titleLabel);
        comparePanel.add(chartPanel);

        // Create the frame to display the comparison view
        JFrame frame = new JFrame();
        frame.setSize(800, 744);
        frame.setResizable(false);
        frame.add(comparePanel);
        frame.setVisible(true);
    }
}
