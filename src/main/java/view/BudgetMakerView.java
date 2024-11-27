package view;

import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetState;
import interface_adapter.budget.BudgetViewModel;
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

    private final BudgetViewModel budgetViewModel;
    private final BudgetController budgetController;
    private final Map<String, Double> percentageCategories;
    private JFrame addCategoryPopUp = new JFrame();
    private JFrame mainFrame;
    private int x = 0;
    private JPanel mainPanel;


    public BudgetMakerView(BudgetViewModel budgetViewModel, BudgetController controller) {

        this.budgetController = controller;
        this.budgetViewModel = budgetViewModel;
        this.percentageCategories = new HashMap<>(Map.of(
                "Housing", 1.0,
                "Food", 1.0,
                "Transportation", 1.0,
                "Utilities", 1.0,
                "Entertainment", 1.0,
                "Healthcare", 1.0));

        // main budget maker user interface

        JLabel budgetTitleLabel = new Heading("Budget Maker", 28).getHeading();
        budgetTitleLabel.setBounds(35, 23, 191, 43);


        JLabel allowanceLabel = new JLabel("Enter your monthly allowance or salary: ");
        allowanceLabel.setBounds(35, 77, 300, 18);
        allowanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField incomeTextField = new JTextField();
        incomeTextField.setBounds(32, 107, 320, 60);
        incomeTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        incomeTextField.setBackground(Color.decode("#D6DCE6"));

        JLabel categoryHeading = new Heading("Categories", 22).getHeading();
        categoryHeading.setBounds(35, 197, 200, 25);

        JCheckBox housingCheckBox = new JCheckBox("Housing");
        housingCheckBox.setBounds(35, 258, 120, 18);
        housingCheckBox.setSelected(true);

        JCheckBox foodCheckBox = new JCheckBox("Food");
        foodCheckBox.setBounds(35, 288, 120, 18);
        foodCheckBox.setSelected(true);

        JCheckBox transportCheckBox = new JCheckBox("Transportation");
        transportCheckBox.setBounds(35, 318, 130, 18);
        transportCheckBox.setSelected(true);

        JCheckBox utilityCheckBox = new JCheckBox("Utilities");
        utilityCheckBox.setBounds(35, 348, 120, 18);
        utilityCheckBox.setSelected(true);

        JCheckBox entertainmentCheckBox = new JCheckBox("Entertainment");
        entertainmentCheckBox.setBounds(35, 378, 120, 18);
        entertainmentCheckBox.setSelected(true);

        JCheckBox healthcareCheckBox = new JCheckBox("Healthcare");
        healthcareCheckBox.setBounds(35, 408, 120, 18);
        healthcareCheckBox.setSelected(true);

        ColouredButton createBudget = new ColouredButton("Create Budget", "#1A1A1A",
                "#FFFFFF", 16);
        JButton createBudgetButton = createBudget.getButton();
        createBudgetButton.setBounds(32, 480, 320, 60);

        ColouredButton addCategory = new ColouredButton("＋ Add ", "#1A1A1A",
                "#FFFFFF", 15);
        JButton addCategoryButton = addCategory.getButton();
        addCategoryButton.setBounds(258,192, 96, 38);

        JPanel budgetMaker = new JPanel();
        budgetMaker.setSize(830, 600);
        budgetMaker.add(budgetTitleLabel);
        budgetMaker.add(allowanceLabel);
        budgetMaker.add(incomeTextField);
        budgetMaker.add(categoryHeading);
        budgetMaker.add(housingCheckBox);
        budgetMaker.add(foodCheckBox);
        budgetMaker.add(transportCheckBox);
        budgetMaker.add(utilityCheckBox);
        budgetMaker.add(entertainmentCheckBox);
        budgetMaker.add(healthcareCheckBox);
        budgetMaker.add(createBudgetButton);
        budgetMaker.add(addCategoryButton);
        budgetMaker.setLayout(null);
        budgetMaker.setBackground(Color.decode("#FFFFFF"));

        createBudgetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create budget clicked");
                Double income = Double.parseDouble(incomeTextField.getText());
                System.out.println(income);
                Map<String, Double> selectedCategories = getSelectedCategories();
                System.out.println(selectedCategories);
                budgetController.createBudget(income, selectedCategories);

                final BudgetState currentState = budgetViewModel.getState();

                //JFrame pieChartPopUp = new JFrame();
                PieChart pieChart = new PieChart("Monthly Budget", currentState.getCategoryAllocations());

                ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                chartPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
                chartPanel.setBackground(Color.decode("#FFFFFF"));
                chartPanel.setBounds(400, 80, 380, 380);
                chartPanel.setVisible(true);

                budgetMaker.add(chartPanel);
                budgetMaker.repaint();
                budgetMaker.revalidate();
            }
        });

        addCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AddCategoryView addCategoryPopUpPanel = new AddCategoryView();
                addCategoryPopUp.setContentPane(addCategoryPopUpPanel);
                addCategoryPopUp.setSize(400, 278);
                addCategoryPopUp.setVisible(true);
                addCategoryPopUp.setResizable(false);

                addCategoryPopUpPanel.getAddCategoryButton().addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String category = addCategoryPopUpPanel.getCategory().getText();
                        Double percentage = Double.parseDouble(addCategoryPopUpPanel.getPercentage().getText());
                        addCategory(category, percentage);
                        addCategoryPopUp.dispose();
                    }
                });
            }
        });

        JFrame frame = new JFrame("Budget Maker");
        frame.setSize(830, 600);
        frame.setResizable(false);
        frame.setContentPane(budgetMaker);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        mainFrame = frame;
        mainPanel = budgetMaker;
    }

    private void addCategory(String category, Double percentage) {
        JCheckBox checkBox = new JCheckBox(category);
        checkBox.setSelected(true);
        checkBox.setBounds(200, 258+30*x, 200, 18);
        x = x + 1;
        percentageCategories.put(category, percentage/100);
        mainPanel.add(checkBox);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private Map<String, Double> getSelectedCategories() {
        Map<String, Boolean> selectedCategories = new HashMap<>();
        for (Component component : mainPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                selectedCategories.put(((JCheckBox) component).getText(), ((JCheckBox) component).isSelected());
            }
        }
        Map<String, Double> selectedPercentageCategories = new HashMap<>();
        for (String categories : selectedCategories.keySet()) {
            if(selectedCategories.get(categories)){
                selectedPercentageCategories.put(categories, percentageCategories.get(categories));
            }
            else{
                selectedPercentageCategories.put(categories, 0.0);
            }
        }
        return selectedPercentageCategories;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}