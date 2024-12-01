package use_case.login;

/**
 * Encapsulates the input data required for the login process.
 */
public class LogInInputData {
    /** The username entered by the user */
    private final String username;

    /** The password entered by the user */
    private final String password;

    /**
     * Constructs a LogInInputData object with the specified username and password.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public LogInInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username entered by the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password entered by the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}
