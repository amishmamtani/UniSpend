package interface_adapter.budgetcompare;

import java.util.HashMap;

public class BudgetCompareState {
    private HashMap<String, Double> advisedAllocations;
    private HashMap<String, Double> spentAllocations;
    private HashMap<String, Double> netAllocations;
    private double unspent_income;

    public BudgetCompareState(HashMap<String, Double> advisedAllocations, HashMap<String, Double> spentAllocations,
                                   HashMap<String, Double> netAllocations, double unspent_income){
        this.advisedAllocations = advisedAllocations;
        this.spentAllocations = spentAllocations;
        this.netAllocations = netAllocations;
        this.unspent_income = unspent_income;
    }

    public BudgetCompareState(){

    }

    public HashMap<String, Double> getAdvisedAllocations() {return advisedAllocations;}

    public HashMap<String, Double> getSpentAllocations() {return spentAllocations;}

    public HashMap<String, Double> getNetAllocations() {return netAllocations;}

    public double getUnspent_income() {return unspent_income;}
}
