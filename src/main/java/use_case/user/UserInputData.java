package use_case.user;

import entity.User;

public class UserInputData {
    private final User user;

    public UserInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
