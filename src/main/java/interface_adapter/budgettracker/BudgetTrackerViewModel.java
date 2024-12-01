package interface_adapter.budgettracker;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for managing the state of the budget tracker view.
 */
public class BudgetTrackerViewModel extends ViewModel<BudgetTrackerState> {

    /**
     * Constructs a BudgetTrackerViewModel with an initial state and a view name.
     */
    public BudgetTrackerViewModel() {
        super("budget tracker");
        setState(new BudgetTrackerState());
    }
}