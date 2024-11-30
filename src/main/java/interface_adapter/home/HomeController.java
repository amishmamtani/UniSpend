package interface_adapter.home;

import use_case.home.HomeInteractor;

public class HomeController {
    private final HomeInteractor homeInteractor;
    public HomeController(HomeInteractor homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    public void switchToBudgetMaker() {
        System.out.println("Switching to Budget Maker");
        homeInteractor.switchToBudgetMaker();
    }

    public void switchToBudgetTracker() {
        homeInteractor.switchToBudgetTracker();
    }

    public void switchToBudgetCompare() {
        homeInteractor.switchToBudgetCompare();
    }

    public void switchToChatBot() {
        homeInteractor.switchToChatBot();
    }
}
