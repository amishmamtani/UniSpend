package interface_adapter.budgettracker;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.home.HomeViewModel;
import use_case.budgettracker.BudgetTrackerOutputBoundary;
import use_case.budgettracker.BudgetTrackerOutputData;

public class BudgetTrackerPresenter implements BudgetTrackerOutputBoundary {
    private final ViewModel<BudgetTrackerState> viewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public BudgetTrackerPresenter(ViewManagerModel viewManagerModel, ViewModel<BudgetTrackerState> viewModel,
                                  HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.homeViewModel = homeViewModel;
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

    @Override
    public void switchBack() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
