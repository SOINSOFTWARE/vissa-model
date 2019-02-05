package com.soinsoftware.vissa.model;

public enum EPaymemtType {

	PREPAY("PREPAY"), PAID("PAID"), CREDIT("CREDIT");
	private String name;

	private EPaymemtType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
