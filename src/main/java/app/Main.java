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

        AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addBudgetMaker()
                                            .addBudgetTracker()
                                            .addChatbot()
                                            .addHome()
                                            .addLogIn()
                                            .addSignUp()
                                            .build();
        application.setVisible(true);


    }
}