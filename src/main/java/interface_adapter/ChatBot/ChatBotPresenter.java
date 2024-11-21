package interface_adapter.ChatBot;

import interface_adapter.ViewModel;
import use_case.chatBot.ChatBotOutputBoundary;
import use_case.chatBot.ChatBotOutputData;

public class ChatBotPresenter implements ChatBotOutputBoundary {
    private final ViewModel<ChatBotState> viewModel;

    public ChatBotPresenter(ViewModel<ChatBotState> viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentAnswer(ChatBotOutputData outputData) {
        ChatBotState chatBotState = new ChatBotState(
                outputData.getAnswer()
        );

        viewModel.setState(chatBotState);
        viewModel.firePropertyChanged();

    }


}
