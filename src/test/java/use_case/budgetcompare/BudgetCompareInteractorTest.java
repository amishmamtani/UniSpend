package use_case.budgetcompare;

import entity.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class BudgetCompareInteractorTest {

    @Test
    public void compareAllCategoriesInMakerAlsoInTracker(){

        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "HOUSING", 2000.0,
                "ENTERTAINMENT", 0.0,
                "FOOD", 100.0,
                "TRAVEL", 50.0,
                "UNSPENT INCOME", 2850.0
        ));

        HashMap<String, Double> advisedCategories = new HashMap<>(Map.of(
                "HOUSING", 4000.0,
                "ENTERTAINMENT", 100.0,
                "FOOD", 750.0,
                "TRAVEL", 150.0
        ));

        User user = new User("CT1", "CT1", "CT1", "CT1");
        user.setBudgetTracker(alreadySpentCategories);
        user.setBudget(advisedCategories);
        Double unspent_income = user.getBudgetTracker().get("UNSPENT INCOME");

        BudgetCompareOutputBoundary net = new BudgetCompareOutputBoundary() {
            @Override
            public void presentBudgetCompare(BudgetCompareOutputData budgetCompareOutputData) {
                assertEquals(advisedCategories, budgetCompareOutputData.getAdvisedAllocations());
                assertEquals(alreadySpentCategories, budgetCompareOutputData.getSpentAllocations());

                HashMap<String, Double> expectedNetAllocations = new HashMap<>(Map.of(
                        "HOUSING", 2000.0,
                        "ENTERTAINMENT", 100.0,
                        "FOOD", 650.0,
                        "TRAVEL", 100.0));
                assertEquals(expectedNetAllocations, budgetCompareOutputData.getNetAllocations());
                assertEquals(unspent_income, budgetCompareOutputData.getUnspent_income());

                assertEquals(alreadySpentCategories, user.getBudgetTracker());
                assertEquals(advisedCategories, user.getBudget());

            }
        };
        BudgetCompareInteractor budgetCompareInteractor;
        budgetCompareInteractor = new BudgetCompareInteractor(net);
        BudgetCompareInputData compareInputData = new BudgetCompareInputData(user);
        budgetCompareInteractor.createBudgetCompare(compareInputData);
    }


    @Test
    public void compareOneCategoryInMakerNotInTracker(){

        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "HOUSING", 2000.0,
                "ENTERTAINMENT", 0.0,
                "FOOD", 100.0,
                "UNSPENT INCOME", 2900.0
        ));

        HashMap<String, Double> advisedCategories = new HashMap<>(Map.of(
                "HOUSING", 4000.0,
                "ENTERTAINMENT", 100.0,
                "FOOD", 750.0,
                "TRAVEL", 150.0
        ));

        User user = new User("CT2", "CT2", "CT2", "CT2");
        user.setBudgetTracker(alreadySpentCategories);
        user.setBudget(advisedCategories);
        Double unspent_income = user.getBudgetTracker().get("UNSPENT INCOME");

        BudgetCompareOutputBoundary net = new BudgetCompareOutputBoundary() {
            @Override
            public void presentBudgetCompare(BudgetCompareOutputData budgetCompareOutputData) {
                assertEquals(advisedCategories, budgetCompareOutputData.getAdvisedAllocations());
                assertEquals(alreadySpentCategories, budgetCompareOutputData.getSpentAllocations());
                assertEquals(unspent_income, budgetCompareOutputData.getUnspent_income());

                HashMap<String, Double> expectedNetAllocations = new HashMap<>(Map.of(
                        "HOUSING", 2000.0,
                        "ENTERTAINMENT", 100.0,
                        "FOOD", 650.0,
                        "TRAVEL", 150.0));
                assertEquals(expectedNetAllocations, budgetCompareOutputData.getNetAllocations());

                assertEquals(alreadySpentCategories, user.getBudgetTracker());
                assertEquals(advisedCategories, user.getBudget());

            }
        };
        BudgetCompareInteractor budgetCompareInteractor;
        budgetCompareInteractor = new BudgetCompareInteractor(net);
        BudgetCompareInputData compareInputData = new BudgetCompareInputData(user);
        budgetCompareInteractor.createBudgetCompare(compareInputData);
    }


    @Test
    public void CompareOneCategoryInMakerNotInTrackerWhenTrackerOrderJumbled(){

        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "UNSPENT INCOME", 2900.0,
                "ENTERTAINMENT", 0.0,
                "FOOD", 100.0,
                "HOUSING", 2000.0
                ));

        HashMap<String, Double> advisedCategories = new HashMap<>(Map.of(
                "HOUSING", 4000.0,
                "ENTERTAINMENT", 100.0,
                "FOOD", 750.0,
                "TRAVEL", 150.0
        ));

        User user = new User("CT2", "CT2", "CT2", "CT2");
        user.setBudgetTracker(alreadySpentCategories);
        user.setBudget(advisedCategories);
        Double unspent_income = user.getBudgetTracker().get("UNSPENT INCOME");

        BudgetCompareOutputBoundary net = new BudgetCompareOutputBoundary() {
            @Override
            public void presentBudgetCompare(BudgetCompareOutputData budgetCompareOutputData) {
                assertEquals(advisedCategories, budgetCompareOutputData.getAdvisedAllocations());
                assertEquals(alreadySpentCategories, budgetCompareOutputData.getSpentAllocations());

                HashMap<String, Double> expectedNetAllocations = new HashMap<>(Map.of(
                        "HOUSING", 2000.0,
                        "ENTERTAINMENT", 100.0,
                        "FOOD", 650.0,
                        "TRAVEL", 150.0));
                assertEquals(expectedNetAllocations, budgetCompareOutputData.getNetAllocations());
                assertEquals(unspent_income, budgetCompareOutputData.getUnspent_income());

                assertEquals(alreadySpentCategories, user.getBudgetTracker());
                assertEquals(advisedCategories, user.getBudget());

            }
        };
        BudgetCompareInteractor budgetCompareInteractor;
        budgetCompareInteractor = new BudgetCompareInteractor(net);
        BudgetCompareInputData compareInputData = new BudgetCompareInputData(user);
        budgetCompareInteractor.createBudgetCompare(compareInputData);
    }

    @Test
    public void compareOneCategoryNotInMakerButInTracker(){

        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "HOUSING", 2000.0,
                "ENTERTAINMENT", 0.0,
                "FOOD", 100.0,
                "TRAVEL", 50.0,
                "OTHER", 1000.0,
                "UNSPENT INCOME", 2850.0
        ));

        HashMap<String, Double> advisedCategories = new HashMap<>(Map.of(
                "HOUSING", 4000.0,
                "ENTERTAINMENT", 100.0,
                "FOOD", 750.0,
                "TRAVEL", 150.0
        ));

        User user = new User("CT1", "CT1", "CT1", "CT1");
        user.setBudgetTracker(alreadySpentCategories);
        user.setBudget(advisedCategories);
        Double unspent_income = user.getBudgetTracker().get("UNSPENT INCOME");

        BudgetCompareOutputBoundary net = new BudgetCompareOutputBoundary() {
            @Override
            public void presentBudgetCompare(BudgetCompareOutputData budgetCompareOutputData) {
                assertEquals(advisedCategories, budgetCompareOutputData.getAdvisedAllocations());
                assertEquals(alreadySpentCategories, budgetCompareOutputData.getSpentAllocations());

                HashMap<String, Double> expectedNetAllocations = new HashMap<>(Map.of(
                        "HOUSING", 2000.0,
                        "ENTERTAINMENT", 100.0,
                        "FOOD", 650.0,
                        "TRAVEL", 100.0));
                assertEquals(expectedNetAllocations, budgetCompareOutputData.getNetAllocations());
                assertEquals(unspent_income, budgetCompareOutputData.getUnspent_income());

                assertEquals(alreadySpentCategories, user.getBudgetTracker());
                assertEquals(advisedCategories, user.getBudget());

            }
        };
        BudgetCompareInteractor budgetCompareInteractor;
        budgetCompareInteractor = new BudgetCompareInteractor(net);
        BudgetCompareInputData compareInputData = new BudgetCompareInputData(user);
        budgetCompareInteractor.createBudgetCompare(compareInputData);
    }

    @Test
    public void compareFullySpentIncomeAndOrderJumbled(){

        HashMap<String, Double> alreadySpentCategories = new HashMap<>(Map.of(
                "ENTERTAINMENT", 100.0,
                "FOOD", 750.0,
                "TRAVEL", 150.0,
                "UNSPENT INCOME", 0.0,
                "HOUSING", 4000.0

        ));

        HashMap<String, Double> advisedCategories = new HashMap<>(Map.of(
                "HOUSING", 4000.0,
                "ENTERTAINMENT", 100.0,
                "FOOD", 750.0,
                "TRAVEL", 150.0
        ));

        User user = new User("CT1", "CT1", "CT1", "CT1");
        user.setBudgetTracker(alreadySpentCategories);
        user.setBudget(advisedCategories);
        Double unspent_income = user.getBudgetTracker().get("UNSPENT INCOME");

        BudgetCompareOutputBoundary net = new BudgetCompareOutputBoundary() {
            @Override
            public void presentBudgetCompare(BudgetCompareOutputData budgetCompareOutputData) {
                assertEquals(advisedCategories, budgetCompareOutputData.getAdvisedAllocations());
                assertEquals(alreadySpentCategories, budgetCompareOutputData.getSpentAllocations());

                HashMap<String, Double> expectedNetAllocations = new HashMap<>(Map.of(
                        "HOUSING", 0.0,
                        "ENTERTAINMENT", 0.0,
                        "TRAVEL", 0.0,
                        "FOOD", 0.0
                        ));
                assertEquals(expectedNetAllocations, budgetCompareOutputData.getNetAllocations());
                assertEquals(unspent_income, budgetCompareOutputData.getUnspent_income());

                assertEquals(alreadySpentCategories, user.getBudgetTracker());
                assertEquals(advisedCategories, user.getBudget());

            }
        };
        BudgetCompareInteractor budgetCompareInteractor;
        budgetCompareInteractor = new BudgetCompareInteractor(net);
        BudgetCompareInputData compareInputData = new BudgetCompareInputData(user);
        budgetCompareInteractor.createBudgetCompare(compareInputData);
    }
  
}