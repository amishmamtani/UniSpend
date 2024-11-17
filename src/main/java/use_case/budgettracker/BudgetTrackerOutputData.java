package use_case.budgettracker;

import java.util.Map;

public class BudgetTrackerOutputData {
    private final double income;
    private final Map<String, Double> alreadySpentCategories;
    private final double unspent_income;

    public BudgetTrackerOutputData( double income, Map<String, Double> alreadySpentCategories, double unspent_income) {
        this.income = income;
        this.alreadySpentCategories = alreadySpentCategories;
        this.unspent_income = unspent_income;
    }

    public double getIncome() {return income;}

    public Map<String, Double> getAlreadySpentCategories() {return alreadySpentCategories;}

    public double getUnspent_income() {return unspent_income;}
}
