package use_case.budget;

public interface BudgetInputBoundary {
    void createBudget(BudgetInputData inputData, BudgetOutputBoundary outputBoundary);
}

