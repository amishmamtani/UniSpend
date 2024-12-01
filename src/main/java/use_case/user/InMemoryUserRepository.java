package use_case.user;

import entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * An in-memory implementation of the UserRepository interface.
 * This repository stores user data in a HashMap for testing and development purposes.
 */
class InMemoryUserRepository implements UserRepository {

    /** A map to store users, keyed by their last name */
    private final Map<String, User> users = new HashMap<>();

    /**
     * Saves a user to the repository. If a user with the same last name exists, it is overwritten.
     *
     * @param user The user to save.
     */
    @Override
    public void saveUser(User user) {
        users.put(user.getLastName(), user);
    }

    /**
     * Retrieves a user by their last name.
     *
     * @param lastName The last name of the user to retrieve.
     * @return The user with the given last name, or null if not found.
     */
    @Override
    public User getUserByLastName(String lastName) {
        return users.get(lastName);
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to retrieve.
     * @return The user with the given email, or null if not found.
     */
    @Override
    public User getUserByEmail(String email) {
        return users.get(email);
    }

    /**
     * Deletes a user by their last name.
     *
     * @param lastName The last name of the user to delete.
     */
    @Override
    public void deleteUserByLastName(String lastName) {
        users.remove(lastName);
    }

    /**
     * Retrieves all users stored in the repository.
     *
     * @return An iterable collection of all users.
     */
    @Override
    public Iterable<User> getAllUsers() {
        return users.values();
    }
}
