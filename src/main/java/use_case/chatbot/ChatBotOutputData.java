package use_case.chatbot;

import entity.Answer;

public class ChatBotOutputData {
    private final Answer answer;

    public ChatBotOutputData(Answer answer) {
        this.answer = answer;
    }
    public Answer getAnswer() {return answer;}
}
