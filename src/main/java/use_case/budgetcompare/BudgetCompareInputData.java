package use_case.budgetcompare;

import entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates the input data required for a budget comparison.
 */
public class BudgetCompareInputData {
    /** The advised allocations for the budget categories */
    private final HashMap<String, Double> advisedAllocations;

    /** The actual spending allocations for the budget categories */
    private final HashMap<String, Double> spentAllocations;

    /**
     * Constructs a BudgetCompareInputData object using the user's budget and budget tracker.
     *
     * @param user The user whose budget and spending data will be used for the comparison.
     */
    public BudgetCompareInputData(User user) {

        HashMap<String, Double> advisedAllocations1;
        advisedAllocations1 = user.getBudget();
        HashMap<String, Double> updatedAllocations = new HashMap<>();
        for (Map.Entry<String, Double> entry : advisedAllocations1.entrySet()) {
            // Convert the key to uppercase and add it to the new map
            updatedAllocations.put(entry.getKey().toUpperCase(), entry.getValue());
        }

        this.advisedAllocations = updatedAllocations;
        this.spentAllocations = user.getBudgetTracker();
    }

    /**
     * (Deprecated) Constructs a BudgetCompareInputData object with advised and spent allocations.
     */

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
}
