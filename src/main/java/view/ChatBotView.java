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
import view.components.ColouredButton;

public class ChatBotView extends JPanel implements ActionListener, PropertyChangeListener {
    private ChatBotController chatBotController;
    private ChatBotViewModel chatBotViewModel;
    private JScrollPane chatBotScrollPane;
    private Integer distance = 0;
    private JTextField textInput;
    private ButtonGroup languageButtonGroup;
    private Map<String, String> languageMap = new HashMap<>(Map.of(
            "English", "en",
            "French", "fr"
    ));
    private static final String TRANSLATION_API_URL_EN = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-<source>-en";
    private static final String TRANSLATION_API_URL_FR = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-fr";
    private static final String API_KEY = System.getenv("TRANSLATION_API_KEY");

    public ChatBotView(ChatBotController chatBotController, ChatBotViewModel chatBotViewModel) {
        this.chatBotController = chatBotController;
        this.chatBotViewModel = chatBotViewModel;

        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.decode("#D6DCE6"));

        chatBotScrollPane = new JScrollPane();
        chatBotScrollPane.setBounds(10,10, 810,480);
        chatBotScrollPane.getViewport().setBackground(Color.decode("#D6DCE6"));
        chatBotScrollPane.setBorder(BorderFactory.createEmptyBorder());

        textInput = new JTextField();
        textInput.setBounds(120, 496, 550, 60);
        textInput.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        textInput.setBackground(Color.decode("#FFFFFF"));


        JButton sendButton = new ColouredButton("Send","#1A1A1A","#FFFFFF", 15).getButton();
        sendButton.setBounds(680,496, 130 ,60);

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

        this.add(chatBotScrollPane);
        this.add(textInput);
        this.add(sendButton);
        this.add(englishButton);
        this.add(frenchButton);

        JFrame frame = new JFrame("Chat Bot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(830,600);
        frame.setResizable(false);
        frame.setContentPane(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.decode("#FFFFFF"));
        frame.setVisible(true);

        textInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUserMessage();
                Timer timer = new Timer(1250, new ActionListener(){
                    public void actionPerformed(ActionEvent event) {
                        updateChatBotMessage();
                        ((Timer) event.getSource()).stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                updateUserMessage();
                Timer timer = new Timer(1250, new ActionListener(){
                    public void actionPerformed(ActionEvent event) {
                        updateChatBotMessage();
                        ((Timer) event.getSource()).stop();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        warmUpModel("https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-fr-en");
        warmUpModel("https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-fr");

    }

    private JPanel newMessagePanel(String text, Boolean isUser){
        JPanel textMessagePanel = new JPanel();
        textMessagePanel.setLayout(new GridBagLayout());

        String textMessage = text;
        JLabel textMessageLabel = new JLabel(textMessage);
        textMessageLabel.setForeground(Color.decode("#FFFFFF"));

        FontMetrics fm = getFontMetrics(textMessageLabel.getFont());
        int textWidth = fm.stringWidth(textMessage) + 30;
        System.out.println(textWidth);

        textMessagePanel.add(textMessageLabel);

        if (isUser){
            textMessagePanel.setBackground(Color.decode("#2166DE"));
            textMessagePanel.setBounds(800-textWidth, 10 + 60 * distance, textWidth, 40);
        }
        else {
            textMessagePanel.setBackground(Color.decode("#3C5175"));
            textMessagePanel.setBounds(10, 10 + 60 * distance, textWidth, 40);
        }

        distance += 1;
        return textMessagePanel;

    }

    private void sendQuestion(String question, String language) {
        String questionText = question.trim();
        String selectedLanguage = language;

        if (!questionText.isEmpty()) {
            if (!"English".equals(selectedLanguage)) {
                try {
                    questionText = translateWithRetry(questionText, TRANSLATION_API_URL_EN.replace("<source>", languageMap.get(selectedLanguage)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error translating question: " + ex.getMessage());
                    return;
                }
            }
            chatBotController.generateResponse(new entity.Question(questionText));
        }
    }

    private void updateUserMessage(){
        Container viewportView = (Container) chatBotScrollPane.getViewport().getView();

        if (viewportView == null) {
            viewportView = new JPanel();
            viewportView.setLayout(null);
            chatBotScrollPane.setViewportView(viewportView);
        }

        viewportView.add(newMessagePanel(textInput.getText(), true));

        sendQuestion(textInput.getText(), languageButtonGroup.getSelection().getActionCommand());

        viewportView.setBackground(Color.decode("#D6DCE6"));

        viewportView.revalidate();
        viewportView.repaint();

        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = chatBotScrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });

        textInput.setText("");
    }

    private void updateChatBotMessage(){
        Container viewportView = (Container) chatBotScrollPane.getViewport().getView();

        if (viewportView == null) {
            viewportView = new JPanel();
            viewportView.setLayout(null);
            chatBotScrollPane.setViewportView(viewportView);
        }

        ChatBotState currentState = chatBotViewModel.getState();
        String answerText = currentState.getAnswer().getAnswer();
        if(languageButtonGroup.getSelection().getActionCommand() == "French") {
            try {
                answerText = translateWithRetry(answerText, TRANSLATION_API_URL_FR);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        viewportView.add(newMessagePanel(answerText, false));

        viewportView.setBackground(Color.decode("#D6DCE6"));

        viewportView.revalidate();
        viewportView.repaint();

        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = chatBotScrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}