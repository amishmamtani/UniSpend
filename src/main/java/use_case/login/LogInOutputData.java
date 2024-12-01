package use_case.login;

/**
 * Encapsulates the output data for the login process, including the username and status.
 */
public class LogInOutputData {

    /** The username of the user attempting to log in */
    private final String username;

    /** Indicates whether the login use case failed */
    private final boolean useCaseFailed;

    /**
     * Constructs a LogInOutputData object with the specified username and failure status.
     *
     * @param username      The username of the user attempting to log in.
     * @param useCaseFailed True if the login use case failed, otherwise false.
     */
    public LogInOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Retrieves the username of the user attempting to log in.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
}
