package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import use_case.login.LogInOutputBoundary;
import use_case.login.LogInOutputData;

public class LogInPresenter implements LogInOutputBoundary {
    private final LogInViewModel loginViewModel;
    private final HomeViewModel homeViewModel;
    private final ViewManagerModel viewManagerModel;

    public LogInPresenter(LogInViewModel loginViewModel,
                          HomeViewModel homeViewModel,
                          ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LogInOutputData response) {
        final HomeState homeState = homeViewModel.getState();
        homeState.setEmailId(response.getUsername());
        this.viewManagerModel.setState(homeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LogInState loginState =  loginViewModel.getState();
        //loginState.setLoginError(errorMessage);
        loginViewModel.firePropertyChanged();
    }
}
