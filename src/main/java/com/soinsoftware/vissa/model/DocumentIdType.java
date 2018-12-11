// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public enum DocumentIdType {

	CC("Cédula de ciudadanía"), CE("Cédula de extranjería"), NIT("NIT"), PASSPORT("Pasaporte");

	private String display;

	private DocumentIdType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public static DocumentIdType fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}