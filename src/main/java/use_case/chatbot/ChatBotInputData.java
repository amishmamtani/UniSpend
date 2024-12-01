package use_case.chatbot;

import entity.Question;

/**
 * Encapsulates the input data required for generating a chatbot response.
 */
public class ChatBotInputData {
    /** The question to be processed by the chatbot */
    private final Question question;

    /**
     * Constructs a ChatBotInputData object with the specified question.
     *
     * @param question The question to be processed.
     */
    public ChatBotInputData(Question question) {
        this.question = question;
    }

    /**
     * Retrieves the question to be processed by the chatbot.
     *
     * @return The question.
     */
    public Question getQuestion() {
        return question;
    }
}
