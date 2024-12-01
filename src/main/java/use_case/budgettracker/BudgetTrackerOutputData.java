package use_case.budgettracker;

import entity.User;

import java.util.HashMap;

/**
 * Encapsulates the output data for a budget tracker
 */
public class BudgetTrackerOutputData {
    /** The user's total income */
    private final double income;
    /** The categories and the amounts already spent by the user */
    private final HashMap<String, Double> alreadySpentCategories;
    /** The amount of income left unspent */
    private final double unspent_income;
    /** true if the user has spent more than their income */
    private final boolean spent_more_than_income;

  
    /**
     * Constructs a BudgetTrackerOutputData object
     *
     * @param income                User's total income.
     * @param alreadySpentCategories Categories and the amounts already spent by the user.
     * @param unspent_income        The amount of income left unspent.
     * @param spent_more_than_income Indicates whether the user has overspent.
     * @param user the current user
     */
    private final User user;

    public BudgetTrackerOutputData(double income, HashMap<String, Double> alreadySpentCategories,
                                   double unspent_income,
                                   boolean spent_more_than_income, User user) {
        this.income = income;
        this.alreadySpentCategories = alreadySpentCategories;
        this.unspent_income = unspent_income;
        this.spent_more_than_income = spent_more_than_income;
        this.user = user;
    }

    /** @return the user's total income */
    public double getIncome() {return income;}

    /** @return the categories and the amounts already spent by the user */
    public HashMap<String, Double> getAlreadySpentCategories() {return alreadySpentCategories;}

    /** @return the amount of income left unspent */
    public double getUnspent_income() {return unspent_income;}

    /** @return whether the user has spent more than their income */
    public boolean isSpent_more_than_income() {return spent_more_than_income;}

    public User getUser() {return user;}
}
