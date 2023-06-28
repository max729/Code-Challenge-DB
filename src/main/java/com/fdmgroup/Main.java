package com.fdmgroup;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;
import com.fdmgroup.model.Index;
import com.fdmgroup.model.MarketIndex;
import com.fdmgroup.model.Trade;
import com.fdmgroup.service.CSVFileReader;
import com.fdmgroup.service.ConsoleResultPrinter;
import com.fdmgroup.service.DailyCompanyResultCalculator;
import com.fdmgroup.service.DailyIndexResultCalculator;

/**
 * 
 * Main class for start point of the program. Calls all necessary service class
 * to print the daily trade results form the csv file to the console
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

		Map<LocalDate, HashMap<String, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies = dailyCompanyResultCalculator
				.calculateAllDailyCompanyResults(trades);

		Set<String> companies = dailyCompanyResultCalculator.getTradedCompanies(trades);

		DailyIndexResultCalculator dailyIndexResultCalculator = new DailyIndexResultCalculator();

		List<Index> indices = new ArrayList<>();

		indices.add(new MarketIndex("Market Index"));

		Map<LocalDate, HashMap<String, DailyIndexTradeResult>> dailyTradeResultsOfIndices = dailyIndexResultCalculator
				.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies, indices);

		ConsoleResultPrinter consoleResultPrinter = new ConsoleResultPrinter();
		consoleResultPrinter.printResults(dailyTradeResultsOfCompanies, dailyTradeResultsOfIndices, companies);

	}

}
