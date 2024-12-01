package use_case.budget;

/**
 * Defines the input boundary for budget-related use cases.
 */
public interface BudgetInputBoundary {

    /**
     * Creates a budget using the provided input data.
     *
     * @param inputData The input data required to create the budget.
     */
    void createBudget(BudgetInputData inputData);

    /**
     * Switches back to the previous view or state.
     */
    void switchBack();
}
