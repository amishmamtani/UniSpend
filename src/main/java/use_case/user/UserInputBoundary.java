package use_case.user;

/**
 * Defines the input boundary for user-related use cases.
 */
public interface UserInputBoundary {

    /**
     * Creates a new user using the provided input data.
     *
     * @param inputData The input data required to create the user.
     */
    void createUser(UserInputData inputData);

    /**
     * Updates an existing user with the provided input data.
     *
     * @param inputData The input data containing updated user details.
     */
    void updateUser(UserInputData inputData);

    /**
     * Fetches a user by their last name.
     *
     * @param lastName The last name of the user to fetch.
     * @return The output data containing user details.
     */
    UserOutputData fetchUser(String lastName);
}
