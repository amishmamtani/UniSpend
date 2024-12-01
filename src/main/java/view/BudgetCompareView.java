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

public class BudgetCompareView {
    private final BudgetCompareViewModel viewModel;
    private final BudgetCompareController controller;
    private final User user;

    public BudgetCompareView(BudgetCompareViewModel viewModel, BudgetCompareController controller, User user) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.user = user;

        JLabel titleLabel = new Heading("Spending Analysis", 30).getHeading();
        titleLabel.setBounds(90, 43, 230, 43);

        this.controller.createBudgetCompare(this.user);
        final BudgetCompareState currentState = this.viewModel.getState();
        HashMap<String, Double> finalSpentCategories = new HashMap<>();
        for (String key: currentState.getAdvisedAllocations().keySet()) {
            String uppercaseKey = key.toUpperCase();
            Double value = currentState.getAdvisedAllocations().get(key);
            currentState.getAdvisedAllocations().remove(key);
            currentState.getAdvisedAllocations().put(uppercaseKey, value);

            for (String key2  : currentState.getSpentAllocations().keySet()) {
                if (key2.equals(key)) {
                    finalSpentCategories.put(key2, currentState.getSpentAllocations().get(key2));
                }
            }
        }
        BarChart barChart = new BarChart("A comparison of what you spent vs. what you were supposed to spend",
                currentState.getAdvisedAllocations(), finalSpentCategories);
        ChartPanel chartPanel = new ChartPanel(barChart.getBarChart());
        chartPanel.setPreferredSize(new java.awt.Dimension( 600 , 600));
        chartPanel.setBackground(Color.decode("#FFFFFF"));
        chartPanel.setBounds(34, 120, 600, 400);
        chartPanel.setVisible(true);

        JPanel comparePanel = new JPanel();
        comparePanel.add(titleLabel);
        comparePanel.add(chartPanel);
        JFrame frame = new JFrame();
        frame.setSize(800, 744);
        frame.setResizable(false);
        frame.add(comparePanel);
        frame.setVisible(true);

    }
}
