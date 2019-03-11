package com.soinsoftware.vissa.model;

public enum EDocumentType {

	CO("CO"), VE("VE"), RE("RE"), DV("DV");
	private String name;

	private EDocumentType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
