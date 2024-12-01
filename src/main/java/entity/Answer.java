package entity;

/**
 * Represents an answer with a single string value.
 */
public class Answer {
    /** The text of the answer */
    private String answer;

    /**
     * Constructs an Answer object with the specified text.
     *
     * @param answer The text of the answer.
     */
    public Answer(String answer) {
        this.answer = answer;
    }

    /**
     * Retrieves the text of the answer.
     *
     * @return The text of the answer.
     */
    public String getAnswer() {
        return answer;
    }
}
