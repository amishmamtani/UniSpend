package use_case.signup;

/**
 * Defines the output boundary for sign-up-related use cases.
 */
public interface SignUpOutputBoundary {

    /**
     * Switches the view to the login screen.
     */
    void switchToLogInView();

    /**
     * Prepares the failure view when the sign-up process fails.
     *
     * @param s The error message explaining the sign-up failure.
     */
    void prepareFailView(String s);
}
