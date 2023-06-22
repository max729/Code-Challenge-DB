package com.fdmgroup.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.TreeMap;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;

public class ConsoleResultPrinter {

	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public static void printResults(TreeMap< LocalDate , EnumMap< Company, DailyCompanyTradeResult >> dailyTradeResultsOfCompanies,
										HashMap<LocalDate, DailyIndexTradeResult> dailyIndexResults) {
		
		dailyTradeResultsOfCompanies.forEach( (date, companieResults )->{
			
			System.out.println( "---------------------" + date + "---------------------"  );
			
			companieResults.forEach( (company,result)->{
				System.out.println( company + ": " );
				System.out.println( "Price of the first Trade: " + df.format(result.getFirstTrade().getPrice())   );
				System.out.println( "Price of the last Trade: " + df.format(result.getLastTrade().getPrice())   );
				System.out.println( "Price of the most expensive Trade: " + df.format(result.getHeighestTrade().getPrice())   );
				System.out.println( "Price of the cheapest Trade: " + df.format(result.getLowestTrade().getPrice())   );
				System.out.println( "Total Daily Volume: " + df.format(result.getTradeVolume())   );
			});
			
			
			if(	dailyIndexResults.containsKey(date)) {
				
				DailyIndexTradeResult dailyIndexTradeResult = dailyIndexResults.get(date);
				
				System.out.println("********************");
				System.out.println("Index : " );
				System.out.println( "Price of the first Trade: " + df.format(dailyIndexTradeResult.getPriceOfFirstTrade())   );
				System.out.println( "Price of the last Trade: " + df.format(dailyIndexTradeResult.getPriceOfLastTrade())   );
				System.out.println( "Price of the most expensive Trade: " + df.format(dailyIndexTradeResult.getPriceOfHeihestTrade())   );
				System.out.println( "Price of the cheapest Trade: " + df.format(dailyIndexTradeResult.getPriceOfLowestTrade())  );
				System.out.println( "Total Daily Volume:: " + df.format(dailyIndexTradeResult.getTradeVolume())  );
				
			}
			
			
		});
		
	}
	
	
}
