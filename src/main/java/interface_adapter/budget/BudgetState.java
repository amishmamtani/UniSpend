package interface_adapter.budget;

import java.util.HashMap;
import java.util.Map;

public class BudgetState {
    private double income;
    private HashMap<String, Double> categoryAllocations;
    private double savings;
    private double investments;
    private String emailId;

    public BudgetState(double income, HashMap<String, Double> categoryAllocations, double savings, double investments) {
        this.income = income;
        this.categoryAllocations = categoryAllocations;
        this.savings = savings;
        this.investments = investments;
    }

    public BudgetState() {
    }
    public void setIncome(double income) {this.income = income;}
    public void setCategoryAllocations(HashMap<String, Double> categoryAllocations) {this.categoryAllocations = categoryAllocations;}
    public void setSavings(double savings) {this.savings = savings;}
    public void setInvestments(double investments) {this.investments = investments;}


    public double getIncome() {
        return income;
    }

    public HashMap<String, Double> getCategoryAllocations() {
        return categoryAllocations;
    }

    public double getSavings() {
        return savings;
    }

    public double getInvestments() {
        return investments;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
