package com.fdmgroup.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;
import com.fdmgroup.model.Index;

/**
 * Service class for calculating all daily trade indexes
 * 
 * @author Max Schoppe
 *
 */
public class DailyIndexResultCalculator {

	
	/**
	 * Calculates the daily Trade {@link com.fdmgroup.model.DailyCompanyTradeResult
	 * index} for every days and every different index
	 * 
	 * @param Map ( date , Map Caompany , Daily Company Trade Result)
	 * @return Map ( date , List different daily indices Results)
	 */
	public Map<LocalDate, HashMap<String, DailyIndexTradeResult>> calculateAllDailyIndexResults(
				Map<LocalDate, HashMap<String, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies,
				List<Index> indices) {

		HashMap<LocalDate, HashMap<String, DailyIndexTradeResult>> dailyIndexResults = new HashMap<>();

		HashMap<String, DailyCompanyTradeResult> lastCompanyResults = new HashMap<>();

		dailyTradeResultsOfCompanies.forEach((date, dailyCompanyTradeResults) -> {

			for (Index index : indices) {

				DailyIndexTradeResult dailyIndexTradeResult = new DailyIndexTradeResult();

				for (String company : index.getIndexCompanies()) {

					DailyCompanyTradeResult dailyCompanyTradeResult;

					if (dailyCompanyTradeResults.containsKey(company)) {

						dailyCompanyTradeResult = dailyCompanyTradeResults.get(company);

						lastCompanyResults.put(company, dailyCompanyTradeResult);

					} else if (lastCompanyResults.containsKey(company)) {
						dailyCompanyTradeResult = lastCompanyResults.get(company);

					} else
						return;

					updateDailyIndexTradeResult(company, dailyCompanyTradeResult, dailyIndexTradeResult, index);

				}

				dailyIndexResults.computeIfAbsent(date, k -> new HashMap<>()).put(index.getName(),
						dailyIndexTradeResult);
			}

		});

		return dailyIndexResults;

	}

	/**
	 * calculates a fraction of the Index for a company and add it to the Index result data
	 * function has side effects
	 * @param company name
	 * @param dailyCompanyTradeResult
	 * @param dailyIndexTradeResult 
	 * @param index that should be calculated
	 */
	private void updateDailyIndexTradeResult(	String companyName, 
												DailyCompanyTradeResult dailyCompanyTradeResult,
												DailyIndexTradeResult dailyIndexTradeResult, 
												Index index) {

		double companyWeight = index.getIndexWeightOfCompany(companyName);

		dailyIndexTradeResult.setPriceOfFirstTrade(dailyIndexTradeResult.getPriceOfFirstTrade()
				+ companyWeight * dailyCompanyTradeResult.getFirstTrade().getPrice());
		dailyIndexTradeResult.setPriceOfHeihestTrade(dailyIndexTradeResult.getPriceOfHeihestTrade()
				+ companyWeight * dailyCompanyTradeResult.getHeighestTrade().getPrice());
		dailyIndexTradeResult.setPriceOfLastTrade(dailyIndexTradeResult.getPriceOfLastTrade()
				+ companyWeight * dailyCompanyTradeResult.getLastTrade().getPrice());
		dailyIndexTradeResult.setPriceOfLowestTrade(dailyIndexTradeResult.getPriceOfLowestTrade()
				+ companyWeight * dailyCompanyTradeResult.getLowestTrade().getPrice());
		dailyIndexTradeResult.setTradeVolume(dailyIndexTradeResult.getTradeVolume() 
				+ companyWeight * dailyCompanyTradeResult.getTradeVolume());

	}

}
