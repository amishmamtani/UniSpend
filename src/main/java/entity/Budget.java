package entity;

import java.util.Map;

public class Budget implements BudgetInterface {
    private final double income;
    private final Map<String, Double> categoryAllocations;
    private final double savings;
    private final double investments;

    public Budget(double income, Map<String, Double> categoryAllocations, double savings, double investments) {
        this.income = income;
        this.categoryAllocations = categoryAllocations;
        this.savings = savings;
        this.investments = investments;
    }

    @Override
    public double getIncome() {
        return income;
    }

    @Override
    public Map<String, Double> getCategoryAllocations() {
        return categoryAllocations;
    }

    @Override
    public double getSavings() {
        return savings;
    }

    @Override
    public double getInvestments() {
        return investments;
    }
}

