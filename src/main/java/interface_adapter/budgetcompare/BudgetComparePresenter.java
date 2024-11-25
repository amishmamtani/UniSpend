package interface_adapter.budgetcompare;

import interface_adapter.ViewModel;
import use_case.budgetcompare.BudgetCompareOutputBoundary;
import use_case.budgetcompare.BudgetCompareOutputData;

public class BudgetComparePresenter implements BudgetCompareOutputBoundary {
    private final ViewModel<BudgetCompareState> viewModel;

    public BudgetComparePresenter(ViewModel<BudgetCompareState> viewModel) {this.viewModel = viewModel;}

    public void presentBudgetCompare(BudgetCompareOutputData outputData) {
        BudgetCompareState budgetCompareState = new BudgetCompareState(
                outputData.getAdvisedAllocations(),
                outputData.getSpentAllocations(),
                outputData.getNetAllocations(),
                outputData.getUnspent_income()
        );

        viewModel.setState(budgetCompareState);
//        viewModel.firePropertyChanged();
    }
}
