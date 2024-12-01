package use_case.home;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HomeInteractorTest {
    private HomeInteractor homeInteractor;
    private HomeOutputBoundary outputBoundary;

    @BeforeEach
    void setUp() {
        outputBoundary = new TestHomeOutputBoundary();
        homeInteractor = new HomeInteractor(outputBoundary);
    }

    @Test
    void testSwitchToBudgetCompare() {
        homeInteractor.switchToBudgetCompare();
        assertTrue(((TestHomeOutputBoundary) outputBoundary).switchToBudgetCompareCalled);
    }

    @Test
    void testSwitchToBudgetMaker() {
        homeInteractor.switchToBudgetMaker();
        assertTrue(((TestHomeOutputBoundary) outputBoundary).switchToBudgetMakerCalled);
    }

    @Test
    void testSwitchToBudgetTracker() {
        homeInteractor.switchToBudgetTracker();
        assertTrue(((TestHomeOutputBoundary) outputBoundary).switchToBudgetTrackerCalled);
    }

    @Test
    void testSwitchToChatBot() {
        homeInteractor.switchToChatBot();
        assertTrue(((TestHomeOutputBoundary) outputBoundary).switchToChatBotCalled);
    }

    private static class TestHomeOutputBoundary implements HomeOutputBoundary {

        boolean switchToBudgetMakerCalled = false;
        boolean switchToBudgetTrackerCalled = false;
        boolean switchToBudgetCompareCalled = false;
        boolean switchToChatBotCalled = false;

        @Override
        public void switchToBudgetMaker() {
            switchToBudgetMakerCalled = true;
        }

        @Override
        public void switchToBudgetTracker() {
            switchToBudgetTrackerCalled = true;
        }

        @Override
        public void switchToBudgetCompare() {
            switchToBudgetCompareCalled = true;
        }

        @Override
        public void switchToChatBot() {
            switchToChatBotCalled = true;
        }
    }
}
