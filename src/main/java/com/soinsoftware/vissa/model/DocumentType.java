// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public enum DocumentType {

	CC("Cédula de ciudadanía"), CE("Cédula de extranjería"), NIT("NIT"), PASSPORT("Pasaporte");

	private String display;

	private DocumentType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public static DocumentType fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}