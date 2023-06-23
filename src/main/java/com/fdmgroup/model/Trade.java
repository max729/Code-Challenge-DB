package com.fdmgroup.model;

import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

/**
 * Data class that saves all given informations of Trades from the csv file. 
 * Uses opencsv module and opencsv annotations for converting from csv line to java object
 * 
 * @author Max Schoppe
 *
 */
public class Trade {
	
	@CsvBindByPosition(position = 0)
	@CsvDate("yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
	
	@CsvBindByPosition(position = 1)
	private Company company;
	
	@CsvBindByPosition(position = 2, locale = "de-DE")
	private double price;
	
	@CsvBindByPosition(position = 3)
	private int trades;
	
	public Trade() {
		super();
	}

	public Trade(LocalDateTime date, Company company, double price, int trades) {
		super();
		this.date = date;
		this.company = company;
		this.price = price;
		this.trades = trades;
	}
	
	

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTrades() {
		return trades;
	}

	public void setTrades(int trades) {
		this.trades = trades;
	}

	@Override
	public String toString() {
		return "Trade [date=" + date + ", company=" + company + ", price=" + price + ", trades=" + trades + "]";
	}
	

}
