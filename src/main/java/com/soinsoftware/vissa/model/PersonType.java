// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public enum PersonType {

	SUPPLIER("Proveedor"), CUSTOMER("Cliente"), USER("Usuario");

	private String name;

	private PersonType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static PersonType fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}