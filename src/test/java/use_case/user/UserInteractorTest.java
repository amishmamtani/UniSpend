package use_case.user;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class UserInteractorTest {

    private UserInteractor userInteractor;
    private InMemoryUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        userInteractor = new UserInteractor(userRepository);
    }

    @Test
    void testCreateUserWithIncomeAndBudget() {
        User user = new User("John", "Doe", "password123",
                "john.doe@example.com", 5000.0);
        HashMap<String, Double> budget = new HashMap<>();
        budget.put("Housing", 1500.0);
        budget.put("Food", 500.0);
        user.setBudget(budget);

        UserInputData inputData = new UserInputData(user);
        userInteractor.createUser(inputData);

        User savedUser = userRepository.getUserByLastName("Doe");

        assertNotNull(savedUser, "The user should be saved in the repository.");
        assertEquals(5000.0, savedUser.getIncome(), "The user's income should be saved correctly.");
        assertEquals(budget, savedUser.getBudget(), "The user's budget should be saved correctly.");
    }

    @Test
    void testUpdateUserBudget() {
        User user = new User("Jane", "Smith", "password123",
                "jane.smith@example.com", 3000.0);
        userRepository.saveUser(user);

        HashMap<String, Double> newBudget = new HashMap<>();
        newBudget.put("Transportation", 200.0);
        newBudget.put("Healthcare", 300.0);
        user.setBudget(newBudget);

        UserInputData inputData = new UserInputData(user);
        userInteractor.updateUser(inputData);

        User updatedUser = userRepository.getUserByLastName("Smith");

        assertNotNull(updatedUser, "The updated user should exist in the repository.");
        assertEquals(newBudget, updatedUser.getBudget(), "The user's updated budget should be saved correctly.");
    }

    @Test
    void testFetchUserWithBudgetTracker() {
        User user = new User("Alice", "Johnson", "password123",
                "alice.johnson@example.com", 4000.0);
        HashMap<String, Double> budgetTracker = new HashMap<>();
        budgetTracker.put("Housing", 1000.0);
        budgetTracker.put("Food", 300.0);
        user.setBudgetTracker(budgetTracker);

        userRepository.saveUser(user);

        UserOutputData outputData = userInteractor.fetchUser("Johnson");

        assertNotNull(outputData, "Output data should not be null for an existing user.");
        assertEquals(user, outputData.getUser(), "The fetched user should match the one in the repository.");
        assertEquals(budgetTracker, outputData.getUser().getBudgetTracker(),
                "The user's budget tracker should be retrieved correctly.");
    }

    @Test
    void testDeleteUser() {
        User user = new User("Tom", "Williams", "password123",
                "tom.williams@example.com");
        userRepository.saveUser(user);

        userRepository.deleteUserByLastName("Williams");

        assertNull(userRepository.getUserByLastName("Williams"),
                "The user should be deleted from the repository.");
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User("Emma", "Brown", "password123",
                "emma.brown@example.com", 3000.0);
        User user2 = new User("Liam", "Davis", "password123",
                "liam.davis@example.com", 4000.0);

        userRepository.saveUser(user1);
        userRepository.saveUser(user2);

        Iterable<User> allUsers = userRepository.getAllUsers();
        int count = 0;
        for (User user : allUsers) {
            count++;
        }

        assertEquals(2, count, "There should be two users in the repository.");
    }

    @Test
    void testCreateUserWithNullValues() {
        // Creating a user with null attributes
        User user = new User(null, "Doe", null, "john.doe@example.com");
        UserInputData inputData = new UserInputData(user);

        userInteractor.createUser(inputData);

        User savedUser = userRepository.getUserByLastName("Doe");

        assertNotNull(savedUser, "The user should be saved in the repository even if some fields are null.");
        assertNull(savedUser.getFirstName(), "The user's first name should be null.");
        assertEquals("Doe", savedUser.getLastName(),
                "The user's last name should be saved correctly.");
        assertNull(savedUser.getPassword(), "The user's password should be null.");
        assertEquals("john.doe@example.com", savedUser.getEmail(),
                "The user's email should be saved correctly.");
    }

    @Test
    void testUpdateUserWithNullBudget() {
        // Save an initial user
        User user = new User("Jane", "Smith", "password123",
                "jane.smith@example.com", 3000.0);
        userRepository.saveUser(user);

        // Update the user with a null budget
        user.setBudget(null);
        UserInputData inputData = new UserInputData(user);

        userInteractor.updateUser(inputData);

        User updatedUser = userRepository.getUserByLastName("Smith");

        assertNotNull(updatedUser, "The updated user should exist in the repository.");
        assertNull(updatedUser.getBudget(), "The user's budget should be null after update.");
    }

    @Test
    void testFetchUserNonExisting() {
        // Attempt to fetch a user that doesn't exist
        UserOutputData outputData = userInteractor.fetchUser("NonExistingLastName");

        assertNull(outputData, "Output data should be null for a non-existing user.");
    }

    @Test
    void testFetchUserNullLastName() {
        // Attempt to fetch a user with a null last name
        UserOutputData outputData = userInteractor.fetchUser(null);

        assertNull(outputData, "Output data should be null if the last name provided is null.");
    }

    @Test
    void testDeleteUserNullLastName() {
        // Attempt to delete a user with a null last name
        userRepository.deleteUserByLastName(null);

        // Verify no exceptions or errors occur, and no users are affected
        assertTrue(userRepository.getAllUsers().iterator().hasNext() == false,
                "The repository should remain unaffected.");
    }

    @Test
    void testCreateUserWithEmptyFields() {
        // Creating a user with empty strings as attributes
        User user = new User("", "", "", "");
        UserInputData inputData = new UserInputData(user);

        userInteractor.createUser(inputData);

        User savedUser = userRepository.getUserByLastName("");

        assertNotNull(savedUser, "The user with empty fields should be saved in the repository.");
        assertEquals("", savedUser.getFirstName(),
                "The user's first name should be an empty string.");
        assertEquals("", savedUser.getLastName(), "The user's last name should be an empty string.");
        assertEquals("", savedUser.getPassword(), "The user's password should be an empty string.");
        assertEquals("", savedUser.getEmail(), "The user's email should be an empty string.");
    }

    @Test
    void testUpdateUserWithNullLastName() {
        // Save an initial user
        User user = new User("Alice", "Brown",
                "password123", "alice.brown@example.com", 4000.0);
        userRepository.saveUser(user);

        // Attempt to update the user with a null last name
        user.setLastName(null);
        UserInputData inputData = new UserInputData(user);

        userInteractor.updateUser(inputData);

        // Verify the original user remains unchanged
        User savedUser = userRepository.getUserByLastName("Brown");
        assertNotNull(savedUser, "The original user should remain in the repository.");
        assertEquals("Alice", savedUser.getFirstName(),
                "The user's first name should remain unchanged.");
    }
}
