package com.soinsoftware.vissa.model;

public enum EDocumentStatus {

	NEW("NUEVA"), REGISTERED("REGISTRADA"), DECLINED("CANCELADA"), REJECTED("RECHAZADA");
	private String name;

	private EDocumentStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
