package use_case.user;

import entity.User;

public interface UserRepository {
    void saveUser(User user);

    User getUserByLastName(String lastName);

    void deleteUserByLastName(String lastName);

    Iterable<User> getAllUsers();
}
