package use_case.chatbot;

/**
 * A test implementation of the ChatBotOutputBoundary interface for use in testing.
 */
class TestPresenter implements ChatBotOutputBoundary {

    /**
     * Presents the chatbot's response data.
     * Currently implemented as a no-op for testing purposes.
     *
     * @param outputData The output data containing the chatbot's response.
     */
    @Override
    public void presentAnswer(ChatBotOutputData outputData) {
        // No implementation required for testing
    }

    /**
     * Switches back to the previous view or state.
     * Currently implemented as a no-op for testing purposes.
     */
    @Override
    public void switchBack() {
        // No implementation required for testing
    }
}
