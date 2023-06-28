package com.fdmgroup.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;

/**
 * Service for printing the daily trade Volume to a chart
 * @author xam
 *
 */
public class ResultToChartService extends JFrame {

	private static final long serialVersionUID = -2746358342084953121L;
	XYSplineRenderer xySplineRenderer = new XYSplineRenderer();

	public ResultToChartService(Map<LocalDate, HashMap<String, DailyIndexTradeResult>> dailyIndexResults,
								Map<LocalDate, HashMap<String, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies,
								Set<String> companies) {

		initUI(dailyIndexResults, dailyTradeResultsOfCompanies, companies);

	}

	private void initUI(Map<LocalDate, HashMap<String, DailyIndexTradeResult>> dailyIndexResults,
						Map<LocalDate, HashMap<String, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies,
						Set<String> companies) {

		XYDataset dataset = createDataset(dailyIndexResults, dailyTradeResultsOfCompanies, companies);
		JFreeChart chart = createChart(dataset);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		add(chartPanel);

		pack();
		setTitle("Daily Trade Volume");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private XYDataset createDataset(Map<LocalDate, HashMap<String, DailyIndexTradeResult>> dailyIndexResults,
									Map<LocalDate, HashMap<String, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies,
									Set<String> companies) {

		var dataset = new TimeSeriesCollection();

		for (String company : companies) {

			TimeSeries compSeries = new TimeSeries(company);

			dailyTradeResultsOfCompanies.forEach((date, compResult) -> {

				if (compResult.containsKey(company)) {
					compSeries.add(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()),
							compResult.get(company).getTradeVolume());
				}

			});

			dataset.addSeries(compSeries);

		}

		TimeSeries series = new TimeSeries("Market Index");

		dailyIndexResults.forEach((date, dailyIndexResult) -> {

			series.add(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()),
					dailyIndexResult.get("Market Index").getTradeVolume());

		});

		dataset.addSeries(series);

		return dataset;
	}

	private JFreeChart createChart(XYDataset dataset) {

		JFreeChart chart = ChartFactory.createTimeSeriesChart("Trade Volume per day", "date", "Trade Volume (€)",
				dataset, true, true, false);

		XYPlot plot = chart.getXYPlot();

		var renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		renderer.setSeriesPaint(1, Color.BLUE);
		renderer.setSeriesStroke(1, new BasicStroke(2.0f));
		renderer.setSeriesPaint(2, Color.GREEN);
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		renderer.setSeriesPaint(3, Color.ORANGE);
		renderer.setSeriesStroke(3, new BasicStroke(2.0f));
		renderer.setSeriesPaint(4, Color.BLACK);
		renderer.setSeriesStroke(4, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		NumberAxis axis = (NumberAxis) plot.getRangeAxis();
		axis.setNumberFormatOverride(new DecimalFormat("0"));

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Trade Volume per day", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;
	}

}
