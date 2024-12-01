package view.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.HashMap;

/**
 * A class for creating and managing a pie chart based on a dataset.
 */
public class PieChart {

    /** The JFreeChart instance representing the pie chart */
    private JFreeChart chart;

    /**
     * Constructs a PieChart instance with the specified title and data.
     *
     * @param title The title of the pie chart.
     * @param data  A map of categories and their corresponding values.
     */
    public PieChart(String title, HashMap<String, Double> data) {
        // Create the dataset for the pie chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String key : data.keySet()) {
            String label = key + ": " + data.get(key); // Create a label with category and value
            dataset.setValue(label, data.get(key)); // Add category and value to the dataset
        }

        // Create the pie chart using the dataset
        chart = ChartFactory.createPieChart(title, dataset, true, true, false);

        // Configure the plot of the pie chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY); // Set background color of the plot
        plot.setBackgroundPaint(Color.WHITE); // Set chart background color
    }

    /**
     * Retrieves the JFreeChart instance representing the pie chart.
     *
     * @return The pie chart instance.
     */
    public JFreeChart getChart() {
        return chart;
    }
}
