package interface_adapter.login;

import use_case.login.LogInInputData;
import use_case.login.LogInInteractor;

/**
 * Controller for managing login operations.
 * Delegates login-related actions to the LogInInteractor.
 */
public class LogInController {
    /** The interactor responsible for handling login use cases */
    private final LogInInteractor logInInteractor;

    /**
     * Constructs a LogInController with the specified interactor.
     *
     * @param logInInteractor The interactor responsible for login operations.
     */
    public LogInController(LogInInteractor logInInteractor) {
        this.logInInteractor = logInInteractor;
    }

    /**
     * Prepares a success view for login by executing the interactor with default empty credentials.
     */
    public void prepareSuccessView() {
        logInInteractor.execute(new LogInInputData("", ""));
    }

    /**
     * Executes the login process with the provided username and password.
     *
     * @param username The username for login.
     * @param password The password for login.
     */
    public void execute(String username, String password) {
        final LogInInputData inputData = new LogInInputData(username, password);
        logInInteractor.execute(inputData);
    }

    /**
     * Switches the view to the Sign-Up screen.
     */
    public void switchToSignUp() {
        logInInteractor.switchToSignUp();
    }
}
