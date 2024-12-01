package use_case.login;

/**
 * Defines the input boundary for login-related use cases.
 */
public interface LogInInputBoundary {

    /**
     * Executes the login process with the provided input data.
     *
     * @param loginInputData The input data required for the login process.
     */
    void execute(LogInInputData loginInputData);

    /**
     * Switches the view to the sign-up screen.
     */
    void switchToSignUp();
}
