package view;

import interface_adapter.chatbot.ChatBotController;
import interface_adapter.chatbot.ChatBotViewModel;
import interface_adapter.chatbot.ChatBotState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import view.components.ColouredButton;

/**
 * This class represents the ChatBot view, allowing users to interact with a chatbot, send questions,
 * and view the chatbot's responses in different languages.
 */
public class ChatBotView extends JPanel implements ActionListener, PropertyChangeListener {

    /** The name of the view */
    private final String viewName = "chatbot";

    /** The controller for managing chatbot interactions */
    private ChatBotController chatBotController;

    /** The view model for managing chatbot state */
    private ChatBotViewModel chatBotViewModel;

    /** The scroll pane for displaying chatbot messages */
    private JScrollPane chatBotScrollPane;

    /** The user's question text */
    private String questionText;

    /** The distance variable used for adjusting the position of new messages */
    private Integer distance = 0;

    /** The message count to track the number of interactions */
    private Integer messageCount = 0;

    /** The text input field for user questions */
    private JTextField textInput;

    /** The button group for selecting the language */
    private ButtonGroup languageButtonGroup;

    /** A map to store language codes corresponding to language names */
    private Map<String, String> languageMap = new HashMap<>(Map.of(
            "English", "en",
            "French", "fr"
    ));

    /** The API URL for translation from the source language to English */
    private static final String TRANSLATION_API_URL_EN =
            "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-<source>-en";

    /** The API URL for translation from English to French */
    private static final String TRANSLATION_API_URL_FR =
            "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-fr";

    /** The environment variables loaded using dotenv */
    private final Dotenv dotenv = Dotenv.load();

    /** The API key for accessing the translation API */
    private final String API_KEY = this.dotenv.get("TRANSLATION_API_KEY");

    /**
     * Constructs the ChatBotView with the given controller and view model.
     *
     * @param chatBotController The controller responsible for handling chatbot interactions.
     * @param chatBotViewModel The view model managing the chatbot state.
     */
    public ChatBotView(ChatBotController chatBotController, ChatBotViewModel chatBotViewModel) {
        this.chatBotController = chatBotController;
        this.chatBotViewModel = chatBotViewModel;

        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.decode("#D6DCE6"));

        // Create the back button and configure its appearance
        ImageIcon backIcon = new ImageIcon("src/main/resources/back.png");
        JLabel backButton = new JLabel(backIcon);
        backButton.setBounds(28, 29, backIcon.getIconWidth(), backIcon.getIconHeight());
        System.out.println(backIcon.getIconHeight());

        // Set up the scroll pane for chatbot messages
        chatBotScrollPane = new JScrollPane();
        chatBotScrollPane.setBounds(10, 60, 810, 430);
        chatBotScrollPane.getViewport().setBackground(Color.decode("#D6DCE6"));
        chatBotScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Set up the text input field for user questions
        textInput = new JTextField();
        textInput.setBounds(120, 496, 550, 60);
        textInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textInput.setBackground(Color.decode("#FFFFFF"));

        // Create the send button
        JButton sendButton = new ColouredButton("Send", "#1A1A1A",
                "#FFFFFF", 15).getButton();
        sendButton.setBounds(680, 496, 130, 60);

        // Set up the language selection buttons (English and French)
        languageButtonGroup = new ButtonGroup();
        JRadioButton englishButton = new JRadioButton("English");
        englishButton.setSelected(true);
        englishButton.setActionCommand("English");
        englishButton.setBounds(20, 496, 120, 30);

        JRadioButton frenchButton = new JRadioButton("French");
        frenchButton.setBounds(20, 521, 120, 30);
        frenchButton.setActionCommand("French");
        languageButtonGroup.add(englishButton);
        languageButtonGroup.add(frenchButton);

        // Add components to the view
        this.add(backButton);
        this.add(chatBotScrollPane);
        this.add(textInput);
        this.add(sendButton);
        this.add(englishButton);
        this.add(frenchButton);

