package com.fdmgroup.model;

public class DailyCompanyTradeResult {

	private Trade lastTrade;
	private Trade firstTrade;
	private Trade heighestTrade;
	private Trade lowestTrade;
	private int numberOfTrades;
	private double sumOfTradePrices;

	public DailyCompanyTradeResult(Trade lastTrade, Trade firstTrade, Trade heighestTrade, Trade lowestTrade,
			int numberOfTrades, double sumOfTradePrices) {
		super();
		this.lastTrade = lastTrade;
		this.firstTrade = firstTrade;
		this.heighestTrade = heighestTrade;
		this.lowestTrade = lowestTrade;
		this.numberOfTrades = numberOfTrades;
		this.sumOfTradePrices = sumOfTradePrices;
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

	public int getNumberOfTrades() {
		return numberOfTrades;
	}

	public void setNumberOfTrades(int numberOfTrades) {
		this.numberOfTrades = numberOfTrades;
	}

	public double getSumOfTradePrices() {
		return sumOfTradePrices;
	}

	public void setSumOfTradePrices(double sumOfTradePrices) {
		this.sumOfTradePrices = sumOfTradePrices;
	}

	public void incrementNumberOfTradesByOne() {
		numberOfTrades++;
	}

	public void incrementSumOfTradePrices(double amount) {
		sumOfTradePrices += amount;
	}

}
