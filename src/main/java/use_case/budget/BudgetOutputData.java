package use_case.budget;

import entity.User;

import java.util.Map;

public class BudgetOutputData {
    private final double income;
    private final Map<String, Double> categoryAllocations;
    private final double savings;
    private final double investments;
    private final User user;

    public BudgetOutputData(double income, Map<String, Double> categoryAllocations, double savings, double investments, User user) {
        this.income = income;
        this.categoryAllocations = categoryAllocations;
        this.savings = savings;
        this.investments = investments;
        this.user = user;
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

    public User getUser() {return user;}
}
