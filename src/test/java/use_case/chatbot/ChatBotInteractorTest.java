package use_case.chatbot;

import entity.Question;
import entity.VectorizedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatBotInteractorTest {

    private ChatBotInteractor interactor;
    private List<VectorizedResponse> mockResponses;

    @BeforeEach
    void setUp() {
        // Initialize mock responses for testing
        mockResponses = new ArrayList<>();
        mockResponses.add(new VectorizedResponse("Matched response", new double[]{0.1, 0.2, 0.3, 0.4, 0.5}));
        mockResponses.add(new VectorizedResponse("Non-matching response", new double[]{0.6, 0.7, 0.8, 0.9, 1.0}));
    }

    @Test
    void testGenerateResponseWithMatch() {
        interactor = new ChatBotInteractor(new ChatBotOutputBoundary() {
            @Override
            public void presentAnswer(ChatBotOutputData outputData) {
                assertEquals("Set a monthly savings goal and automate transfers to a savings account right after receiving your income.", outputData.getAnswer().getAnswer());
            }

            @Override
            public void switchBack() {

            }
        });

        ChatBotInputData inputData = new ChatBotInputData(new Question("how do I save money"));
        interactor.generateResponse(inputData);
 }

    @Test
    void testGenerateResponseWithNoMatch() {
        interactor = new ChatBotInteractor(new TestPresenter()) {
            protected double[] vectorizeUserQuestion(String question) {
                return new double[]{0.0, 0.0, 0.0, 0.0, 0.0}; // No match
            }

            protected List<VectorizedResponse> loadVectorizedData() {
                return mockResponses; // Use predefined responses
            }
        };

        ChatBotInputData inputData = new ChatBotInputData(new Question("Test question"));
        interactor.generateResponse(inputData);

        assertEquals("Sorry, I couldn't find an answer to your question.", interactor.getGeneratedAnswer().getAnswer());
    }


    @Test
    void testLoadVectorizedDataSuccess() {
        interactor = new ChatBotInteractor(new TestPresenter()) {
            protected List<VectorizedResponse> loadVectorizedData() {
                return mockResponses; // Simulate a successful load
            }
        };

        List<VectorizedResponse> responses = interactor.loadVectorizedData();
        assertFalse(responses.isEmpty(), "Vectorized data should not be empty");
    }


    @Test
    void testFindBestMatchWithMatch() {
        interactor = new ChatBotInteractor(new TestPresenter()) {
            @Override
            protected List<VectorizedResponse> loadVectorizedData() {
                return mockResponses;
            }
        };

        double[] userVector = {0.1, 0.2, 0.3, 0.4, 0.5}; // Exact match
        VectorizedResponse match = interactor.findBestMatch(userVector);

        assertNotNull(match);
        assertEquals("Matched response", match.getResponse());
    }

    @Test
    void testFindBestMatchNoMatch() {
        interactor = new ChatBotInteractor(new TestPresenter()) {
            @Override
            protected List<VectorizedResponse> loadVectorizedData() {
                return mockResponses;
            }
        };

        double[] userVector = {0.0, 0.0, 0.0, 0.0, 0.0}; // No match
        VectorizedResponse match = interactor.findBestMatch(userVector);

        assertNull(match, "No match should be found");
    }

    @Test
    void testWeightedSimilarity() {
        interactor = new ChatBotInteractor(new TestPresenter());

        double[] vec1 = {1.0, 2.0, 3.0};
        double[] vec2 = {1.0, 2.0, 3.0};
        double similarity = interactor.weightedSimilarity(vec1, vec2);

        assertEquals(1.0, similarity, 0.001, "Similarity should be 1.0 for identical vectors");
    }

    @Test
    void testWeightedSimilarityDifferentVectors() {
        interactor = new ChatBotInteractor(new TestPresenter());

        double[] vec1 = {1.0, 0.0, 0.0};
        double[] vec2 = {0.0, 1.0, 0.0};
        double similarity = interactor.weightedSimilarity(vec1, vec2);

        assertEquals(0.0, similarity, 0.001, "Similarity should be 0.0 for orthogonal vectors");
    }
}

