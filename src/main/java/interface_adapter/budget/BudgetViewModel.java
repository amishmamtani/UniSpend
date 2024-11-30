package interface_adapter.budget;
import interface_adapter.ViewModel;

/**
 * The View Model for the BudgetViewModel
 */
public class BudgetViewModel extends ViewModel<BudgetState> {
    public BudgetViewModel() {
        super("budget maker");
        setState(new BudgetState());
    }

}
