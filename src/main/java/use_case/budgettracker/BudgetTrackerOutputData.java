package use_case.budgettracker;

import entity.User;

import java.util.HashMap;

public class BudgetTrackerOutputData {
    private final double income;
    private final HashMap<String, Double> alreadySpentCategories;
    private final double unspent_income;
    private final boolean spent_more_than_income;

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

    public double getIncome() {return income;}

    public HashMap<String, Double> getAlreadySpentCategories() {return alreadySpentCategories;}

    public double getUnspent_income() {return unspent_income;}

    public boolean isSpent_more_than_income() {return spent_more_than_income;}

    public User getUser() {return user;}
}
