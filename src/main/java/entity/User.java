package entity;

import javax.swing.*;
import java.util.HashMap;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Double income;
    private HashMap<String, Double> budget;
    private HashMap<String, Double> budgetTracker;

    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.income = 0.0;
        this.budget = new HashMap<>();
        this.budgetTracker = new HashMap<>();
    }

    public User(String firstName, String lastName, String password, String email, Double income) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.income = income;
        this.budget = new HashMap<>();
        this.budgetTracker = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public Double getIncome() {
        return income;
    }
    public void setIncome(Double income) {
        this.income = income;
    }

    public HashMap<String, Double> getBudget() {
        return budget;
    }
    public void setBudget(HashMap<String, Double> budget) {
        this.budget = budget;
    }
    public HashMap<String, Double> getBudgetTracker() {
        return budgetTracker;
    }
    public void setBudgetTracker(HashMap<String, Double> budgetTracker) {
        this.budgetTracker = budgetTracker;
    }


}
