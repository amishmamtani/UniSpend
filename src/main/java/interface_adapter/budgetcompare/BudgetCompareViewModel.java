package interface_adapter.budgetcompare;

import interface_adapter.ViewModel;

public class BudgetCompareViewModel extends ViewModel<BudgetCompareState> {
    public BudgetCompareViewModel() {
        super("Spending Comparison");
        setState(new BudgetCompareState());
    }
}
