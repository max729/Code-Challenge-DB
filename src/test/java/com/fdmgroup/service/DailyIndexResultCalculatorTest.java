package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.model.DailyCompanyTradeResult;
import com.fdmgroup.model.DailyIndexTradeResult;
import com.fdmgroup.model.Index;
import com.fdmgroup.model.MarketIndex;
import com.fdmgroup.model.Trade;

public class DailyIndexResultCalculatorTest {
	
	DailyIndexResultCalculator dailyIndexResultCalculator; 
	
	Map< LocalDate , HashMap< String, DailyCompanyTradeResult >> dailyTradeResultsOfCompanies;
	LocalDate date;
	
	Set<String> companies;
	
	List<Index> indices;
	
	String indexName;
	
	@BeforeEach
	void setUp() {
		
		dailyIndexResultCalculator = new DailyIndexResultCalculator();
		
		date = LocalDate.of(2023, 6, 23);
		
		dailyTradeResultsOfCompanies = new TreeMap<>();	
		
		indexName = "Market Index";
		
		indices = new ArrayList<>();
		
		indices.add(new MarketIndex(indexName));
		
		companies = new TreeSet<>();
		companies.add("ABC");
		companies.add("MEGA");
		companies.add("NGL");
		companies.add("TRX");
		
		
		
	}
	
	@Test
	void test_MethodeCalculateAllDailyIndexResults_withOneValideDate () {
		
		dailyTradeResultsOfCompanies.put(date,  new HashMap<>() );
		
		Trade trade = new Trade(LocalDateTime.of(date , LocalTime.of( 10, 0) ), "ABC", 1.0d, 1);
		
		
		for ( String company : companies ) {
			dailyTradeResultsOfCompanies.get(date).put(company, new DailyCompanyTradeResult(trade, trade, trade, trade, 1.0d));			
		}
		
		Map<LocalDate, HashMap<String,DailyIndexTradeResult>> result = dailyIndexResultCalculator.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies,indices)	;
		
		assertEquals(1, result.size());
		
		DailyIndexTradeResult IndexResult = result.get(date).get(indexName);
		
		assertEquals(1, result.size());
		
		
		assertEquals( 1.0d,  IndexResult.getPriceOfFirstTrade()   );
		assertEquals( 1.0d,  IndexResult.getPriceOfLastTrade()  );
		assertEquals( 1.0d,  IndexResult.getPriceOfHeihestTrade()   );
		assertEquals( 1.0d,  IndexResult.getPriceOfLowestTrade()   );
		assertEquals( 1.0d,  IndexResult.getTradeVolume()  );
		
		
	}
	
	
	
	@Test
	void test_MethodeCalculateAllDailyIndexResults_hasCorrectWeights () {
		
		dailyTradeResultsOfCompanies.put(date,  new HashMap<>() );
		
		Trade trade = new Trade(LocalDateTime.of(date , LocalTime.of( 10, 0) ), "ABC", 1.0d, 1);
		
		

		double faktor = 1.0d;
		
		for ( String company : companies ) {
			dailyTradeResultsOfCompanies.get(date).put(company, new DailyCompanyTradeResult(trade, trade, trade, trade, 1.0d*faktor));	
			faktor*=10.0d;
		}
		
		Map<LocalDate, HashMap<String,DailyIndexTradeResult>> result = dailyIndexResultCalculator.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies,indices)	;
		
		assertEquals(1, result.size());
		
		DailyIndexTradeResult IndexResult = result.get(date).get(indexName);
		
		assertEquals(1, result.size());
		

		assertEquals( 243.1d,  IndexResult.getTradeVolume() );
		
		
	}
	
	@Test
	void test_MethodeCalculateAllDailyIndexResults_withOneInvalideDate () {
		
		
		dailyTradeResultsOfCompanies.put(date,  new HashMap<>() );
		
		Trade trade = new Trade(LocalDateTime.of(date , LocalTime.of( 10, 0) ), "ABC", 1.0d, 1);
		

		dailyTradeResultsOfCompanies.get(date).put("ABC", new DailyCompanyTradeResult(trade, trade, trade, trade, 1.0d));			
		
		
		Map<LocalDate, HashMap<String,DailyIndexTradeResult>> result = dailyIndexResultCalculator.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies ,indices)	;
		
		assertEquals(0, result.size());
		
	
		
	}
	
	
	@Test
	void test_MethodeCalculateAllDailyIndexResults_withTwoValideDates () {
		
		dailyTradeResultsOfCompanies.put(date,  new HashMap<>() );
		
		Trade trade = new Trade(LocalDateTime.of(date , LocalTime.of( 10, 0) ), "ABC", 1.0d, 1);
		
		
		
		for ( String company : companies) {
			dailyTradeResultsOfCompanies.get(date).put(company, new DailyCompanyTradeResult(trade, trade, trade, trade, 1.0d));			
		}
		
		LocalDate dateAfter= date.plusDays(1);
		
		dailyTradeResultsOfCompanies.put(dateAfter,  new HashMap<>() );
		
		Map<LocalDate, HashMap<String,DailyIndexTradeResult>> result = dailyIndexResultCalculator.calculateAllDailyIndexResults(dailyTradeResultsOfCompanies,indices)	;
		
		assertEquals(2, result.size());
		
		DailyIndexTradeResult IndexResult = result.get(dateAfter).get(indexName);
		
		assertEquals( 1.0d,  IndexResult.getPriceOfFirstTrade()   );
		assertEquals( 1.0d,  IndexResult.getPriceOfLastTrade()  );
		assertEquals( 1.0d,  IndexResult.getPriceOfHeihestTrade()   );
		assertEquals( 1.0d,  IndexResult.getPriceOfLowestTrade()   );
		assertEquals( 1.0d,  IndexResult.getTradeVolume()  );
		
	
		
	}
	

}
