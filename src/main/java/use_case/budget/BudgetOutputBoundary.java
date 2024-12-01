package use_case.budget;

/**
 * Defines the output boundary for budget-related use cases.
 */
public interface BudgetOutputBoundary {

    /**
     * Presents the budget data to the user or external system.
     *
     * @param outputData The output data to be presented.
     */
    void presentBudget(BudgetOutputData outputData);

    /**
     * Switches back to the previous view or state.
     */
    void switchBack();
}
