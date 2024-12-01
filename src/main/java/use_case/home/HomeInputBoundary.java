package use_case.home;

/**
 * Defines the input boundary for home-related use cases.
 */
public interface HomeInputBoundary {

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
