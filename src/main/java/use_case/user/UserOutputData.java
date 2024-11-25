package use_case.user;

import entity.User;

public class UserOutputData {
    private final User user;

    public UserOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
