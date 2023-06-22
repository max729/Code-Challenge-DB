package com.fdmgroup.model;

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
		return "DailyIndexTradeResult: \n [priceOfLastTrade=" + priceOfLastTrade + ", priceOfFirstTrade="
				+ priceOfFirstTrade + ", priceOfHeihestTrade=" + priceOfHeihestTrade + ", priceOfLowestTrade="
				+ priceOfLowestTrade + ", tradeVolume=" + tradeVolume + "] \n";
	}
	
	

}
