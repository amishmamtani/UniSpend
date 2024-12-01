package use_case.chatbot;

/**
 * Defines the output boundary for chatbot-related use cases.
 */
public interface ChatBotOutputBoundary {

    /**
     * Presents the chatbot's response data to the user or external system.
     *
     * @param outputData The output data containing the chatbot's response.
     */
    void presentAnswer(ChatBotOutputData outputData);

    /**
     * Switches back to the previous view or state.
     */
    void switchBack();
}
