package interface_adapter.signup;

import use_case.signup.SignUpInputData;
import use_case.signup.SignUpInteractor;

public class SignUpController {
    private final SignUpInteractor signUpInteractor;

    public SignUpController(SignUpInteractor signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    public void execute(String firstName, String lastName, String email, String password) {
        SignUpInputData signUpInputData = new SignUpInputData(firstName, lastName, email, password);
        signUpInteractor.execute(signUpInputData);
    }
    public void switchToLogInView(){
        System.out.println("In the controller");
        signUpInteractor.switchToLogInView();
    }
}
