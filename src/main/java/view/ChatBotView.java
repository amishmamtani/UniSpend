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

public class ChatBotView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "chatbot";
    private ChatBotController chatBotController;
    private ChatBotViewModel chatBotViewModel;
    private JScrollPane chatBotScrollPane;
    private String questionText;
    private Integer distance = 0;
    private Integer messageCount = 0;
    private JTextField textInput;
    private ButtonGroup languageButtonGroup;
    private Map<String, String> languageMap = new HashMap<>(Map.of(
            "English", "en",
            "French", "fr"
    ));
    private static final String TRANSLATION_API_URL_EN = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-<source>-en";
    private static final String TRANSLATION_API_URL_FR = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-en-fr";
    private final Dotenv dotenv = Dotenv.load();
    private final String API_KEY = this.dotenv.get("TRANSLATION_API_KEY");

    public ChatBotView(ChatBotController chatBotController, ChatBotViewModel chatBotViewModel) {
        this.chatBotController = chatBotController;
        this.chatBotViewModel = chatBotViewModel;

        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.decode("#D6DCE6"));

        ImageIcon backIcon = new ImageIcon("src/main/resources/back.png");
        JLabel backButton = new JLabel(backIcon);
        backButton.setBounds(28, 29, backIcon.getIconWidth(), backIcon.getIconHeight());
        System.out.println(backIcon.getIconHeight());

        chatBotScrollPane = new JScrollPane();
        chatBotScrollPane.setBounds(10,60, 810,430);
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

        this.add(backButton);
        this.add(chatBotScrollPane);
        this.add(textInput);
        this.add(sendButton);
        this.add(englishButton);
        this.add(frenchButton);


        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                chatBotController.switchBack();
            }
        });
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

        questionText = textInput.getText();
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
        messageCount += 1;
    }

    private void updateChatBotMessage(){
        messageCount += 1;
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
        System.out.println(messageCount);

        if(messageCount % 6 == 0){
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
        final ChatBotState state = (ChatBotState) evt.getNewValue();
    }

    public String getViewName() {
        return viewName;
    }
}