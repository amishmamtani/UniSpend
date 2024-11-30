package interface_adapter.ChatBot;

import interface_adapter.ViewModel;

public class ChatBotViewModel extends ViewModel<ChatBotState> {
    public ChatBotViewModel() {
        super("chatbot");
        setState(new ChatBotState());
    }
}
