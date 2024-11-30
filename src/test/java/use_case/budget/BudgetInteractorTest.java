package use_case.budget;


import app.MarketHealthService;
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
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories);
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
                    assertEquals(750, outputData.getCategoryAllocations().get("Savings"));
                    assertEquals(1750, outputData.getCategoryAllocations().get("Investments"));
                }
                else {
                    assertEquals(750, outputData.getCategoryAllocations().get("Inestments"));
                    assertEquals(1750, outputData.getCategoryAllocations().get("Savings"));
                }

            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories);
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

        budgetInteractor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(500, outputData.getCategoryAllocations().get("Music"));
                double markethealth = MarketHealthService.getEconomicIndicator();
                if (markethealth > 2) {
                    assertEquals(2850, outputData.getCategoryAllocations().get("Savings"));
                    assertEquals(6650, outputData.getCategoryAllocations().get("Investments"));
                }
                else {
                    assertEquals(6650, outputData.getCategoryAllocations().get("Savings"));
                    assertEquals(2850, outputData.getCategoryAllocations().get("Investments"));
                }

            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories);
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

        budgetInteractor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(1000, outputData.getCategoryAllocations().get("Impossible"));
            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories);
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

        budgetInteractor = new BudgetInteractor(new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(1000, outputData.getCategoryAllocations().get("Housing"));
            }
        });
        BudgetInputData testData = new BudgetInputData(income, selectedCategories);
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

        BudgetInputData inputData = new BudgetInputData(income, selectedCategories);

        BudgetInteractor interactor = new BudgetInteractor(outputData -> {
            assertFalse(outputData.getCategoryAllocations().containsKey("Savings"));
            assertFalse(outputData.getCategoryAllocations().containsKey("Investments"));
        });

        interactor.createBudget(inputData);
    }





}