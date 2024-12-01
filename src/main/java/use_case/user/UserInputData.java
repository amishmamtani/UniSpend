package use_case.user;

import entity.User;

/**
 * Encapsulates the input data required for user-related operations.
 */
public class UserInputData {

    /** The user entity containing user details */
    private final User user;

    /**
     * Constructs a UserInputData object with the specified user.
     *
     * @param user The user entity containing user details.
     */
    public UserInputData(User user) {
        this.user = user;
    }

    /**
     * Retrieves the user entity associated with this input data.
     *
     * @return The user entity.
     */
    public User getUser() {
        return user;
    }
}
