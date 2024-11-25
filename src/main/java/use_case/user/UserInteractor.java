package use_case.user;

import entity.User;

public class UserInteractor implements UserInputBoundary {
    private final UserRepository userRepository;

    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserInputData inputData) {
        User user = inputData.getUser();
        userRepository.saveUser(user);
    }

    @Override
    public void updateUser(UserInputData inputData) {
        User user = inputData.getUser();
        userRepository.saveUser(user);
    }

    @Override
    public UserOutputData fetchUser(String lastName) {
        User user = userRepository.getUserByLastName(lastName);
        if (user != null) {
            return new UserOutputData(user);
        }
        return null;
    }
}
