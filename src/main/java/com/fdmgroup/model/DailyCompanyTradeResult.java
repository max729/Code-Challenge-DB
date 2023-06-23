package com.fdmgroup.model;

import java.text.DecimalFormat;

/**
 * Data class with result of all daily trades of a company
 * 
 * @author Max Schoppe
 *
 */
public class DailyCompanyTradeResult {

	private Trade lastTrade;
	private Trade firstTrade;
	private Trade heighestTrade;
	private Trade lowestTrade;
	private double tradeVolume;

	public DailyCompanyTradeResult(Trade lastTrade, Trade firstTrade, Trade heighestTrade, Trade lowestTrade,double tradeVolume) {
		super();
		this.lastTrade = lastTrade;
		this.firstTrade = firstTrade;
		this.heighestTrade = heighestTrade;
		this.lowestTrade = lowestTrade;
		this.tradeVolume = tradeVolume;
	}

	public DailyCompanyTradeResult() {
		super();
	}

	public Trade getLastTrade() {
		return lastTrade;
	}

	public void setLastTrade(Trade lastTrade) {
		this.lastTrade = lastTrade;
	}

	public Trade getFirstTrade() {
		return firstTrade;
	}

	public void setFirstTrade(Trade firstTrade) {
		this.firstTrade = firstTrade;
	}

	public Trade getHeighestTrade() {
		return heighestTrade;
	}

	public void setHeighestTrade(Trade heighestTrade) {
		this.heighestTrade = heighestTrade;
	}

	public Trade getLowestTrade() {
		return lowestTrade;
	}

	public void setLowestTrade(Trade lowestTrade) {
		this.lowestTrade = lowestTrade;
	}
	
	public double getTradeVolume() {
		return tradeVolume;
	}

	public void setTradeVolume(double tradeVolume) {
		this.tradeVolume = tradeVolume;
	}

	public void incrementTradeVolume(double amount) {
		tradeVolume += amount;
	}

	@Override
	public String toString() {
		
		final DecimalFormat df = new DecimalFormat("0.00");
		
		return "Daily Company Trade Result: \n"
				+ "Price of the first Trade: " + df.format(firstTrade.getPrice()) + "\n"
				+ "Price of the last Trade: "  + df.format(lastTrade.getPrice()) + "\n" 
				+ "Price of the most expensive Trade: "  + df.format(heighestTrade.getPrice()) + "\n" 
				+ "Price of the cheapest Trade: "  + df.format(lowestTrade.getPrice()) + "\n" 
				+ "Total Daily Volume: " + df.format(tradeVolume) ;
	}
	
	

}
