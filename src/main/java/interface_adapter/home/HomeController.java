package interface_adapter.home;

import use_case.home.HomeInteractor;

/**
 * Controller for managing home screen operations.
 * Delegates navigation actions to the HomeInteractor.
 */
public class HomeController {
    /** The interactor responsible for handling home screen navigation */
    private final HomeInteractor homeInteractor;

    /**
     * Constructs a HomeController with the specified interactor.
     *
     * @param homeInteractor The interactor responsible for home screen navigation operations.
     */
    public HomeController(HomeInteractor homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    /**
     * Switches the view to the Budget Maker.
     */
    public void switchToBudgetMaker() {
        System.out.println("Switching to Budget Maker");
        homeInteractor.switchToBudgetMaker();
    }

    /**
     * Switches the view to the Budget Tracker.
     */
    public void switchToBudgetTracker() {
        homeInteractor.switchToBudgetTracker();
    }

    /**
     * Switches the view to the Budget Compare.
     */
    public void switchToBudgetCompare() {
        homeInteractor.switchToBudgetCompare();
    }

    /**
     * Switches the view to the ChatBot.
     */
    public void switchToChatBot() {
        homeInteractor.switchToChatBot();
    }
}
