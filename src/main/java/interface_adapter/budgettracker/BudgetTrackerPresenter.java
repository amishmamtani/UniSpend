package interface_adapter.budgettracker;

import interface_adapter.ViewModel;
import interface_adapter.budget.BudgetState;
import use_case.budget.BudgetOutputData;
import use_case.budgettracker.BudgetTrackerOutputBoundary;
import use_case.budgettracker.BudgetTrackerOutputData;

import java.util.HashMap;

public class BudgetTrackerPresenter implements BudgetTrackerOutputBoundary {
    private final ViewModel<BudgetTrackerState> viewModel;

    public BudgetTrackerPresenter(ViewModel<BudgetTrackerState> viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentBudgetTracker(BudgetTrackerOutputData outputData) {
        BudgetTrackerState budgetTrackerState = new BudgetTrackerState(
                outputData.getIncome(),
                outputData.getAlreadySpentCategories(),
                outputData.getUnspent_income(),
                outputData.isSpent_more_than_income()
        );

        // Update the ViewModel with the new state
        viewModel.setState(budgetTrackerState);
        viewModel.firePropertyChanged(); // Notify listeners that the state has changed
    }
}
