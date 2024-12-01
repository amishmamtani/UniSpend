package use_case.budgetcompare;

import java.util.HashMap;
import java.util.Map;

/**
 * Interactor for handling budget comparison operations.
 * Implements the BudgetCompareInputBoundary interface.
 */
public class BudgetCompareInteractor implements BudgetCompareInputBoundary {

    /** Presenter responsible for formatting and displaying budget comparison output */
    private final BudgetCompareOutputBoundary budgetComparePresenter;

    /**
     * Constructs a BudgetCompareInteractor with the specified presenter.
     *
     * @param budgetComparePresenter The presenter responsible for budget comparison output.
     */
    public BudgetCompareInteractor(BudgetCompareOutputBoundary budgetComparePresenter) {
        this.budgetComparePresenter = budgetComparePresenter;
    }

    /**
     * Creates a budget comparison using the provided input data, including advised allocations and spent allocations.
     * Calculates the unspent income and net allocations for each category.
     *
     * @param compareInputData The input data required for the budget comparison.
     */
    @Override
    public void createBudgetCompare(BudgetCompareInputData compareInputData) {
        HashMap<String, Double> advisedAllocations = compareInputData.getAdvisedAllocations();
        HashMap<String, Double> spentAllocations = compareInputData.getSpentAllocations();

        double unspent_income = getUnspentIncome(spentAllocations);

        HashMap<String, Double> netAllocations = getNetAllocations(advisedAllocations, spentAllocations);

        /**
         * Note:
         * - Assumes `spentAllocations` and `advisedAllocations` have matching categories, except for "UNSPENT INCOME"
         *   in `spentAllocations`, which is not in `advisedAllocations`.
         * - Open questions:
         *   1. What if `spentAllocations` contains a category not in `advisedAllocations`?
         *   2. What if `advisedAllocations` contains a category not in `spentAllocations`?
         *   3. Should we assume the user only spends in categories defined in the budget maker?
         *   4. How to handle cases where the user adds a new category in the tracker not present in the maker?
         */

        BudgetCompareOutputData compareOutputData = new BudgetCompareOutputData(advisedAllocations, spentAllocations,
                netAllocations, unspent_income);
        budgetComparePresenter.presentBudgetCompare(compareOutputData);
    }

    /**
     * Retrieves the unspent income from the spent allocations.
     *
     * @param spentAllocations A map of categories and their respective spending amounts.
     * @return The unspent income amount.
     */
    private static Double getUnspentIncome(HashMap<String, Double> spentAllocations) {
        return spentAllocations.get("UNSPENT INCOME");
    }

    /**
     * Calculates the net allocations for each category by subtracting the spent amount from the advised amount.
     *
     * @param advisedAllocations A map of advised allocations for each category.
     * @param spentAllocations   A map of spent allocations for each category.
     * @return A map of net allocations for each category.
     */
    private static HashMap<String, Double> getNetAllocations(HashMap<String, Double> advisedAllocations,
                                                             HashMap<String, Double> spentAllocations) {
        HashMap<String, Double> netAllocations = new HashMap<>();
        for (Map.Entry<String, Double> entry : advisedAllocations.entrySet()) {
            String category = entry.getKey();
            Double advisedAmount = entry.getValue();

            if (spentAllocations.containsKey(category)) {
                Double spentAmount = spentAllocations.get(category);
                Double netAmount = advisedAmount - spentAmount;
                netAllocations.put(category, netAmount);
            } else {
                netAllocations.put(category, advisedAmount);
            }
        }
        return netAllocations;
    }
}
