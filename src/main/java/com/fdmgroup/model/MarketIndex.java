package com.fdmgroup.model;

import java.util.EnumMap;
import java.util.Set;

public class MarketIndex implements Index {
	
	private final EnumMap<Company, Double> companyWeightMap;
	
	private final String name;
	
	public MarketIndex(String name) {
		
		this.name = name;
		
		companyWeightMap = new EnumMap<>(Company.class);
		
		companyWeightMap.put( Company.ABC , 0.1d);
		companyWeightMap.put( Company.MEGA, 0.3d);
		companyWeightMap.put( Company.NGL, 0.4d);
		companyWeightMap.put( Company.TRX, 0.2d);
		
		
	}
	
	
	
	@Override
	public double getIndexWeightOfCompany(Company company) {
				
		return companyWeightMap.getOrDefault(company, 0d);
	}

	
	@Override
	public Set<Company> getIndexCompanies() {

		return companyWeightMap.keySet();
	}


	public String getName() {
		return name;
	}
	
	
	
}
