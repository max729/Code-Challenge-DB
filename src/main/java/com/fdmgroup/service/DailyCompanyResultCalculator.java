package com.fdmgroup.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.Trade;

/**
 * Service class for calculating result of all daily trades of companies
 * 
 * @author Max Schoppe
 *
 */
public class DailyCompanyResultCalculator {

	public TreeMap<LocalDate, EnumMap<Company, DailyCompanyTradeResult>> calculateAllDailyCompanyResults(
			List<Trade> trades) {

		HashMap<LocalDate, EnumMap<Company, List<Trade>>> tradesPerDayAndCompany = splitTradesIntoDateAndCompanyMap(
				trades);

		TreeMap<LocalDate, EnumMap<Company, DailyCompanyTradeResult>> dailyTradeResultsOfCompanies = new TreeMap<>();

		tradesPerDayAndCompany.forEach((date, dailyTradesOfCompanies) -> {

			dailyTradeResultsOfCompanies.put(date, new EnumMap<>(Company.class));

			EnumMap<Company, DailyCompanyTradeResult> dailyCompaniesResult = dailyTradeResultsOfCompanies.get(date);

			dailyTradesOfCompanies.forEach((company, companyTrades) -> {

				dailyCompaniesResult.put(company, CalculateDailyCompanyResult(companyTrades));
			});

		});

		return dailyTradeResultsOfCompanies;

	}

	private HashMap<LocalDate, EnumMap<Company, List<Trade>>> splitTradesIntoDateAndCompanyMap(
			List<Trade> trades) {

		HashMap<LocalDate, EnumMap<Company, List<Trade>>> tradesPerDayAndCompany = new HashMap<>();

		for (Trade trade : trades) {
			tradesPerDayAndCompany.computeIfAbsent(trade.getDate().toLocalDate(), k -> new EnumMap<>(Company.class))
					.computeIfAbsent(trade.getCompany(), k -> new ArrayList<>()).add(trade);

		}

		return tradesPerDayAndCompany;
	}

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

			dailyCompanyTradeResult.incrementTradeVolume(trade.getPrice() * trade.getTrades());

		}

		return dailyCompanyTradeResult;
	}

}
