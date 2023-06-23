package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;
import com.fdmgroup.model.Trade;

public class DailyIndexResultCalculatorTest {
	
	DailyIndexResultCalculator dailyIndexResultCalculator; 
	
	TreeMap< LocalDate , EnumMap< Company, DailyCompanyTradeResult >> dailyTradeResultsOfCompanies;
	LocalDate date;
	
	@BeforeEach
	void setUp() {
		
		dailyIndexResultCalculator = new DailyIndexResultCalculator();
		
		date = LocalDate.of(2023, 6, 23);
		
		dailyTradeResultsOfCompanies = new TreeMap<>();
		
		
		
		
		
		
		
	}
	
	@Test
	void test_MethodeCalculateAllDailyIndexResults_withOneValideDate () {
		
		dailyTradeResultsOfCompanies.put(date,  new EnumMap<>(Company.class) );
		
		Trade trade = new Trade(LocalDateTime.of(date , LocalTime.of( 10, 0) ), Company.ABC, 1.0d, 1);
		
		for ( Company company : Company.values()) {
			dailyTradeResultsOfCompanies.get(date).put(company, new DailyCompanyTradeResult(trade, trade, trade, trade, 1.0d));			
		}
		
		HashMap<LocalDate, DailyIndexTradeResult> result = dailyIndexResultCalculator.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies)	;
		
		assertEquals(1, result.size());
		
		DailyIndexTradeResult IndexResult = result.get(date);
		
		assertEquals( 1.0d,  IndexResult.getPriceOfFirstTrade()   );
		assertEquals( 1.0d,  IndexResult.getPriceOfLastTrade()  );
		assertEquals( 1.0d,  IndexResult.getPriceOfHeihestTrade()   );
		assertEquals( 1.0d,  IndexResult.getPriceOfLowestTrade()   );
		assertEquals( 1.0d,  IndexResult.getTradeVolume()  );
		
		
	}
	
	@Test
	void test_MethodeCalculateAllDailyIndexResults_withOneInvalideDate () {
		
		
		dailyTradeResultsOfCompanies.put(date,  new EnumMap<>(Company.class) );
		
		Trade trade = new Trade(LocalDateTime.of(date , LocalTime.of( 10, 0) ), Company.ABC, 1.0d, 1);
		

		dailyTradeResultsOfCompanies.get(date).put(Company.ABC, new DailyCompanyTradeResult(trade, trade, trade, trade, 1.0d));			
		
		
		HashMap<LocalDate, DailyIndexTradeResult> result = dailyIndexResultCalculator.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies)	;
		
		assertEquals(0, result.size());
		
	
		
	}
	
	
	@Test
	void test_MethodeCalculateAllDailyIndexResults_withTwoValideDates () {
		
		dailyTradeResultsOfCompanies.put(date,  new EnumMap<>(Company.class) );
		
		Trade trade = new Trade(LocalDateTime.of(date , LocalTime.of( 10, 0) ), Company.ABC, 1.0d, 1);
		
		
		
		for ( Company company : Company.values()) {
			dailyTradeResultsOfCompanies.get(date).put(company, new DailyCompanyTradeResult(trade, trade, trade, trade, 1.0d));			
		}
		
		LocalDate dateAfter= date.plusDays(1);
		
		dailyTradeResultsOfCompanies.put(dateAfter,  new EnumMap<>(Company.class) );
		
		HashMap<LocalDate, DailyIndexTradeResult> result = dailyIndexResultCalculator.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies)	;
		
		assertEquals(2, result.size());
		
		DailyIndexTradeResult IndexResult = result.get(dateAfter);
		
		assertEquals( 1.0d,  IndexResult.getPriceOfFirstTrade()   );
		assertEquals( 1.0d,  IndexResult.getPriceOfLastTrade()  );
		assertEquals( 1.0d,  IndexResult.getPriceOfHeihestTrade()   );
		assertEquals( 1.0d,  IndexResult.getPriceOfLowestTrade()   );
		assertEquals( 1.0d,  IndexResult.getTradeVolume()  );
		
	
		
	}
	

}
