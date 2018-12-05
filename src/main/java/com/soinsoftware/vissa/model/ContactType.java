// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public enum ContactType {

	A("Tipo A"), B("Tipo B");

	private String display;

	private ContactType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public static ContactType fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}