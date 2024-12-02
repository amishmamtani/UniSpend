package view.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.HashMap;

/**
 * A class for creating and managing a bar chart that compares advised and actual spending.
 */
public class BarChart {

    /** The JFreeChart instance representing the bar chart */
    private JFreeChart barChart;

    /**
     * Constructs a BarChart instance with the specified title, advised allocations, and spent allocations.
     *
     * @param title             The title of the bar chart.
     * @param advisedAllocations A map of categories to advised spending allocations.
     * @param spentAllocations   A map of categories to actual spending allocations.
     */
    public BarChart(String title,
                    HashMap<String, Double> advisedAllocations,
                    HashMap<String, Double> spentAllocations,
                    HashMap<String,Double> netAllocations) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add advised spending data to the dataset
        advisedAllocations.keySet().forEach(key -> dataset.addValue(
                advisedAllocations.get(key), "Advised Spending", key));

        // Add actual spending data to the dataset
        spentAllocations.keySet().forEach(key -> dataset.addValue(
                spentAllocations.get(key), "Actual Spending", key));

        // Add difference between advised and actual spending to the dataset
        netAllocations.keySet().forEach(key -> dataset.addValue(netAllocations.get(key), "Net Spending", key));

        // Create the bar chart
        barChart = ChartFactory.createBarChart(
                title,                 // Chart title
                "Category",            // Domain axis label
                "Spending",            // Range axis label
                dataset,               // Dataset
                PlotOrientation.VERTICAL, // Chart orientation
                true,                  // Include legend
                true,                  // Tooltips
                false                  // URLs
        );
    }

    /**
     * Retrieves the JFreeChart instance representing the bar chart.
     *
     * @return The bar chart instance.
     */
    public JFreeChart getBarChart() {
        return barChart;
    }
}
