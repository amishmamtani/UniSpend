package view;

import entity.User;
import interface_adapter.user.MongoUserRepository;
import org.jfree.chart.ChartPanel;
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
    public BudgetCompareView(BudgetCompareViewModel viewModel, BudgetCompareController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        MongoUserRepository userRepository = new MongoUserRepository();
        this.user = userRepository.getUserByLastName("K");

        JLabel titleLabel = new Heading("Spending Analysis", 30).getHeading();
        titleLabel.setBounds(90, 43, 230, 43);

//        System.out.println(user.getBudget());
        BarChart barChart = new BarChart("A comparison of what you spent vs. what you were supposed to spend",
                user.getBudget(), user.getBudgetTracker());
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
