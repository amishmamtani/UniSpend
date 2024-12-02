package view;

import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetState;
import interface_adapter.budget.BudgetViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.user.MongoUserRepository;
import view.components.ColouredButton;
import view.components.Heading;
import view.components.PieChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for creating and managing the Budget Maker view, which allows the user to enter their budget details,
 * select categories, and create a monthly budget.
 */
public class BudgetView extends JPanel implements ActionListener, PropertyChangeListener {

    /** The name of the view */
    private final String viewName = "budget maker";

    /** The view model for managing the state of the Budget Maker view */
    private final BudgetViewModel budgetViewModel;

    /** The controller for handling user actions in the Budget Maker view */
    private final BudgetController budgetController;

    /** The home view model used for state management */
    private  HomeViewModel homeViewModel;

    /** A map containing the categories and their respective percentage values */
    private final Map<String, Double> percentageCategories;

    /** The JFrame used for the "Add Category" pop-up window */
    private JFrame addCategoryPopUp = new JFrame();

    /** The main panel containing the components of the view */
    private JPanel mainPanel;

    /** The y-coordinate offset for positioning dynamically added checkboxes */
    private int disctance = 0;

    /**
     * Constructs a BudgetMakerView instance with the specified view model and controller.
     *
     * @param budgetViewModel The view model for managing the state of the Budget Maker view.
     * @param controller The controller for handling user actions in the Budget Maker view.
     */
    public BudgetView(BudgetViewModel budgetViewModel, BudgetController controller) {
        this.budgetController = controller;
        this.budgetViewModel = budgetViewModel;
        this.percentageCategories = new HashMap<>(Map.of(
                "Housing", 1.0,
                "Food", 1.0,
                "Transportation", 1.0,
                "Utilities", 1.0,
                "Entertainment", 1.0,
                "Healthcare", 1.0));

        // Main budget maker user interface
        ImageIcon backIcon = new ImageIcon("src/main/resources/back.png");
        JLabel backButton = new JLabel(backIcon);
        backButton.setBounds(28, 29, backIcon.getIconWidth(), backIcon.getIconHeight());

        // Create and configure the budget title label
        JLabel budgetTitleLabel = new Heading("Budget Maker", 28).getHeading();
        budgetTitleLabel.setBounds(90, 23, 191, 43);

        // Create and configure the income label and text field
        JLabel allowanceLabel = new JLabel("Enter your monthly allowance or salary: ");
        allowanceLabel.setBounds(35, 77, 300, 18);
        allowanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField incomeTextField = new JTextField();
        incomeTextField.setBounds(32, 107, 320, 60);
        incomeTextField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        incomeTextField.setBackground(Color.decode("#D6DCE6"));

        // Create and configure the category heading label
        JLabel categoryHeading = new Heading("Categories", 22).getHeading();
        categoryHeading.setBounds(35, 197, 200, 25);

        // Create and configure the checkboxes for each category
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

        // Create and configure the "Create Budget" button
        ColouredButton createBudget = new ColouredButton("Create Budget", "#1A1A1A", "#FFFFFF", 16);
        JButton createBudgetButton = createBudget.getButton();
        createBudgetButton.setBounds(32, 480, 320, 60);

        // Create and configure the "Add Category" button
        ColouredButton addCategory = new ColouredButton("＋ Add ", "#1A1A1A", "#FFFFFF", 15);
        JButton addCategoryButton = addCategory.getButton();
        addCategoryButton.setBounds(258, 192, 96, 38);

        // Add components to the main panel
        this.setSize(830, 600);
        this.add(backButton);
        this.add(budgetTitleLabel);
        this.add(allowanceLabel);
        this.add(incomeTextField);
        this.add(categoryHeading);
        this.add(housingCheckBox);
        this.add(foodCheckBox);
        this.add(transportCheckBox);
        this.add(utilityCheckBox);
        this.add(entertainmentCheckBox);
        this.add(healthcareCheckBox);
        this.add(createBudgetButton);
        this.add(addCategoryButton);
        this.setLayout(null);
        this.setBackground(Color.decode("#FFFFFF"));
        mainPanel = this;

        // Add listener for the back button
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                budgetController.switchBack();
            }
        });

        // Add listener for the "Create Budget" button
        createBudgetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create budget clicked");
                if(incomeTextField.getText().matches("[0-9]+")){
                    Double income = Double.parseDouble(incomeTextField.getText());
                    System.out.println(income);
                    Map<String, Double> selectedCategories = getSelectedCategories();
                    System.out.println(selectedCategories);
                    MongoUserRepository userRepository = new MongoUserRepository();
                    System.out.println("Budget Maker Email: " + budgetViewModel.getState().getEmailId());
                    budgetController.createBudget(income, selectedCategories, userRepository.getUserByEmail(budgetViewModel.getState().getEmailId()));
                    final BudgetState currentState = budgetViewModel.getState();

                    if(!currentState.getCategoryAllocations().containsKey("Impossible")){
                        PieChart pieChart = new PieChart("Monthly Budget", currentState.getCategoryAllocations());

                        // Create and display the pie chart
                        ChartPanel chartPanel = new ChartPanel(pieChart.getChart());
                        chartPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
                        chartPanel.setBackground(Color.decode("#FFFFFF"));
                        chartPanel.setBounds(400, 80, 380, 380);
                        chartPanel.setVisible(true);
                        mainPanel.add(chartPanel);
                        mainPanel.setComponentZOrder(chartPanel, 0);
                        mainPanel.repaint();
                        mainPanel.revalidate();
                    } else {
                        // Display a dialog if the budget is not feasible
                        JDialog dialog = new JDialog();
                        dialog.setTitle("");
                        dialog.setSize(310, 130);
                        dialog.setLayout(null);
                        dialog.setModal(true);
                        JLabel message = new JLabel("<html>To ensure all essential needs are met, " +
                                "the income entered should be higher. Please adjust the input accordingly.</html>");
                        message.setBounds(30, 10, 250, 80);
                        dialog.add(message);
                        dialog.setVisible(true);
                    }
                }
                else{

                    JDialog dialog = new JDialog();
                    dialog.setTitle("");
                    dialog.setSize(310, 130);
                    dialog.setLayout(null);
                    dialog.setModal(true);
                    JLabel message = new JLabel("Invalid Income");
                    message.setBounds(30, 10, 250, 80);
                    dialog.add(message);
                    dialog.setVisible(true);
                }

            }
        });

        // Add listener for the "Add Category" button
        addCategoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Open the Add Category pop-up window
                AddCategoryView addCategoryPopUpPanel = new AddCategoryView();
                addCategoryPopUp.setContentPane(addCategoryPopUpPanel);
                addCategoryPopUp.setSize(400, 318);
                addCategoryPopUp.setVisible(true);
                addCategoryPopUp.setResizable(false);

                // Add listener for the "Add Category" button in the pop-up
                addCategoryPopUpPanel.getAddCategoryButton().addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String category = addCategoryPopUpPanel.getCategory().getText();
                        if (category.isEmpty()){
                            JOptionPane.showMessageDialog(null,
                                    "Please enter a valid category.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else if(!addCategoryPopUpPanel.getPercentage().getText().matches("[0-9]+")){
                            JOptionPane.showMessageDialog(null,
                                    "Invalid Percentage",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            Double percentage = Double.parseDouble(addCategoryPopUpPanel.getPercentage().getText());
                            addCategory(category, percentage);
                            addCategoryPopUp.dispose();
                        }
                    }
                });
            }
        });
    }

    /**
     * Adds a new category to the list with the specified name and percentage.
     *
     * @param category The name of the new category.
     * @param percentage The percentage for the new category.
     */
    private void addCategory(String category, Double percentage) {
        JCheckBox checkBox = new JCheckBox(category);
        checkBox.setSelected(true);
        checkBox.setBounds(200, 258 + 30 * disctance, 200, 18);
        disctance = disctance + 1;
        percentageCategories.put(category, percentage / 100);
        this.add(checkBox);
        this.revalidate();
        this.repaint();
    }

    /**
     * Retrieves the selected categories and their corresponding percentages.
     *
     * @return A map containing the selected categories and their respective percentages.
     */
    private Map<String, Double> getSelectedCategories() {
        Map<String, Boolean> selectedCategories = new HashMap<>();
        for (Component component : this.getComponents()) {
            if (component instanceof JCheckBox) {
                selectedCategories.put(((JCheckBox) component).getText(), ((JCheckBox) component).isSelected());
            }
        }
        Map<String, Double> selectedPercentageCategories = new HashMap<>();
        for (String categories : selectedCategories.keySet()) {
            if (selectedCategories.get(categories)) {
                selectedPercentageCategories.put(categories, percentageCategories.get(categories));
            } else {
                selectedPercentageCategories.put(categories, 0.0);
            }
        }
        return selectedPercentageCategories;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty method for implementing ActionListener interface
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BudgetState state = (BudgetState) evt.getNewValue();
    }

    /**
     * Retrieves the view name for the Budget Maker view.
     *
     * @return The name of the view.
     */
    public String getViewName() {
        return viewName;
    }
}
