package com.fdmgroup.service;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.TreeMap;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;

/**
 * Service class for printing out the daily trade results to the Console
 * 
 * @author Max Schoppe
 *
 */

public class ConsoleResultPrinter {

	/**
	 * Prints all daily trade result of all company and the index to the console
	 * 
	 * @param dailyTradeResultsOfCompanies: Map with Date as key and daily trade results as value. The results are further map into company results
	 * @param dailyIndexResults: Map with Date as key and daily trade results of the index as value.
	 */
	public static void printResults(TreeMap< LocalDate , EnumMap< Company, DailyCompanyTradeResult >> dailyTradeResultsOfCompanies,
										HashMap<LocalDate, DailyIndexTradeResult> dailyIndexResults) {
		
		dailyTradeResultsOfCompanies.forEach( (date, companieResults )->{
			
			System.out.println( "---------------------" + date + "---------------------"  );
			
			for (Company company : Company.values()  ) {
				System.out.println( company + ": " );
				if(companieResults.containsKey(company)) {
					System.out.println( companieResults.get(company) );
				} else {
					System.out.println("Was not traded this day");
				}
				
				System.out.println("********************");
			}
			
			
			if(	dailyIndexResults.containsKey(date)) {
				
				DailyIndexTradeResult dailyIndexTradeResult = dailyIndexResults.get(date);
				
				System.out.println("Index : " );
				System.out.println(dailyIndexTradeResult);
				System.out.println("********************");
			}
			
			
		});
		
	}
	
	
}
