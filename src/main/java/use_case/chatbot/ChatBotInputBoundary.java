package use_case.chatbot;

/**
 * Defines the input boundary for chatbot-related use cases.
 */
public interface ChatBotInputBoundary {

    /**
     * Generates a chatbot response based on the provided input data.
     *
     * @param inputData The input data required to generate the response.
     */
    void generateResponse(ChatBotInputData inputData);

    /**
     * Switches back to the previous view or state.
     */
    void switchBack();
}
