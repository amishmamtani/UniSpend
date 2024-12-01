package app;

import interface_adapter.budgetcompare.BudgetCompareViewModel;
import interface_adapter.chatbot.ChatBotController;
import interface_adapter.chatbot.ChatBotPresenter;
import interface_adapter.chatbot.ChatBotViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetPresenter;
import interface_adapter.budget.BudgetViewModel;
import interface_adapter.budgettracker.BudgetTrackerController;
import interface_adapter.budgettracker.BudgetTrackerPresenter;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.login.LogInController;
import interface_adapter.login.LogInPresenter;
import interface_adapter.login.LogInViewModel;
import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpPresenter;
import interface_adapter.signup.SignUpViewModel;
import use_case.budget.BudgetInteractor;
import use_case.budgettracker.BudgetTrackerInteractor;
import use_case.chatbot.ChatBotInteractor;
import use_case.home.HomeInteractor;
import use_case.login.LogInInteractor;
import use_case.signup.SignUpInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private SignUpView signUpView;
    private SignUpViewModel signUpViewModel = new SignUpViewModel();
    private SignUpInteractor signUpInteractor;
    private SignUpController signUpController;
    private SignUpPresenter signUpPresenter;

    private LogInView logInView;
    private LogInViewModel logInViewModel;
    private LogInController logInController;
    private LogInInteractor logInInteractor;
    private LogInPresenter logInPresenter;

    private BudgetMakerView budgetMakerView;
    private BudgetViewModel budgetViewModel;
    private BudgetController budgetController;
    private BudgetPresenter budgetPresenter;
    private BudgetInteractor budgetInteractor;

    private BudgetTrackerView budgetTrackerView;
    private BudgetTrackerViewModel budgetTrackerViewModel;
    private BudgetTrackerController budgetTrackerController;
    private BudgetTrackerPresenter budgetTrackerPresenter;
    private BudgetTrackerInteractor budgetTrackerInteractor;

    private ChatBotView chatBotView;
    private ChatBotViewModel chatBotViewModel;
    private ChatBotController chatBotController;
    private ChatBotPresenter chatBotPresenter;
    private ChatBotInteractor chatBotInteractor;

    private HomeView homeView;
    private HomeViewModel homeViewModel = new HomeViewModel();
    private HomeController homeController;
    private HomePresenter homePresenter;
    private HomeInteractor homeInteractor;

    private BudgetCompareViewModel budgetCompareViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
        cardPanel.setSize(830,600);
    }

    public AppBuilder addSignUp() {
        //signUpViewModel = new SignUpViewModel();
        signUpPresenter = new SignUpPresenter(viewManagerModel, signUpViewModel, logInViewModel);
        signUpInteractor = new SignUpInteractor(signUpPresenter);
        signUpController = new SignUpController(signUpInteractor);
        signUpView = new SignUpView(signUpController, signUpViewModel);
        cardPanel.add(signUpView, signUpView.getViewName());
        return this;
    }

    public AppBuilder addLogIn() {
        logInViewModel = new LogInViewModel();
        logInPresenter = new LogInPresenter(logInViewModel, homeViewModel, viewManagerModel, signUpViewModel);
        logInInteractor = new LogInInteractor(logInPresenter);
        logInController = new LogInController(logInInteractor);
        logInView = new LogInView(logInController, logInViewModel);
        cardPanel.add(logInView, logInView.getViewName());
        return this;
    }

    public AppBuilder addHome() {
        //homeViewModel = new HomeViewModel();
        homePresenter = new HomePresenter(viewManagerModel, homeViewModel,
                budgetViewModel, budgetTrackerViewModel, chatBotViewModel,
                budgetCompareViewModel);
        homeInteractor = new HomeInteractor(homePresenter);
        homeController = new HomeController(homeInteractor);
        homeView = new HomeView(homeController, homeViewModel);
        cardPanel.add(homeView, homeView.getViewName());
        return this;
    }

    public AppBuilder addBudgetMaker() {
        budgetViewModel = new BudgetViewModel();
        //homeViewModel = new HomeViewModel();
        budgetPresenter = new BudgetPresenter(viewManagerModel, budgetViewModel, homeViewModel);
        budgetInteractor = new BudgetInteractor(budgetPresenter);
        budgetController = new BudgetController(budgetInteractor);
        budgetMakerView = new BudgetMakerView(budgetViewModel, budgetController);
        cardPanel.add(budgetMakerView, budgetMakerView.getViewName());
        return this;
    }

    public AppBuilder addBudgetTracker() {
        budgetTrackerViewModel = new BudgetTrackerViewModel();
        //homeViewModel = new HomeViewModel();
        budgetTrackerPresenter = new BudgetTrackerPresenter(viewManagerModel, budgetTrackerViewModel, homeViewModel);
        budgetTrackerInteractor = new BudgetTrackerInteractor(budgetTrackerPresenter);
        budgetTrackerController = new BudgetTrackerController(budgetTrackerInteractor);
        budgetTrackerView = new BudgetTrackerView(budgetTrackerViewModel, budgetTrackerController);
        cardPanel.add(budgetTrackerView, budgetTrackerView.getViewName());
        return this;
    }

    public AppBuilder addChatbot() {
        chatBotViewModel = new ChatBotViewModel();
        //homeViewModel = new HomeViewModel();
        chatBotPresenter = new ChatBotPresenter(viewManagerModel, chatBotViewModel, homeViewModel);
        chatBotInteractor = new ChatBotInteractor(chatBotPresenter);
        chatBotController = new ChatBotController(chatBotInteractor);
        chatBotView = new ChatBotView(chatBotController, chatBotViewModel);
        cardPanel.add(chatBotView, chatBotView.getViewName());
        return this;
    }

    public JFrame build() {
        JFrame frame = new JFrame("UniSpend");
        frame.setSize(830, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cardPanel);

        // Set the initial view
        viewManagerModel.setState(signUpView.getViewName());
        viewManagerModel.firePropertyChanged();

        return frame;
    }
}