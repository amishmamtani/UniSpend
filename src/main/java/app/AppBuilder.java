package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LogInController;
import interface_adapter.login.LogInViewModel;
import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpPresenter;
import interface_adapter.signup.SignUpViewModel;
import use_case.signup.SignUpInteractor;
import view.LogInView;
import view.SignUpView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private SignUpView signUpView;
    private SignUpViewModel signUpViewModel;
    private SignUpInteractor signUpInteractor;
    private SignUpController signUpController;
    private SignUpPresenter signUpPresenter;
    private LogInView logInView;
    private LogInViewModel logInViewModel;
    private LogInController logInController;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
        cardPanel.setSize(830,600);
    }

    public AppBuilder addSignUpView() {
        signUpViewModel = new SignUpViewModel();
        signUpPresenter = new SignUpPresenter(new ViewManagerModel(), signUpViewModel, logInViewModel);
        signUpInteractor = new SignUpInteractor(signUpPresenter);
        signUpController = new SignUpController(signUpInteractor);
        signUpView = new SignUpView(signUpController, signUpViewModel);
        cardPanel.add(signUpView, signUpView.getViewName());
        return this;
    }

    public AppBuilder addLogInView() {
        logInViewModel = new LogInViewModel();
        logInController = new LogInController();
        logInView = new LogInView(logInController, logInViewModel);
        cardPanel.add(logInView, logInView.getViewName());
        return this;
    }

    public JFrame build() {
        JFrame frame = new JFrame("UniSpend");
        frame.setSize(830, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cardPanel);

        viewManagerModel.setState(signUpView.getViewName());
        viewManagerModel.firePropertyChanged();

        return frame;
    }
}
