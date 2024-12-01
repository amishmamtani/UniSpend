package use_case.signup;

public interface SignUpOutputBoundary {
    void switchToLogInView();
    void prepareFailView(String s);
}
