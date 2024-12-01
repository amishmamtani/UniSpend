package use_case.user;

import entity.User;

/**
 * Interactor for handling user-related operations.
 * Implements the UserInputBoundary interface.
 */
public class UserInteractor implements UserInputBoundary {

    /** Repository for managing user data */
    private final UserRepository userRepository;

    /**
     * Constructs a UserInteractor with the specified user repository.
     *
     * @param userRepository The repository for managing user data.
     */
    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user using the provided input data and saves it to the repository.
     *
     * @param inputData The input data containing user details.
     */
    @Override
    public void createUser(UserInputData inputData) {
        User user = inputData.getUser();
        userRepository.saveUser(user);
    }

    /**
     * Updates an existing user with the provided input data and saves the changes to the repository.
     *
     * @param inputData The input data containing updated user details.
     */
    @Override
    public void updateUser(UserInputData inputData) {
        User user = inputData.getUser();
        userRepository.saveUser(user);
    }

    /**
     * Fetches a user by their last name from the repository and returns the corresponding output data.
     *
     * @param lastName The last name of the user to fetch.
     * @return The output data containing user details, or null if the user is not found.
     */
    @Override
    public UserOutputData fetchUser(String lastName) {
        User user = userRepository.getUserByLastName(lastName);
        if (user != null) {
            return new UserOutputData(user);
        }
        return null;
    }
}
