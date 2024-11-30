package interface_adapter.home;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignUpState;

public class HomeViewModel extends ViewModel<HomeState> {
    public HomeViewModel() {
        super("home");
        setState(new HomeState());
    }
}
