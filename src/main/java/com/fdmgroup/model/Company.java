package com.fdmgroup.model;

public enum Company {
	ABC,
	MEGA,
	NGL,
	TRX;
	
	public static double getWeight(Company company) {
		
		switch (company) {
		case ABC: {
			return 0.1;
		}
		case MEGA: {
			return 0.3;
		}
		case NGL: {
			return 0.4;
		}
		case TRX: {
			return 0.2;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + company);
		}
		
		
	}
	
}
