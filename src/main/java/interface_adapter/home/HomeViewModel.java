package interface_adapter.home;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for managing the state of the home view.
 */
public class HomeViewModel extends ViewModel<HomeState> {

    /**
     * Constructs a HomeViewModel with an initial state and a view name.
     */
    public HomeViewModel() {
        super("home");
        setState(new HomeState());
    }
}
