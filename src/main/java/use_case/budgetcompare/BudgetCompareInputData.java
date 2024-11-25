package use_case.budgetcompare;

import java.util.HashMap;

public class BudgetCompareInputData {
    private final HashMap<String, Double> advisedAllocations;
    private final HashMap<String, Double> spentAllocations;

    public BudgetCompareInputData(HashMap<String, Double> advisedAllocations, HashMap<String, Double> spentAllocations){
        this.advisedAllocations = advisedAllocations;
        this.spentAllocations = spentAllocations;
    }

    public HashMap<String, Double> getAdvisedAllocations() {return advisedAllocations;}

    public HashMap<String, Double> getSpentAllocations() {return spentAllocations;}
}
