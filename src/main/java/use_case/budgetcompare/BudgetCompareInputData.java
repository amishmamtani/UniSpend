package use_case.budgetcompare;

import entity.User;

import java.util.HashMap;

public class BudgetCompareInputData {
    private final HashMap<String, Double> advisedAllocations;
    private final HashMap<String, Double> spentAllocations;

    /**
     * OLD BudgetCompareInputData without using user
     */
//    public BudgetCompareInputData(HashMap<String, Double> advisedAllocations, HashMap<String, Double> spentAllocations){
//        this.advisedAllocations = advisedAllocations;
//        this.spentAllocations = spentAllocations;
//    }

    public BudgetCompareInputData(User user) {
        this.advisedAllocations = user.getBudget();
        this.spentAllocations = user.getBudgetTracker();
    }

    public HashMap<String, Double> getAdvisedAllocations() {return advisedAllocations;}

    public HashMap<String, Double> getSpentAllocations() {return spentAllocations;}
}
