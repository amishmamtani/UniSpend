package app;

import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetPresenter;
import interface_adapter.budget.BudgetViewModel;
import use_case.budget.BudgetInteractor;
import use_case.budget.BudgetOutputBoundary;
import view.BudgetMakerView;
import interface_adapter.ChatBot.ChatBotController;
import interface_adapter.ChatBot.ChatBotPresenter;
import interface_adapter.ChatBot.ChatBotViewModel;
import view.ChatBotView;
import use_case.chatBot.ChatBotInteractor;

public class Main {
    public static void main(String[] args) {
        BudgetViewModel BVM = new BudgetViewModel();
        BudgetOutputBoundary BOB = new BudgetPresenter(BVM);
        BudgetInteractor budgetInteractor = new BudgetInteractor(BOB);
        BudgetController BPC = new BudgetController(budgetInteractor);
        new BudgetMakerView(BVM, BPC);

        ChatBotViewModel viewModel = new ChatBotViewModel();
        ChatBotPresenter presenter = new ChatBotPresenter(viewModel);
        ChatBotInteractor interactor = new ChatBotInteractor(presenter);
        ChatBotController controller = new ChatBotController(interactor);
        ChatBotView view = new ChatBotView(controller, viewModel);
        javax.swing.SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
        });
    }
}