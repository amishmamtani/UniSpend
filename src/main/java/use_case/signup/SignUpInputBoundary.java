package use_case.signup;

/**
 * Defines the input boundary for sign-up-related use cases.
 */
public interface SignUpInputBoundary {

    /**
     * Executes the sign-up process with the provided input data.
     *
     * @param signUpInputData The input data required for the sign-up process.
     */
    void execute(SignUpInputData signUpInputData);

    /**
     * Switches the view to the login screen.
     */
    void switchToLogInView();
}
