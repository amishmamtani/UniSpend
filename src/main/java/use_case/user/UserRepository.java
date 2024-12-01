package use_case.user;

import entity.User;

/**
 * Defines the repository interface for user-related operations.
 */
public interface UserRepository {

    /**
     * Saves a user to the repository.
     *
     * @param user The user to save.
     */
    void saveUser(User user);

    /**
     * Retrieves a user by their last name.
     *
     * @param lastName The last name of the user to retrieve.
     * @return The user with the given last name, or null if not found.
     */
    User getUserByLastName(String lastName);

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to retrieve.
     * @return The user with the given email, or null if not found.
     */
    User getUserByEmail(String email);

    /**
     * Deletes a user by their last name.
     *
     * @param lastName The last name of the user to delete.
     */
    void deleteUserByLastName(String lastName);

    /**
     * Retrieves all users stored in the repository.
     *
     * @return An iterable collection of all users.
     */
    Iterable<User> getAllUsers();
}
