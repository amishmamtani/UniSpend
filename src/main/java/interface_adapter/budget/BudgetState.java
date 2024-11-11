package interface_adapter.budget;

import java.util.Map;

public class BudgetState {
    private double income;
    private Map<String, Double> categoryAllocations;
    private double savings;
    private double investments;

    public BudgetState(double income, Map<String, Double> categoryAllocations, double savings, double investments) {
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
