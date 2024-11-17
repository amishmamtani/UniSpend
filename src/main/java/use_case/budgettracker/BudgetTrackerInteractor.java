package use_case.budgettracker;

import java.util.HashMap;
import java.util.Map;

public class BudgetTrackerInteractor implements BudgetTrackerInputBoundary {
    private final BudgetTrackerOutputBoundary budgetTrackerPresenter;
    private final HashMap<String, Double> madeBudgetTracker;

    public BudgetTrackerInteractor(BudgetTrackerOutputBoundary budgetTrackerPresenter) {
        this.budgetTrackerPresenter = budgetTrackerPresenter;
        this.madeBudgetTracker = new HashMap<>(Map.of("Food", 0.0 ,"Entertainment", 0.0));
    }

    public void createBudgetTracker(BudgetTrackerInputData trackerInputData) {
        double income = trackerInputData.getIncome();
        Map<String, Boolean> selectedCategories = trackerInputData.getSelectedCategories();
        Map<String, Double> alreadySpentCategories = trackerInputData.getAlreadySpentCategories();
        /**
         * Finds how much of the income is unspent
         */
        double unspent_income = get_unspent_income(alreadySpentCategories, income);

        BudgetTrackerOutputData trackerOutputData = new BudgetTrackerOutputData(income, alreadySpentCategories,
                unspent_income);
        budgetTrackerPresenter.presentBudgetTracker(trackerOutputData);
    }


    /**
     * Complete this to update the tarcker based on category and value
     */
    public void update_tracker()



    /**
     * Obtains the value of unspent income based on how much has already been spent on each category.
     */
    private static double get_unspent_income(Map<String, Double> alreadySpentCategories, double income) {
        double unspent_income = income;
        for (Double amountSpent : alreadySpentCategories.values()) {
             unspent_income = income - amountSpent;
        }
        return unspent_income;
    }

}


