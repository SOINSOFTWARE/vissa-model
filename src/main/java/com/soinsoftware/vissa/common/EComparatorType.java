package com.soinsoftware.vissa.common;

public enum EComparatorType {

	EQ("EQ"), LE("LE"), GE("GE");
	private String name;

	private EComparatorType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
