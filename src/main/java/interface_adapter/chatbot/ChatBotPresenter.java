package interface_adapter.chatbot;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.home.HomeViewModel;
import use_case.chatbot.ChatBotOutputBoundary;
import use_case.chatbot.ChatBotOutputData;

/**
 * Presenter for chatbot operations.
 * Formats the output data and updates the associated view state.
 */
public class ChatBotPresenter implements ChatBotOutputBoundary {
    /** The ViewModel for managing the chatbot state */
    private final ViewModel<ChatBotState> viewModel;

    /** The ViewManagerModel for handling view navigation */
    private final ViewManagerModel viewManagerModel;

    /** The ViewModel for the home view */
    private final HomeViewModel homeViewModel;

    /**
     * Constructs a ChatBotPresenter with the specified view manager, chatbot view model, and home view model.
     *
     * @param viewManagerModel The view manager for managing navigation.
     * @param viewModel        The ViewModel for managing the chatbot state.
     * @param homeViewModel    The ViewModel for the home view.
     */
    public ChatBotPresenter(ViewManagerModel viewManagerModel, ViewModel<ChatBotState> viewModel,
                            HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.homeViewModel = homeViewModel;
    }

    /**
     * Formats and updates the chatbot state with the generated answer and notifies the ViewModel of changes.
     *
     * @param outputData The output data from the chatbot use case.
     */
    @Override
    public void presentAnswer(ChatBotOutputData outputData) {
        ChatBotState chatBotState = new ChatBotState(
                outputData.getAnswer()
        );

        // Update the ViewModel with the new state
        viewModel.setState(chatBotState);
        viewModel.firePropertyChanged(); // Notify listeners that the state has changed
    }

    /**
     * Switches the view back to the home screen and notifies the view manager of the change.
     */
    @Override
    public void switchBack() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
