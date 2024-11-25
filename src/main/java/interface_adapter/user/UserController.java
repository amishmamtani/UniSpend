package interface_adapter.user;

import use_case.user.UserInputBoundary;
import use_case.user.UserInputData;
import entity.User;

public class UserController {
    private final UserInputBoundary userInputBoundary;

    public UserController(UserInputBoundary userInputBoundary) {
        this.userInputBoundary = userInputBoundary;
    }

    public void createUser(String firstName, String lastName, String password, String email, double income) {
        User user = new User(firstName, lastName, password, email);
        user.setIncome(income);
        UserInputData inputData = new UserInputData(user);
        userInputBoundary.createUser(inputData);
    }

    public void updateUser(UserInputData userInputData) {
        userInputBoundary.updateUser(userInputData);
    }

    public User fetchUser(String lastName) {
        return userInputBoundary.fetchUser(lastName).getUser();
    }
}
