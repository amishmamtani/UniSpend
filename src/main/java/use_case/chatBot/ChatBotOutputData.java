package use_case.chatBot;

import entity.Answer;

public class ChatBotOutputData {
    private final Answer answer;

    public ChatBotOutputData(Answer answer) {
        this.answer = answer;
    }
    public Answer getAnswer() {return answer;}
}
