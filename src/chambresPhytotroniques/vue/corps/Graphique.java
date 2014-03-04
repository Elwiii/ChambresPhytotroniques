package chambresPhytotroniques.vue.corps;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import chambresPhytotroniques.modele.Valeur;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import org.jfree.chart.axis.NumberAxis;

public class Graphique extends JPanel {

	private static final long serialVersionUID = 6944417158982129865L;

	private static final String TITLE = null;

	private static final String XAXIS = null;

	private static final String YAXIS = null;

	public static final String CO2_LABEL = "CO2";

	public static final String O3_LABEL = "O3";

	public static final String TEMPERATURE_LABEL = "Temperature";

	public static final String HUMIDITE_LABEL = "Humidite";

	private JFreeChart jFreeChart;

	private ChartPanel chartPanel;

	private DefaultXYDataset dataset;
        
        private int sonde;

	public Graphique(int sonde) {
		super();

                this.sonde = sonde;
                
		this.dataset = new DefaultXYDataset();
		this.jFreeChart = ChartFactory.createXYLineChart(TITLE, XAXIS, YAXIS,
				this.dataset, PlotOrientation.HORIZONTAL, false, false, false);
                
                DecimalFormat format = new DecimalFormat();
                format.setMaximumFractionDigits(3);
                format.setMinimumFractionDigits(3);
                
                                          
                NumberAxis axis = (NumberAxis) jFreeChart.getXYPlot().getDomainAxis();
                axis.setLowerBound(0);
                axis.setNumberFormatOverride(format);
                             
		this.chartPanel = new ChartPanel(jFreeChart);
                
                this.setBackground(Color.white);
                
                if(sonde%2 == 0) {
                   jFreeChart.getXYPlot().setBackgroundPaint(Color.GRAY);
                }

		//this.setLayout(new BorderLayout());
		//this.add(this.chartPanel, BorderLayout.CENTER);    
                
                this.chartPanel.setPreferredSize(new Dimension(350,64));
                this.chartPanel.setLocation(0,0);
                this.add(this.chartPanel);

	}

	public void setListCo2(LinkedList<Valeur> listCo2) {
		setList(listCo2, CO2_LABEL, TempsReel.CO2_COLOR);
	}

	public void setListO3(LinkedList<Valeur> listO3) {
		setList(listO3, O3_LABEL, TempsReel.O3_COLOR);
	}

	public void setListTemperature(LinkedList<Valeur> listTemperature) {
		// setList(listTemperature, TEMPERATURE_LABEL,
		// TempsReel.TEMPERATURE_COLOR);
	}

	public void setListHumidite(LinkedList<Valeur> listHumidite) {
		// setList(listHumidite, HUMIDITE_LABEL, TempsReel.HUMIDITE_COLOR);
	}

	private void setList(LinkedList<Valeur> list, String label, Color color) {
		double[][] data = new double[2][(list.size() - 50 > 0) ? 50 : list
				.size()];

		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		for (int i = (list.size() - 50 > 0) ? list.size() : 0; i < list.size(); i++) {
			Valeur val = list.get(i);
			// data[0] = tableau des Y
			data[0][i] = val.getValeur();
			// data[1] = tableau des X
			data[1][i] = val.getDate().getTime();

			if (min > val.getDate().getTime()) {
				min = val.getDate().getTime();
			}
			if (max < val.getDate().getTime()) {
				max = val.getDate().getTime();
			}
		}

		this.dataset.removeSeries(label);
		this.dataset.addSeries(label, data);

		jFreeChart
				.getXYPlot()
				.getRenderer()
				.setSeriesPaint(
						jFreeChart.getXYPlot().getDataset().indexOf(label),
						color);

		jFreeChart.getXYPlot().getRangeAxis().setRange(min, max);

	}
        
        @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
                if(sonde == 3 || sonde == 7) {
                    g.setColor(Color.RED);
                    g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
                    g.setColor(Color.BLACK);
                    g.drawLine(0, 0, this.getWidth() - 1, 0);
                } else if (sonde == 4 || sonde == 8) {
                    g.setColor(Color.BLACK);
                    g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
                    g.setColor(Color.RED);
                    g.drawLine(0, 0, this.getWidth() - 1, 0);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1);
                    g.drawLine(0, 0, this.getWidth() - 1, 0);
                }
           }

    /**
     * @return the chartPanel
     */
    public ChartPanel getChartPanel() {
        return chartPanel;
    }

}
