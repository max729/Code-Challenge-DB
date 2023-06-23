package com.fdmgroup.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import com.fdmgroup.model.Trade;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;



/**
 * Service class for reading a csv file of trades using opencsv module.
 * Every line get map to java objects {@link com.fdmgroup.model.Trade Trade}
 * 
 * @author Max Schoppe
 *
 */
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
