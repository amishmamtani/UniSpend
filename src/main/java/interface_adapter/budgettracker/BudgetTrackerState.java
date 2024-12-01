package interface_adapter.budgettracker;

import java.util.HashMap;

/**
 * Represents the state of a budget tracker, including income, spending details, and user information.
 */
public class BudgetTrackerState {
    /** The user's total income */
    private double income;

    /** A map of categories and their respective amounts already spent */
    private HashMap<String, Double> alreadySpentCategories;

    /** The amount of unspent income remaining */
    private double unspent_income;

    /** Indicates whether the user has spent more than their income */
    private boolean spent_more_than_income;

    /** The user's email ID */
    private String emailId;

    /**
     * Sets the user's total income.
     *
     * @param income The income amount to set.
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * Sets the map of categories and their respective amounts already spent.
     *
     * @param alreadySpentCategories A map of categories and spending amounts.
     */
    public void setAlreadySpentCategories(HashMap<String, Double> alreadySpentCategories) {
        this.alreadySpentCategories = alreadySpentCategories;
    }

    /**
     * Sets the amount of unspent income remaining.
     *
     * @param unspent_income The unspent income amount to set.
     */
    public void setUnspent_income(double unspent_income) {
        this.unspent_income = unspent_income;
    }

    /**
     * Sets whether the user has spent more than their income.
     *
     * @param spent_more_than_income A boolean indicating overspending.
     */
    public void setSpent_more_than_income(boolean spent_more_than_income) {
        this.spent_more_than_income = spent_more_than_income;
    }

    /**
     * Constructs a BudgetTrackerState with specified income, spending details, and overspending indicator.
     *
     * @param income                The user's total income.
     * @param alreadySpentCategories A map of categories and spending amounts.
     * @param unspent_income        The amount of unspent income remaining.
     * @param spent_more_than_income A boolean indicating overspending.
     */
    public BudgetTrackerState(double income, HashMap<String, Double> alreadySpentCategories,
                              double unspent_income, boolean spent_more_than_income) {
        this.income = income;
        this.alreadySpentCategories = alreadySpentCategories;
        this.unspent_income = unspent_income;
        this.spent_more_than_income = spent_more_than_income;
    }

    /**
     * Constructs an empty BudgetTrackerState with no initial values.
     */
    public BudgetTrackerState() {
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
     * Retrieves the map of categories and their respective amounts already spent.
     *
     * @return A map of spending categories and amounts.
     */
    public HashMap<String, Double> getAlreadySpentCategories() {
        return alreadySpentCategories;
    }

    /**
     * Retrieves the amount of unspent income remaining.
     *
     * @return The unspent income amount.
     */
    public double getUnspent_income() {
        return unspent_income;
    }

    /**
     * Indicates whether the user has spent more than their income.
     *
     * @return True if the user has overspent, false otherwise.
     */
    public boolean isSpent_more_than_income() {
        return spent_more_than_income;
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
