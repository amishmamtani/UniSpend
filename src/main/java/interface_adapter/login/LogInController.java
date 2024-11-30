package interface_adapter.login;

import use_case.login.LogInInputData;
import use_case.login.LogInInteractor;

public class LogInController {
    private final LogInInteractor logInInteractor;
    public LogInController(LogInInteractor logInInteractor) {
        this.logInInteractor = logInInteractor;
    }
    public void prepareSuccessView(){
        logInInteractor.execute(new LogInInputData("", ""));
    }
}
