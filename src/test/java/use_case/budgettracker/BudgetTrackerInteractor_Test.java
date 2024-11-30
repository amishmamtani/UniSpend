package use_case.budgettracker;

import entity.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BudgetTrackerInteractor_Test {

//    @Test
//    public void firstTimeCreatingTracker() {
//        double income = 5000;
//        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
//                "UNSPENT INCOME", 5000.0
//        ));
//        double amount_spent = 0.0;
//        String category_spent_on = "none";
//
//        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
//            @Override
//            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
//                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
//                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
//                        "UNSPENT INCOME", 5000.0
//                ));
//                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);
//                Double currentIncome = budgetTrackerOutputData.getIncome();
//                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
//                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
//                assertEquals(Optional.of(5000.0), Optional.of(newUnspentIncome));
//                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
//                assertEquals(false, spentMoreThanIncome);
//            }
//        };
//        BudgetTrackerInteractor budgetTrackerInteractor;
//        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
//        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
//                amount_spent, category_spent_on);
//        budgetTrackerInteractor.createBudgetTracker(trackerInputData);
//    }

    @Test
    public void firstTimeCreatingTracker() {
        double income = 5000.0;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "UNSPENT INCOME", 5000.0
        ));
        double amount_spent = 0.0;
        String category_spent_on = "none";
        User user = new User("IT1", "IT1", "IT1", "IT1", income);

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "UNSPENT INCOME", 5000.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);
                Double currentIncome = budgetTrackerOutputData.getIncome();
                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(5000.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertFalse(spentMoreThanIncome);
                assertEquals(expectedAlreadySpentCategories, user.getBudgetTracker());
                assertEquals("IT1", user.getFirstName());
                assertEquals("IT1", user.getEmail());
                assertEquals("IT1", user.getLastName());
                assertEquals("IT1", user.getPassword());
                assertEquals((Double) income, user.getIncome());

                HashMap<String, Double> expectedUserBudget = new HashMap<>();
                assertEquals(expectedUserBudget, user.getBudget());
            }

            @Override
            public void switchBack() {

            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on, user);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);
    }

    @Test
    public void successNewUpdatingTracker() {

        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "HOUSING", 2000.0,
                "ENTERTAINMENT", 0.0,
                "FOOD", 100.0,
                "TRAVEL", 50.0,
                "UNSPENT INCOME", 2850.0
        ));
        double amount_spent = 10.0;
        String category_spent_on = "Food";
        User user = new User("IT2", "IT2", "IT2", "IT2", income);
        user.setBudgetTracker(alreadySpentCategories);

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "HOUSING", 2000.0,
                        "ENTERTAINMENT", 0.0,
                        "FOOD", 110.0,
                        "TRAVEL", 50.0,
                        "UNSPENT INCOME", 2840.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(2840.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertFalse(spentMoreThanIncome);
                assertEquals(expectedAlreadySpentCategories, user.getBudgetTracker());
                assertEquals("IT2", user.getFirstName());
                assertEquals("IT2", user.getEmail());
                assertEquals("IT2", user.getLastName());
                assertEquals("IT2", user.getPassword());

                assertEquals((Double) income, user.getIncome());

                HashMap<String, Double> expectedUserBudget = new HashMap<>();
                assertEquals(expectedUserBudget, user.getBudget());
            }

            @Override
            public void switchBack() {

            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on, user);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);

    }

    @Test
    public void nothingNewUpdated() {
        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "HOUSING", 2000.0,
                "ENTERTAINMENT", 0.0,
                "FOOD", 100.0,
                "TRAVEL", 50.0,
                "UNSPENT INCOME", 2850.0
        ));
        double amount_spent = 0.0;
        String category_spent_on = "none";
        User user = new User("IT3", "IT3", "IT3", "IT3", income);
        user.setBudgetTracker(alreadySpentCategories);

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "HOUSING", 2000.0,
                        "ENTERTAINMENT", 0.0,
                        "FOOD", 100.0,
                        "TRAVEL", 50.0,
                        "UNSPENT INCOME", 2850.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);
                Double currentIncome = budgetTrackerOutputData.getIncome();
                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(2850.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertEquals(false, spentMoreThanIncome);
                assertEquals(expectedAlreadySpentCategories, user.getBudgetTracker());
                assertEquals("IT3", user.getFirstName());
                assertEquals("IT3", user.getEmail());
                assertEquals("IT3", user.getLastName());
                assertEquals("IT3", user.getPassword());

                assertEquals((Double) income, user.getIncome());

                HashMap<String, Double> expectedUserBudget = new HashMap<>();
                assertEquals(expectedUserBudget, user.getBudget());

            }

            @Override
            public void switchBack() {

            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on, user);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);


    }

    @Test
    public void alternateNewNewCategory() {
        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "HOUSING", 2000.0,
                "ENTERTAINMENT", 0.0,
                "FOOD", 100.0,
                "TRAVEL", 50.0,
                "UNSPENT INCOME", 2850.0
        ));
        double amount_spent = 120.0;
        String category_spent_on = "SHOPPING";
        User user = new User("IT4", "IT4", "IT4", "IT4", income);
        user.setBudgetTracker(alreadySpentCategories);

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "HOUSING", 2000.0,
                        "ENTERTAINMENT", 0.0,
                        "FOOD", 100.0,
                        "TRAVEL", 50.0,
                        "UNSPENT INCOME", 2730.0,
                        "SHOPPING", 120.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);
                Double currentIncome = budgetTrackerOutputData.getIncome();
                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(2730.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertEquals(false, spentMoreThanIncome);
                assertEquals(expectedAlreadySpentCategories, user.getBudgetTracker());
                assertEquals("IT4", user.getFirstName());
                assertEquals("IT4", user.getEmail());
                assertEquals("IT4", user.getLastName());
                assertEquals("IT4", user.getPassword());

                assertEquals((Double) income, user.getIncome());

                HashMap<String, Double> expectedUserBudget = new HashMap<>();
                assertEquals(expectedUserBudget, user.getBudget());
            }

            @Override
            public void switchBack() {

            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on, user);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);

    }

    @Test
    public void newNewCategoryAndSpentMoreThanIncome() {
        double income = 5000;
        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "UNSPENT INCOME", 2850.0,
                "HOUSING", 2000.0,
                "ENTERTAINMENT", 0.0,
                "FOOD", 100.0,
                "TRAVEL", 50.0
        ));
        double amount_spent = 6000.0;
        String category_spent_on = "Shopping";
        User user = new User("IT5", "IT5", "IT5", "IT5", income);
        user.setBudgetTracker(alreadySpentCategories);

        BudgetTrackerOutputBoundary updatedAllocations = new BudgetTrackerOutputBoundary() {
            @Override
            public void presentBudgetTracker(BudgetTrackerOutputData budgetTrackerOutputData) {
                HashMap<String, Double> actualAlreadySpentCategories = budgetTrackerOutputData.getAlreadySpentCategories();
                HashMap<String, Double> expectedAlreadySpentCategories = new HashMap<>(Map.of(
                        "UNSPENT INCOME", -3150.0,
                        "HOUSING", 2000.0,
                        "ENTERTAINMENT", 0.0,
                        "FOOD", 100.0,
                        "TRAVEL", 50.0,
                        "SHOPPING", 6000.0
                ));
                assertEquals(expectedAlreadySpentCategories, actualAlreadySpentCategories);

                Double spentFood = budgetTrackerOutputData.getAlreadySpentCategories().get("FOOD");
                assertEquals(Optional.of(100.0), Optional.of(spentFood));
                Double spentHousing = budgetTrackerOutputData.getAlreadySpentCategories().get("HOUSING");
                assertEquals(Optional.of(2000.0), Optional.of(spentHousing));
                Double spentEntertainment = budgetTrackerOutputData.getAlreadySpentCategories().get("ENTERTAINMENT");
                assertEquals(Optional.of(0.0), Optional.of(spentEntertainment));
                Double spentTravel = budgetTrackerOutputData.getAlreadySpentCategories().get("TRAVEL");
                assertEquals(Optional.of(50.0), Optional.of(spentTravel));
                Double spentShopping = budgetTrackerOutputData.getAlreadySpentCategories().get("SHOPPING");
                assertEquals(Optional.of(6000.0), Optional.of(spentShopping));
                int lengthOfAllocations = budgetTrackerOutputData.getAlreadySpentCategories().size();
                assertEquals(6, lengthOfAllocations);
                Double currentIncome = budgetTrackerOutputData.getIncome();
                assertEquals(Optional.of(5000.0), Optional.of(currentIncome));
                Double newUnspentIncome = budgetTrackerOutputData.getUnspent_income();
                assertEquals(Optional.of(-3150.0), Optional.of(newUnspentIncome));
                boolean spentMoreThanIncome = budgetTrackerOutputData.isSpent_more_than_income();
                assertEquals(true, spentMoreThanIncome);
                assertEquals(expectedAlreadySpentCategories, user.getBudgetTracker());
                assertEquals("IT5", user.getFirstName());
                assertEquals("IT5", user.getEmail());
                assertEquals("IT5", user.getLastName());
                assertEquals("IT5", user.getPassword());

                assertEquals((Double) income, user.getIncome());

                HashMap<String, Double> expectedUserBudget = new HashMap<>();
                assertEquals(expectedUserBudget, user.getBudget());
            }

            @Override
            public void switchBack() {

            }
        };
        BudgetTrackerInteractor budgetTrackerInteractor;
        budgetTrackerInteractor = new BudgetTrackerInteractor(updatedAllocations);
        BudgetTrackerInputData trackerInputData = new BudgetTrackerInputData(income, alreadySpentCategories,
                amount_spent, category_spent_on, user);
        budgetTrackerInteractor.createBudgetTracker(trackerInputData);

    }

}