package use_case.login;

import entity.User;
import interface_adapter.user.MongoUserRepository;

/**
 * Interactor for handling login operations.
 * Implements the LogInInputBoundary interface.
 */
public class LogInInteractor implements LogInInputBoundary {

    /** Output boundary for managing login-related responses */
    private final LogInOutputBoundary logInOutputBoundary;

    /** Repository for accessing user data */
    private final MongoUserRepository userRepo = new MongoUserRepository();

    /**
     * Constructs a LogInInteractor with the specified output boundary.
     *
     * @param logInOutputBoundary The output boundary responsible for login responses.
     */
    public LogInInteractor(LogInOutputBoundary logInOutputBoundary) {
        this.logInOutputBoundary = logInOutputBoundary;
    }

    /**
     * Executes the login process with the provided input data.
     * Verifies the user credentials and prepares the appropriate response.
     *
     * @param loginInputData The input data containing login credentials.
     */
    @Override
    public void execute(LogInInputData loginInputData) {
        final String emailID = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        System.out.println("email ID " + emailID);

        User user = userRepo.getUserByEmail(emailID);

        if (user == null) {
            logInOutputBoundary.prepareFailView("Account does not exist");
            System.out.println("Account does not exist");
        } else {
            final String pass = user.getPassword();
            if (!pass.equals(password)) {
                logInOutputBoundary.prepareFailView("Incorrect password");
                System.out.println("Incorrect password");
            } else {
                // Set current user and prepare success view
                System.out.println("Logged in successfully " + user.getEmail());
                logInOutputBoundary.prepareSuccessView(new LogInOutputData(emailID, false));
            }
        }
    }

    /**
     * Switches the view to the sign-up screen.
     */
    @Override
    public void switchToSignUp() {
        logInOutputBoundary.switchToSignUp();
    }
}
