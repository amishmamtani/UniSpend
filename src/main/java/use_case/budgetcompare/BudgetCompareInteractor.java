package use_case.budgetcompare;

import use_case.budgettracker.BudgetTrackerInputBoundary;
import use_case.budgettracker.BudgetTrackerOutputBoundary;

import java.util.HashMap;
import java.util.Map;

public class BudgetCompareInteractor implements BudgetCompareInputBoundary {
    private final BudgetCompareOutputBoundary budgetComparePresenter;

    public BudgetCompareInteractor(BudgetCompareOutputBoundary budgetComparePresenter){
        this.budgetComparePresenter = budgetComparePresenter;
    }

    @Override
    public void createBudgetCompare(BudgetCompareInputData compareInputData) {
        HashMap<String,Double> advisedAllocations = compareInputData.getAdvisedAllocations();
        HashMap<String, Double> spentAllocations = compareInputData.getSpentAllocations();

        double unspent_income = getUnspentIncome(spentAllocations);

        HashMap<String, Double> netAllocations = getNetAllocations(advisedAllocations, spentAllocations);

        /**
         * I have implemented it so far assuming that spentAllocations and advisedAllocations all have the exact same
         * categories and nothing else (other than unspent_income in spentAllocations which is not in
         * advisedAllocations)
         *
         * Now I need to update it in the event where:
         * 1. spentAllocations has a category advisedAllocations doesn't
         * 2. advisedAllocations has a category spentAllocations doesn't
         *
         * Should we assume user will only spend in categories in the budget maker?
         * What happens if the user now adds a category to the tracker which wasn't in the maker?? -> How will we
         * calculate the net amount they have left to spent on that category
         */

        BudgetCompareOutputData compareOutputData = new BudgetCompareOutputData(advisedAllocations, spentAllocations,
                netAllocations, unspent_income);
        budgetComparePresenter.presentBudgetCompare(compareOutputData);
    }

    private static Double getUnspentIncome(HashMap<String, Double> spentAllocations) {
        return spentAllocations.get("UNSPENT INCOME");
    }

    private static HashMap<String, Double> getNetAllocations(HashMap<String, Double> advisedAllocations,
                                                             HashMap<String, Double> spentAllocations){
        HashMap<String, Double> netAllocations = new HashMap<>();
        for (Map.Entry<String, Double> entry : advisedAllocations.entrySet()){
            String category = entry.getKey();
            Double advisedAmount = entry.getValue();

            if (spentAllocations.containsKey(category)) {
                Double spentAmount = spentAllocations.get(category);
                Double netAmount = advisedAmount - spentAmount;
                netAllocations.put(category, netAmount);
            }

            else {
                netAllocations.put(category, advisedAmount);
            }
        }
        return netAllocations;

    }
}
