package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import interface_adapter.signup.SignUpViewModel;
import use_case.login.LogInOutputBoundary;
import use_case.login.LogInOutputData;

public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel loginViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignUpViewModel signUpViewModel;

    public LogInPresenter(LogInViewModel loginViewModel,
                          HomeViewModel homeViewModel,
                          ViewManagerModel viewManagerModel, SignUpViewModel signUpViewModel) {
        this.loginViewModel = loginViewModel;
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signUpViewModel = signUpViewModel;
    }

    @Override
    public void prepareSuccessView(LogInOutputData response) {
        final HomeState homeState = homeViewModel.getState();
        if (homeState.getEmailId() == null){
            System.out.println("Log In prepare success view: " + response.getUsername());
            homeState.setEmailId(response.getUsername());
            homeViewModel.setState(homeState);
            homeViewModel.firePropertyChanged();
        }
        System.out.println("Home Presenter: "+ homeState.getEmailId());
        this.viewManagerModel.setState(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LogInState loginState =  loginViewModel.getState();
        //loginState.setLoginError(errorMessage);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSignUp() {
        this.viewManagerModel.setState(signUpViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
