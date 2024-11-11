package use_case.budget;

import app.MarketHealthService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Map;

public class BudgetITest {

    private BudgetI budgetInteractor;

    @Before
    public void setUp() {
        budgetInteractor = new BudgetI();
    }

    @Test
    public void successBudgetCreationWithHighInflationTest() {
        double income = 5000;
        Map<String, Boolean> selectedCategories = Map.of(
                "Housing", true,
                "Food", true,
                "Transportation", true,
                "Utilities", false,
                "Entertainment", true,
                "Healthcare", true
        );

        BudgetOutputBoundary apiInflationPresenter = new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(5000, outputData.getIncome(), 0.01);
                assertEquals(1500, outputData.getCategoryAllocations().get("Housing"), 0.01);
                assertEquals(750, outputData.getCategoryAllocations().get("Food"), 0.01);
                assertEquals(500, outputData.getCategoryAllocations().get("Transportation"), 0.01);
                assertEquals(1000, outputData.getSavings(), 0.01);
                assertEquals(500, outputData.getInvestments(), 0.01);
            }
        };

        BudgetInputData inputData = new BudgetInputData(income, selectedCategories);
        budgetInteractor.createBudget(inputData, apiInflationPresenter);
    }

    @Test
    public void successBudgetCreationWithLowInflationTest() {
        double income = 5000;
        Map<String, Boolean> selectedCategories = Map.of(
                "Housing", true,
                "Food", true,
                "Transportation", true,
                "Utilities", false,
                "Entertainment", true,
                "Healthcare", true
        );

        MarketHealthService.setMockMarketHealth(3.0);

        BudgetOutputBoundary highInflationPresenter = new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                assertEquals(5000, outputData.getIncome(), 0.01);
                assertEquals(1500, outputData.getCategoryAllocations().get("Housing"), 0.01);
                assertEquals(750, outputData.getCategoryAllocations().get("Food"), 0.01);
                assertEquals(500, outputData.getCategoryAllocations().get("Transportation"), 0.01);
                assertEquals(500, outputData.getSavings(), 0.01);
                assertEquals(1000, outputData.getInvestments(), 0.01);
            }
        };

        BudgetInputData inputData = new BudgetInputData(income, selectedCategories);
        budgetInteractor.createBudget(inputData, highInflationPresenter);
    }
}

