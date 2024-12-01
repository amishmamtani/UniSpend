package use_case.signup;

import entity.User;
import interface_adapter.user.MongoUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignUpInteractorTest {

    private MongoUserRepository userRepository;
    private TestSignUpOutputBoundary outputBoundary;
    private SignUpInteractor interactor;

    @BeforeEach
    void setUp() {
        userRepository = new MongoUserRepository();
        outputBoundary = new TestSignUpOutputBoundary();
        interactor = new SignUpInteractor(outputBoundary);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteUserByEmail("testlogin.doe@example.com");
    }

    @Test
    void testExecute_SuccessfulSignUp() {
        SignUpInputData inputData = new SignUpInputData(
                "Hugh", "Janus", "testlogin.doe@example.com", "password123");

        interactor.execute(inputData);

        User createdUser = userRepository.getUserByEmail(inputData.getEmail());
        assertNotNull(createdUser);
        assertEquals("Hugh", createdUser.getFirstName());
        assertEquals("Janus", createdUser.getLastName());
        assertEquals("testlogin.doe@example.com", createdUser.getEmail());
        assertTrue(outputBoundary.switchToLogInViewCalled);
    }

    @Test
    void testExecute_AccountAlreadyExists() {
        SignUpInputData inputData = new SignUpInputData(
                "Hugh", "Janus", "testlogin.doe@example.com", "password123");
        User existingUser = new User("Hugh", "Janus","password123", "testlogin.doe@example.com");
        userRepository.saveUser(existingUser);

        interactor.execute(inputData);

        assertEquals("Account Already Exists!", outputBoundary.failMessage);
    }

    @Test
    void testSwitchToLogInView() {
        interactor.switchToLogInView();

        assertTrue(outputBoundary.switchToLogInViewCalled);
    }

    private static class TestSignUpOutputBoundary implements SignUpOutputBoundary {
        boolean switchToLogInViewCalled = false;
        String failMessage = null;

        @Override
        public void switchToLogInView() {
            switchToLogInViewCalled = true;
        }

        @Override
        public void prepareFailView(String message) {
            failMessage = message;
        }
    }
}
