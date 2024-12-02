package use_case.chatbot;

import entity.Answer;
import entity.VectorizedResponse;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Interactor for chatbot functionality.
 * Handles question processing, vectorized data comparison, and response generation.
 * Implements the ChatBotInputBoundary interface.
 */
public class ChatBotInteractor implements ChatBotInputBoundary {

    /** Presenter responsible for formatting and displaying chatbot responses */
    private final ChatBotOutputBoundary chatBotPresenter;

    /** Environment variables loaded using Dotenv */
    private final Dotenv dotenv = Dotenv.load();

    /** API key for embedding service */
    private final String API_KEY = this.dotenv.get("CHATBOT_API_KEY");

    /** URL for the Cohere embedding service */
    private static final String COHERE_EMBED_URL = "https://api.cohere.ai/embed";

    /** Preloaded vectorized responses for matching questions */
    private final List<VectorizedResponse> vectorizedResponses;

    /** Generated answer from the chatbot */
    private Answer generatedAnswer;

    /**
     * Constructs a ChatBotInteractor with the specified presenter and loads vectorized response data.
     *
     * @param chatBotPresenter The presenter responsible for chatbot output.
     */
    public ChatBotInteractor(ChatBotOutputBoundary chatBotPresenter) {
        this.chatBotPresenter = chatBotPresenter;
        this.vectorizedResponses = loadVectorizedData();
    }

    /**
     * Processes the input question, generates a vector representation, and finds the best-matched response.
     *
     * @param inputData The input data containing the question to be processed.
     */
    @Override
    public void generateResponse(ChatBotInputData inputData) {
        String question = inputData.getQuestion().getQuestion();

        try {
            double[] userQuestionVector = vectorizeUserQuestion(question);
            VectorizedResponse bestMatch = findBestMatch(userQuestionVector);

            String responseText = bestMatch != null ?
                    bestMatch.getResponse() : "Sorry, I couldn't find an answer to your question.";
            Answer answer = new Answer(responseText);

            ChatBotOutputData outputData = new ChatBotOutputData(answer);
            chatBotPresenter.presentAnswer(outputData);
            generatedAnswer = new Answer(responseText);

        } catch (Exception e) {
            e.printStackTrace();

            Answer errorAnswer = new Answer("There was an error processing your question.");
            ChatBotOutputData outputData = new ChatBotOutputData(errorAnswer);
            chatBotPresenter.presentAnswer(outputData);
        }
    }


    /**
     * Retrieves the last generated answer.
     *
     * @return The generated answer.
     */
    public Answer getGeneratedAnswer() {
        return generatedAnswer;
    }

    /**
     * Loads vectorized response data from a JSON file.
     *
     * @return A list of preloaded vectorized responses.
     */
    List<VectorizedResponse> loadVectorizedData() {
        List<VectorizedResponse> responses = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("VectorizedData.json")) {
            if (is == null) {
                throw new RuntimeException("Vectorized data file not found!");
            }
            String jsonText = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(jsonText);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String response = obj.getString("response");
                JSONArray vectorArray = obj.getJSONArray("vector");

                double[] vector = new double[vectorArray.length()];
                for (int j = 0; j < vectorArray.length(); j++) {
                    vector[j] = vectorArray.getDouble(j);
                }

                responses.add(new VectorizedResponse(response, vector));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }

    /**
     * Converts a user's question into a vector representation using the Cohere embedding service.
     *
     * @param question The user's question.
     * @return A vector representation of the question.
     * @throws Exception If the embedding service fails.
     */
    private double[] vectorizeUserQuestion(String question) throws Exception {
        OkHttpClient client = new OkHttpClient();

        JSONObject requestBody = new JSONObject();
        requestBody.put("texts", new JSONArray(List.of(question)));

        Request request = new Request.Builder()
                .url(COHERE_EMBED_URL)
                .post(RequestBody.create(requestBody.toString(), MediaType.get("application/json")))
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONArray embeddings = new JSONObject(responseBody).getJSONArray(
                        "embeddings").getJSONArray(0);

                double[] vector = new double[embeddings.length()];
                for (int i = 0; i < embeddings.length(); i++) {
                    vector[i] = embeddings.getDouble(i);
                }
                return vector;
            } else {
                throw new Exception("Failed to fetch embeddings: " + response.body().string());
            }
        }
    }

    /**
     * Finds the best matching response for a user's question vector using vectorized response data.
     *
     * @param userVector The vector representation of the user's question.
     * @return The best matching vectorized response, or null if no match exceeds the similarity threshold.
     */
    VectorizedResponse findBestMatch(double[] userVector) {
        VectorizedResponse bestMatch = null;
        double bestSimilarity = -1;
        double similarityThreshold = 0.2; // Adjust this value as needed

        for (VectorizedResponse response : vectorizedResponses) {
            double similarity = weightedSimilarity(userVector, response.getVector());
            if (similarity > bestSimilarity && similarity > similarityThreshold) {
                bestSimilarity = similarity;
                bestMatch = response;
            }
        }
        return bestMatch;
    }


    /**
     * Computes a weighted similarity score between two vectors.
     *
     * @param vec1 The first vector.
     * @param vec2 The second vector.
     * @return The weighted similarity score.
     */
    double weightedSimilarity(double[] vec1, double[] vec2) {
        int minLength = Math.min(vec1.length, vec2.length);
        double dotProduct = 0.0;
        double vec1Magnitude = 0.0;
        double vec2Magnitude = 0.0;

        for (int i = 0; i < minLength; i++) {
            dotProduct += vec1[i] * vec2[i];
            vec1Magnitude += vec1[i] * vec1[i];
            vec2Magnitude += vec2[i] * vec2[i];
        }

        vec1Magnitude = Math.sqrt(vec1Magnitude);
        vec2Magnitude = Math.sqrt(vec2Magnitude);

        return (vec1Magnitude > 0 && vec2Magnitude > 0) ? dotProduct / (vec1Magnitude * vec2Magnitude) : 0.0;
    }

    /**
     * Switches back to the previous view or state.
     */
    @Override
    public void switchBack() {
        chatBotPresenter.switchBack();
    }

}
