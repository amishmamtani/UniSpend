package interface_adapter.budgetcompare;

import entity.User;
import use_case.budgetcompare.BudgetCompareInputData;
import use_case.budgetcompare.BudgetCompareInteractor;

import java.util.HashMap;

public class BudgetCompareController {
    private final BudgetCompareInteractor budgetCompareInteractor;

    public BudgetCompareController(BudgetCompareInteractor budgetCompareInteractor){
        this.budgetCompareInteractor = budgetCompareInteractor;
    }

    /**
     * Old createBudgetCompare when I DON'T take in User
     */
//    public void createBudgetCompare(HashMap<String, Double> advisedAllocations,
//                                    HashMap<String, Double> spentAllocations){
//        BudgetCompareInputData compareInputData = new BudgetCompareInputData(advisedAllocations, spentAllocations);
//        budgetCompareInteractor.createBudgetCompare(compareInputData);
//    }

    public void createBudgetCompare(User user){
        BudgetCompareInputData compareInputData = new BudgetCompareInputData(user);
        budgetCompareInteractor.createBudgetCompare(compareInputData);
    }
}
