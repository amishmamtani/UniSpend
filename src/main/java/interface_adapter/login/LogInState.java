package interface_adapter.login;

/**
 * Represents the state of the login view.
 * This class can be extended to include additional properties such as login error messages or user details.
 */
public class LogInState {
    private String logInError;

    public String getLogInError() {
        return logInError;
    }

    public void setLogInError(String logInError) {
        this.logInError = logInError;
    }
}
