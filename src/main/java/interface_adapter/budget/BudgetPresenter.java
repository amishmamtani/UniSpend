package interface_adapter.budget;

import use_case.budget.BudgetOutputBoundary;
import use_case.budget.BudgetOutputData;
import interface_adapter.ViewModel;

public class BudgetPresenter implements BudgetOutputBoundary {
    private final ViewModel<BudgetState> viewModel;

    public BudgetPresenter(ViewModel<BudgetState> viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentBudget(BudgetOutputData outputData) {
        // Create a new BudgetState object based on the output data
        BudgetState budgetState = new BudgetState(
                outputData.getIncome(),
                outputData.getCategoryAllocations(),
                outputData.getSavings(),
                outputData.getInvestments()
        );

        // Update the ViewModel with the new state
        viewModel.setState(budgetState);
        viewModel.firePropertyChanged(); // Notify listeners that the state has changed
    }
}
