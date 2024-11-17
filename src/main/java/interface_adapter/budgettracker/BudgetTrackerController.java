package interface_adapter.budgettracker;

import use_case.budgettracker.BudgetTrackerInputData;
import use_case.budgettracker.BudgetTrackerInteractor;

import java.util.Map;

// Not sure if i have implemented this correctly

public class BudgetTrackerController {
    private final BudgetTrackerInteractor budgetTrackerInteractor;

    public BudgetTrackerController(BudgetTrackerInteractor budgetTrackerInteractor) {
        this.budgetTrackerInteractor = budgetTrackerInteractor;
    }

    public void createBudgetTracker(double income, Map<String, Boolean> selectedCategories, Map<String, Double> alreadySpentCategories) {
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, selectedCategories, alreadySpentCategories);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);
    }

}
