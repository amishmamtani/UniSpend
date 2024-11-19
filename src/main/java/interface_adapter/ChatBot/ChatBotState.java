package interface_adapter.ChatBot;

import entity.Answer;

public class ChatBotState {
    private Answer answer;
    public ChatBotState(Answer answer) {
        this.answer = answer;
    }

    public ChatBotState(){}
    public Answer getAnswer() {return answer;}
}
