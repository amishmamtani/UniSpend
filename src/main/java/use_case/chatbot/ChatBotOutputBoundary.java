package use_case.chatbot;

public interface ChatBotOutputBoundary {
    void presentAnswer(ChatBotOutputData outputData);
    void switchBack();
}
