package interface_adapter.budgetcompare;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for managing the state of the budget comparison view.
 */
public class BudgetCompareViewModel extends ViewModel<BudgetCompareState> {

    /**
     * Constructs a BudgetCompareViewModel with an initial state and a view name.
     */
    public BudgetCompareViewModel() {
        super("budget compare");
        setState(new BudgetCompareState());
    }
}
