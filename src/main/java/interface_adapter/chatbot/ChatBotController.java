package interface_adapter.chatbot;

import entity.Question;
import use_case.chatbot.ChatBotInputData;
import use_case.chatbot.ChatBotInteractor;

public class ChatBotController {
    private final ChatBotInteractor chatBotInteractor;

    public ChatBotController(ChatBotInteractor chatBotInteractor) {
        this.chatBotInteractor = chatBotInteractor;
    }

    public void generateResponse(Question question) {
        ChatBotInputData inputData = new ChatBotInputData(question);
        chatBotInteractor.generateResponse(inputData);
    }

    public void switchBack(){
        chatBotInteractor.switchBack();
    }

}
