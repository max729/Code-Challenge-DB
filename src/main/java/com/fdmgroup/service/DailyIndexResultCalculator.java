package com.fdmgroup.service;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fdmgroup.model.Company;
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

	public HashMap<LocalDate, HashMap<String, DailyIndexTradeResult>> calculateAllDailyIndexResults(
			TreeMap<LocalDate, EnumMap<Company, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies,
			List<Index> indices) {

		HashMap<LocalDate, HashMap<String, DailyIndexTradeResult>> dailyIndexResults = new HashMap<>();

		EnumMap<Company, DailyCompanyTradeResult> lastCompanyResults = new EnumMap<>(Company.class);

		dailyTradeResultsOfCompanies.forEach((date, dailyCompanyTradeResults) -> {

			for (Index index : indices) {

				DailyIndexTradeResult dailyIndexTradeResult = new DailyIndexTradeResult();

				for (Company company : index.getIndexCompanies()) {

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

	private void updateDailyIndexTradeResult(Company company, DailyCompanyTradeResult dailyCompanyTradeResult,
			DailyIndexTradeResult dailyIndexTradeResult, Index index) {

		double companyWeight = index.getIndexWeightOfCompany(company);

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
