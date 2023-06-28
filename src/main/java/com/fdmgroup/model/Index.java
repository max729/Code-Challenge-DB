package com.fdmgroup.model;

import java.util.Set;

public interface Index {

	public abstract double getIndexWeightOfCompany(String companyName);
	
	public abstract String getName();
	
	public abstract Set<String> getIndexCompanies();
	
	
	
}
