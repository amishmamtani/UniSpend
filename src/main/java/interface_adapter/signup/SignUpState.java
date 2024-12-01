package interface_adapter.signup;

/**
 * Represents the state of the sign-up view, including error messages.
 */
public class SignUpState {
    /** The error message related to the sign-up process */
    private String error;

    /**
     * Retrieves the error message.
     *
     * @return The error message.
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message.
     *
     * @param error The error message to set.
     */
    public void setError(String error) {
        this.error = error;
    }
}
