package com.fdmgroup.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	 * @param dailyTradeResultsOfCompanies: Map with Date as key and daily trade
	 *                                      results as value. The results are
	 *                                      further map into company results
	 * @param dailyIndexResults:            Map with Date as key and daily trade
	 *                                      results of the index as value.
	 */
	public void printResults(	Map<LocalDate, HashMap<String, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies,
								Map<LocalDate, HashMap<String, DailyIndexTradeResult>> dailyTradeResultsOfIndices, 
								Set<String> companies) {

		dailyTradeResultsOfCompanies.forEach((date, companieResults) -> {

			System.out.println("---------------------" + date + "---------------------");

			for (String company : companies) {
				System.out.println(company + ": ");
				if (companieResults.containsKey(company)) {
					System.out.println(companieResults.get(company));
				} else {
					System.out.println("Was not traded this day");
				}

				System.out.println("********************");
			}

			if (dailyTradeResultsOfIndices.containsKey(date)) {

				dailyTradeResultsOfIndices.get(date).forEach((IndexName, dailyIndexTradeResult) -> {

					System.out.println("Index : " + IndexName);
					System.out.println(dailyIndexTradeResult);
					System.out.println("********************");

				});

			}

		});

	}

}
