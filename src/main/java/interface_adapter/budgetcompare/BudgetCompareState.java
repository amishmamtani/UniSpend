package interface_adapter.budgetcompare;

import java.util.HashMap;

/**
 * Represents the state of a budget comparison, including advised allocations, spent allocations,
 * net allocations, and unspent income.
 */
public class BudgetCompareState {
    /** The advised allocations for the budget categories */
    private HashMap<String, Double> advisedAllocations;

    /** The actual spending allocations for the budget categories */
    private HashMap<String, Double> spentAllocations;

    /** The net difference between advised and spent allocations */
    private HashMap<String, Double> netAllocations;

    /** The amount of unspent income remaining */
    private double unspent_income;

    /**
     * Constructs a BudgetCompareState with the specified advised allocations, spent allocations,
     * net allocations, and unspent income.
     *
     * @param advisedAllocations The advised allocations for the budget categories.
     * @param spentAllocations   The actual spending allocations for the budget categories.
     * @param netAllocations     The net difference between advised and spent allocations.
     * @param unspent_income     The amount of unspent income remaining.
     */
    public BudgetCompareState(HashMap<String, Double> advisedAllocations, HashMap<String, Double> spentAllocations,
                              HashMap<String, Double> netAllocations, double unspent_income) {
        this.advisedAllocations = advisedAllocations;
        this.spentAllocations = spentAllocations;
        this.netAllocations = netAllocations;
        this.unspent_income = unspent_income;
    }

    /**
     * Constructs an empty BudgetCompareState with no initial values.
     */
    public BudgetCompareState() {
    }

    /**
     * Retrieves the advised allocations for the budget categories.
     *
     * @return A map of advised allocations.
     */
    public HashMap<String, Double> getAdvisedAllocations() {
        return advisedAllocations;
    }

    /**
     * Retrieves the actual spending allocations for the budget categories.
     *
     * @return A map of spent allocations.
     */
    public HashMap<String, Double> getSpentAllocations() {
        return spentAllocations;
    }

    /**
     * Retrieves the net difference between advised and spent allocations.
     *
     * @return A map of net allocations.
     */
    public HashMap<String, Double> getNetAllocations() {
        return netAllocations;
    }

    /**
     * Retrieves the amount of unspent income remaining.
     *
     * @return The unspent income amount.
     */
    public double getUnspent_income() {
        return unspent_income;
    }
}
