package entity;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    HashMap<String, Double> advisedCategories = new HashMap<>(Map.of(
            "HOUSING", 4000.0,
            "ENTERTAINMENT", 100.0,
            "FOOD", 750.0,
            "TRAVEL", 150.0
    ));

    Budget budget = new Budget(5000, advisedCategories, 100.0, 200.0 );

    @Test
    void getIncome() {
        assertEquals(5000, budget.getIncome());
    }

    @Test
    void getCategoryAllocations() {
        assertEquals( new HashMap<>(Map.of("HOUSING", 4000.0,
                "ENTERTAINMENT", 100.0,
                "FOOD", 750.0,
                "TRAVEL", 150.0)), budget.getCategoryAllocations());
    }

    @Test
    void getSavings() {
        assertEquals(100.0, budget.getSavings());
    }

    @Test
    void getInvestments() {
        assertEquals(200.0, budget.getInvestments());
    }
}