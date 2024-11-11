package app;

import view.BudgetMakerView;
import view.categories;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        new BudgetMakerView();
        categories category = new categories(5000);
        System.out.println(category.generateBudget());
    }
}