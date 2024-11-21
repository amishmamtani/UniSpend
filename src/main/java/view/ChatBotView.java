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

public class ChatBotView extends JFrame {

    private final ChatBotController controller;
    private final ChatBotViewModel viewModel;

    private JTextArea responseArea;
    private JTextField questionField;
    private JButton sendButton;

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
                    updateResponseArea((ChatBotState) evt.getNewValue());
                }
            }
        });
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
        sendButton = new JButton("Send");

        inputPanel.add(questionField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

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
        if (!questionText.isEmpty()) {
            controller.generateResponse(new entity.Question(questionText));
            questionField.setText("");
        }
    }

    private void updateResponseArea(ChatBotState state) {
        if (state.getAnswer() != null) {
            responseArea.append("ChatBot: " + state.getAnswer().getAnswer() + "\n");
        }
    }

    public static void main(String[] args) {
        ChatBotViewModel viewModel = new ChatBotViewModel();
        ChatBotController controller = new ChatBotController(new use_case.chatBot.ChatBotInteractor(
                new interface_adapter.ChatBot.ChatBotPresenter(viewModel)
        ));

        SwingUtilities.invokeLater(() -> {
            ChatBotView view = new ChatBotView(controller, viewModel);
            view.setVisible(true);
        });
    }
}
