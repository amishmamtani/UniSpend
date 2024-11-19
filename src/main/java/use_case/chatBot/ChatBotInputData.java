package use_case.chatBot;

import entity.Answer;
import entity.Question;

public class ChatBotInputData {
    private final Question question;

    public ChatBotInputData(Question question) {
        this.question = question;
    }
    public Question getQuestion() {
        return question;
    }
}
