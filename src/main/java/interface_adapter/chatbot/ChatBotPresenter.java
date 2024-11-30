package interface_adapter.chatbot;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.home.HomeViewModel;
import use_case.chatbot.ChatBotOutputBoundary;
import use_case.chatbot.ChatBotOutputData;

public class ChatBotPresenter implements ChatBotOutputBoundary {
    private final ViewModel<ChatBotState> viewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public ChatBotPresenter(ViewManagerModel viewManagerModel, ViewModel<ChatBotState> viewModel,
                            HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void presentAnswer(ChatBotOutputData outputData) {
        ChatBotState chatBotState = new ChatBotState(
                outputData.getAnswer()
        );

        viewModel.setState(chatBotState);
        viewModel.firePropertyChanged();

    }

    @Override
    public void switchBack() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
