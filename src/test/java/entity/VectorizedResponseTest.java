package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorizedResponseTest {

    @Test
    void getResponse() {
        VectorizedResponse vectorizedResponse = new VectorizedResponse("Yes!", new double[]{1.0, 2.0});
        String expected = "Yes!";
        assertEquals(expected, vectorizedResponse.getResponse());
    }

    @Test
    void getVector() {
        VectorizedResponse vectorizedResponse = new VectorizedResponse("Yes!", new double[]{1.0, 2.0});
        double[] expected = {1.0, 2.0};
        assertArrayEquals(expected, vectorizedResponse.getVector());
    }
}