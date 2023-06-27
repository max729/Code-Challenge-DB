package com.fdmgroup;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;
import com.fdmgroup.model.Index;
import com.fdmgroup.model.MarketIndex;
import com.fdmgroup.model.Trade;
import com.fdmgroup.service.ConsoleResultPrinter;
import com.fdmgroup.service.DailyCompanyResultCalculator;
import com.fdmgroup.service.DailyIndexResultCalculator;
import com.fdmgroup.service.ResultToChartService;
import com.fdmgroup.service.CSVFileReader;

/**
 * 
 * Main class for start point of the program.
 * Calls all necessary service class to print the daily trade results form the csv file to the console
 * 
 * @author Max Schoppe
 *
 */
public class Main {

	public static void main(String[] args) {

		String filePath = "./src/main/resources/test-market.csv";
		
		CSVFileReader csvFileReader = new CSVFileReader();
		
		List<Trade> trades;
		
		try {
			trades = csvFileReader.readFromFile(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		DailyCompanyResultCalculator dailyCompanyResultCalculator = new DailyCompanyResultCalculator();

		TreeMap<LocalDate, EnumMap<Company, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies = dailyCompanyResultCalculator
				.calculateAllDailyCompanyResults(trades);

		
		DailyIndexResultCalculator dailyIndexResultCalculator = new DailyIndexResultCalculator();
		
		List<Index> indices = new ArrayList<>();
		
		indices.add(new MarketIndex("Market Index"));
		
		
		
		HashMap<LocalDate, HashMap<String, DailyIndexTradeResult> > dailyTradeResultsOfIndices = dailyIndexResultCalculator
				.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies, indices);
		

		
		ConsoleResultPrinter consoleResultPrinter = new ConsoleResultPrinter();
		consoleResultPrinter.printResults(dailyTradeResultsOfCompanies, dailyTradeResultsOfIndices);
		
        

	}

}
