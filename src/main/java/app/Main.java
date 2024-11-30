package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetPresenter;
import interface_adapter.budget.BudgetViewModel;
import interface_adapter.budgettracker.BudgetTrackerController;
import interface_adapter.budgettracker.BudgetTrackerPresenter;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import interface_adapter.login.LogInController;
import interface_adapter.login.LogInViewModel;
import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpPresenter;
import interface_adapter.signup.SignUpViewModel;
import use_case.budget.BudgetInteractor;
import use_case.budgettracker.BudgetTrackerInteractor;
import use_case.signup.SignUpInteractor;
import view.*;

import interface_adapter.ChatBot.ChatBotController;
import interface_adapter.ChatBot.ChatBotPresenter;
import interface_adapter.ChatBot.ChatBotViewModel;
import use_case.chatBot.ChatBotInteractor;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        BudgetViewModel budgetViewModel = new BudgetViewModel();
        BudgetPresenter budgetPresenter = new BudgetPresenter(budgetViewModel);
        BudgetInteractor budgetInteractor = new BudgetInteractor(budgetPresenter);
        BudgetController budgetController = new BudgetController(budgetInteractor);
        new BudgetMakerView(budgetViewModel, budgetController);


        BudgetTrackerViewModel budgetTrackerViewModel = new BudgetTrackerViewModel();
        BudgetTrackerPresenter budgetTrackerPresenter = new BudgetTrackerPresenter(budgetTrackerViewModel);
        BudgetTrackerInteractor budgetTrackerInteractor = new BudgetTrackerInteractor(budgetTrackerPresenter);
        BudgetTrackerController budgetTrackerController = new BudgetTrackerController(budgetTrackerInteractor);
        new BudgetTrackerView(budgetTrackerViewModel, budgetTrackerController);

        ChatBotViewModel chatBotViewModel = new ChatBotViewModel();
        ChatBotPresenter chatBotPresenter = new ChatBotPresenter(chatBotViewModel);
        ChatBotInteractor chatBotInteractor = new ChatBotInteractor(chatBotPresenter);
        ChatBotController chatBotController = new ChatBotController(chatBotInteractor);
        new ChatBotView(chatBotController, chatBotViewModel);

        LogInViewModel logInViewModel = new LogInViewModel();
        LogInController logInController = new LogInController();
        new LogInView(logInController, logInViewModel);

        SignUpViewModel signUpViewModel = new SignUpViewModel();
        SignUpPresenter signUpPresenter = new SignUpPresenter(new ViewManagerModel(), signUpViewModel, logInViewModel);
        SignUpInteractor signUpInteractor = new SignUpInteractor(signUpPresenter);
        SignUpController signUpController = new SignUpController(signUpInteractor);
        new SignUpView(signUpController, signUpViewModel);

        AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLogInView()
                                            .addSignUpView()
                                            .build();
        application.setVisible(true);

        new HomeView();

    }
}