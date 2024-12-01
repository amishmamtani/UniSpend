package use_case.signup;

/**
 * Encapsulates the input data required for the sign-up process.
 */
public class SignUpInputData {

    /** The first name of the user */
    private final String firstName;

    /** The last name of the user */
    private final String lastName;

    /** The email address of the user */
    private final String email;

    /** The password chosen by the user */
    private final String password;

    /**
     * Constructs a SignUpInputData object with the specified user details.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param password  The password chosen by the user.
     */
    public SignUpInputData(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the first name of the user.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the user.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the password chosen by the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}
