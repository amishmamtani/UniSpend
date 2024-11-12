package interface_adapter.budget;

import use_case.budget.BudgetInteractor;
import use_case.budget.BudgetInputData;
import use_case.budget.BudgetOutputBoundary;

import java.util.Map;

public class BudgetController {
    private final BudgetInteractor budgetInteractor;

    public BudgetController(BudgetInteractor budgetInteractor) {
        this.budgetInteractor = budgetInteractor;
    }

    public void createBudget(double income, Map<String, Boolean> selectedCategories, BudgetOutputBoundary outputBoundary) {
        BudgetInputData inputData = new BudgetInputData(income, selectedCategories);
        budgetInteractor.createBudget(inputData, outputBoundary);
    }
}
