package use_case.budget;

import app.MarketHealthService;
import java.util.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map;

public class BudgetInteractor implements BudgetInputBoundary {

    private final BudgetOutputBoundary budgetPresenter;

    public BudgetInteractor(BudgetOutputBoundary budgetPresenter) {
        this.budgetPresenter = budgetPresenter;
    }
    @Override
    public void createBudget(BudgetInputData inputData) {
        double income = inputData.getIncome();
        double spending = 0;
        double indexer = 0;
        Map<String, Double> selectedCategories = inputData.getSelectedCategories();

        Map<String, Double> categoryAllocations = new HashMap<>();
        double marketHealth = MarketHealthService.getEconomicIndicator();
        double savingsPercentage = marketHealth > 2 ? 0.3 : 0.7;
        double investmentsPercentage = marketHealth > 2 ? 0.7 : 0.3;
        String[] essentials = new String[]{"Housing", "Food", "Transportations", "Healthcare", "Utilities"};
        double essentialNum = 0;

        Map<String, double[]> defaultAllocations = new LinkedHashMap<>(Map.of(
                "Housing", new double[]{0.3, 1000},
                "Food", new double[]{0.15, 200},
                "Transportation", new double[]{0.1, 0},
                "Utilities", new double[]{0.05, 0},
                "Entertainment", new double[]{0.05, 0},
                "Healthcare", new double[]{0.05, 0}
        ));

        for (Map.Entry<String, Double> entry : selectedCategories.entrySet()) {
            if (defaultAllocations.containsKey(entry.getKey())) {
                if (entry.getValue() == 0) {
                    defaultAllocations.remove(entry.getKey());
                }
            }
            else {
                defaultAllocations.put(entry.getKey(), new double[]{entry.getValue(), 0});
                }

        }

        for (String essential : essentials) {
            if (defaultAllocations.containsKey(essential)) {
                essentialNum += Math.max(defaultAllocations.get(essential)[0] * income,
                        defaultAllocations.get(essential)[1]);
            }
        }

        if (essentialNum > income) {
            categoryAllocations.put("Impossible", 1000.0);
            spending = income;
        }

        for (Map.Entry<String, double[]> entry : defaultAllocations.entrySet()) {
            double allocation = entry.getValue()[0] * income;
            if ((allocation + spending) <= income && allocation >= entry.getValue()[1]){
                categoryAllocations.put(entry.getKey(), allocation);
                spending += allocation;
            }
            else if ((entry.getValue()[1] + spending) <= income && allocation >= entry.getValue()[1]){
                categoryAllocations.put(entry.getKey(), entry.getValue()[1]);
                spending += entry.getValue()[1];
            }
        }


        double savings = 0;
        double investments = 0;
        if (income > spending){
            double difference = income - spending;
            savings = difference * savingsPercentage;
            investments = difference * investmentsPercentage;
            categoryAllocations.put("Savings", savings);
            categoryAllocations.put("Investments", investments);
        }


        BudgetOutputData outputData = new BudgetOutputData(income, categoryAllocations, savings, investments);
        budgetPresenter.presentBudget(outputData);
    }

}
