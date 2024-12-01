package use_case.budgettracker;

import entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates the input data required for managing a budget tracker
 *
 */
public class BudgetTrackerInputData {
    /** Users income*/
    private final double income;
    /** Categories and amounts the user has spent on*/
    private final HashMap<String, Double> alreadySpentCategories;
    /** The amount the user just spent*/
    private final double amount_spent;
    /** The category the user just spent on*/
    private final String category_spent_on;
    /** The current user */
    private final User user;


    /**
     * Constructs a BudgetTrackerInputData object with income, spending details, and user information.
     *
     * @param income                User's income.
     * @param alreadySpentCategories Categories and the amounts already spent by the user.
     * @param amount_spent          The amount the user just spent.
     * @param category_spent_on     The category the user just spent on.
     * @param user                  The current user.
     */
    public BudgetTrackerInputData(double income, HashMap<String, Double> alreadySpentCategories, double amount_spent,
                                  String category_spent_on, User user) {
        this.income = income;
        this.alreadySpentCategories = alreadySpentCategories;
        this.amount_spent = amount_spent;
        this.category_spent_on = category_spent_on.toUpperCase();
        this.user = user;
    }

    /** @return the users income*/
    public double getIncome() { return income; }

    /** @return the categories and the amounts the user has spent on*/
    public HashMap<String, Double> getAlreadySpentCategories() { return alreadySpentCategories; }

    /** @return  the amount the user just spent*/
    public double getAmount_spent() {return amount_spent;}

    /** @return the category the user just spent on*/
    public String getCategory_spent_on() {return category_spent_on;}

    /** Returns the amount the current user
     * @return current user*/
    public User getUser() {return user;}
}
