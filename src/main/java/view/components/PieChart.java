package view.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.HashMap;

public class PieChart {
    private JFreeChart chart;
    public PieChart(String title, HashMap<String, Double> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String key : data.keySet()) {
            String label = key + ": " + data.get(key);
            dataset.setValue(label, data.get(key));
        }
        chart = ChartFactory.createPieChart(title, dataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY); // Set background color of the plot

        // Change the background color of the chart
        plot.setBackgroundPaint(Color.WHITE);

    }

    public JFreeChart getChart() {
        return chart;
    }
}
