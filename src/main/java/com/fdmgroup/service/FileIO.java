package com.fdmgroup.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import com.fdmgroup.model.Trade;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class FileIO {
	
	public static List<Trade> readFromFile(String filePath) {
		
		List<Trade> readingResults = null;
		
		try {
			Reader reader = new BufferedReader(new FileReader(filePath));
			
			CsvToBean<Trade> csvReader = new CsvToBeanBuilder<Trade>(reader)
												.withType(Trade.class)
												.withSeparator(';')
												.withIgnoreLeadingWhiteSpace(true)
												.build();
			readingResults = csvReader.parse();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		
		return readingResults;
		
	}

}
