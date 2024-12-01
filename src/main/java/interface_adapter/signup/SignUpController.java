package interface_adapter.signup;

import use_case.signup.SignUpInputData;
import use_case.signup.SignUpInteractor;

/**
 * Controller for managing sign-up operations.
 * Delegates sign-up-related actions to the SignUpInteractor.
 */
public class SignUpController {
    /** The interactor responsible for handling sign-up use cases */
    private final SignUpInteractor signUpInteractor;

    /**
     * Constructs a SignUpController with the specified interactor.
     *
     * @param signUpInteractor The interactor responsible for sign-up operations.
     */
    public SignUpController(SignUpInteractor signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    /**
     * Executes the sign-up process with the provided user details.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param password  The password chosen by the user.
     */
    public void execute(String firstName, String lastName, String email, String password) {
        SignUpInputData signUpInputData = new SignUpInputData(firstName, lastName, email, password);
        signUpInteractor.execute(signUpInputData);
    }

    /**
     * Switches the view to the login screen.
     */
    public void switchToLogInView() {
        System.out.println("In the controller");
        signUpInteractor.switchToLogInView();
    }
}
