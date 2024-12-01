package interface_adapter.budgettracker;

import java.util.HashMap;

public class BudgetTrackerState {
    private double income;
    private HashMap<String, Double> alreadySpentCategories;
    private double unspent_income;
    private boolean spent_more_than_income;
    private String emailId;

    public void setIncome(double income) {
        this.income = income;
    }

    public void setAlreadySpentCategories(HashMap<String, Double> alreadySpentCategories) {
        this.alreadySpentCategories = alreadySpentCategories;
    }

    public void setUnspent_income(double unspent_income) {
        this.unspent_income = unspent_income;
    }

    public void setSpent_more_than_income(boolean spent_more_than_income) {
        this.spent_more_than_income = spent_more_than_income;
    }

    public BudgetTrackerState(double income, HashMap<String, Double> alreadySpentCategories, double unspent_income,
                              boolean spent_more_than_income) {
        this.income = income;
        this.alreadySpentCategories = alreadySpentCategories;
        this.unspent_income = unspent_income;
        this.spent_more_than_income = spent_more_than_income;
    }

    public BudgetTrackerState(){

    }

    public double getIncome() {return income;}

    public HashMap<String, Double> getAlreadySpentCategories() {
        return (HashMap<String, Double>) alreadySpentCategories;}

    public double getUnspent_income() {return unspent_income;}

    public boolean isSpent_more_than_income() {return spent_more_than_income;}

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
