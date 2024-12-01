package use_case.budgetcompare;

/**
 * Defines the output boundary for budget comparison use cases.
 */
public interface BudgetCompareOutputBoundary {

    /**
     * Presents the budget comparison data to the user or external system.
     *
     * @param budgetCompareOutputData The output data to be presented.
     */
    void presentBudgetCompare(BudgetCompareOutputData budgetCompareOutputData);
}
