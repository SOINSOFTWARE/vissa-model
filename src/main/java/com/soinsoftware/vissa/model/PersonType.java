// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public enum PersonType {

	SUPPLIER("Proveedor"), CUSTOMER("Cliente");

	private String display;

	private PersonType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public static PersonType fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}