        // Add mouse listener to back button
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                chatBotController.switchBack();
            }
        });

        // Add action listener to the text input field for user messages
        textInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUserMessage();
                Timer timer = new Timer(1250, new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        updateChatBotMessage();
                        ((Timer) event.getSource()).stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        // Add action listener to the send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                updateUserMessage();
                Timer timer = new Timer(1250, new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        updateChatBotMessage();
                        ((Timer) event.getSource()).stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        // Warm up the translation models
        warmUpModel("https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-fr-en");
        warmUpModel("https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-fr");
    }

    /**
     * Creates a new panel for displaying a chat message.
     *
     * @param text The text of the message.
     * @param isUser Boolean indicating whether the message is from the user or the chatbot.
     * @return The panel containing the message.
     */
    private JPanel newMessagePanel(String text, Boolean isUser) {
        JPanel textMessagePanel = new JPanel();
        textMessagePanel.setLayout(new GridBagLayout());

        String textMessage = text;
        JLabel textMessageLabel = new JLabel(textMessage);
        textMessageLabel.setForeground(Color.decode("#FFFFFF"));

        FontMetrics fm = getFontMetrics(textMessageLabel.getFont());
        int textWidth = fm.stringWidth(textMessage) + 30;
        System.out.println(textWidth);

        textMessagePanel.add(textMessageLabel);

        // Set panel properties based on whether the message is from the user or chatbot
        if (isUser) {
            textMessagePanel.setBackground(Color.decode("#2166DE"));
            textMessagePanel.setBounds(800 - textWidth, 10 + 60 * distance, textWidth, 40);
        } else {
            textMessagePanel.setBackground(Color.decode("#3C5175"));
            textMessagePanel.setBounds(10, 10 + 60 * distance, textWidth, 40);
        }

        distance += 1;
        return textMessagePanel;
    }

    /**
     * Sends a question to the chatbot, optionally translating it to English first.
     *
     * @param question The user's question.
     * @param language The selected language for the question.
     */
    private void sendQuestion(String question, String language) {
        String questionText = question.trim();
        String selectedLanguage = language;

        if (!questionText.isEmpty()) {
            if (!"English".equals(selectedLanguage)) {
                try {
                    questionText = translateWithRetry(questionText, TRANSLATION_API_URL_EN.replace(
                            "<source>", languageMap.get(selectedLanguage)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Error translating question: " + ex.getMessage());
                    return;
                }
            }
            chatBotController.generateResponse(new entity.Question(questionText));
        }
    }

    /**
     * Updates the user's message in the chat interface.
     */
    private void updateUserMessage() {
        Container viewportView = (Container) chatBotScrollPane.getViewport().getView();

        if (viewportView == null) {
            viewportView = new JPanel();
            viewportView.setLayout(null);
            chatBotScrollPane.setViewportView(viewportView);
        }

        questionText = textInput.getText();
        viewportView.add(newMessagePanel(textInput.getText(), true));

        sendQuestion(textInput.getText(), languageButtonGroup.getSelection().getActionCommand());

        viewportView.setBackground(Color.decode("#D6DCE6"));

        viewportView.revalidate();
        viewportView.repaint();

        // Scroll to the latest message
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = chatBotScrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });

        textInput.setText("");
        messageCount += 1;
    }

    /**
     * Updates the chatbot's message in the chat interface.
     */
    private void updateChatBotMessage() {
        messageCount += 1;
        Container viewportView = (Container) chatBotScrollPane.getViewport().getView();

        if (viewportView == null) {
            viewportView = new JPanel();
            viewportView.setLayout(null);
            chatBotScrollPane.setViewportView(viewportView);
        }

        ChatBotState currentState = chatBotViewModel.getState();
        String answerText = currentState.getAnswer().getAnswer();
        if (languageButtonGroup.getSelection().getActionCommand() == "French") {
            try {
                answerText = translateWithRetry(answerText, TRANSLATION_API_URL_FR);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println(messageCount);

        // If message count is a multiple of 6, clear the previous messages
        if (messageCount % 6 == 0) {
            System.out.println("messageCount if statement");
            viewportView.removeAll();
            distance = 0;
            viewportView.add(newMessagePanel(questionText, true));
            viewportView.revalidate();
            viewportView.repaint();
        }

        viewportView.add(newMessagePanel(answerText, false));

        viewportView.setBackground(Color.decode("#D6DCE6"));

        viewportView.revalidate();
        viewportView.repaint();

        // Scroll to the latest message
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = chatBotScrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

    /**
     * Translates a given text using the translation API, with retries in case of failure.
     *
     * @param text The text to be translated.
     * @param url The API URL for translation.
     * @return The translated text.
     * @throws Exception If the translation API fails.
     */
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

    /**
     * Warms up the translation models by sending a GET request to the model's API.
     *
     * @param url The URL of the translation model's API.
     */
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty method for implementing ActionListener interface
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChatBotState state = (ChatBotState) evt.getNewValue();
    }

    /**
     * Retrieves the view name for the chatbot view.
     *
     * @return The name of the view.
     */
    public String getViewName() {
        return viewName;
    }
}
