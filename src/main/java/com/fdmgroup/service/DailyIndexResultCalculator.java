package com.fdmgroup.service;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.TreeMap;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;

/**
 * Service class for calculating all daily trade indexes
 * 
 * @author Max Schoppe
 *
 */
public class DailyIndexResultCalculator {

	public HashMap<LocalDate, DailyIndexTradeResult> calculateAllDailyIndexResults(
			TreeMap<LocalDate, EnumMap<Company, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies) {

		HashMap<LocalDate, DailyIndexTradeResult> dailyIndexResults = new HashMap<>();

		EnumMap<Company, DailyCompanyTradeResult> lastCompanyResults = new EnumMap<>(Company.class);

		dailyTradeResultsOfCompanies.forEach((date, dailyCompanyTradeResults) -> {

			DailyIndexTradeResult dailyIndexTradeResult = new DailyIndexTradeResult();

			for (Company company : Company.values()) {
				DailyCompanyTradeResult dailyCompanyTradeResult;
				if (dailyCompanyTradeResults.containsKey(company)) {

					dailyCompanyTradeResult = dailyCompanyTradeResults.get(company);

					lastCompanyResults.put(company, dailyCompanyTradeResult);

				} else if (lastCompanyResults.containsKey(company)) {
					dailyCompanyTradeResult = lastCompanyResults.get(company);

				} else
					return;

				calculateIndexFraction(company, dailyCompanyTradeResult, dailyIndexTradeResult);

			}

			dailyIndexResults.put(date, dailyIndexTradeResult);

		});

		return dailyIndexResults;

	}

	private void calculateIndexFraction(Company company, DailyCompanyTradeResult dailyCompanyTradeResult,
			DailyIndexTradeResult dailyIndexTradeResult) {

		dailyIndexTradeResult.setPriceOfFirstTrade(dailyIndexTradeResult.getPriceOfFirstTrade()
				+ Company.getIndexWeight(company) * dailyCompanyTradeResult.getFirstTrade().getPrice());
		dailyIndexTradeResult.setPriceOfHeihestTrade(dailyIndexTradeResult.getPriceOfHeihestTrade()
				+ Company.getIndexWeight(company) * dailyCompanyTradeResult.getHeighestTrade().getPrice());
		dailyIndexTradeResult.setPriceOfLastTrade(dailyIndexTradeResult.getPriceOfLastTrade()
				+ Company.getIndexWeight(company) * dailyCompanyTradeResult.getLastTrade().getPrice());
		dailyIndexTradeResult.setPriceOfLowestTrade(dailyIndexTradeResult.getPriceOfLowestTrade()
				+ Company.getIndexWeight(company) * dailyCompanyTradeResult.getLowestTrade().getPrice());
		dailyIndexTradeResult.setTradeVolume(dailyIndexTradeResult.getTradeVolume()
				+ Company.getIndexWeight(company) * dailyCompanyTradeResult.getTradeVolume());

	}

}
