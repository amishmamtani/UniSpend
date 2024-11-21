package use_case.budgettracker;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class BudgetTrackerInteractor_Test {

    @Test
    public void successUpdatingTracker() {

        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "Housing", 2000.0,
                "Entertainment", 0.0,
                "Food", 100.0,
                "Travel", 50.0
        ));
        double amount_spent = 10.0;
        String category_spent_on = "Food";

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                Double spentFood = budgetTrackerOutputData.getAlreadySpentCategories().get("Food");
                assertEquals(Optional.of(110.0), Optional.of(spentFood));
            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);

    }

}
