package use_case.budgettracker;

import java.util.Map;

public class BudgetTrackerInputData {
    private final double income;
    private final Map<String, Boolean> selectedCategories;
    private final Map<String, Double> alreadySpentCategories;
//    private final double amount_spent;
//    private final String category_spent_on;

    public BudgetTrackerInputData(double income, Map<String, Boolean> selectedCategories, Map<String, Double> alreadySpentCategories) {
        this.income = income;
        this.selectedCategories = selectedCategories;
        this.alreadySpentCategories = alreadySpentCategories;
    }

    public double getIncome() { return income; }

    public Map<String, Boolean> getSelectedCategories() {
        return selectedCategories;
    }

    public Map<String, Double> getAlreadySpentCategories() { return alreadySpentCategories; }
}
