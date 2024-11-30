package use_case.signup;

public class SignUpInteractor implements SignUpInputBoundary{
    private final SignUpOutputBoundary signUpOutputBoundary;

    public SignUpInteractor(SignUpOutputBoundary signUpOutputBoundary) {
        this.signUpOutputBoundary = signUpOutputBoundary;
    }

    @Override
    public void execute(SignUpInputData signUpInputData) {

    }

    @Override
    public void switchToLogInView() {
        System.out.println("In the interactor");
        signUpOutputBoundary.switchToLogInView();
    }
}
