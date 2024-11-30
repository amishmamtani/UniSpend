package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void getQuestion() {
        Question question = new Question("Are we testing Question entity?");
        String expected = "Are we testing Question entity?";
        assertEquals(expected, question.getQuestion());
    }
}