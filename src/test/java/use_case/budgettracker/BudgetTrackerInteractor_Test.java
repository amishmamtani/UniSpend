package use_case.budgettracker;

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
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "Housing", 2000.0,
                        "Entertainment", 0.0,
                        "Food", 110.0,
                        "Travel", 50.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(2840.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertEquals(false, spentMoreThanIncome);
            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);

    }

    @Test
    public void nothingUpdated() {
        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "Housing", 2000.0,
                "Entertainment", 0.0,
                "Food", 100.0,
                "Travel", 50.0
        ));
        double amount_spent = 0.0;
        String category_spent_on = "none";

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "Housing", 2000.0,
                        "Entertainment", 0.0,
                        "Food", 100.0,
                        "Travel", 50.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);
                Double currentIncome = budgetTrackerOutputData.getIncome();
                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(2850.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertEquals(false, spentMoreThanIncome);
            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);


    }

    @Test
    public void newCategory() {
        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "Housing", 2000.0,
                "Entertainment", 0.0,
                "Food", 100.0,
                "Travel", 50.0
        ));
        double amount_spent = 120.0;
        String category_spent_on = "Shopping";

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                Double spentFood = budgetTrackerOutputData.getAlreadySpentCategories().get("Food");
                assertEquals(Optional.of(100.0), Optional.of(spentFood));
                Double spentHousing = budgetTrackerOutputData.getAlreadySpentCategories().get("Housing");
                assertEquals(Optional.of(2000.0), Optional.of(spentHousing));
                Double spentEntertainment = budgetTrackerOutputData.getAlreadySpentCategories().get("Entertainment");
                assertEquals(Optional.of(0.0), Optional.of(spentEntertainment));
                Double spentTravel = budgetTrackerOutputData.getAlreadySpentCategories().get("Travel");
                assertEquals(Optional.of(50.0), Optional.of(spentTravel));
                Double spentShopping = budgetTrackerOutputData.getAlreadySpentCategories().get("Shopping");
                assertEquals(Optional.of(120.0), Optional.of(spentShopping));
                int lengthOfAllocations = budgetTrackerOutputData.getAlreadySpentCategories().size();
                assertEquals(5,lengthOfAllocations);
                Double currentIncome = budgetTrackerOutputData.getIncome();
                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(2730.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertEquals(false, spentMoreThanIncome);
            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);

    }

    @Test
    public void newCategoryAndSpentMoreThanIncome() {
        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "Housing", 2000.0,
                "Entertainment", 0.0,
                "Food", 100.0,
                "Travel", 50.0
        ));
        double amount_spent = 6000.0;
        String category_spent_on = "Shopping";

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "Housing", 2000.0,
                        "Entertainment", 0.0,
                        "Food", 100.0,
                        "Travel", 50.0,
                        "Shopping", 6000.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);

                Double spentFood = budgetTrackerOutputData.getAlreadySpentCategories().get("Food");
                assertEquals(Optional.of(100.0), Optional.of(spentFood));
                Double spentHousing = budgetTrackerOutputData.getAlreadySpentCategories().get("Housing");
                assertEquals(Optional.of(2000.0), Optional.of(spentHousing));
                Double spentEntertainment = budgetTrackerOutputData.getAlreadySpentCategories().get("Entertainment");
                assertEquals(Optional.of(0.0), Optional.of(spentEntertainment));
                Double spentTravel = budgetTrackerOutputData.getAlreadySpentCategories().get("Travel");
                assertEquals(Optional.of(50.0), Optional.of(spentTravel));
                Double spentShopping = budgetTrackerOutputData.getAlreadySpentCategories().get("Shopping");
                assertEquals(Optional.of(6000.0), Optional.of(spentShopping));
                int lengthOfAllocations = budgetTrackerOutputData.getAlreadySpentCategories().size();
                assertEquals(5,lengthOfAllocations);
                Double currentIncome = budgetTrackerOutputData.getIncome();
                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(-3150.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertEquals(true, spentMoreThanIncome);
            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);

    }

    @Test
    public void alternateNewCategory (){
        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "Housing", 2000.0,
                "Entertainment", 0.0,
                "Food", 100.0,
                "Travel", 50.0
        ));
        double amount_spent = 120.0;
        String category_spent_on = "Shopping";

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "Housing", 2000.0,
                        "Entertainment", 0.0,
                        "Food", 100.0,
                        "Travel", 50.0,
                        "Shopping", 120.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);
                Double currentIncome = budgetTrackerOutputData.getIncome();
                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(2730.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertEquals(false, spentMoreThanIncome);
            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);

    }

}
