package use_case.budget;

import entity.User;

import java.util.Map;

/**
 * Encapsulates the input data required for creating a budget.
 */
public class BudgetInputData {
    /** The user's total income */
    private final double income;

    /** The selected categories and their allocations */
    private final Map<String, Double> selectedCategories;

    /** The user for whom the budget is being created */
    private final User user;

    /**
     * Constructs a BudgetInputData object with the specified income, selected categories, and user information.
     *
     * @param income             The user's total income.
     * @param selectedCategories A map of categories and their allocated amounts.
     * @param user               The user for whom the budget is being created.
     */
    public BudgetInputData(double income, Map<String, Double> selectedCategories, User user) {
        this.income = income;
        this.selectedCategories = selectedCategories;
        this.user = user;
    }

    /**
     * Retrieves the user's total income.
     *
     * @return The total income.
     */
    public double getIncome() {
        return income;
    }

    /**
     * Retrieves the selected categories and their allocations.
     *
     * @return A map of categories and their allocated amounts.
     */
    public Map<String, Double> getSelectedCategories() {
        return selectedCategories;
    }

    /**
     * Retrieves the user for whom the budget is being created.
     *
     * @return The user.
     */
    public User getUser() {
        return user;
    }
}
