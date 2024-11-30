package view.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.HashMap;
import java.awt.*;

public class BarChart {
    private JFreeChart barChart;

    public BarChart(String title, HashMap<String, Double> advisedAllocations,
                    HashMap<String, Double> spentAllocations) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        advisedAllocations.keySet().forEach(key -> dataset.addValue(
                advisedAllocations.get(key), "Advised Spending", key));
        spentAllocations.keySet().forEach(key -> dataset.addValue(
                spentAllocations.get(key), "Actual Spending", key));

        barChart = ChartFactory.createBarChart(title, "Category", "Spending",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);
    }

    public JFreeChart getBarChart() {return barChart;}
}
