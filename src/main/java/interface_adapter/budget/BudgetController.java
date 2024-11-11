package interface_adapter.budget;

import use_case.budget.BudgetI;
import use_case.budget.BudgetInputData;
import use_case.budget.BudgetOutputBoundary;

import java.util.Map;

public class BudgetController {
    private final BudgetI budgetInteractor;

    public BudgetController(BudgetI budgetInteractor) {
        this.budgetInteractor = budgetInteractor;
    }

    public void createBudget(double income, Map<String, Boolean> selectedCategories, BudgetOutputBoundary outputBoundary) {
        BudgetInputData inputData = new BudgetInputData(income, selectedCategories);
        budgetInteractor.createBudget(inputData, outputBoundary);
    }
}
