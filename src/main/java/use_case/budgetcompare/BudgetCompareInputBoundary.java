package use_case.budgetcompare;

/**
 * Defines the input boundary for budget comparison use cases.
 */
public interface BudgetCompareInputBoundary {

    /**
     * Creates a budget comparison based on the provided input data.
     *
     * @param budgetCompareInputData The input data required for the budget comparison.
     */
    void createBudgetCompare(BudgetCompareInputData budgetCompareInputData);
}
