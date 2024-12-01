package use_case.login;

public interface LogInOutputBoundary {
    void prepareSuccessView(LogInOutputData outputData);

    void prepareFailView(String errorMessage);

    void switchToSignUp();
}
