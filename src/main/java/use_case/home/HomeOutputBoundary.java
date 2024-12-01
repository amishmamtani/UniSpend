package use_case.home;

/**
 * Defines the output boundary for home-related navigation use cases.
 */
public interface HomeOutputBoundary {

    /**
     * Switches the view to the Budget Maker.
     */
    void switchToBudgetMaker();

    /**
     * Switches the view to the Budget Tracker.
     */
    void switchToBudgetTracker();

    /**
     * Switches the view to the Budget Compare.
     */
    void switchToBudgetCompare();

    /**
     * Switches the view to the ChatBot.
     */
    void switchToChatBot();
}
