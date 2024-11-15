package use_case.budget;

import app.MarketHealthService;

import java.util.HashMap;
import java.util.Map;

public class BudgetInteractor implements BudgetInputBoundary {

    private final BudgetOutputBoundary budgetPresenter;

    public BudgetInteractor(BudgetOutputBoundary budgetPresenter) {
        this.budgetPresenter = budgetPresenter;
    }
    @Override
    public void createBudget(BudgetInputData inputData) {
        double income = inputData.getIncome();
        Map<String, Boolean> selectedCategories = inputData.getSelectedCategories();

        Map<String, Double> categoryAllocations = new HashMap<>();
        double marketHealth = MarketHealthService.getEconomicIndicator();
        double savingsPercentage = marketHealth > 2 ? 0.1 : 0.2;
        double investmentsPercentage = marketHealth > 2 ? 0.2 : 0.1;

        Map<String, Double> defaultAllocations = Map.of(
                "Housing", 0.3,
                "Food", 0.15,
                "Transportation", 0.1,
                "Utilities", 0.05,
                "Entertainment", 0.05,
                "Healthcare", 0.05,
                "Savings", savingsPercentage,
                "Investments", investmentsPercentage
        );

        for (Map.Entry<String, Boolean> entry : selectedCategories.entrySet()) {
            if (entry.getValue() && defaultAllocations.containsKey(entry.getKey())) {
                double allocation = defaultAllocations.get(entry.getKey()) * income;
                categoryAllocations.put(entry.getKey(), allocation);

            }
        }



        double savings = savingsPercentage * income;
        double investments = investmentsPercentage * income;


        BudgetOutputData outputData = new BudgetOutputData(income, categoryAllocations, savings, investments);
        budgetPresenter.presentBudget(outputData);
    }

}
