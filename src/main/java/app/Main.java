package app;

import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetPresenter;
import interface_adapter.budget.BudgetViewModel;
import use_case.budget.BudgetInteractor;
import use_case.budget.BudgetOutputBoundary;
import view.BudgetMakerView;

public class Main {
    public static void main(String[] args) {
        BudgetViewModel BVM = new BudgetViewModel();
        BudgetOutputBoundary BOB = new BudgetPresenter(BVM);
        BudgetInteractor budgetInteractor = new BudgetInteractor(BOB);
        BudgetController BPC = new BudgetController(budgetInteractor);
        new BudgetMakerView(BVM, BPC);
    }
}