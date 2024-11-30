package interface_adapter.budget;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.budget.BudgetOutputBoundary;
import use_case.budget.BudgetOutputData;
import interface_adapter.ViewModel;

import java.util.HashMap;

public class BudgetPresenter implements BudgetOutputBoundary {
    private final ViewModel<BudgetState> viewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public BudgetPresenter(ViewManagerModel viewManagerModel, ViewModel<BudgetState> viewModel, HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.homeViewModel = homeViewModel;
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

    @Override
    public void switchBack() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
