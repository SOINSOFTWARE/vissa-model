package com.soinsoftware.vissa.model;

public enum EEgressType {

	LOAN("PRESTAMO"), EXPENSE("GASTO"), OTHER("OTRO");
	private String name;

	private EEgressType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
