package entity;

/**
 * Represents a question with a single string value.
 */
public class Question {
    /** The text of the question */
    private String question;

    /**
     * Constructs a Question object with the specified text.
     *
     * @param question The text of the question.
     */
    public Question(String question) {
        this.question = question;
    }

    /**
     * Retrieves the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestion() {
        return question;
    }
}
