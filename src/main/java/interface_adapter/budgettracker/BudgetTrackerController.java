package interface_adapter.budgettracker;

import entity.User;
import use_case.budgettracker.BudgetTrackerInputData;
import use_case.budgettracker.BudgetTrackerInteractor;

import java.util.HashMap;

/**
 * Controller for managing budget tracker operations.
 * Delegates operations to the BudgetTrackerInteractor.
 */
public class BudgetTrackerController {
    /** The interactor responsible for handling budget tracker use cases */
    private final BudgetTrackerInteractor budgetTrackerInteractor;

    /**
     * Constructs a BudgetTrackerController with the specified interactor.
     *
     * @param budgetTrackerInteractor The interactor responsible for budget tracker operations.
     */
    public BudgetTrackerController(BudgetTrackerInteractor budgetTrackerInteractor) {
        this.budgetTrackerInteractor = budgetTrackerInteractor;
    }

    /**
     * Creates a budget tracker using the provided data, including income, spending details, and user information.
     *
     * @param income                The user's total income.
     * @param alreadySpentCategories A map of categories and their respective amounts already spent.
     * @param amount_spent          The amount the user just spent.
     * @param category_spent_on     The category the user just spent on.
     * @param user                  The current user.
     */
    public void createBudgetTracker(double income, HashMap<String, Double> alreadySpentCategories, double amount_spent,
                                    String category_spent_on, User user) {
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income,
                alreadySpentCategories, amount_spent, category_spent_on, user);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);
    }

    /**
     * Switches back to the previous state or screen.
     */
    public void switchBack() {
        budgetTrackerInteractor.switchBack();
    }
}
