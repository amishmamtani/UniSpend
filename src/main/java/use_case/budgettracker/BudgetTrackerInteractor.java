package use_case.budgettracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BudgetTrackerInteractor implements BudgetTrackerInputBoundary {
    private final BudgetTrackerOutputBoundary budgetTrackerPresenter;

    public BudgetTrackerInteractor(BudgetTrackerOutputBoundary budgetTrackerPresenter) {
        this.budgetTrackerPresenter = budgetTrackerPresenter;
    }

    public void createBudgetTracker(BudgetTrackerInputData trackerInputData) {
        double income = trackerInputData.getIncome();
        HashMap<String, Double> alreadySpentCategories = trackerInputData.getAlreadySpentCategories();
        double amount_spent = trackerInputData.getAmount_spent();
        String category_spent_on = trackerInputData.getCategory_spent_on();

        /**
         * Updates tracker if its their first time creating a tracker and there is no tracker already stored
         */
        if (!Objects.equals(category_spent_on, "none")) {
            update_tracker(trackerInputData, category_spent_on, amount_spent, alreadySpentCategories);
        }

        /**
         * Finds how much of the income is unspent
         */
        double unspent_income = get_unspent_income(alreadySpentCategories, income);

        boolean spent_more_than_income = unspent_income < 0;

        BudgetTrackerOutputData trackerOutputData = new BudgetTrackerOutputData(income, alreadySpentCategories,
                unspent_income, spent_more_than_income);
        budgetTrackerPresenter.presentBudgetTracker(trackerOutputData);
    }

    /**
     * This updates the tracker if its their first time creating the tracker
     * @param trackerInputData
     * @param category_spent_on
     * @param amount_spent
     * @param alreadySpentCategories
     */
    private void update_tracker(BudgetTrackerInputData trackerInputData, String category_spent_on, double amount_spent,
                                HashMap<String, Double> alreadySpentCategories) {
        if(alreadySpentCategories.containsKey(category_spent_on)){
            double currentValue = alreadySpentCategories.get(category_spent_on);
            double updatedValue = currentValue + amount_spent;
            alreadySpentCategories.put(category_spent_on, updatedValue);
        } else{
            alreadySpentCategories.put(category_spent_on, amount_spent);
        }
    }

    /**
     * Obtains the value of unspent income based on how much has already been spent on each category.
     */
    private static double get_unspent_income(HashMap<String, Double> alreadySpentCategories, double income) {
        double unspent_income = income;
        for (Double amountSpent : alreadySpentCategories.values()) {
            unspent_income = unspent_income - amountSpent;
        }
        return unspent_income;
    }
}


