package interface_adapter.chatbot;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for managing the state of the chatbot view.
 */
public class ChatBotViewModel extends ViewModel<ChatBotState> {

    /**
     * Constructs a ChatBotViewModel with an initial state and a view name.
     */
    public ChatBotViewModel() {
        super("chatbot");
        setState(new ChatBotState());
    }
}
