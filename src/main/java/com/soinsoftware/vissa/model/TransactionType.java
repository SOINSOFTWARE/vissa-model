package com.soinsoftware.vissa.model;

public enum TransactionType {

	ENTRADA("ENTRADA"),SALIDA("SALIDA");
	private String name;
	
	private TransactionType(String  name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
