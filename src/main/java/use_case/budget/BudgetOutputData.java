package use_case.budget;

import entity.User;

import java.util.Map;

/**
 * Encapsulates the output data for a budget, including income, category allocations, savings, investments, and user details.
 */
public class BudgetOutputData {
    /** The user's total income */
    private final double income;

    /** The allocations across different spending categories */
    private final Map<String, Double> categoryAllocations;

    /** The amount allocated for savings */
    private final double savings;

    /** The amount allocated for investments */
    private final double investments;

    /** The user associated with this budget */
    private final User user;

    /**
     * Constructs a BudgetOutputData object with the specified income, category allocations, savings, investments, and user details.
     *
     * @param income             The user's total income.
     * @param categoryAllocations A map of categories and their allocated amounts.
     * @param savings            The amount allocated for savings.
     * @param investments        The amount allocated for investments.
     * @param user               The user associated with this budget.
     */
    public BudgetOutputData(double income, Map<String, Double> categoryAllocations, double savings, double investments, User user) {
        this.income = income;
        this.categoryAllocations = categoryAllocations;
        this.savings = savings;
        this.investments = investments;
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
     * Retrieves the allocations across different spending categories.
     *
     * @return A map of categories and their allocated amounts.
     */
    public Map<String, Double> getCategoryAllocations() {
        return categoryAllocations;
    }

    /**
     * Retrieves the amount allocated for savings.
     *
     * @return The savings amount.
     */
    public double getSavings() {
        return savings;
    }

    /**
     * Retrieves the amount allocated for investments.
     *
     * @return The investments amount.
     */
    public double getInvestments() {
        return investments;
    }

    /**
     * Retrieves the user associated with this budget.
     *
     * @return The user.
     */
    public User getUser() {
        return user;
    }
}
