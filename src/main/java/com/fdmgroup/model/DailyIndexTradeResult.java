package com.fdmgroup.model;

import java.text.DecimalFormat;

/**
 * Data class for holding the values of daily trades index
 * 
 * @author Max Schoppe
 *
 */
public class DailyIndexTradeResult {

	private double priceOfLastTrade;
	private double priceOfFirstTrade;
	private double priceOfHeihestTrade;
	private double priceOfLowestTrade;
	private double tradeVolume;

	public DailyIndexTradeResult() {
		super();
	}

	public DailyIndexTradeResult(double priceOfLastTrade, double priceOfFirstTrade, double priceOfHeihestTrade,
			double priceOfLowestTrade, double tradeVolume) {
		super();
		this.priceOfLastTrade = priceOfLastTrade;
		this.priceOfFirstTrade = priceOfFirstTrade;
		this.priceOfHeihestTrade = priceOfHeihestTrade;
		this.priceOfLowestTrade = priceOfLowestTrade;
		this.tradeVolume = tradeVolume;
	}

	public double getPriceOfLastTrade() {
		return priceOfLastTrade;
	}

	public void setPriceOfLastTrade(double priceOfLastTrade) {
		this.priceOfLastTrade = priceOfLastTrade;
	}

	public double getPriceOfFirstTrade() {
		return priceOfFirstTrade;
	}

	public void setPriceOfFirstTrade(double priceOfFirstTrade) {
		this.priceOfFirstTrade = priceOfFirstTrade;
	}

	public double getPriceOfHeihestTrade() {
		return priceOfHeihestTrade;
	}

	public void setPriceOfHeihestTrade(double priceOfHeihestTrade) {
		this.priceOfHeihestTrade = priceOfHeihestTrade;
	}

	public double getPriceOfLowestTrade() {
		return priceOfLowestTrade;
	}

	public void setPriceOfLowestTrade(double priceOfLowestTrade) {
		this.priceOfLowestTrade = priceOfLowestTrade;
	}

	public double getTradeVolume() {
		return tradeVolume;
	}

	public void setTradeVolume(double tradeVolume) {
		this.tradeVolume = tradeVolume;
	}

	@Override
	public String toString() {
		
		final DecimalFormat df = new DecimalFormat("0.00");
		
		return "Daily Index Trade Result: \n"
				+ "Price of the first Trade: " + df.format(getPriceOfFirstTrade()) + "\n"
				+ "Price of the last Trade: "  + df.format(getPriceOfLastTrade()) + "\n"
				+ "Price of the most expensive Trade: "  + df.format(getPriceOfHeihestTrade()) + "\n"
				+ "Price of the cheapest Trade: " + df.format(getPriceOfLowestTrade()) + "\n"
				+ "Total Daily Volume: " + df.format(getTradeVolume());

	}
	
	

}
