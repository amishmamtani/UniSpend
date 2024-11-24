package use_case.budgetcompare;

import use_case.budgettracker.BudgetTrackerInputBoundary;
import use_case.budgettracker.BudgetTrackerOutputBoundary;

public class BudgetCompareInteractor implements BudgetCompareInputBoundary {
    private final BudgetCompareOutputBoundary budgetComparePresenter;

    public BudgetCompareInteractor(BudgetCompareOutputBoundary budgetComparePresenter){
        this.budgetComparePresenter = budgetComparePresenter;
    }

    @Override
    public void createBudgetCompare(BudgetCompareInputData budgetCompareInputData) {
        /**
         * TO COMPLETE THIS METHOD
         */
    }
}
