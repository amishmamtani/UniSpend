package use_case.budgettracker;

import entity.User;
import interface_adapter.user.MongoUserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Interactor class for updating the Budget Tracker
 */
public class BudgetTrackerInteractor implements BudgetTrackerInputBoundary {
    /** Presenter for formatting and displaying budget tracker output */
    private final BudgetTrackerOutputBoundary budgetTrackerPresenter;

    /**
     * Constructs a BudgetTrackerInteractor with the specified presenter.
     *
     * @param budgetTrackerPresenter the presenter responsible for handling output data
     */
    public BudgetTrackerInteractor(BudgetTrackerOutputBoundary budgetTrackerPresenter) {
        this.budgetTrackerPresenter = budgetTrackerPresenter;
    }

    /**
     * Creates a budget tracker using the provided input data and updates the user and database.
     *
     * @param trackerInputData input data of class BudgetTrackerInputData
     */
    public void createBudgetTracker(BudgetTrackerInputData trackerInputData) {
        double income = trackerInputData.getIncome();
        HashMap<String, Double> alreadySpentCategories = trackerInputData.getAlreadySpentCategories();
        double amount_spent = trackerInputData.getAmount_spent();
        String category_spent_on = trackerInputData.getCategory_spent_on();
        User user = trackerInputData.getUser();

        /**
         * Updates tracker with category spent on and the amount spent
         */
        if (!Objects.equals(category_spent_on, "NONE")) {
            update_tracker(category_spent_on, amount_spent, alreadySpentCategories);
        }

        double unspent_income = get_unspent_income(alreadySpentCategories);
        boolean spent_more_than_income = unspent_income < 0;

        /**
         * Updates the users budget tracker
         */
        user.setBudgetTracker(alreadySpentCategories);

        /**
         * Initialises us being able to access the database
         */
        MongoUserRepository userRepository = new MongoUserRepository();

        /**
         * Updates the user in the database.
         */
        userRepository.saveUser(user);

        BudgetTrackerOutputData trackerOutputData = new BudgetTrackerOutputData(income, alreadySpentCategories,
                unspent_income, spent_more_than_income, user);
        budgetTrackerPresenter.presentBudgetTracker(trackerOutputData);
    }

    /**
     * Switches back to the homescreen.
     */
    @Override
    public void switchBack() {
        budgetTrackerPresenter.switchBack();
    }

    /**
     *  Updates alreadySpentCategories with the category_spent_on and amount_spent
     */
    private void update_tracker(String category_spent_on, double amount_spent,
                                HashMap<String, Double> alreadySpentCategories) {
        if(alreadySpentCategories.containsKey(category_spent_on)){
            double currentValue = alreadySpentCategories.get(category_spent_on);
            double updatedValue = currentValue + amount_spent;
            alreadySpentCategories.put(category_spent_on, updatedValue);
        } else{
            alreadySpentCategories.put(category_spent_on, amount_spent);
        }
        double unspent_income = alreadySpentCategories.get("UNSPENT INCOME");
        unspent_income = unspent_income - amount_spent;
        alreadySpentCategories.put("UNSPENT INCOME",unspent_income);
    }

    /**
     * @return the value of unspent income based on the value in alreadySpentCategories
     */
    private static double get_unspent_income(HashMap<String, Double> alreadySpentCategories){
        return alreadySpentCategories.get("UNSPENT INCOME");
    }




//    /**
//     * Obtains the value of unspent income based on how much has already been spent on each category.
//     */
//    private static double get_unspent_income(HashMap<String, Double> alreadySpentCategories, double income) {
//        double unspent_income = income;
//        for (Double amountSpent : alreadySpentCategories.values()) {
//            unspent_income = unspent_income - amountSpent;
//        }
//        return unspent_income;
//    }
}


