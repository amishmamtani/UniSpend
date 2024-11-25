package use_case.budgetcompare;

import java.util.HashMap;

public class BudgetCompareOutputData {
    private final HashMap<String, Double> advisedAllocations;
    private final HashMap<String, Double> spentAllocations;
    private final HashMap<String, Double> netAllocations;
    private final double unspent_income;

    public BudgetCompareOutputData(HashMap<String, Double> advisedAllocations, HashMap<String, Double> spentAllocations,
                                   HashMap<String, Double> netAllocations, double unspent_income){
        this.advisedAllocations = advisedAllocations;
        this.spentAllocations = spentAllocations;
        this.netAllocations = netAllocations;
        this.unspent_income = unspent_income;
    }

    public HashMap<String, Double> getAdvisedAllocations() {return advisedAllocations;}

    public HashMap<String, Double> getSpentAllocations() {return spentAllocations;}

    public HashMap<String, Double> getNetAllocations() {return netAllocations;}

    public double getUnspent_income() {return unspent_income;}
}
