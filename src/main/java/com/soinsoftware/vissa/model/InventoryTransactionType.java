package com.soinsoftware.vissa.model;

public enum InventoryTransactionType {

	ENTRADA("ENTRADA"),SALIDA("SALIDA");
	private String name;
	
	private InventoryTransactionType(String  name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
