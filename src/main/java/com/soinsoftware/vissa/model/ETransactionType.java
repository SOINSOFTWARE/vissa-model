package com.soinsoftware.vissa.model;

public enum ETransactionType {

	ENTRADA("ENTRADA"),SALIDA("SALIDA");
	private String name;
	
	private ETransactionType(String  name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
