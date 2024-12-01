package use_case.budgettracker;

/**
 * Defines the output boundary for the budget tracker
 */
public interface BudgetTrackerOutputBoundary {
    /**
     * Presents the budget tracker data to the user or external system.
     *
     * @param budgetTrackerOutputData the output data to be presented
     */
    void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData);
    /**
     * Switches back to the previous state or context of the budget tracker.
     */
    void switchBack();
}
