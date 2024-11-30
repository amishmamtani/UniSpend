package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    @Test
    void getAnswer() {
        Answer answer = new Answer("This is a test for the Answer entity");
        String expected = "This is a test for the Answer entity";
        assertEquals(expected, answer.getAnswer());
    }
}