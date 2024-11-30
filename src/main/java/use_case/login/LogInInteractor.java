package use_case.login;

import entity.User;
import interface_adapter.user.MongoUserRepository;

public class LogInInteractor implements LogInInputBoundary{
    private LogInOutputBoundary logInOutputBoundary;
    private MongoUserRepository userRepo = new MongoUserRepository();
    private User user;
    public LogInInteractor(LogInOutputBoundary logInOutputBoundary) {

        this.logInOutputBoundary = logInOutputBoundary;
    }
    @Override
    public void execute(LogInInputData loginInputData) {
        final String emailID = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        user = userRepo.getUserByEmail(emailID);
        if (user == null) {
            logInOutputBoundary.prepareFailView("Account does not exist");
            System.out.println("Account does not exist");
        }
        else {
            final String pass = user.getPassword();
            if (!pass.equals(password)) {
                logInOutputBoundary.prepareFailView("Incorrect password");
                System.out.println("Incorrect password");
            }
            else {
                // set current user
                logInOutputBoundary.prepareSuccessView(new LogInOutputData("", false));
            }
        }
    }
}
