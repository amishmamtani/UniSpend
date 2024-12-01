package entity;

import java.util.Map;

/**
 * Represents a budget with income, category allocations, savings, and investments.
 */
public class Budget implements BudgetInterface {
    /** The total income of the user */
    private final double income;

    /** Allocations of income across different spending categories */
    private final Map<String, Double> categoryAllocations;

    /** The amount saved by the user */
    private final double savings;

    /** The amount invested by the user */
    private final double investments;

    /**
     * Constructs a Budget object with specified income, category allocations, savings, and investments.
     *
     * @param income              The total income of the user.
     * @param categoryAllocations A map representing the allocations of income across spending categories.
     * @param savings             The amount saved by the user.
     * @param investments         The amount invested by the user.
     */
    public Budget(double income, Map<String, Double> categoryAllocations, double savings, double investments) {
        this.income = income;
        this.categoryAllocations = categoryAllocations;
        this.savings = savings;
        this.investments = investments;
    }

    /**
     * Retrieves the total income of the user.
     *
     * @return The total income.
     */
    @Override
    public double getIncome() {
        return income;
    }

    /**
     * Retrieves the allocations of income across spending categories.
     *
     * @return A map of category names and their allocated amounts.
     */
    @Override
    public Map<String, Double> getCategoryAllocations() {
        return categoryAllocations;
    }

    /**
     * Retrieves the amount saved by the user.
     *
     * @return The savings amount.
     */
    @Override
    public double getSavings() {
        return savings;
    }

    /**
     * Retrieves the amount invested by the user.
     *
     * @return The investment amount.
     */
    @Override
    public double getInvestments() {
        return investments;
    }
}
