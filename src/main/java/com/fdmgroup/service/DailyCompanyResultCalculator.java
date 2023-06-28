package com.fdmgroup.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.Trade;

/**
 * Service class for calculating result of all daily trades of companies
 * 
 * @author Max Schoppe
 *
 */
public class DailyCompanyResultCalculator {

	/**
	 * Calculates the daily Trade {@link com.fdmgroup.model.DailyCompanyTradeResult
	 * Result} for every days and every Company contained in the input
	 * 
	 * @param trades list of all {@link com.fdmgroup.model.Trade Trades}
	 * @return Map ( date , Map Caompany , Daily Company Trade Result)
	 */
	public Map<LocalDate, HashMap<String, DailyCompanyTradeResult>> calculateAllDailyCompanyResults(
			List<Trade> trades) {

		Map<LocalDate, HashMap<String, List<Trade>>> tradesPerDayAndCompany = splitTradesIntoDateAndCompanyMap(trades);

		TreeMap<LocalDate, HashMap<String, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies = new TreeMap<>();

		tradesPerDayAndCompany.forEach((date, dailyTradesOfCompanies) -> {

			dailyTradeResultsOfCompanies.put(date, new HashMap<>());

			HashMap<String, DailyCompanyTradeResult> dailyCompaniesResult = dailyTradeResultsOfCompanies.get(date);

			dailyTradesOfCompanies.forEach((company, companyTrades) -> {

				dailyCompaniesResult.put(company, CalculateDailyCompanyResult(companyTrades));
			});

		});

		return dailyTradeResultsOfCompanies;

	}
	
	/**
	 * loops throw list off all trades and returns the set of all traded companies
	 * 
	 * @param trades list of all trades
	 * @return set of all traded companies
	 */
	public Set<String> getTradedCompanies(List<Trade> trades) {
		Set<String> companies = new HashSet<>();
		
		for(Trade trade: trades) {
			companies.add(trade.getCompany());
		}
		return new TreeSet<>(companies);
	}

	/**
	 * Splits the list of Trades into two nested Maps with the date as first key and
	 * the Company as second key.
	 * 
	 * @param trades: List of {@link com.fdmgroup.model.Trade Trades}
	 * @return Map with ( key of date , value: Map ( key of company , value: Trade
	 *         ))
	 */
	private Map<LocalDate, HashMap<String, List<Trade>>> splitTradesIntoDateAndCompanyMap(List<Trade> trades) {

		HashMap<LocalDate, HashMap<String, List<Trade>>> tradesPerDayAndCompany = new HashMap<>();

		for (Trade trade : trades) {
			tradesPerDayAndCompany.computeIfAbsent(trade.getDate().toLocalDate(), k -> new HashMap<>())
					.computeIfAbsent(trade.getCompany(), k -> new ArrayList<>()).add(trade);

		}

		return tradesPerDayAndCompany;
	}

	/**
	 * Loops throw the daily company trades and calculates
	 * {@link com.fdmgroup.model.DailyCompanyTradeResult company results}
	 * 
	 * @param trades for one Company on one day
	 * @return daily company trade result
	 */
	private DailyCompanyTradeResult CalculateDailyCompanyResult(List<Trade> trades) {

		DailyCompanyTradeResult dailyCompanyTradeResult = new DailyCompanyTradeResult();

		dailyCompanyTradeResult.setFirstTrade(trades.get(0));
		dailyCompanyTradeResult.setHeighestTrade(trades.get(0));
		dailyCompanyTradeResult.setLastTrade(trades.get(0));
		dailyCompanyTradeResult.setLowestTrade(trades.get(0));

		for (Trade trade : trades) {

			if (dailyCompanyTradeResult.getFirstTrade().getDate().isAfter(trade.getDate())) {
				dailyCompanyTradeResult.setFirstTrade(trade);
			}

			if (dailyCompanyTradeResult.getLastTrade().getDate().isBefore(trade.getDate())) {
				dailyCompanyTradeResult.setLastTrade(trade);
			}

			if (dailyCompanyTradeResult.getHeighestTrade().getPrice() < trade.getPrice()) {
				dailyCompanyTradeResult.setHeighestTrade(trade);
			}

			if (dailyCompanyTradeResult.getLowestTrade().getPrice() > trade.getPrice()) {
				dailyCompanyTradeResult.setLowestTrade(trade);
			}

			dailyCompanyTradeResult.incrementTradeVolume(trade.getPrice() * trade.getNumberTraded());

		}

		return dailyCompanyTradeResult;
	}

}
