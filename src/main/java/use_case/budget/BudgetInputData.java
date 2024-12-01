package use_case.budget;

import entity.User;

import java.util.Map;

public class BudgetInputData {
    private final double income;
    private final Map<String, Double> selectedCategories;
    private final User user;


    public BudgetInputData(double income, Map<String, Double> selectedCategories, User user) {
        this.income = income;
        this.selectedCategories = selectedCategories;
        this.user = user;
    }

    public double getIncome() {
        return income;
    }

    public Map<String, Double> getSelectedCategories() {
        return selectedCategories;
    }

    public User getUser() {return user; }
}
