package app;

import interface_adapter.budget.BudgetController;
import interface_adapter.budget.BudgetPresenter;
import interface_adapter.budget.BudgetViewModel;
import interface_adapter.budgettracker.BudgetTrackerController;
import interface_adapter.budgettracker.BudgetTrackerPresenter;
import interface_adapter.budgettracker.BudgetTrackerViewModel;
import use_case.budget.BudgetInteractor;
import use_case.budget.BudgetOutputBoundary;
import use_case.budgettracker.BudgetTrackerInteractor;
import use_case.budgettracker.BudgetTrackerOutputBoundary;
import view.BudgetMakerView;
import view.BudgetTrackerView;

public class Main {
    public static void main(String[] args) {
        BudgetViewModel BVM = new BudgetViewModel();
        BudgetOutputBoundary BOB = new BudgetPresenter(BVM);
        BudgetInteractor budgetInteractor = new BudgetInteractor(BOB);
        BudgetController BPC = new BudgetController(budgetInteractor);
        new BudgetMakerView(BVM, BPC);

        BudgetTrackerViewModel BVM2 = new BudgetTrackerViewModel();
        BudgetTrackerOutputBoundary BTOB = new BudgetTrackerPresenter(BVM2);
        BudgetTrackerInteractor budgetTrackerInteractor = new BudgetTrackerInteractor(BTOB);
        BudgetTrackerController BTC = new BudgetTrackerController(budgetTrackerInteractor);
        new BudgetTrackerView(BVM2, BTC);
    }
}