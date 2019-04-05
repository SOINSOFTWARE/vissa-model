package com.soinsoftware.vissa.model;

public enum EPaymentStatus {

	PAYED("PAGADA"),EXPIRED("VENCIDA"),PENDING("PENDIENTE");
	private String name;
	
	private EPaymentStatus(String  name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
