package com.hoffnungland.orderEntry.entity;

/**
 * 
 * @author manuel.m.speranza
 * @version 0.1
 */
public enum EuCountry {
	
	Austria("AT"), Belgium("BE"), Bulgaria("BG"), Cyprus("CY"), CzechRepublic("CZ"), Germany("DE"), Denmark("DK"), Estonia("EE"),
	Greece("EL"), Spain("ES"), Finland("FI"), France ("FR"), UnitedKingdom("GB"), Croatia("HR"), Hungary("HU"), Ireland("IE"),
	Italy("IT"), Lithuania("LT"), Luxembourg("LU"), Latvia("LV"), Malta("MT"), Netherlands("NL"), Poland("PL"), Portugal("PT"),
	Romania("RO"), Sweden("SE"), Slovenia("SI"), Slovakia("SK");
	
	private final String code;
	
	EuCountry( String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
