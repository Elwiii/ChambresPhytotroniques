package test;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;

public class TestJFreeChart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create a dataset...
		DefaultXYDataset data = new DefaultXYDataset();
		data.addSeries("test1", new double[][] { { 1, 2, 1, 2, 1, 2, 1, 2, 1 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 } });
		data.addSeries("test2", new double[][] { { 9, 8, 7, 6, 5, 4, 3, 2, 1 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 } });

		// create a chart...
		JFreeChart chart = ChartFactory.createXYLineChart(null, "CO2", "Temps",
				data, PlotOrientation.HORIZONTAL, false, false, false);

		// changement des couleurs des lignes
		((XYPlot) chart.getPlot()).getRenderer().setSeriesPaint(0, Color.GREEN);
		((XYPlot) chart.getPlot()).getRenderer().setSeriesPaint(1, Color.BLACK);

		// create and display a frame...
		ChartFrame frame = new ChartFrame("blup", chart);
		frame.pack();
		frame.setVisible(true);

		data.removeSeries("test1");
		data.addSeries("test1", new double[][] { { 1, 2, 3, 2, 1, 2, 3, 2, 1 },
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 } });
	}
}
