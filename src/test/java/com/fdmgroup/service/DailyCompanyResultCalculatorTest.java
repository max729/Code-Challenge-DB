package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.Trade;

public class DailyCompanyResultCalculatorTest {

	DailyCompanyResultCalculator dailyCompanyResultCalculator;
	List<Trade> trades;
	LocalDate date;

	@BeforeEach
	void setUp() {

		dailyCompanyResultCalculator = new DailyCompanyResultCalculator();

		date = LocalDate.of(2023, 6, 23);

		trades = new ArrayList<>();
		
	}

	@Test
	void test_MethodeCalculateAllDailyCompanyResults_withOneCompanyAsInput() {
		
		trades.add(new Trade(LocalDateTime.of(date, LocalTime.of(10, 0)), "ABC", 10.0d, 1));
		trades.add(new Trade(LocalDateTime.of(date, LocalTime.of(9, 0)),  "ABC", 9.0d, 1));
		trades.add(new Trade(LocalDateTime.of(date, LocalTime.of(11, 0)), "ABC", 11.0d, 2));
		trades.add(new Trade(LocalDateTime.of(date, LocalTime.of(10, 0)), "ABC", 20.0d, 1));
		trades.add(new Trade(LocalDateTime.of(date, LocalTime.of(10, 0)), "ABC", 1.0d, 7));
		
		Map<LocalDate, HashMap<String, DailyCompanyTradeResult>> result = dailyCompanyResultCalculator
				.calculateAllDailyCompanyResults(trades);

		assertEquals(1, result.size());

		assertEquals(1, result.get(date).size());

		DailyCompanyTradeResult abcCompanyResult = result.get(date).get("ABC");

		assertEquals(9.0d, abcCompanyResult.getFirstTrade().getPrice());
		assertEquals(11.0d, abcCompanyResult.getLastTrade().getPrice());
		assertEquals(20.0d, abcCompanyResult.getHeighestTrade().getPrice());
		assertEquals(1.0d, abcCompanyResult.getLowestTrade().getPrice());
		assertEquals(68.0d, abcCompanyResult.getTradeVolume());

	}
	


}
