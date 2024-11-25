package use_case.user;

public interface UserInputBoundary {
    void createUser(UserInputData inputData);

    void updateUser(UserInputData inputData);

    UserOutputData fetchUser(String lastName);
}
