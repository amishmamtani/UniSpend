package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for managing the state of the login view.
 */
public class LogInViewModel extends ViewModel<LogInState> {

    /**
     * Constructs a LogInViewModel with an initial state and a view name.
     */
    public LogInViewModel() {
        super("log in");
        setState(new LogInState());
    }
}
