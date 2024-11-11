package use_case.budget;

import java.util.Map;

public class BudgetOutputData {
    private final double income;
    private final Map<String, Double> categoryAllocations;
    private final double savings;
    private final double investments;

    public BudgetOutputData(double income, Map<String, Double> categoryAllocations, double savings, double investments) {
        this.income = income;
        this.categoryAllocations = categoryAllocations;
        this.savings = savings;
        this.investments = investments;
    }

    public double getIncome() {
        return income;
    }

    public Map<String, Double> getCategoryAllocations() {
        return categoryAllocations;
    }

    public double getSavings() {
        return savings;
    }

    public double getInvestments() {
        return investments;
    }
}
