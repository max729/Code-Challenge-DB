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
	private String company;
	
	@CsvBindByPosition(position = 2, locale = "de-DE")
	private double price;
	
	@CsvBindByPosition(position = 3)
	private int numberTraded;
	
	public Trade() {
		super();
	}

	public Trade(LocalDateTime date, String company, double price, int numberTraded) {
		super();
		this.date = date;
		this.company = company;
		this.price = price;
		this.numberTraded = numberTraded;
	}
	
	

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberTraded() {
		return numberTraded;
	}

	public void setNumberTraded(int numberTraded) {
		this.numberTraded = numberTraded;
	}

	@Override
	public String toString() {
		return "Trade [date=" + date + ", company=" + company + ", price=" + price + ", numberTraded=" + numberTraded + "]";
	}
	

}
