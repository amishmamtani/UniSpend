package use_case.budget;

import java.util.Map;

public class BudgetInputData {
    private final double income;
    private final Map<String, Boolean> selectedCategories;

    public BudgetInputData(double income, Map<String, Boolean> selectedCategories) {
        this.income = income;
        this.selectedCategories = selectedCategories;
    }

    public double getIncome() {
        return income;
    }

    public Map<String, Boolean> getSelectedCategories() {
        return selectedCategories;
    }
}
