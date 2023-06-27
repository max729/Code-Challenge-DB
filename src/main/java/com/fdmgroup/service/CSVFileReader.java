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
 * Service class for reading a csv file of trades using opencsv module. Every
 * line get map to java objects {@link com.fdmgroup.model.Trade Trade}
 * 
 * @author Max Schoppe
 *
 */
public class CSVFileReader {

	public List<Trade> readFromFile(String filePath) throws FileNotFoundException {

	
		Reader reader = new BufferedReader(new FileReader(filePath));

		CsvToBean<Trade> csvReader = new CsvToBeanBuilder<Trade>(reader).withType(Trade.class).withSeparator(';')
				.withIgnoreLeadingWhiteSpace(true).build();
		
		List<Trade> readingResults = csvReader.parse();

		return readingResults;

	}

}
