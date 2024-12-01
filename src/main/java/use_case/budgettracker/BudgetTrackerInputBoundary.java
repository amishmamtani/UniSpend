package use_case.budgettracker;

/**
 * Defines the input boundary for the budget tracker
 */
public interface BudgetTrackerInputBoundary {

    /**
     * Creates a budget tracker using the provided input data.
     *
     * @param budgetTrackerInputData the input data required to initialize the budget tracker
     */
    void createBudgetTracker(BudgetTrackerInputData budgetTrackerInputData);

    /**
     * Switches back to the home screen.
     */
    void switchBack();
}
