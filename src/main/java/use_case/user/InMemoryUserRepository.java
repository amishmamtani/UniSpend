package use_case.user;

import entity.User;

import java.util.HashMap;
import java.util.Map;

class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void saveUser(User user) {
        users.put(user.getLastName(), user);
    }

    @Override
    public User getUserByLastName(String lastName) {
        return users.get(lastName);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.get(email);
    }

    @Override
    public void deleteUserByLastName(String lastName) {
        users.remove(lastName);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return users.values();
    }
}
