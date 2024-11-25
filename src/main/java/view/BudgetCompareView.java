package view;

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
//    private final BudgetOutputData advisedSpending;
//    private final BudgetTrackerOutputData actualSpending;
    public BudgetCompareView(BudgetCompareViewModel viewModel, BudgetCompareController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
//        this.advisedSpending =  new BudgetOutputData(...).getCategoryAllocations();
//        this.actualSpending = new BudgetTrackerOutputData(...).getAlreadySpentCategories();

        JLabel titleLabel = new Heading("Spending Analysis", 30).getHeading();
        titleLabel.setBounds(90, 43, 230, 43);

//        BarChart barChart = new BarChart("Spending Analysis", advisedSpending, actualSpending);

        HashMap<String, Double> testdata1 = new HashMap<>(Map.of(
                "housing", 400.0,
                "food", 250.0,
                "utilities", 60.0));

        HashMap<String, Double> testdata2 = new HashMap<>(Map.of(
                "housing", 500.0,
                "food", 200.0,
                "utilities", 50.0));

        BarChart barChart = new BarChart("A comparison of what you spent vs. what you were supposed to spend", testdata1, testdata2);
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
