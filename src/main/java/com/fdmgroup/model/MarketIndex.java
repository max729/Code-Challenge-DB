package com.fdmgroup.model;

import java.util.HashMap;
import java.util.Set;

/**
 * 
 * Marked Index class hold all values to calculate the market index
 * 
 * @author xam
 *
 */
public class MarketIndex implements Index {

	private final HashMap<String, Double> companyWeightMap;

	private final String name;

	public MarketIndex(String name) {

		this.name = name;

		companyWeightMap = new HashMap<>();

		companyWeightMap.put("ABC" , 0.1d);
		companyWeightMap.put("MEGA", 0.3d);
		companyWeightMap.put("NGL" , 0.4d);
		companyWeightMap.put("TRX" , 0.2d);

	}

	@Override
	public double getIndexWeightOfCompany(String companyName) {

		return companyWeightMap.getOrDefault(companyName, 0d);
	}

	@Override
	public Set<String> getIndexCompanies() {

		return companyWeightMap.keySet();
	}

	public String getName() {
		return name;
	}

}
