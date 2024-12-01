package interface_adapter.budget;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.budget.BudgetOutputBoundary;
import use_case.budget.BudgetOutputData;
import interface_adapter.ViewModel;

import java.util.HashMap;

/**
 * Acts as the presenter for budget-related operations, formatting the output data and updating the view state.
 */
public class BudgetPresenter implements BudgetOutputBoundary {
    /** The ViewModel for managing the budget state */
    private final ViewModel<BudgetState> viewModel;

    /** The ViewManagerModel for handling view navigation */
    private final ViewManagerModel viewManagerModel;

    /** The ViewModel for the home view */
    private final HomeViewModel homeViewModel;

    /** The current state of the budget */
    private final BudgetState budgetState = new BudgetState();

    /**
     * Constructs a BudgetPresenter with the specified view manager, budget view model, and home view model.
     *
     * @param viewManagerModel The view manager for managing navigation.
     * @param viewModel        The ViewModel for managing the budget state.
     * @param homeViewModel    The ViewModel for the home view.
     */
    public BudgetPresenter(ViewManagerModel viewManagerModel, ViewModel<BudgetState> viewModel,
                           HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.homeViewModel = homeViewModel;
        System.out.println(homeViewModel.getState().getEmailId());
        this.budgetState.setEmailId(homeViewModel.getState().getEmailId());
    }

    /**
     * Formats and updates the budget state based on the provided output data, and notifies the view model.
     *
     * @param outputData The data output from the budget use case.
     */
    @Override
    public void presentBudget(BudgetOutputData outputData) {
        budgetState.setIncome(outputData.getIncome());
        budgetState.setInvestments(outputData.getInvestments());
        budgetState.setCategoryAllocations((HashMap<String, Double>) outputData.getCategoryAllocations());
        budgetState.setSavings(outputData.getSavings());
        budgetState.setEmailId(outputData.getUser().getEmail());
        // Update the ViewModel with the new state
        viewModel.setState(budgetState);
        viewModel.firePropertyChanged(); // Notify listeners that the state has changed
    }

    /**
     * Switches the view back to the home screen and notifies the view manager of the change.
     */
    @Override
    public void switchBack() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
