package use_case.budgettracker;

import java.util.HashMap;
import java.util.Map;

public class BudgetTrackerInputData {
    private final double income;
    private final HashMap<String, Double> alreadySpentCategories;
    private final double amount_spent;
    private final String category_spent_on;

    public BudgetTrackerInputData(double income, HashMap<String, Double> alreadySpentCategories, double amount_spent,
                                  String category_spent_on) {
        this.income = income;
        this.alreadySpentCategories = alreadySpentCategories;
        this.amount_spent = amount_spent;
        this.category_spent_on = category_spent_on.toUpperCase();
    }

    public double getIncome() { return income; }

    public HashMap<String, Double> getAlreadySpentCategories() { return alreadySpentCategories; }

    public double getAmount_spent() {return amount_spent;}

    public String getCategory_spent_on() {return category_spent_on;}
}
