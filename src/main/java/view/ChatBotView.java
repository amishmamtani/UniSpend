package view;

import interface_adapter.ChatBot.ChatBotController;
import interface_adapter.ChatBot.ChatBotViewModel;
import interface_adapter.ChatBot.ChatBotState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatBotView extends JFrame {

    private final ChatBotController controller;
    private final ChatBotViewModel viewModel;

    private JTextArea responseArea;
    private JTextField questionField;
    private JComboBox<String> languageDropdown;
    private Map<String, String> languageMap = new HashMap<>(Map.of(
            "English", "en",
            "French", "fr"
    ));
    private JButton sendButton;

    private static final String TRANSLATION_API_URL_EN = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-<source>-en";
    private static final String TRANSLATION_API_URL_FR = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-fr";
    private static final String API_KEY = "Bearer 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";

    public ChatBotView(ChatBotController controller, ChatBotViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        setTitle("ChatBot");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();

        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())) {
                    try {
                        updateResponseArea((ChatBotState) evt.getNewValue());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        warmUpModel("https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-fr-en");
        warmUpModel("https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-fr");
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        responseArea = new JTextArea();
        responseArea.setEditable(false);
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        JScrollPane responseScrollPane = new JScrollPane(responseArea);
        add(responseScrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        questionField = new JTextField();

        languageDropdown = new JComboBox<>(new String[]{
                "English", "French"
        });

        sendButton = new JButton("Send");

        JPanel topInputPanel = new JPanel();
        topInputPanel.setLayout(new BorderLayout());
        topInputPanel.add(languageDropdown, BorderLayout.WEST);
        topInputPanel.add(questionField, BorderLayout.CENTER);
        topInputPanel.add(sendButton, BorderLayout.EAST);

        inputPanel.add(topInputPanel, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendQuestion();
            }
        });

        questionField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendQuestion();
            }
        });
    }

    private void sendQuestion() {
        String questionText = questionField.getText().trim();
        String selectedLanguage = (String) languageDropdown.getSelectedItem();

        if (!questionText.isEmpty()) {
            if (!"English".equals(selectedLanguage)) {
                try {
                    questionText = translateWithRetry(questionText, TRANSLATION_API_URL_EN.replace("<source>", languageMap.get(selectedLanguage)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error translating question: " + ex.getMessage());
                    return;
                }
            }
            controller.generateResponse(new entity.Question(questionText));
            questionField.setText("");
        }
    }

    private void updateResponseArea(ChatBotState state) throws Exception {
        if (state.getAnswer() != null) {
            String selectedLanguage = (String) languageDropdown.getSelectedItem();
            String answerText = state.getAnswer().getAnswer();

            if ("French".equals(selectedLanguage)) {
                answerText = translateWithRetry(answerText, TRANSLATION_API_URL_FR);
            }
            responseArea.append("ChatBot: " + answerText + "\n");
        }
    }

    private String translateWithRetry(String text, String url) throws Exception {
        OkHttpClient client = new OkHttpClient();
        JSONObject requestBody = new JSONObject();
        requestBody.put("inputs", text);

        int retryCount = 0;
        int maxRetries = 5;
        int retryDelay = 20000;

        while (retryCount < maxRetries) {
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(requestBody.toString(), MediaType.get("application/json")))
                    .addHeader("Authorization", API_KEY)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                if (response.isSuccessful()) {
                    JSONArray translations = new JSONArray(responseBody);
                    return translations.getJSONObject(0).getString("translation_text");
                } else {
                    JSONObject error = new JSONObject(responseBody);
                    if (error.has("error") && error.getString("error").contains("is currently loading")) {
                        System.out.println("Model is loading. Retrying in " + retryDelay / 1000 + " seconds...");
                        Thread.sleep(retryDelay);
                        retryCount++;
                    } else {
                        throw new Exception("Translation API failed: " + responseBody);
                    }
                }
            }
        }

        throw new Exception("Translation API failed after retries.");
    }

    private void warmUpModel(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Model warm-up failed: " + response.body().string());
            } else {
                System.out.println("Model is warmed up and ready.");
            }
        } catch (Exception e) {
            System.out.println("Error warming up model: " + e.getMessage());
        }
    }
}
