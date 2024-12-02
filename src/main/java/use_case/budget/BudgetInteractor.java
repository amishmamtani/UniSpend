package use_case.budget;

import app.MarketHealthService;
import entity.User;
import interface_adapter.user.MongoUserRepository;
import use_case.user.UserRepository;

import java.util.*;

/**
 * Interactor for handling budget creation and related operations.
 * Implements the BudgetInputBoundary interface.
 */
public class BudgetInteractor implements BudgetInputBoundary {

    /** Presenter responsible for formatting and displaying budget output */
    private final BudgetOutputBoundary budgetPresenter;

    /**
     * Constructs a BudgetInteractor with the specified presenter.
     *
     * @param budgetPresenter The presenter responsible for handling budget output.
     */
    public BudgetInteractor(BudgetOutputBoundary budgetPresenter) {
        this.budgetPresenter = budgetPresenter;
    }

    /**
     * Creates a budget based on the input data, including income, selected categories,
     * and economic indicators. Allocates spending to various categories and calculates
     * savings and investments.
     *
     * @param inputData The input data required to create the budget.
     */
    @Override
    public void createBudget(BudgetInputData inputData) {
        double income = inputData.getIncome();
        double spending = 0;
        User user = inputData.getUser();
        Map<String, Double> selectedCategories = inputData.getSelectedCategories();

        Map<String, Double> categoryAllocations = new HashMap<>();
        double marketHealth = MarketHealthService.getEconomicIndicator();
        double savingsPercentage = marketHealth > 2 ? 0.3 : 0.7;
        double investmentsPercentage = marketHealth > 2 ? 0.7 : 0.3;
        String[] essentials = new String[]{"Housing", "Food", "Transportations", "Healthcare", "Utilities"};
        double essentialNum = 0;

        Map<String, double[]> defaultAllocations = new LinkedHashMap<>(Map.of(
                "Housing", new double[]{0.3, 1000},
                "Food", new double[]{0.15, 200},
                "Transportation", new double[]{0.1, 0},
                "Utilities", new double[]{0.05, 0},
                "Entertainment", new double[]{0.05, 0},
                "Healthcare", new double[]{0.05, 0}
        ));

        // Adjust default allocations based on selected categories
        for (Map.Entry<String, Double> entry : selectedCategories.entrySet()) {
            if (defaultAllocations.containsKey(entry.getKey())) {
                if (entry.getValue() == 0) {
                    defaultAllocations.remove(entry.getKey());
                }
            } else {
                defaultAllocations.put(entry.getKey(), new double[]{entry.getValue(), 0});
            }
        }

        // Calculate total essential spending
        for (String essential : essentials) {
            if (defaultAllocations.containsKey(essential)) {
                essentialNum += Math.max(defaultAllocations.get(essential)[0] * income,
                        defaultAllocations.get(essential)[1]);
            }
        }

        // Handle scenario where essential spending exceeds income
        if (essentialNum > income) {
            categoryAllocations.put("Impossible", 1000.0);
            spending = income;
        }

        // Allocate budget to new categories
        for (Map.Entry<String, double[]> entry : defaultAllocations.entrySet()) {
            double allocation = entry.getValue()[0] * income;
            double minimum = entry.getValue()[1];

            if ((allocation + spending) <= income && allocation >= minimum) {
                categoryAllocations.put(entry.getKey(), allocation);
                spending += allocation;
            } else if ((minimum + spending) <= income) {
                categoryAllocations.put(entry.getKey(), minimum);
                spending += minimum;
            }
        }

        // Calculate savings and investments
        double savings = 0;
        double investments = 0;
        if (income > spending) {
            double difference = income - spending;
            savings = difference * savingsPercentage;
            investments = difference * investmentsPercentage;
            categoryAllocations.put("UNSPENT INCOME", savings);
            categoryAllocations.put("Investments", investments);
        }

        // Update user data and save to database
        user.setBudget((HashMap<String, Double>) categoryAllocations);
        user.setIncome(income);
        UserRepository userRepository = new MongoUserRepository();
        userRepository.saveUser(user);

        // Prepare and present output data
        BudgetOutputData outputData = new BudgetOutputData(income, categoryAllocations, savings, investments, user);
        budgetPresenter.presentBudget(outputData);
    }

    /**
     * Switches back to the previous state or view.
     */
    @Override
    public void switchBack() {
        budgetPresenter.switchBack();
    }
}
