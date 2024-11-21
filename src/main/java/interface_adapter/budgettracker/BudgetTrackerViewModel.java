package interface_adapter.budgettracker;

import interface_adapter.ViewModel;
import interface_adapter.budget.BudgetState;

public class BudgetTrackerViewModel extends ViewModel<BudgetTrackerState> {
    public BudgetTrackerViewModel() {
        super("Budget Tracker");
        setState(new BudgetTrackerState());
    }
}
