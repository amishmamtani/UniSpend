package use_case.chatbot;

import entity.Answer;
import entity.Question;
import entity.VectorizedResponse;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatBotInteractorTest {

    static class TestPresenter implements ChatBotOutputBoundary {
        private ChatBotOutputData lastOutputData;

        @Override
        public void presentAnswer(ChatBotOutputData outputData) {
            this.lastOutputData = outputData;
        }

        @Override
        public void switchBack() {
            // No-op for testing purposes
        }

        public ChatBotOutputData getLastOutputData() {
            return lastOutputData;
        }
    }

    @Test
    void testConstructorWithVectorizedResponses() {
        TestPresenter presenter = new TestPresenter();
        List<VectorizedResponse> responses = List.of(
                new VectorizedResponse("Response 1", new double[]{0.2, 0.3}),
                new VectorizedResponse("Response 2", new double[]{0.5, 0.5})
        );

        ChatBotInteractor interactor = new ChatBotInteractor(presenter, responses);

        assertNotNull(interactor);
        assertEquals(2, responses.size());
    }

    @Test
    void testConstructorWithoutVectorizedResponses() {
        TestPresenter presenter = new TestPresenter();
        ChatBotInteractor interactor = new ChatBotInteractor(presenter);

        assertNotNull(interactor);
        assertNotNull(interactor.loadVectorizedData());
    }


    @Test
    void testLoadVectorizedDataHandlesMissingFileGracefully() {
        // Arrange: Create a TestPresenter
        TestPresenter presenter = new TestPresenter();

        // Use a non-existent vector file to trigger the exception
        String invalidVectorFile = "NonExistentFile.json";

        // Act: Initialize ChatBotInteractor with an invalid file
        ChatBotInteractor interactor = new ChatBotInteractor(presenter, invalidVectorFile);

        // Call the method to ensure it processes the exception path
        List<VectorizedResponse> responses = interactor.loadVectorizedData();

        // Assert: Ensure that the response list is empty and no exceptions crash the flow
        assertNotNull(responses);
        assertTrue(responses.isEmpty());
    }




    @Test
    void testGenerateResponseMatch() {
        TestPresenter presenter = new TestPresenter();
        List<VectorizedResponse> responses = List.of(
                new VectorizedResponse("Best Match", new double[]{0.5, 0.5, 0.5})
        );

        ChatBotInteractor interactor = new ChatBotInteractor(presenter, responses);

        ChatBotInputData inputData = new ChatBotInputData(new Question("Best Match"));
        interactor.generateResponse(inputData);

        ChatBotOutputData outputData = presenter.getLastOutputData();
        assertNotNull(outputData);
        assertEquals("Best Match", outputData.getAnswer().getAnswer());
    }

    @Test
    void testGenerateResponseNoMatch() {
        TestPresenter presenter = new TestPresenter();
        List<VectorizedResponse> responses = new ArrayList<>();

        ChatBotInteractor interactor = new ChatBotInteractor(presenter, responses);

        ChatBotInputData inputData = new ChatBotInputData(new Question("Unmatched Question"));
        interactor.generateResponse(inputData);

        ChatBotOutputData outputData = presenter.getLastOutputData();
        assertNotNull(outputData);
        assertEquals("Sorry, I couldn't find an answer to your question.", outputData.getAnswer().getAnswer());
    }

    @Test
    void testGenerateResponseExceptionHandling() {
        TestPresenter presenter = new TestPresenter();
        ChatBotInteractor interactor = new ChatBotInteractor(presenter) {
            @Override
            double[] vectorizeUserQuestion(String question) throws Exception {
                throw new Exception("Simulated exception");
            }
        };

        ChatBotInputData inputData = new ChatBotInputData(new Question("Test Exception"));
        interactor.generateResponse(inputData);

        ChatBotOutputData outputData = presenter.getLastOutputData();
        assertNotNull(outputData);
        assertEquals("There was an error processing your question.", outputData.getAnswer().getAnswer());
    }
    @Test
    void testLoadVectorizedDataHandlesMissingFile() {
        String nonExistentFile = "NonExistentVectorizedData.json";
        ChatBotInteractor interactor = new ChatBotInteractor(new TestPresenter(), nonExistentFile);

        // Verify that the interactor does not crash and handles the missing file gracefully
        assertDoesNotThrow(() -> {
            List<VectorizedResponse> responses = interactor.loadVectorizedData();
            assertTrue(responses.isEmpty(), "Responses should be empty if the file is missing.");
        });
    }


    @Test
    void testLoadVectorizedData() {
        ChatBotInteractor interactor = new ChatBotInteractor(new TestPresenter());
        List<VectorizedResponse> responses = interactor.loadVectorizedData();

        assertNotNull(responses);
        // Ensure the response loading logic works correctly
        // Test this with a valid `VectorizedData.json` file in your resources.
    }

    @Test
    void testFindBestMatch() {
        TestPresenter presenter = new TestPresenter();
        List<VectorizedResponse> responses = List.of(
                new VectorizedResponse("Match 1", new double[]{0.8, 0.6, 0.4}),
                new VectorizedResponse("Match 2", new double[]{0.5, 0.5, 0.5})
        );

        ChatBotInteractor interactor = new ChatBotInteractor(presenter, responses);

        double[] userVector = {0.5, 0.5, 0.5};
        VectorizedResponse bestMatch = interactor.findBestMatch(userVector);

        assertNotNull(bestMatch);
        assertEquals("Match 2", bestMatch.getResponse());
    }

    @Test
    void testWeightedSimilarity() {
        ChatBotInteractor interactor = new ChatBotInteractor(new TestPresenter());

        double[] vector1 = {1.0, 2.0, 3.0};
        double[] vector2 = {1.0, 2.0, 3.0};

        double similarity = interactor.weightedSimilarity(vector1, vector2);
        assertEquals(1.0, similarity, 0.01);
    }

    @Test
    void testSwitchBack() {
        TestPresenter presenter = new TestPresenter();
        ChatBotInteractor interactor = new ChatBotInteractor(presenter);

        interactor.switchBack();
        // No assertions needed as this is a no-op, but ensures no exceptions occur
    }
}
