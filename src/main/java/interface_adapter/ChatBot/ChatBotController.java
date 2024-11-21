package interface_adapter.ChatBot;

import entity.Answer;
import entity.Question;
import use_case.chatBot.ChatBotInputData;
import use_case.chatBot.ChatBotInteractor;

public class ChatBotController {
    private final ChatBotInteractor chatBotInteractor;

    public ChatBotController(ChatBotInteractor chatBotInteractor) {
        this.chatBotInteractor = chatBotInteractor;
    }

    public void generateResponse(Question question) {
        ChatBotInputData inputData = new ChatBotInputData(question);
        chatBotInteractor.generateResponse(inputData);
    }

}
