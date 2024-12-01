package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LogInViewModel;
import use_case.signup.SignUpOutputBoundary;
import use_case.signup.SignUpOutputData;

/**
 * Presenter for sign-up operations.
 * Handles the transition between views and updates the sign-up view state.
 */
public class SignUpPresenter implements SignUpOutputBoundary {
    /** The ViewModel for managing the sign-up state */
    private final SignUpViewModel signUpViewModel;

    /** The ViewManagerModel for handling view navigation */
    private final ViewManagerModel viewManagerModel;

    /** The ViewModel for managing the login view state */
    private final LogInViewModel logInViewModel;

    /**
     * Constructs a SignUpPresenter with the specified view manager, sign-up view model, and login view model.
     *
     * @param viewManagerModel The view manager for managing navigation between views.
     * @param signUpViewModel  The ViewModel for managing the sign-up state.
     * @param logInViewModel   The ViewModel for managing the login state.
     */
    public SignUpPresenter(ViewManagerModel viewManagerModel, SignUpViewModel signUpViewModel,
                           LogInViewModel logInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signUpViewModel = signUpViewModel;
        this.logInViewModel = logInViewModel;
    }

    /**
     * Prepares the success view for sign-up by switching to the login view and notifying the ViewManagerModel.
     *
     * @param signUpOutputData The output data from the sign-up process.
     */
    public void prepareSuccessView(SignUpOutputData signUpOutputData) {
        viewManagerModel.setState(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the login screen and notifies the ViewManagerModel.
     */
    @Override
    public void switchToLogInView() {
        System.out.println("In the presenter");
        viewManagerModel.setState(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println(viewManagerModel.getState());
    }

    /**
     * Prepares the failure view for sign-up by updating the sign-up state with the error message.
     *
     * @param error The error message to display.
     */
    @Override
    public void prepareFailView(String error) {
        SignUpState signUpState = signUpViewModel.getState();
        signUpState.setError(error);
    }
}
