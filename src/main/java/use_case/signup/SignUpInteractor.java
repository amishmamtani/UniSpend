package use_case.signup;

import entity.User;
import interface_adapter.user.MongoUserRepository;

/**
 * Interactor for handling sign-up operations.
 * Implements the SignUpInputBoundary interface.
 */
public class SignUpInteractor implements SignUpInputBoundary {

    /** Output boundary for managing sign-up-related responses */
    private final SignUpOutputBoundary signUpOutputBoundary;

    /**
     * Constructs a SignUpInteractor with the specified output boundary.
     *
     * @param signUpOutputBoundary The output boundary responsible for sign-up responses.
     */
    public SignUpInteractor(SignUpOutputBoundary signUpOutputBoundary) {
        this.signUpOutputBoundary = signUpOutputBoundary;
    }

    /**
     * Executes the sign-up process with the provided input data.
     * Checks for existing accounts with the same email and creates a new user if none exists.
     *
     * @param signUpInputData The input data containing user details for sign-up.
     */
    @Override
    public void execute(SignUpInputData signUpInputData) {
        MongoUserRepository userRepository = new MongoUserRepository();

        if (userRepository.getUserByEmail(signUpInputData.getEmail()) == null) {
            User newUser = new User(signUpInputData.getFirstName(),
                    signUpInputData.getLastName(),
                    signUpInputData.getPassword(),
                    signUpInputData.getEmail());
            userRepository.saveUser(newUser);
            signUpOutputBoundary.switchToLogInView();
        } else {
            signUpOutputBoundary.prepareFailView("Account Already Exists!");
        }
    }

    /**
     * Switches the view to the login screen.
     */
    @Override
    public void switchToLogInView() {
        System.out.println("In the sign up interactor");
        signUpOutputBoundary.switchToLogInView();
    }
}
