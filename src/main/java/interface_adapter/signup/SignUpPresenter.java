package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LogInViewModel;
import use_case.signup.SignUpOutputBoundary;
import use_case.signup.SignUpOutputData;

public class SignUpPresenter implements SignUpOutputBoundary {
    private final SignUpViewModel signUpViewModel;
    private ViewManagerModel viewManagerModel;
    private LogInViewModel logInViewModel;

    public SignUpPresenter(ViewManagerModel viewManagerModel, SignUpViewModel signUpViewModel, LogInViewModel logInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signUpViewModel = signUpViewModel;
        this.logInViewModel = logInViewModel;
    }

    public void prepareSuccessView(SignUpOutputData signUpOutputData) {
        viewManagerModel.setState(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLogInView() {
        System.out.println("In the presenter");
        viewManagerModel.setState(logInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println(viewManagerModel.getState());
    }
}
