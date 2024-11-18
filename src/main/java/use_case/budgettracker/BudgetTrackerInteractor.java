package use_case.budgettracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BudgetTrackerInteractor implements BudgetTrackerInputBoundary {
    private final BudgetTrackerOutputBoundary budgetTrackerPresenter;
//    private final HashMap<String, Double> madeBudgetTracker;

    public BudgetTrackerInteractor(BudgetTrackerOutputBoundary budgetTrackerPresenter) {
        this.budgetTrackerPresenter = budgetTrackerPresenter;
//        this.madeBudgetTracker = new HashMap<>(Map.of("Food", 0.0, "Entertainment", 0.0));
    }

    public void createBudgetTracker(BudgetTrackerInputData trackerInputData) {
        double income = trackerInputData.getIncome();
        HashMap<String, Double> alreadySpentCategories = trackerInputData.getAlreadySpentCategories();
        double amount_spent = trackerInputData.getAmount_spent();
        String category_spent_on = trackerInputData.getCategory_spent_on();

        /**
         * Updates tracker if its their first time creating a tracker and there is no tracker already stored
         */
        if (amount_spent > 0) {
            update_tracker(trackerInputData, category_spent_on, amount_spent, alreadySpentCategories);
        }

//        /**
//         * Updates tracker if there is already a tracker and the user has spent an amount on a given category
//         */
//        if (amount_spent > 0) {
//            update_made_tracker(madeBudgetTracker, category_spent_on, amount_spent);
//        }

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
     * Complete this to update the tracker based on category and value if they already have a made budget tracker
//     */
//    private void update_made_tracker(HashMap<String, Double> madeBudgetTracker, String category_spent_on,
//                                double amount_spent) {
//        if (madeBudgetTracker.containsKey(category_spent_on)) {
//            double currentValue = madeBudgetTracker.get(category_spent_on);
//            double updatedValue = currentValue + amount_spent;
//            madeBudgetTracker.put(category_spent_on, updatedValue);
//        }
//    }

    /**
     * This updates the tracker if its their first time creating the tracker
     * @param trackerInputData
     * @param category_spent_on
     * @param amount_spent
     * @param alreadySpentCategories
     */
    private void update_tracker(BudgetTrackerInputData trackerInputData, String category_spent_on, double amount_spent,
                                HashMap<String, Double> alreadySpentCategories) {
        if(alreadySpentCategories.containsKey(category_spent_on) && !Objects.equals(category_spent_on, "none")){
            double currentValue = alreadySpentCategories.get(category_spent_on);
            double updatedValue = currentValue + amount_spent;
            alreadySpentCategories.put(category_spent_on, updatedValue);
        }
    }



//        double amount_spent = trackerInputData.getAmount_spent();
//        String category_spent_on = trackerInputData.getCategory_spent_on();
//
//        if(madeBudgetTracker.containsKey(category_spent_on)){
//            double currentValue = madeBudgetTracker.get(category_spent_on);
//            double updatedValue = currentValue + amount_spent;
//            madeBudgetTracker.put(category_spent_on, updatedValue);
//        }





    /**
     * Obtains the value of unspent income based on how much has already been spent on each category.
     */
    private static double get_unspent_income(HashMap<String, Double> alreadySpentCategories, double income) {
        double unspent_income = income;
        for (Double amountSpent : alreadySpentCategories.values()) {
            unspent_income = income - amountSpent;
        }
        return unspent_income;
    }
}

//    private void update_unspent_income(){
//        double uns
//
//    }



