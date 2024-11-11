package entity;

import java.util.Map;

public interface BudgetInterface {
    double getIncome();

    Map<String, Double> getCategoryAllocations();

    double getSavings();

    double getInvestments();
}

