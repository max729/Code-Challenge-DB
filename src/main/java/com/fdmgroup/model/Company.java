package com.fdmgroup.model;

public enum Company {
	ABC,
	MEGA,
	NGL,
	TRX;
	
	public static double getIndexWeight(Company company) {
		
		switch (company) {
		case ABC: {
			return 0.1d;
		}
		case MEGA: {
			return 0.3d;
		}
		case NGL: {
			return 0.4d;
		}
		case TRX: {
			return 0.2d;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + company);
		}
		
		
	}
	
}
