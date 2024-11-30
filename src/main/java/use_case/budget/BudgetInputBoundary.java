package use_case.budget;

public interface BudgetInputBoundary {
    void createBudget(BudgetInputData inputData);
    void switchBack();
}

