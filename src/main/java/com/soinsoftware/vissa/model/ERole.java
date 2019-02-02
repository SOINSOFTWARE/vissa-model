package com.soinsoftware.vissa.model;

public enum ERole {

	SUDO("SUDO"), MANAGER("Gerente"), ADMINISTRATOR("Administrador"), SALESMAN("Vendedor");
	private String name;

	private ERole(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
