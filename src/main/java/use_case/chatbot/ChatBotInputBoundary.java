package use_case.chatbot;

public interface ChatBotInputBoundary {
    public void generateResponse (ChatBotInputData inputData);
    void switchBack();

}
