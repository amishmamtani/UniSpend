package use_case.chatbot;

import entity.Question;
import entity.VectorizedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatBotInteractorTest {

    private ChatBotInteractor interactor;
    private List<VectorizedResponse> mockVectorizedResponses;

    @BeforeEach
    void setUp() {
        // Mock vectorized responses for testing
        mockVectorizedResponses = new ArrayList<>();
        mockVectorizedResponses.add(new VectorizedResponse("Save at least 20% of your income.", new double[]{0.1, 0.2, 0.3}));
        mockVectorizedResponses.add(new VectorizedResponse("Invest early for better returns.", new double[]{0.4, 0.5, 0.6}));
        mockVectorizedResponses.add(new VectorizedResponse("Build an emergency fund.", new double[]{0.7, 0.8, 0.9}));

        // Create the interactor with mocked responses
        interactor = new ChatBotInteractor(null) {
            protected List<VectorizedResponse> loadVectorizedData() {
                return mockVectorizedResponses; // Use mocked data
            }


            protected double[] vectorizeUserQuestion(String question) {
                // Mock vectorization based on the question
                if (question.equalsIgnoreCase("How much should I save?")) {
                    return new double[]{0.1, 0.2, 0.3}; // Closest to "Save at least 20% of your income."
                } else if (question.equalsIgnoreCase("When should I start investing?")) {
                    return new double[]{0.4, 0.5, 0.6}; // Closest to "Invest early for better returns."
                }
                return new double[]{0.0, 0.0, 0.0}; // No match
            }
        };
    }

    @Test
    void testGenerateResponse_MatchedResponse() {
        // Simulate user input
        ChatBotInputData inputData = new ChatBotInputData(new Question("How much should I save?"));

        // Execute interactor logic
        interactor.generateResponse(inputData);

        // Assert the response
        assertEquals("Save at least 20% of your income.", interactor.getGeneratedAnswer().getAnswer());
    }

    @Test
    void testGenerateResponse_AnotherMatchedResponse() {
        // Simulate user input
        ChatBotInputData inputData = new ChatBotInputData(new Question("When should I start investing?"));

        // Execute interactor logic
        interactor.generateResponse(inputData);

        // Assert the response
        assertEquals("Invest early for better returns.", interactor.getGeneratedAnswer().getAnswer());
    }

    @Test
    void testGenerateResponse_NoMatchedResponse() {
        // Simulate user input
        ChatBotInputData inputData = new ChatBotInputData(new Question("What is financial independence?"));

        // Execute interactor logic
        interactor.generateResponse(inputData);

        // Assert the response
        assertEquals("Sorry, I couldn't find an answer to your question.", interactor.getGeneratedAnswer().getAnswer());
    }
}
