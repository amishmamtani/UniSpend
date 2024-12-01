package entity;

import java.util.Map;

/**
 * Defines the interface for a budget, specifying methods to access income, category allocations, savings, and investments.
 */
public interface BudgetInterface {

    /**
     * Retrieves the total income associated with the budget.
     *
     * @return The total income.
     */
    double getIncome();

    /**
     * Retrieves the allocations of income across various spending categories.
     *
     * @return A map of category names and their allocated amounts.
     */
    Map<String, Double> getCategoryAllocations();

    /**
     * Retrieves the total savings in the budget.
     *
     * @return The savings amount.
     */
    double getSavings();

    /**
     * Retrieves the total investments in the budget.
     *
     * @return The investment amount.
     */
    double getInvestments();
}
