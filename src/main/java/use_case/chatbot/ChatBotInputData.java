package use_case.chatbot;

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
