package interface_adapter.budget;

import entity.User;
import use_case.budget.BudgetInteractor;
import use_case.budget.BudgetInputData;

import java.util.Map;

/**
 * Acts as the controller for handling budget-related user actions.
 * Delegates the operations to the BudgetInteractor.
 */
public class BudgetController {
    /** The interactor responsible for handling budget-related use cases */
    private final BudgetInteractor budgetInteractor;

    /**
     * Constructs a BudgetController with the specified interactor.
     *
     * @param budgetInteractor The interactor responsible for budget operations.
     */
    public BudgetController(BudgetInteractor budgetInteractor) {
        this.budgetInteractor = budgetInteractor;
    }

    /**
     * Creates a budget for the user based on the provided income, selected categories, and user details.
     *
     * @param income             The user's total income.
     * @param selectedCategories The categories and their allocated amounts.
     * @param user               The current user.
     */
    public void createBudget(double income, Map<String, Double> selectedCategories, User user) {
        BudgetInputData inputData = new BudgetInputData(income, selectedCategories, user);
        budgetInteractor.createBudget(inputData);
    }

    /**
     * Switches back to the previous state or screen.
     */
    public void switchBack() {
        budgetInteractor.switchBack();
    }
}
