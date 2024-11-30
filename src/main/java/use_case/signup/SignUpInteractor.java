package use_case.signup;

import entity.User;
import interface_adapter.user.MongoUserRepository;

public class SignUpInteractor implements SignUpInputBoundary{
    private final SignUpOutputBoundary signUpOutputBoundary;

    public SignUpInteractor(SignUpOutputBoundary signUpOutputBoundary) {
        this.signUpOutputBoundary = signUpOutputBoundary;
    }

    @Override
    public void execute(SignUpInputData signUpInputData) {
        MongoUserRepository userRepository = new MongoUserRepository();
        if (userRepository.getUserByEmail(signUpInputData.getEmail()) == null) {
            User newUser = new User(signUpInputData.getFirstName(),
                    signUpInputData.getLastName(),
                    signUpInputData.getEmail(),
                    signUpInputData.getPassword());
            userRepository.saveUser(newUser);
            signUpOutputBoundary.switchToLogInView();
        }

    }

    @Override
    public void switchToLogInView() {
        System.out.println("In the sign up interactor");
        signUpOutputBoundary.switchToLogInView();
    }
}
