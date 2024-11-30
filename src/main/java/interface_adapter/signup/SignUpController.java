package interface_adapter.signup;

import use_case.signup.SignUpInteractor;

public class SignUpController {
    private final SignUpInteractor signUpInteractor;

    public SignUpController(SignUpInteractor signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    public void switchToLogInView(){
        System.out.println("In the controller");
        signUpInteractor.switchToLogInView();
    }
}
