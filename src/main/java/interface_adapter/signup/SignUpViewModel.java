package interface_adapter.signup;

import interface_adapter.ViewModel;

public class SignUpViewModel extends ViewModel<SignUpState> {
    public SignUpViewModel() {
        super("sign up");
        setState(new SignUpState());
    }
}
