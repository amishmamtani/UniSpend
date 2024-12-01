package interface_adapter.home;

import interface_adapter.ViewManagerModel;
import interface_adapter.budget.BudgetViewModel;
import interface_adapter.budgetcompare.BudgetCompareViewModel;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import interface_adapter.chatbot.ChatBotViewModel;
import use_case.home.HomeOutputBoundary;

public class HomePresenter implements HomeOutputBoundary {
    private final HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;
    private BudgetViewModel budgetViewModel;
    private BudgetTrackerViewModel budgetTrackerViewModel;
    private ChatBotViewModel chatBotViewModel;
    private BudgetCompareViewModel budgetCompareViewModel;

    public HomePresenter(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                         BudgetViewModel budgetViewModel, BudgetTrackerViewModel budgetTrackerViewModel,
                         ChatBotViewModel chatBotViewModel, BudgetCompareViewModel budgetCompareViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.budgetViewModel = budgetViewModel;
        this.budgetTrackerViewModel = budgetTrackerViewModel;
        this.chatBotViewModel = chatBotViewModel;
        this.budgetCompareViewModel = budgetCompareViewModel;
    }

    @Override
    public void switchToBudgetMaker() {
        budgetViewModel.getState().setEmailId(homeViewModel.getState().getEmailId());
        viewManagerModel.setState(budgetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToBudgetTracker() {
        budgetTrackerViewModel.getState().setEmailId(homeViewModel.getState().getEmailId());
        viewManagerModel.setState(budgetTrackerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToBudgetCompare() {
        viewManagerModel.setState(budgetCompareViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChatBot() {
        viewManagerModel.setState(chatBotViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
