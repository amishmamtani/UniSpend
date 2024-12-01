package use_case.login;

/**
 * Defines the output boundary for login-related use cases.
 */
public interface LogInOutputBoundary {

    /**
     * Prepares the success view when the login process completes successfully.
     *
     * @param outputData The output data containing login success details.
     */
    void prepareSuccessView(LogInOutputData outputData);

    /**
     * Prepares the failure view when the login process fails.
     *
     * @param errorMessage The error message explaining the login failure.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches the view to the sign-up screen.
     */
    void switchToSignUp();
}
