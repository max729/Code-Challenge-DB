package com.fdmgroup.model;

import java.util.Set;

public interface Index {

	public abstract double getIndexWeightOfCompany(Company company);
	
	public abstract String getName();
	
	public abstract Set<Company> getIndexCompanies();
	
	
	
}
