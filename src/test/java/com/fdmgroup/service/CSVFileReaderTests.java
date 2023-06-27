package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.Trade;

class CSVFileReaderTests {
	
	private CSVFileReader csvFileReader;
	
	
	@BeforeEach
	public void setUp(){
		csvFileReader = new CSVFileReader();
	}
	
	
	@Test
	void test_readFromFile_withWrongFilePath() {
		
		String filePath = "./src/test/resources/test_is_not_there.csv";
		
		assertThrows( FileNotFoundException.class, ()->	csvFileReader.readFromFile(filePath));
		
		String filePath2 = "./src/test/resources/test_wrong_format.csv";
		
		assertThrows( RuntimeException.class, ()->	csvFileReader.readFromFile(filePath2));
		
	}
	
	@Test
	void test_readFromFile_withCorretFileWithOneLine() {
		
		String filePath = "./src/test/resources/test_correct_first_line.csv";
		
		List<Trade> trades;
		
		try {
			trades = csvFileReader.readFromFile(filePath);
			
			Trade trade = trades.get(0);
		
			assertEquals( trade.getPrice(), 3997.90d  );
			assertEquals( trade.getCompany(), Company.valueOf("TRX") );
			assertEquals( trade.getTrades(), 879  );
			assertEquals( trade.getDate(),  LocalDateTime.of( 2023,06,01, 9,0,1   )  );
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		


		
	}

}
