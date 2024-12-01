package interface_adapter.home;

import interface_adapter.ViewManagerModel;
import interface_adapter.budget.BudgetViewModel;
import interface_adapter.budgetcompare.BudgetCompareViewModel;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import interface_adapter.chatbot.ChatBotViewModel;
import use_case.home.HomeOutputBoundary;

/**
 * Presenter for home screen operations.
 * Handles the transition between different views and updates the ViewManagerModel accordingly.
 */
public class HomePresenter implements HomeOutputBoundary {
    /** The ViewModel for managing the state of the home view */
    private final HomeViewModel homeViewModel;

    /** The ViewManagerModel for handling view navigation */
    private ViewManagerModel viewManagerModel;

    /** The ViewModel for the Budget Maker view */
    private BudgetViewModel budgetViewModel;

    /** The ViewModel for the Budget Tracker view */
    private BudgetTrackerViewModel budgetTrackerViewModel;

    /** The ViewModel for the ChatBot view */
    private ChatBotViewModel chatBotViewModel;

    /** The ViewModel for the Budget Compare view */
    private BudgetCompareViewModel budgetCompareViewModel;

    /**
     * Constructs a HomePresenter with the specified view manager, home view model, and other view models.
     *
     * @param viewManagerModel      The view manager for managing navigation.
     * @param homeViewModel         The ViewModel for managing the home view state.
     * @param budgetViewModel       The ViewModel for the Budget Maker view.
     * @param budgetTrackerViewModel The ViewModel for the Budget Tracker view.
     * @param chatBotViewModel      The ViewModel for the ChatBot view.
     * @param budgetCompareViewModel The ViewModel for the Budget Compare view.
     */
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

    /**
     * Switches the view to the Budget Maker and updates the ViewManagerModel.
     */
    @Override
    public void switchToBudgetMaker() {
        budgetViewModel.getState().setEmailId(homeViewModel.getState().getEmailId());
        viewManagerModel.setState(budgetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the Budget Tracker and updates the ViewManagerModel.
     */
    @Override
    public void switchToBudgetTracker() {
        budgetTrackerViewModel.getState().setEmailId(homeViewModel.getState().getEmailId());
        viewManagerModel.setState(budgetTrackerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the Budget Compare and updates the ViewManagerModel.
     */
    @Override
    public void switchToBudgetCompare() {
        viewManagerModel.setState(budgetCompareViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the ChatBot and updates the ViewManagerModel.
     */
    @Override
    public void switchToChatBot() {
        viewManagerModel.setState(chatBotViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
