package use_case.home;

import use_case.signup.SignUpOutputBoundary;

/**
 * Interactor for handling home-related navigation operations.
 * Implements the HomeInputBoundary interface.
 */
public class HomeInteractor implements HomeInputBoundary {

    /** Output boundary for managing navigation to various views */
    private final HomeOutputBoundary homeOutputBoundary;

    /**
     * Constructs a HomeInteractor with the specified output boundary.
     *
     * @param homeOutputBoundary The output boundary responsible for navigation actions.
     */
    public HomeInteractor(HomeOutputBoundary homeOutputBoundary) {
        this.homeOutputBoundary = homeOutputBoundary;
    }

    /**
     * Switches the view to the Budget Maker and notifies the output boundary.
     */
    @Override
    public void switchToBudgetMaker() {
        System.out.println("Switching to Budget Maker");
        System.out.println("In the home interactor");
        homeOutputBoundary.switchToBudgetMaker();
    }

    /**
     * Switches the view to the Budget Tracker and notifies the output boundary.
     */
    @Override
    public void switchToBudgetTracker() {
        System.out.println("In the home interactor");
        homeOutputBoundary.switchToBudgetTracker();
    }

    /**
     * Switches the view to the Budget Compare and notifies the output boundary.
     */
    @Override
    public void switchToBudgetCompare() {
        System.out.println("In the home interactor");
        homeOutputBoundary.switchToBudgetCompare();
    }

    /**
     * Switches the view to the ChatBot and notifies the output boundary.
     */
    @Override
    public void switchToChatBot() {
        System.out.println("In the home interactor");
        homeOutputBoundary.switchToChatBot();
    }
}
