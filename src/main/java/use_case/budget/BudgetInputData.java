package use_case.budget;

import java.util.Map;

public class BudgetInputData {
    private final double income;
    private final Map<String, Double> selectedCategories;

    public BudgetInputData(double income, Map<String, Double> selectedCategories) {
        this.income = income;
        this.selectedCategories = selectedCategories;
    }

    public double getIncome() {
        return income;
    }

    public Map<String, Double> getSelectedCategories() {
        return selectedCategories;
    }
}
