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
    private final BudgetState budgetState = new BudgetState();

    public BudgetPresenter(ViewManagerModel viewManagerModel, ViewModel<BudgetState> viewModel, HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.homeViewModel = homeViewModel;
        System.out.println(homeViewModel.getState().getEmailId());
        this.budgetState.setEmailId(homeViewModel.getState().getEmailId());
    }

    @Override
    public void presentBudget(BudgetOutputData outputData) {
        budgetState.setIncome(outputData.getIncome());
        budgetState.setInvestments(outputData.getInvestments());
        budgetState.setCategoryAllocations((HashMap<String, Double>) outputData.getCategoryAllocations());
        budgetState.setSavings(outputData.getSavings());
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
