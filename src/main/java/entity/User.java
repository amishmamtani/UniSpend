package entity;

import java.util.HashMap;

/**
 * Represents a user with personal details, income, budget, and budget tracker.
 */
public class User {
    /** The user's first name */
    private String firstName;

    /** The user's last name */
    private String lastName;

    /** The user's password */
    private String password;

    /** The user's email */
    private String email;

    /** The user's income */
    private Double income;

    /** The user's budget allocations across categories */
    private HashMap<String, Double> budget;

    /** The user's budget tracker for spending */
    private HashMap<String, Double> budgetTracker;

    /**
     * Constructs a User object with personal details and initializes income, budget, and budget tracker.
     *
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param password  The user's password.
     * @param email     The user's email.
     */
    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.income = 0.0;
        this.budget = new HashMap<>();
        this.budgetTracker = new HashMap<>();
    }

    /**
     * Constructs a User object with personal details and specified income, initializing budget and budget tracker.
     *
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param password  The user's password.
     * @param email     The user's email.
     * @param income    The user's income.
     */
    public User(String firstName, String lastName, String password, String email, Double income) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.income = income;
        this.budget = new HashMap<>();
        this.budgetTracker = new HashMap<>();
    }

    /**
     * Retrieves the user's first name.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the user's last name.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the user's email.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the user's password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the user's income.
     *
     * @return The income amount.
     */
    public Double getIncome() {
        return income;
    }

    /**
     * Sets the user's income.
     *
     * @param income The income amount to set.
     */
    public void setIncome(Double income) {
        this.income = income;
    }

    /**
     * Retrieves the user's budget allocations.
     *
     * @return A map of category names and their allocated amounts.
     */
    public HashMap<String, Double> getBudget() {
        return budget;
    }

    /**
     * Sets the user's budget allocations.
     *
     * @param budget A map of category names and their allocated amounts.
     */
    public void setBudget(HashMap<String, Double> budget) {
        this.budget = budget;
    }

    /**
     * Retrieves the user's budget tracker.
     *
     * @return A map of category names and their tracked spending amounts.
     */
    public HashMap<String, Double> getBudgetTracker() {
        return budgetTracker;
    }

    /**
     * Sets the user's budget tracker.
     *
     * @param budgetTracker A map of category names and their tracked spending amounts.
     */
    public void setBudgetTracker(HashMap<String, Double> budgetTracker) {
        this.budgetTracker = budgetTracker;
    }
}
