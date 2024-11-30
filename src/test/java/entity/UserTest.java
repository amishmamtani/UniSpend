package entity;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void constructorWithoutIncome(){
        User user = new User("First", "Last", "password", "x@mail.com");
        assertEquals("First", user.getFirstName());
    }


    @Test
    void getFirstName() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        assertEquals("First", user.getFirstName());
    }

    @Test
    void getLastName() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        assertEquals("Last", user.getLastName());
    }

    @Test
    void getEmail() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        assertEquals("email@email.com", user.getEmail());
    }

    @Test
    void getPassword() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        assertEquals("password", user.getPassword());
    }

    @Test
    void getIncome() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        assertEquals(1000.0, user.getIncome());
    }

    @Test
    void setIncome() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        user.setIncome(2000.0);
        assertEquals(2000.0, user.getIncome());
    }

    @Test
    void getBudget() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        HashMap<String, Double> expected = new HashMap<>();
        assertEquals(expected, user.getBudget());
    }

    @Test
    void setBudget() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        HashMap<String, Double> newBudget = new HashMap<>(Map.of(
                "HOUSING", 800.0,
                "ENTERTAINMENT", 20.0,
                "FOOD", 170.0,
                "TRAVEL", 10.0
        ));
        user.setBudget(newBudget);
        assertEquals(newBudget, user.getBudget());
    }

    @Test
    void getBudgetTracker() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        HashMap<String, Double> expected = new HashMap<>();
        assertEquals(expected, user.getBudgetTracker());
    }

    @Test
    void setBudgetTracker() {
        User user = new User("First", "Last", "password",
                "email@email.com", 1000.0);
        HashMap<String, Double> newBudgetTracker = new HashMap<>(Map.of(
                "HOUSING", 800.0,
                "ENTERTAINMENT", 20.0,
                "FOOD", 170.0,
                "TRAVEL", 0.0,
                "UNSPENT INCOME", 10.0
        ));
        user.setBudgetTracker(newBudgetTracker);
        assertEquals(newBudgetTracker, user.getBudgetTracker());
    }
}