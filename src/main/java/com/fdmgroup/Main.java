package com.fdmgroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;
import com.fdmgroup.model.Company;
import com.fdmgroup.model.Trade;
import com.fdmgroup.service.DailyCompanyResultCalculator;
import com.fdmgroup.service.DailyIndexCalculator;
import com.fdmgroup.service.FileIO;

public class Main {

	public static void main(String[] args) {


		
		String filePath = "./src/main/resources/test-market.csv";
		
		List<Trade> trades = FileIO.readFromFile(filePath);
		
		
		TreeMap< LocalDate , EnumMap< Company, DailyCompanyTradeResult >> dailyTradeResultsOfCompanies
				= DailyCompanyResultCalculator.calculateAllDailyCompanyResults(trades);
		
		
		HashMap<LocalDate, DailyIndexTradeResult> dailyIndexResults = DailyIndexCalculator.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies);
		
		
		
		
		
		//dailyIndexResults.forEach((date,index) -> {System.out.println( date );
		//	System.out.println(index);
		//});

	}

}
