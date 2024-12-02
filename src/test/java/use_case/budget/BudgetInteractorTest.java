package use_case.budget;


import app.MarketHealthService;
import entity.User;
import interface_adapter.budget.BudgetPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class BudgetInteractorTest {
    private BudgetInteractor budgetInteractor;

    @Test
    void testBudgetcreation() {
        double income = 10000;
        HashMap<String, Double> selectedCategories = new HashMap<>(Map.of(
                "Housing", 0.1,
                "Food", 0.1,
                "Transportation", 0.1,
                "Utilities", 0.1,
                "Entertainment",0.1,
                "Healthcare", 0.1
        ));
        User user = new User("BudgetMakerTest", "User", "Test", "test@test.com");

        budgetInteractor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(3000, outputData.getCategoryAllocations().get("Housing"));
                assertEquals(1500, outputData.getCategoryAllocations().get("Food"));
                assertEquals(1000, outputData.getCategoryAllocations().get("Transportation"));
                assertEquals(500, outputData.getCategoryAllocations().get("Utilities"));
                assertEquals(500, outputData.getCategoryAllocations().get("Entertainment"));
                assertEquals(500, outputData.getCategoryAllocations().get("Healthcare"));
                double markethealth = MarketHealthService.getEconomicIndicator();
                assertEquals(10000, outputData.getIncome());
                if (markethealth > 2) {
                    assertEquals(900, outputData.getSavings());
                    assertEquals(2100, outputData.getInvestments());
                }
                else {
                    assertEquals(900, outputData.getInvestments());
                    assertEquals(2100, outputData.getSavings());
                }

            }

            @Override
            public void switchBack() {

            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories, user);
        budgetInteractor.createBudget(testData);
    }

    @Test
    void testNewCategories() {
        double income = 10000;
        HashMap<String, Double> selectedCategories = new HashMap<>(Map.of(
                "Housing", 0.1,
                "Food", 0.1,
                "Transportation", 0.1,
                "Utilities", 0.1,
                "Entertainment",0.1,
                "Healthcare", 0.1,
                "Music",0.05
        ));
        User user = new User("BudgetMakerTest", "User", "Test", "test@test.com");

        budgetInteractor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(3000, outputData.getCategoryAllocations().get("Housing"));
                assertEquals(1500, outputData.getCategoryAllocations().get("Food"));
                assertEquals(1000, outputData.getCategoryAllocations().get("Transportation"));
                assertEquals(500, outputData.getCategoryAllocations().get("Utilities"));
                assertEquals(500, outputData.getCategoryAllocations().get("Entertainment"));
                assertEquals(500, outputData.getCategoryAllocations().get("Healthcare"));
                assertEquals(500, outputData.getCategoryAllocations().get("Music"));
                double markethealth = MarketHealthService.getEconomicIndicator();
                if (markethealth > 2) {
                    assertEquals(750, outputData.getCategoryAllocations().get("UNSPENT INCOME"));
                    assertEquals(1750, outputData.getCategoryAllocations().get("Investments"));
                }
                else {
                    assertEquals(750, outputData.getCategoryAllocations().get("Inestments"));
                    assertEquals(1750, outputData.getCategoryAllocations().get("UNSPENT INCOME"));
                }

            }

            @Override
            public void switchBack() {

            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories, user);
        budgetInteractor.createBudget(testData);
    }

    @Test
    void testNoEssentialCategories() {
        double income = 10000;
        HashMap<String, Double> selectedCategories = new HashMap<>(Map.of(
                "Housing", 0.0,
                "Food", 0.0,
                "Transportation", 0.0,
                "Utilities", 0.0,
                "Entertainment",0.0,
                "Healthcare", 0.0,
                "Music",0.05
        ));
        User user = new User("BudgetMakerTest", "User", "Test", "test@test.com");

        budgetInteractor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(500, outputData.getCategoryAllocations().get("Music"));
                double markethealth = MarketHealthService.getEconomicIndicator();
                if (markethealth > 2) {
                    assertEquals(2850, outputData.getCategoryAllocations().get("UNSPENT INCOME"));
                    assertEquals(6650, outputData.getCategoryAllocations().get("Investments"));
                }
                else {
                    assertEquals(6650, outputData.getCategoryAllocations().get("UNSPENT INCOME"));
                    assertEquals(2850, outputData.getCategoryAllocations().get("Investments"));
                }

            }

            @Override
            public void switchBack() {

            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories, user);
        budgetInteractor.createBudget(testData);
    }

    @Test
    void testOverBudget() {
        double income = 1000;
        HashMap<String, Double> selectedCategories = new HashMap<>(Map.of(
                "Housing", 0.1,
                "Food", 0.1,
                "Transportation", 0.1,
                "Utilities", 0.1,
                "Entertainment",0.1,
                "Healthcare", 0.1
        ));
        User user = new User("BudgetMakerTest", "User", "Test", "test@test.com");
        budgetInteractor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(1000, outputData.getCategoryAllocations().get("Impossible"));
            }

            @Override
            public void switchBack() {

            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories, user);
        budgetInteractor.createBudget(testData);
    }

    @Test
    void testMinimumEssentialSpending() {
        double income = 2000;
        HashMap<String, Double> selectedCategories = new HashMap<>(Map.of(
                "Housing", 0.1,
                "Food", 0.0,
                "Transportation", 0.0,
                "Utilities", 0.0,
                "Entertainment",0.0,
                "Healthcare", 0.0
        ));
        User user = new User("BudgetMakerTest", "User", "Test", "test@test.com");

        budgetInteractor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(1000, outputData.getCategoryAllocations().get("Housing"));
            }

            @Override
            public void switchBack() {

            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories, user);
        budgetInteractor.createBudget(testData);
    }

    @Test
    void testNoSavingsOrInvestmentsWhenIncomeEqualsSpending() {
        double income = 2000;
        HashMap<String, Double> selectedCategories = new HashMap<>(Map.of(
                "Housing", 0.0,
                "Food", 0.0,
                "Transportation", 0.0,
                "Utilities", 0.0,
                "Entertainment",0.0,
                "Healthcare", 0.0,
                "Music", 1.0
        ));
        User user = new User("BudgetMakerTest", "User", "Test", "test@test.com");
        BudgetInputData inputData = new BudgetInputData(income, selectedCategories, user);

        BudgetInteractor interactor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertFalse(outputData.getCategoryAllocations().containsValue("Savings"));
                assertFalse(outputData.getCategoryAllocations().containsKey("Investments"));
            }

            @Override
            public void switchBack() {

            }
        });
        interactor.createBudget(inputData);
    }





}