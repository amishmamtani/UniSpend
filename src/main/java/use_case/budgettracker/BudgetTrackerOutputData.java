package use_case.budgettracker;

import java.util.HashMap;
import java.util.Map;

public class BudgetTrackerOutputData {
    private final double income;
    private final HashMap<String, Double> alreadySpentCategories;
    private final double unspent_income;
    private final boolean spent_more_than_income;

    public BudgetTrackerOutputData( double income, HashMap<String, Double> alreadySpentCategories,
                                    double unspent_income,
                                    boolean spent_more_than_income) {
        this.income = income;
        this.alreadySpentCategories = alreadySpentCategories;
        this.unspent_income = unspent_income;
        this.spent_more_than_income = spent_more_than_income;
    }

    public double getIncome() {return income;}

    public HashMap<String, Double> getAlreadySpentCategories() {return alreadySpentCategories;}

    public double getUnspent_income() {return unspent_income;}

    public boolean isSpent_more_than_income() {return spent_more_than_income;}
}
