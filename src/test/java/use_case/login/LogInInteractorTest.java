package use_case.login;

import entity.User;
import interface_adapter.user.MongoUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogInInteractorTest {

    private LogInInteractor logInInteractor;

    @Test
    void testAccountDoesNotExist() {
        MongoUserRepository mockUserRepo = new MongoUserRepository() {
            @Override
            public User getUserByEmail(String email) {
                return null; // Simulate account does not exist
            }
        };

        LogInOutputBoundary mockOutputBoundary = new LogInOutputBoundary() {
            @Override
            public void prepareSuccessView(LogInOutputData outputData) {
                fail("Success view should not be called when account does not exist.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Account does not exist", errorMessage);
            }
        };


        LogInInteractor logInInteractor = new LogInInteractor(mockOutputBoundary);
        LogInInputData inputData = new LogInInputData("Anish", "password123");

        logInInteractor.execute(inputData);


    }
}