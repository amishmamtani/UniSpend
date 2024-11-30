package interface_adapter.login;

import interface_adapter.ChatBot.ChatBotState;
import interface_adapter.ViewModel;

public class LogInViewModel extends ViewModel<LogInState> {
    public LogInViewModel() {
        super("log in");
        setState(new LogInState());
    }
}
