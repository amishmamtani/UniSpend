package use_case.user;

import entity.User;

/**
 * Encapsulates the output data for user-related operations.
 */
public class UserOutputData {

    /** The user entity containing user details */
    private final User user;

    /**
     * Constructs a UserOutputData object with the specified user.
     *
     * @param user The user entity containing user details.
     */
    public UserOutputData(User user) {
        this.user = user;
    }

    /**
     * Retrieves the user entity associated with this output data.
     *
     * @return The user entity.
     */
    public User getUser() {
        return user;
    }
}
