package use_case.login;

public class LogInInteractor implements LogInInputBoundary{
    private LogInOutputBoundary logInOutputBoundary;
    public LogInInteractor(LogInOutputBoundary logInOutputBoundary) {
        this.logInOutputBoundary = logInOutputBoundary;
    }
    @Override
    public void execute(LogInInputData loginInputData) {
        logInOutputBoundary.prepareSuccessView(new LogInOutputData("", false));
    }
}
