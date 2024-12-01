package interface_adapter.budgettracker;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.home.HomeViewModel;
import use_case.budgettracker.BudgetTrackerOutputBoundary;
import use_case.budgettracker.BudgetTrackerOutputData;

/**
 * Presenter for budget tracker operations.
 * Formats the output data and updates the associated view state.
 */
public class BudgetTrackerPresenter implements BudgetTrackerOutputBoundary {
    /** The ViewModel for managing the budget tracker state */
    private final ViewModel<BudgetTrackerState> viewModel;

    /** The ViewManagerModel for handling view navigation */
    private final ViewManagerModel viewManagerModel;

    /** The ViewModel for the home view */
    private final HomeViewModel homeViewModel;

    /** The current state of the budget tracker */
    private final BudgetTrackerState budgetTrackerState = new BudgetTrackerState();

    /**
     * Constructs a BudgetTrackerPresenter with the specified view manager, budget tracker view model, and home view model.
     *
     * @param viewManagerModel The view manager for managing navigation.
     * @param viewModel        The ViewModel for managing the budget tracker state.
     * @param homeViewModel    The ViewModel for the home view.
     */
    public BudgetTrackerPresenter(ViewManagerModel viewManagerModel, ViewModel<BudgetTrackerState> viewModel,
                                  HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.homeViewModel = homeViewModel;
        budgetTrackerState.setEmailId(homeViewModel.getState().getEmailId());
    }

    /**
     * Formats and updates the budget tracker state based on the provided output data,
     * and notifies the ViewModel of the changes.
     *
     * @param outputData The data output from the budget tracker use case.
     */
    @Override
    public void presentBudgetTracker(BudgetTrackerOutputData outputData) {
        budgetTrackerState.setIncome(outputData.getIncome());
        budgetTrackerState.setAlreadySpentCategories(outputData.getAlreadySpentCategories());
        budgetTrackerState.setUnspent_income(outputData.getUnspent_income());
        budgetTrackerState.setSpent_more_than_income(outputData.isSpent_more_than_income());
        budgetTrackerState.setEmailId(outputData.getUser().getEmail());

        // Update the ViewModel with the new state
        viewModel.setState(budgetTrackerState);
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
