package use_case.budget;

import app.MarketHealthService;

import java.util.HashMap;
import java.util.Map;

public class BudgetI implements BudgetInputBoundary {

    @Override
    public void createBudget(BudgetInputData inputData, BudgetOutputBoundary outputBoundary) {
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

                System.out.println("Category: " + entry.getKey() + " | Allocation: " + allocation);
            }
        }

        System.out.println("Market Health: " + marketHealth);


        double savings = savingsPercentage * income;
        double investments = investmentsPercentage * income;

        System.out.println("Savings: " + savings + " | Investments: " + investments);

        BudgetOutputData outputData = new BudgetOutputData(income, categoryAllocations, savings, investments);
        outputBoundary.presentBudget(outputData);
    }

}
