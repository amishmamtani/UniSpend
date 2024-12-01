package use_case.login;

import entity.User;
import interface_adapter.user.MongoUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogInInteractorTest {

    private MongoUserRepository userRepository;
    private TestLogInOutputBoundary outputBoundary;
    private LogInInteractor interactor;

    @BeforeEach
    void setUp() {
        userRepository = new MongoUserRepository();
        outputBoundary = new TestLogInOutputBoundary();
        interactor = new LogInInteractor(outputBoundary);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteUserByEmail("testlogin@gmail.com");
    }

    @Test
    void testExecute_AccountDoesNotExist() {
        LogInInputData inputData = new LogInInputData("testlogin@gmail.com", "password123");

        interactor.execute(inputData);

        assertEquals("Account does not exist", outputBoundary.failMessage);
    }

    @Test
    void testExecute_IncorrectPassword() {
        User existingUser = new User("Ben", "Dover","password123",
                "testlogin@gmail.com");
        userRepository.saveUser(existingUser);
        LogInInputData inputData = new LogInInputData("testlogin@gmail.com", "wrongpassword");

        interactor.execute(inputData);

        assertEquals("Incorrect password", outputBoundary.failMessage);
    }

    @Test
    void testExecute_SuccessfulLogin() {
        User existingUser = new User("Ben", "Dover","password123",
                "testlogin@gmail.com");
        userRepository.saveUser(existingUser);
        LogInInputData inputData = new LogInInputData("testlogin@gmail.com", "password123");

        interactor.execute(inputData);

        assertNotNull(outputBoundary.successData);
        assertEquals("testlogin@gmail.com", outputBoundary.successData.getUsername());
    }

    @Test
    void testSwitchToSignUpView() {
        interactor.switchToSignUp();

        assertTrue(outputBoundary.switchToSignUpViewCalled);
    }


    private static class TestLogInOutputBoundary implements LogInOutputBoundary {
        String failMessage = null;
        LogInOutputData successData = null;
        boolean switchToSignUpViewCalled = false;

        @Override
        public void prepareFailView(String message) {
            failMessage = message;
        }

        @Override
        public void switchToSignUp() {
            switchToSignUpViewCalled = true;
        }

        @Override
        public void prepareSuccessView(LogInOutputData data) {
            successData = data;
        }
    }
}
