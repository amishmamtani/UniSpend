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
        // Initialize the repository and output boundary
        userRepository = new MongoUserRepository(); // Test database instance
        outputBoundary = new TestLogInOutputBoundary();
        interactor = new LogInInteractor(outputBoundary);
    }

    @AfterEach
    void tearDown() {
        // Clean up test data
        userRepository.deleteUserByEmail("testlogin@gmail.com");
    }

    @Test
    void testExecute_AccountDoesNotExist() {
        // Arrange
        LogInInputData inputData = new LogInInputData("testlogin@gmail.com", "password123");

        // Act
        interactor.execute(inputData);

        // Assert
        assertEquals("Account does not exist", outputBoundary.failMessage);
    }

    @Test
    void testExecute_IncorrectPassword() {
        // Arrange
        User existingUser = new User("Ben", "Dover","password123", "testlogin@gmail.com");
        userRepository.saveUser(existingUser);
        LogInInputData inputData = new LogInInputData("testlogin@gmail.com", "wrongpassword");

        // Act
        interactor.execute(inputData);

        // Assert
        assertEquals("Incorrect password", outputBoundary.failMessage);
    }

    @Test
    void testExecute_SuccessfulLogin() {
        // Arrange
        User existingUser = new User("Ben", "Dover","password123", "testlogin@gmail.com");
        userRepository.saveUser(existingUser);
        LogInInputData inputData = new LogInInputData("testlogin@gmail.com", "password123");

        // Act
        interactor.execute(inputData);

        // Assert
        assertNotNull(outputBoundary.successData);
        assertEquals("testlogin@gmail.com", outputBoundary.successData.getUsername());
    }

    // Helper class for the output boundary
    private static class TestLogInOutputBoundary implements LogInOutputBoundary {
        String failMessage = null;
        LogInOutputData successData = null;

        @Override
        public void prepareFailView(String message) {
            failMessage = message;
        }

        @Override
        public void prepareSuccessView(LogInOutputData data) {
            successData = data;
        }
    }
}
