package interface_adapter.user;

import entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MongoUserRepositoryTest {

    private MongoUserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new MongoUserRepository();
    }

    @Test
    public void testSaveAndRetrieveBudgetAndTracker() {
        User user = new User("Alice", "Brown", "password123", "alice.brown@example.com");
        user.setIncome(60000.0);

        HashMap<String, Double> budget = new HashMap<>();
        budget.put("Groceries", 300.0);
        budget.put("Entertainment", 150.0);
        user.setBudget(budget);

        HashMap<String, Double> budgetTracker = new HashMap<>();
        budgetTracker.put("Groceries", 100.0);
        budgetTracker.put("Entertainment", 50.0);
        user.setBudgetTracker(budgetTracker);

        userRepository.saveUser(user);

        User retrievedUser = userRepository.getUserByLastName("Brown");

        assertNotNull(retrievedUser);
        assertEquals("Alice", retrievedUser.getFirstName());
        assertEquals("Brown", retrievedUser.getLastName());
        assertEquals("password123", retrievedUser.getPassword());
        assertEquals("alice.brown@example.com", retrievedUser.getEmail());
        assertEquals(60000.0, retrievedUser.getIncome(), 0.001);

        assertNotNull(retrievedUser.getBudget());
        assertEquals(300.0, retrievedUser.getBudget().get("Groceries"), 0.001);
        assertEquals(150.0, retrievedUser.getBudget().get("Entertainment"), 0.001);

        assertNotNull(retrievedUser.getBudgetTracker());
        assertEquals(100.0, retrievedUser.getBudgetTracker().get("Groceries"), 0.001);
        assertEquals(50.0, retrievedUser.getBudgetTracker().get("Entertainment"), 0.001);
    }

    @Test
    public void testUpdateBudgetAndTracker() {
        User user = new User("Bob", "Smith", "securepassword", "bob.smith@example.com");
        user.setIncome(75000.0);

        HashMap<String, Double> initialBudget = new HashMap<>();
        initialBudget.put("Rent", 1300.0);
        initialBudget.put("Utilities", 200.0);
        user.setBudget(initialBudget);

        HashMap<String, Double> initialTracker = new HashMap<>();
        initialTracker.put("Rent", 1200.0);
        initialTracker.put("Utilities", 150.0);
        user.setBudgetTracker(initialTracker);

        userRepository.saveUser(user);

        HashMap<String, Double> updatedBudget = userRepository.getUserByLastName("Smith").getBudget();
        updatedBudget.put("Groceries", 500.0);

        HashMap<String, Double> updatedTracker = userRepository.getUserByLastName("Smith").getBudgetTracker();
        updatedTracker.put("Rent", 400.0 + updatedTracker.get("Rent"));

        User retrievedUser = userRepository.getUserByLastName("Smith");
        assertNotNull(retrievedUser);

        retrievedUser.setBudget(updatedBudget);
        retrievedUser.setBudgetTracker(updatedTracker);

        userRepository.saveUser(retrievedUser);

        User updatedUser = userRepository.getUserByLastName("Smith");
        assertNotNull(updatedUser);

        assertEquals(1300.0, updatedUser.getBudget().get("Rent"), 0.001);
        assertEquals(200.0, updatedUser.getBudget().get("Utilities"), 0.001);
        assertEquals(500.0, updatedUser.getBudget().get("Groceries"), 0.001);

        assertEquals(1600.0, updatedUser.getBudgetTracker().get("Rent"), 0.001);
        assertEquals(150.0, updatedUser.getBudgetTracker().get("Utilities"), 0.001);
    }
}
