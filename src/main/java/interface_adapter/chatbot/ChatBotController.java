package interface_adapter.chatbot;

import entity.Question;
import use_case.chatbot.ChatBotInputData;
import use_case.chatbot.ChatBotInteractor;

/**
 * Controller for managing chatbot operations.
 * Delegates the operations to the ChatBotInteractor.
 */
public class ChatBotController {
    /** The interactor responsible for handling chatbot use cases */
    private final ChatBotInteractor chatBotInteractor;

    /**
     * Constructs a ChatBotController with the specified interactor.
     *
     * @param chatBotInteractor The interactor responsible for chatbot operations.
     */
    public ChatBotController(ChatBotInteractor chatBotInteractor) {
        this.chatBotInteractor = chatBotInteractor;
    }

    /**
     * Generates a chatbot response for the provided question.
     *
     * @param question The question to be answered by the chatbot.
     */
    public void generateResponse(Question question) {
        ChatBotInputData inputData = new ChatBotInputData(question);
        chatBotInteractor.generateResponse(inputData);
    }

    /**
     * Switches back to the previous state or screen.
     */
    public void switchBack() {
        chatBotInteractor.switchBack();
    }
}
