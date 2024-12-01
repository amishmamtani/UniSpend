package interface_adapter.budget;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for managing the state of the budget view.
 */
public class BudgetViewModel extends ViewModel<BudgetState> {

    /**
     * Constructs a BudgetViewModel with an initial state and a view name.
     */
    public BudgetViewModel() {
        super("budget maker");
        setState(new BudgetState());
    }
}
