package interface_adapter.budget;

import use_case.budget.BudgetOutputBoundary;
import use_case.budget.BudgetOutputData;
import interface_adapter.ViewModel;

import java.util.HashMap;

public class BudgetPresenter implements BudgetOutputBoundary {
    private final ViewModel<BudgetState> viewModel;

    public BudgetPresenter(ViewModel<BudgetState> viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentBudget(BudgetOutputData outputData) {
        BudgetState budgetState = new BudgetState(
                outputData.getIncome(),
                (HashMap<String, Double>) outputData.getCategoryAllocations(),
                outputData.getSavings(),
                outputData.getInvestments()
        );

        // Update the ViewModel with the new state
        viewModel.setState(budgetState);
        viewModel.firePropertyChanged(); // Notify listeners that the state has changed
    }
}
