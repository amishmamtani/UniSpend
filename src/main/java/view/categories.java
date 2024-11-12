package view;
import java.util.HashMap;
import java.util.Map;

import use_case.budget.BudgetInteractor;
import use_case.budget.BudgetInputData;
import use_case.budget.BudgetOutputBoundary;
import use_case.budget.BudgetOutputData;


public class categories {
    private BudgetInteractor budgetInteractor = new BudgetInteractor();
    double income;
    Map<String, Boolean> selectedCategories;
    Map<String, Double> percentageCategories;

    public categories(double income) {
        this.income = income;
        this.selectedCategories = Map.of(
                "Housing", true,
                "Food", true,
                "Transportation", true,
                "Utilities", true,
                "Entertainment", true,
                "Healthcare", true,
                "Savings", true,
                "Investments", true);

        this.budgetInteractor = new BudgetInteractor();
        this.percentageCategories = new HashMap<>();

    }

    public HashMap generateBudget() {
        BudgetInputData inputData = new BudgetInputData(income, selectedCategories);


        BudgetOutputBoundary BPC = new BudgetOutputBoundary() {
            @Override
            public void presentBudget(BudgetOutputData outputData) {
                percentageCategories.put("Housing", outputData.getCategoryAllocations().get("Housing"));
                percentageCategories.put("Food", outputData.getCategoryAllocations().get("Food"));
                percentageCategories.put("Transportation", outputData.getCategoryAllocations().get("Transportation"));
                percentageCategories.put("Utilities", outputData.getCategoryAllocations().get("Utilities"));
                percentageCategories.put("Entertainment", outputData.getCategoryAllocations().get("Entertainment"));
                percentageCategories.put("Healthcare", outputData.getCategoryAllocations().get("Healthcare"));
                percentageCategories.put("Savings", outputData.getCategoryAllocations().get("Savings"));
                percentageCategories.put("Investments", outputData.getCategoryAllocations().get("Investments"));
            }
        };
        budgetInteractor.createBudget(inputData, BPC);
        return (HashMap) percentageCategories;
    }
}
