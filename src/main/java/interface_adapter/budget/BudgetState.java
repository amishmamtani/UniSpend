package interface_adapter.budget;

import java.util.HashMap;

/**
 * Represents the state of a budget, including income, category allocations, savings, investments, and email ID.
 */
public class BudgetState {
    /** The user's total income */
    private double income;

    /** The user's allocations across spending categories */
    private HashMap<String, Double> categoryAllocations;

    /** The user's savings amount */
    private double savings;

    /** The user's investments amount */
    private double investments;

    /** The user's email ID */
    private String emailId;

    /**
     * Constructs a BudgetState with the specified income, category allocations, savings, and investments.
     *
     * @param income             The user's total income.
     * @param categoryAllocations A map of spending categories and their allocated amounts.
     * @param savings            The user's savings amount.
     * @param investments        The user's investments amount.
     */
    public BudgetState(double income, HashMap<String, Double> categoryAllocations, double savings, double investments) {
        this.income = income;
        this.categoryAllocations = categoryAllocations;
        this.savings = savings;
        this.investments = investments;
    }

    /**
     * Constructs an empty BudgetState with no initial values.
     */
    public BudgetState() {
    }

    /**
     * Sets the user's total income.
     *
     * @param income The income amount to set.
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * Sets the user's category allocations.
     *
     * @param categoryAllocations A map of spending categories and their allocated amounts.
     */
    public void setCategoryAllocations(HashMap<String, Double> categoryAllocations) {
        this.categoryAllocations = categoryAllocations;
    }

    /**
     * Sets the user's savings amount.
     *
     * @param savings The savings amount to set.
     */
    public void setSavings(double savings) {
        this.savings = savings;
    }

    /**
     * Sets the user's investments amount.
     *
     * @param investments The investments amount to set.
     */
    public void setInvestments(double investments) {
        this.investments = investments;
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
     * Retrieves the user's category allocations.
     *
     * @return A map of spending categories and their allocated amounts.
     */
    public HashMap<String, Double> getCategoryAllocations() {
        return categoryAllocations;
    }

    /**
     * Retrieves the user's savings amount.
     *
     * @return The savings amount.
     */
    public double getSavings() {
        return savings;
    }

    /**
     * Retrieves the user's investments amount.
     *
     * @return The investments amount.
     */
    public double getInvestments() {
        return investments;
    }

    /**
     * Retrieves the user's email ID.
     *
     * @return The email ID.
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Sets the user's email ID.
     *
     * @param emailId The email ID to set.
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
