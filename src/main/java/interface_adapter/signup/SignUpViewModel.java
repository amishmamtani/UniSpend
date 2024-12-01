package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * Represents the ViewModel for managing the state of the sign-up view.
 */
public class SignUpViewModel extends ViewModel<SignUpState> {

    /**
     * Constructs a SignUpViewModel with an initial state and a view name.
     */
    public SignUpViewModel() {
        super("sign up");
        setState(new SignUpState());
    }
}

