package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.ChatBotPresenter;
import interface_adapter.chatbot.ChatBotViewModel;
import interface_adapter.login.LogInController;
import interface_adapter.login.LogInViewModel;
import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpPresenter;
import interface_adapter.signup.SignUpViewModel;
import use_case.chatbot.ChatBotInteractor;
import use_case.signup.SignUpInteractor;
import interface_adapter.chatbot.ChatBotController;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
//        BudgetViewModel budgetViewModel = new BudgetViewModel();
//        BudgetPresenter budgetPresenter = new BudgetPresenter(budgetViewModel);
//        BudgetInteractor budgetInteractor = new BudgetInteractor(budgetPresenter);
//        BudgetController budgetController = new BudgetController(budgetInteractor);
//        new BudgetMakerView(budgetViewModel, budgetController);


//        BudgetTrackerViewModel budgetTrackerViewModel = new BudgetTrackerViewModel();
//        BudgetTrackerPresenter budgetTrackerPresenter = new BudgetTrackerPresenter(viewManagerModel, budgetTrackerViewModel);
//        BudgetTrackerInteractor budgetTrackerInteractor = new BudgetTrackerInteractor(budgetTrackerPresenter);
//        BudgetTrackerController budgetTrackerController = new BudgetTrackerController(budgetTrackerInteractor);
//        new BudgetTrackerView(budgetTrackerViewModel, budgetTrackerController);

//        ChatBotViewModel chatBotViewModel = new ChatBotViewModel();
//        ChatBotPresenter chatBotPresenter = new ChatBotPresenter(new ViewManagerModel(), chatBotViewModel);
//        ChatBotInteractor chatBotInteractor = new ChatBotInteractor(chatBotPresenter);
//        ChatBotController chatBotController = new ChatBotController(chatBotInteractor);
//        new ChatBotView(chatBotController, chatBotViewModel);

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
                                            .addLogIn()
                                            .addSignUp()
                                            .addBudgetMaker()
                                            .addBudgetTracker()
                                            .addChatbot()
                                            .addHome()
                                            .build();
        application.setVisible(true);


    }
}