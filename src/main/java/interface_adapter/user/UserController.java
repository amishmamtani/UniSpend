package interface_adapter.user;

import use_case.user.UserInputBoundary;
import use_case.user.UserInputData;
import entity.User;

/**
 * Controller for managing user operations.
 * Delegates user-related actions to the UserInputBoundary.
 */
public class UserController {
    /** The input boundary responsible for handling user use cases */
    private final UserInputBoundary userInputBoundary;

    /**
     * Constructs a UserController with the specified input boundary.
     *
     * @param userInputBoundary The input boundary responsible for user operations.
     */
    public UserController(UserInputBoundary userInputBoundary) {
        this.userInputBoundary = userInputBoundary;
    }

    /**
     * Creates a new user with the provided details.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param password  The password for the user.
     * @param email     The email address of the user.
     * @param income    The income of the user.
     */
    public void createUser(String firstName, String lastName, String password, String email, double income) {
        User user = new User(firstName, lastName, password, email);
        user.setIncome(income);
        UserInputData inputData = new UserInputData(user);
        userInputBoundary.createUser(inputData);
    }

    /**
     * Updates an existing user with the provided input data.
     *
     * @param userInputData The input data containing updated user details.
     */
    public void updateUser(UserInputData userInputData) {
        userInputBoundary.updateUser(userInputData);
    }

    /**
     * Fetches a user by their last name.
     *
     * @param lastName The last name of the user to fetch.
     * @return The fetched user.
     */
    public User fetchUser(String lastName) {
        return userInputBoundary.fetchUser(lastName).getUser();
    }
}
