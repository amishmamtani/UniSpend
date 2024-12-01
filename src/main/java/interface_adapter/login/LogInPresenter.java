package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import interface_adapter.signup.SignUpViewModel;
import use_case.login.LogInOutputBoundary;
import use_case.login.LogInOutputData;

/**
 * Presenter for login operations.
 * Formats the output data and updates the associated view states.
 */
public class LogInPresenter implements LogInOutputBoundary {
    /** The ViewModel for managing the login state */
    private final LogInViewModel loginViewModel;

    /** The ViewModel for managing the home view state */
    private final HomeViewModel homeViewModel;

    /** The ViewManagerModel for handling view navigation */
    private final ViewManagerModel viewManagerModel;

    /** The ViewModel for managing the sign-up view state */
    private final SignUpViewModel signUpViewModel;

    /**
     * Constructs a LogInPresenter with the specified view models and view manager.
     *
     * @param loginViewModel   The ViewModel for managing the login state.
     * @param homeViewModel    The ViewModel for managing the home view state.
     * @param viewManagerModel The ViewManagerModel for managing navigation between views.
     * @param signUpViewModel  The ViewModel for managing the sign-up view state.
     */
    public LogInPresenter(LogInViewModel loginViewModel,
                          HomeViewModel homeViewModel,
                          ViewManagerModel viewManagerModel, SignUpViewModel signUpViewModel) {
        this.loginViewModel = loginViewModel;
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signUpViewModel = signUpViewModel;
    }

    /**
     * Prepares the success view for login by updating the home view state and notifying the ViewManagerModel.
     *
     * @param response The output data containing login success information.
     */
    @Override
    public void prepareSuccessView(LogInOutputData response) {
        final HomeState homeState = homeViewModel.getState();
        if (homeState.getEmailId() == null) {
            System.out.println("Log In prepare success view: " + response.getUsername());
            homeState.setEmailId(response.getUsername());
            homeViewModel.setState(homeState);
            homeViewModel.firePropertyChanged();
        }
        System.out.println("Home Presenter: " + homeState.getEmailId());
        this.viewManagerModel.setState(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for login by notifying the login view of an error message.
     *
     * @param errorMessage The error message to display.
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final LogInState loginState = loginViewModel.getState();
        // loginState.setLoginError(errorMessage); // Uncomment if error handling is implemented in the state
        loginViewModel.firePropertyChanged();
    }

    /**
     * Switches the view to the Sign-Up screen and notifies the ViewManagerModel of the change.
     */
    @Override
    public void switchToSignUp() {
        this.viewManagerModel.setState(signUpViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
