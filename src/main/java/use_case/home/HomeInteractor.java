package use_case.home;

import use_case.signup.SignUpOutputBoundary;

public class HomeInteractor implements HomeInputBoundary{

    private final HomeOutputBoundary homeOutputBoundary;

    public HomeInteractor(HomeOutputBoundary homeOutputBoundary) {
        this.homeOutputBoundary = homeOutputBoundary;
    }

    @Override
    public void switchToBudgetMaker() {
        System.out.println("Switching to Budget Maker");
        System.out.println("In the home interactor");
        homeOutputBoundary.switchToBudgetMaker();
    }

    @Override
    public void switchToBudgetTracker() {
        System.out.println("In the home interactor");
        homeOutputBoundary.switchToBudgetTracker();
    }

    @Override
    public void switchToBudgetCompare() {
        System.out.println("In the home interactor");
        homeOutputBoundary.switchToBudgetCompare();
    }

    @Override
    public void switchToChatBot() {
        System.out.println("In the home interactor");
        homeOutputBoundary.switchToChatBot();
    }
}
