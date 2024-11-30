package interface_adapter.budgettracker;

import entity.User;
import use_case.budgettracker.BudgetTrackerInputData;
import use_case.budgettracker.BudgetTrackerInteractor;

import java.util.HashMap;
import java.util.Map;

// Not sure if i have implemented this correctly

public class BudgetTrackerController {
    private final BudgetTrackerInteractor budgetTrackerInteractor;

    public BudgetTrackerController(BudgetTrackerInteractor budgetTrackerInteractor) {
        this.budgetTrackerInteractor = budgetTrackerInteractor;
    }

    public void createBudgetTracker(double income, HashMap<String, Double> alreadySpentCategories, double amount_spent,
                                    String category_spent_on, User user) {
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income,
                alreadySpentCategories, amount_spent, category_spent_on, user);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);
    }

    public void switchBack(){
        budgetTrackerInteractor.switchBack();
    }
}
