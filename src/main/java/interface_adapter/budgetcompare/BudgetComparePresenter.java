package interface_adapter.budgetcompare;

import interface_adapter.ViewModel;
import use_case.budgetcompare.BudgetCompareOutputBoundary;
import use_case.budgetcompare.BudgetCompareOutputData;

/**
 * Presenter for budget comparison operations.
 * Formats the output data and updates the associated view model.
 */
public class BudgetComparePresenter implements BudgetCompareOutputBoundary {
    /** The ViewModel for managing the budget comparison state */
    private final ViewModel<BudgetCompareState> viewModel;

    /**
     * Constructs a BudgetComparePresenter with the specified ViewModel.
     *
     * @param viewModel The ViewModel for managing the budget comparison state.
     */
    public BudgetComparePresenter(ViewModel<BudgetCompareState> viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Formats and updates the budget comparison state based on the provided output data.
     *
     * @param outputData The output data from the budget comparison use case.
     */
    public void presentBudgetCompare(BudgetCompareOutputData outputData) {
        BudgetCompareState budgetCompareState = new BudgetCompareState(
                outputData.getAdvisedAllocations(),
                outputData.getSpentAllocations(),
                outputData.getNetAllocations(),
                outputData.getUnspent_income()
        );

        // Update the ViewModel with the new state
        viewModel.setState(budgetCompareState);
//        viewModel.firePropertyChanged(); // Notify listeners of state change (if necessary)
    }
}
