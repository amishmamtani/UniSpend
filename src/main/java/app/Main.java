package app;

import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetPresenter;
import interface_adapter.budget.BudgetViewModel;
import interface_adapter.budgettracker.BudgetTrackerController;
import interface_adapter.budgettracker.BudgetTrackerPresenter;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import use_case.budget.BudgetInteractor;
import use_case.budget.BudgetOutputBoundary;
import use_case.budgettracker.BudgetTrackerInteractor;
import use_case.budgettracker.BudgetTrackerOutputBoundary;
import view.BudgetMakerView;

import view.BudgetTrackerView;

import interface_adapter.ChatBot.ChatBotController;
import interface_adapter.ChatBot.ChatBotPresenter;
import interface_adapter.ChatBot.ChatBotViewModel;
import view.ChatBotView;
import view.ChatBotViewOld;
import use_case.chatBot.ChatBotInteractor;


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

        System.out.println(System.getenv("EMAIL"));

    }
}