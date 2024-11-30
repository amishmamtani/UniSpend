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

public class ChatBotInteractor implements ChatBotInputBoundary {

    private final ChatBotOutputBoundary chatBotPresenter;
    private static final String API_KEY = System.getenv("CHATBOT_API_KEY");
    private static final String COHERE_EMBED_URL = "https://api.cohere.ai/embed";
    private final List<VectorizedResponse> vectorizedResponses;

    public ChatBotInteractor(ChatBotOutputBoundary chatBotPresenter) {
        this.chatBotPresenter = chatBotPresenter;
        this.vectorizedResponses = loadVectorizedData();
    }

    @Override
    public void generateResponse(ChatBotInputData inputData) {
        String question = inputData.getQuestion().getQuestion();

        try {
            double[] userQuestionVector = vectorizeUserQuestion(question);

            VectorizedResponse bestMatch = findBestMatch(userQuestionVector);

            String responseText = bestMatch != null ? bestMatch.getResponse() : "Sorry, I couldn't find an answer to your question.";
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

    @Override
    public void switchBack() {
        chatBotPresenter.switchBack();
    }

    private Answer generatedAnswer;

    public Answer getGeneratedAnswer() {
        return generatedAnswer;
    }

    private List<VectorizedResponse> loadVectorizedData() {
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
                JSONArray embeddings = new JSONObject(responseBody).getJSONArray("embeddings").getJSONArray(0);

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

    private VectorizedResponse findBestMatch(double[] userVector) {
        VectorizedResponse bestMatch = null;
        double bestSimilarity = -1;

        for (VectorizedResponse response : vectorizedResponses) {
            double similarity = weightedSimilarity(userVector, response.getVector());
            if (similarity > bestSimilarity) {
                bestSimilarity = similarity;
                bestMatch = response;
            }
        }
        return bestMatch;
    }

    private double cosineSimilarity(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vector lengths do not match.");
        }
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            norm1 += Math.pow(vector1[i], 2);
            norm2 += Math.pow(vector2[i], 2);
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    private double weightedSimilarity(double[] vec1, double[] vec2) {
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

}
